package com.example.paramedic.model.request;

import java.util.List;

public class ResponseRequestStatus {

    List<RequestStatus> data;
    String status;

    public List<RequestStatus> getData() {
        return data;
    }

    public void setData(List<RequestStatus> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


