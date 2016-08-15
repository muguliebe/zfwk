package zany.model.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class System {

    // ==========================================================================
    // Basic
    // ==========================================================================
    Date   lastUpdate; // yyyy.MM.dd.HH:mm:ss
    String day;        // yyyymmdd
    String time;       // HHmmss


    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDay() {
        return day;
    }


    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void refreshUpdate() {
        lastUpdate = new Date();
        this.day = new SimpleDateFormat("yyyyMMdd").format(lastUpdate);
        time = new SimpleDateFormat("HHmmss").format(lastUpdate);
    }

    // ==========================================================================
    // Cpu
    // ==========================================================================
    CpuEntity cpu = new CpuEntity();

    public CpuEntity getCpu() {
        return cpu;
    }

    public void setCpu(CpuEntity cpuEntity) {
        this.cpu = cpuEntity;
        refreshUpdate();
    }


    // ==========================================================================
    // Memory
    // ==========================================================================
    MemoryEntity memory = new MemoryEntity();

    public MemoryEntity getMemory() {
        return memory;
    }

    public void setMemory(MemoryEntity memoryEntity) {
        this.memory = memoryEntity;
        refreshUpdate();
    }


    // ==========================================================================
    // Network
    // ==========================================================================
    String hostName = "";
    String ip       = "";

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
        refreshUpdate();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
        refreshUpdate();
    }


    // ==========================================================================
    // Process
    // ==========================================================================
    List<ProcessEntity> listProcess;

    public List<ProcessEntity> getProcessList() {
        return listProcess;
    }

    public void setProcessList(List<ProcessEntity> listProcessEntity) {
        this.listProcess = listProcessEntity;
        refreshUpdate();
    }


    @Override
    public String toString() {
        return "{lastUpdate=" + lastUpdate + ", cpuEntity=" + cpu + ", memoryEntity=" + memory + ", hostName="
            + hostName + ", ip=" + ip + ", listProcessEntity=" + listProcess + "}";
    }

}
