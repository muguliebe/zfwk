package zany.util;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInfo;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;
import org.jutils.jprocesses.JProcesses;
import org.jutils.jprocesses.model.ProcessInfo;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import zany.model.system.CpuEntity;
import zany.model.system.MemoryEntity;
import zany.model.system.ProcessEntity;
import zany.model.system.System;

public class SystemInfo {

    // ==========================================================================
    // Declaration
    // ==========================================================================
    private static final Logger    log    = (Logger) LoggerFactory.getLogger(SystemInfo.class);

    static System                  entity;
    static Sigar                   sigar;
    transient static DecimalFormat format = new DecimalFormat("##");

    static {
        initialize();
    }

    private SystemInfo() {
        // don't using constructor
    }

    // ==========================================================================
    // Initialize
    // ==========================================================================
    public static void initialize() {

        log.debug("System Info Initialize Start");
        entity = new System();
        sigar = new Sigar();
        refresh();
        log.debug("System Info Initialize End");
    }


    // ==========================================================================
    // this System Entity
    // ==========================================================================
    public static System getEntity() {
        return entity;
    }

    public static void refresh() {
        refreshCpu();
        refreshMemory();
        refreshNetwork();
        // refreshProcess();
        log.debug("refresh updated:{}", getEntity().getLastUpdate());
    }

    public static boolean isReady() {
        if (entity.getProcessList() == null) {
            return false;
        } else {
            return true;
        }
    }

    // ==========================================================================
    // cpu
    // ==========================================================================
    public static CpuEntity getCpu() {
        return entity.getCpu();
    }

    public static void refreshCpu() {
        log.trace("refresh Cpu Start");
        try {
            CpuPerc perc = sigar.getCpuPerc();

            getCpu().setCpuCnt(sigar.getCpuList().length);

            if (Double.isNaN(perc.getSys()) == false) {
                int tmp = Integer.parseInt(format.format(perc.getSys() * 100));
                getCpu().setSystem(tmp);
            }
            if (Double.isNaN(perc.getIdle()) == false) {
                int tmp = Integer.parseInt(format.format(perc.getIdle() * 100));
                getCpu().setIdle(tmp);
            }
            if (Double.isNaN(perc.getWait()) == false) {
                int tmp = Integer.parseInt(format.format(perc.getWait() * 100));
                getCpu().setWait(tmp);
            }


            if (Double.isNaN(perc.getUser()) == false) {
                int tmp = Integer.parseInt(format.format(perc.getUser() * 100));
                getCpu().setUser(tmp);
            }

        } catch (SigarException e) {
            log.error("get cpu perc error:" + e.getMessage(), e);

        }
        log.trace("refresh Cpu End");
    }

    // ==========================================================================
    // Memory
    // ==========================================================================
    public static MemoryEntity getMemory() {
        return entity.getMemory();
    }

    public static void refreshMemory() {
        log.trace("refresh Memory Start");
        try {
            Mem mem = sigar.getMem();
            Swap swap = sigar.getSwap();

            getMemory().setPhysicalTotal(mem.getTotal());
            getMemory().setPhysicalUsed(mem.getUsed());
            getMemory().setPhysicalFree(mem.getFree());
            getMemory().setSwapTotal(swap.getTotal());
            getMemory().setSwapUsed(swap.getUsed());
            getMemory().setSwapFree(swap.getFree());

        } catch (SigarException e) {
            log.error("get memory perc error:" + e.getMessage(), e);

        }
        log.trace("refresh Memory End");
    }

    // ==========================================================================
    // Network
    // ==========================================================================
    public static String getHostName() throws SigarException {

        if (StringUtils.isEmpty(entity.getHostName())) {
            NetInfo netInfo = sigar.getNetInfo();
            entity.setHostName(netInfo.getHostName());
        }

        return entity.getHostName();

    }

    public static void setHostName(String hostName) {
        entity.setHostName(hostName);
    }

    public static String getIp() throws SigarException {

        if (StringUtils.isEmpty(entity.getIp())) {
            NetInterfaceConfig netInterfaceConfig = sigar.getNetInterfaceConfig();
            entity.setIp(netInterfaceConfig.getAddress());
        }

        return entity.getIp();

    }

    public static void refreshNetwork() {
        log.trace("refresh Network Start");
        try {
            NetInfo netInfo = sigar.getNetInfo();
            entity.setHostName(netInfo.getHostName());

            NetInterfaceConfig netInterfaceConfig = sigar.getNetInterfaceConfig();
            entity.setIp(netInterfaceConfig.getAddress());

        } catch (SigarException e) {
            log.error("network inform setting error:" + e.getMessage(), e);
        }
        log.trace("refresh Network End");
    }

    // ==========================================================================
    // Process
    // ==========================================================================
    public static List<ProcessEntity> getProcessList() {
        return entity.getProcessList();
    }

    public static void refreshProcess() {
        log.trace("refresh Process Start");
        try {
            List<ProcessInfo> listProcessInfo = JProcesses.getProcessList();
            List<ProcessEntity> listProcessEntity = new ArrayList<>();

            for (final ProcessInfo processInfo : listProcessInfo) {

                if (processInfo.getName().equals("System Idle Process")) {
                    continue;
                }
                ProcessEntity process = new ProcessEntity();
                process.setPid(processInfo.getPid());
                process.setTime(processInfo.getTime());
                process.setName(processInfo.getName());
                process.setUser(processInfo.getUser());
                process.setVirtualMemory(processInfo.getVirtualMemory());
                process.setPhysicalMemory(processInfo.getPhysicalMemory());
                process.setCpuUsage(processInfo.getCpuUsage());
                process.setStartTime(processInfo.getStartTime());
                process.setPriority(processInfo.getPriority());

                listProcessEntity.add(process);
            }

            entity.setProcessList(listProcessEntity);

        } catch (Exception e) {
            log.error("process inform setting error:" + e.getMessage(), e);
        }
        log.trace("refresh Process End");
    }

}
