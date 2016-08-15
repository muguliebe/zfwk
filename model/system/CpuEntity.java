package zany.model.system;

public class CpuEntity {
    
    int system;
    int user;
    int idle;
    int wait;
    int cpuCnt;
    
    //==========================================================================
    // Cpu Count
    //==========================================================================
    public int getCpuCnt(){
        return cpuCnt;
    }
    public void setCpuCnt(int cpuCnt){
        this.cpuCnt = cpuCnt;
    }

    //==========================================================================
    // User
    //==========================================================================
    public int getUser() {
        return user;
    }
    public void setUser(int user) {
        this.user = user;
    }
    
    //==========================================================================
    // Idle
    //==========================================================================
    public int getIdle() {
        return idle;
    }
    public void setIdle(int idle) {
        this.idle = idle;
    }
    
    //==========================================================================
    // System
    //==========================================================================
    public int getSystem(){
        return system;
    }
    public void setSystem(int system) {
        this.system = system;
    }

    //==========================================================================
    // Wait
    //==========================================================================
    public int getWait() {
        return wait;
    }
    public void setWait(int wait) {
        this.wait = wait;
    }
    
    //==========================================================================
    // Etc
    //==========================================================================
    @Override
    public String toString() {
        return "CpuEntity [system=" + system + ", user=" + user + ", idle=" + idle + ", wait=" + wait + ", cpuCnt="
        + cpuCnt + "]";
    }
    

}
