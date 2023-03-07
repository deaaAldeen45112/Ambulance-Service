package com.example.paramedic.model.dieseaseStates;

import java.util.List;

public class ResponseDiseaseStates {

    private List<DiseaseStates> data;
    private String status;

    public List<DiseaseStates> getData() {
        return data;
    }

    public void setData(List<DiseaseStates> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
