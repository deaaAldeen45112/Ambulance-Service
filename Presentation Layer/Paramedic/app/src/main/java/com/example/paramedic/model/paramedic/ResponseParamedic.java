package com.example.paramedic.model.paramedic;

import java.util.List;

public class ResponseParamedic {
    List<Paramedic> data;
    String status;

    public List<Paramedic> getData() {
        return data;
    }

    public void setData(List<Paramedic> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
