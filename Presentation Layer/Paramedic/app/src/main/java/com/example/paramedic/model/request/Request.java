package com.example.paramedic.model.request;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Request {
    private int  requestId;
    private String requestStatus;
    private String patientLongitude;
    private String patientLatitude;
    private int patientId;
    private int paramedicId;
    private int diseaseStateId;
    private String requestDescribed;

    public int getRequestId() {
        return requestId;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getPatientLongitude() {
        return patientLongitude;
    }

    public void setPatientLongitude(String patientLongitude) {
        this.patientLongitude = patientLongitude;
    }

    public String getPatientLatitude() {
        return patientLatitude;
    }

    public void setPatientLatitude(String patientLatitude) {
        this.patientLatitude = patientLatitude;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getParamedicId() {
        return paramedicId;
    }

    public void setParamedicId(int paramedicId) {
        this.paramedicId = paramedicId;
    }

    public int getDiseaseStateId() {
        return diseaseStateId;
    }

    public void setDiseaseStateId(int diseaseStateId) {
        this.diseaseStateId = diseaseStateId;
    }

    public String getRequestDescribed() {
        return requestDescribed;
    }

    public void setRequestDescribed(String requestDescribed) {
        this.requestDescribed = requestDescribed;
    }
}
