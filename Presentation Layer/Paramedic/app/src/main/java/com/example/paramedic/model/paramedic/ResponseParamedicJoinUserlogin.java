package com.example.paramedic.model.paramedic;

import java.util.List;

public class ResponseParamedicJoinUserlogin {

    List<ParamedicJoinUserlogin> data;
    String status;

    public List<ParamedicJoinUserlogin> getData() {
        return data;
    }

    public void setData(List<ParamedicJoinUserlogin> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
