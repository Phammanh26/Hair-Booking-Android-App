package com.example.spabookingapp.Entities;

import java.util.Date;

public class HistoryEntity {
    Date slot_date;
    String slot_time;
    String status;

    public HistoryEntity(Date slot_date, String slot_time, String status) {
        this.slot_date = slot_date;
        this.slot_time = slot_time;
        this.status = status;
    }

    public Date getSlot_date() {
        return slot_date;
    }

    public String getSlot_time() {
        return slot_time;
    }

    public String getStatus() {
        return status;
    }

    public void setSlot_date(Date slot_date) {
        this.slot_date = slot_date;
    }

    public void setSlot_time(String slot_time) {
        this.slot_time = slot_time;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
