package com.example.paramedic.model.patient;

import java.util.List;

public class ResponsePatient {
    private List<Patient> data;
    private String status;

    public List<Patient> getData() {
        return data;
    }

    public void setData(List<Patient> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
