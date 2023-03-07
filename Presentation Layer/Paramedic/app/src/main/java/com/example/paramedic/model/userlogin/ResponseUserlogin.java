package com.example.paramedic.model.userlogin;

import java.util.List;

public class ResponseUserlogin {
    List<UserLogin> data;
    String status;

    public List<UserLogin> getData() {
        return data;
    }

    public void setData(List<UserLogin> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
