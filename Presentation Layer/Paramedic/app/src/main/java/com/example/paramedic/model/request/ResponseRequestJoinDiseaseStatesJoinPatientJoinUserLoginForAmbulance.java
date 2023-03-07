package com.example.paramedic.model.request;

import java.util.List;

public class ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance {
    List<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance> data;
    String status;

    public List<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance> getData() {
        return data;
    }

    public void setData(List<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
