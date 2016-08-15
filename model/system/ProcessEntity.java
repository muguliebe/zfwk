package zany.model.system;

public class ProcessEntity implements Comparable<ProcessEntity>{

    String pid;
    String cpuUsage;
    String time;
    String name;
    String user;
    String virtualMemory;
    String physicalMemory;
    String startTime;
    String priority;
    
    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getVirtualMemory() {
        return virtualMemory;
    }
    public void setVirtualMemory(String virtualMemory) {
        this.virtualMemory = virtualMemory;
    }
    public String getPhysicalMemory() {
        return physicalMemory;
    }
    public void setPhysicalMemory(String physicalMemory) {
        this.physicalMemory = physicalMemory;
    }
    public String getCpuUsage() {
        return cpuUsage;
    }
    public void setCpuUsage(String cpuUsage) {
        this.cpuUsage = cpuUsage;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    @Override
    public int compareTo(ProcessEntity o) {
        try{
            int orgCpuUsage = Integer.parseInt(this.cpuUsage  );
            int tarCpuUsage = Integer.parseInt(o.getCpuUsage());
            
            if( tarCpuUsage > orgCpuUsage ){
                return 1;
            }else if( tarCpuUsage < orgCpuUsage ){
                return -1;
            }else{
                return 0;
            }
            
        }catch(Exception e){
            return 0;
        }
    }
    
    @Override
    public String toString() {
        return "ProcessEntity [pid=" + pid + ", cpuUsage=" + cpuUsage + ", time=" + time + ", name=" + name + ", user="
            + user + ", virtualMemory=" + virtualMemory + ", physicalMemory=" + physicalMemory + ", startTime="
            + startTime + ", priority=" + priority + "]";
    }
    
}
