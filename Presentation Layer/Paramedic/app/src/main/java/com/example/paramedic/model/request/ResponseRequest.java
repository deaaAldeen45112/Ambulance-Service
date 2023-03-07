package com.example.paramedic.model.request;

import java.util.List;

public class ResponseRequest {
    List<Request> data;
    String status;

    public List<Request> getData() {
        return data;
    }

    public void setData(List<Request> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
