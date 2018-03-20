package dk.kinoxp.web.model.services.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DateTimeDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public DateTimeDto(Date date){
        this.date = date;
    }

    public DateTimeDto(){
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
