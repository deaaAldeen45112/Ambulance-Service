package com.example.paramedic.model.request;

import java.util.List;

public class ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient {

    List<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient> data;
    String status;

    public List<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient> getData() {
        return data;
    }

    public void setData(List<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
