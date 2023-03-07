package com.example.paramedic.model.request;

public class RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient {

    private int  requestId;
    private String requestStatus;
    private String diseaseStatesName;
    private int diseaseStateId;
    private String requestDescribed;
    private String patientLongitude;
    private String patientLatitude;
    private String paramedicName;
    private String paramedicPhone;
    private int paramedicAge ;
    private String paramedicEmail;
    private int patientId;
    private int paramedicId;


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

    public String getDiseaseStatesName() {
        return diseaseStatesName;
    }

    public void setDiseaseStatesName(String diseaseStatesName) {
        this.diseaseStatesName = diseaseStatesName;
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

    public String getParamedicName() {
        return paramedicName;
    }

    public void setParamedicName(String paramedicName) {
        this.paramedicName = paramedicName;
    }

    public String getParamedicPhone() {
        return paramedicPhone;
    }

    public void setParamedicPhone(String paramedicPhone) {
        this.paramedicPhone = paramedicPhone;
    }

    public int getParamedicAge() {
        return paramedicAge;
    }

    public void setParamedicAge(int paramedicAge) {
        this.paramedicAge = paramedicAge;
    }

    public String getParamedicEmail() {
        return paramedicEmail;
    }

    public void setParamedicEmail(String paramedicEmail) {
        this.paramedicEmail = paramedicEmail;
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
}



