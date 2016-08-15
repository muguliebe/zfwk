package zany.model.framework;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

    // ==========================================================================
    // index
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
    // Basic
    // ==========================================================================
    String type;


}
