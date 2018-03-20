package dk.kinoxp.web.config;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DateTimeHolder {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public DateTimeHolder(Date date){
        this.date = date;
    }

    public DateTimeHolder(){
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
