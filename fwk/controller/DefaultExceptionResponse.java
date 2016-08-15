package zany.fwk.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

public class DefaultExceptionResponse extends DefaultResponse{

    Date timeStamp;
    String status;
    String error;
    String exception;
    String path;
    
    public DefaultExceptionResponse(HttpServletRequest request, Exception exception) {
        super(false, exception.getMessage());
        this.exception = exception.getClass().getName();
        this.path      = request.getServletPath();
        this.error     = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        this.timeStamp = new Date();
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    @Override
    public String toString() {
        return "DefaultExceptionResponse [timeStamp=" + timeStamp + ", status=" + status + ", error=" + error
            + ", exception=" + exception + ", path=" + path + ", bSuccess=" + bSuccess + ", message=" + message + "]";
    }
    
}
