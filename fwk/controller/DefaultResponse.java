package zany.fwk.controller;

import com.google.gson.GsonBuilder;

public class DefaultResponse {

    boolean bSuccess;
    String message;
    
    public DefaultResponse(){
        bSuccess = true;
        message  = "OK";
    }
    
    public DefaultResponse(boolean bSuccess, String message){
        this.bSuccess = bSuccess;
        this.message  = message;
    }
    
    public String getMsg() {
        return message;
    }
    public void setMsg(String message) {
        this.message = message;
    }
    public boolean isbSuccess() {
        return bSuccess;
    }
    public void setbSuccess(boolean bSuccess) {
        this.bSuccess = bSuccess;
    }

    public Object getResponse(){
        return new GsonBuilder().create().toJson(this);
    }
    
    @Override
    public String toString() {
        return "DefaultResponse [bSuccess=" + bSuccess + ", message=" + message + "]";
    }
    
}
