package com.example.paramedic.model.ambulance;

import java.util.List;

public class ResponseAmbulance {
    List<Ambulance> data;
    String status;

    public List<Ambulance> getData() {
        return data;
    }

    public void setData(List<Ambulance> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
