package com.example.paramedic.model.paramedic;

public class Paramedic {

    private int paramedicId;
    private int userloginId;
    private String paramedicStatus;
    private String paramedicLongitude;
    private String paramedicLatitude;

    public int getParamedicId() {
        return paramedicId;
    }

    public void setParamedicId(int paramedicId) {
        this.paramedicId = paramedicId;
    }

    public int getUserloginId() {
        return userloginId;
    }

    public void setUserloginId(int userloginId) {
        this.userloginId = userloginId;
    }

    public String getParamedicStatus() {
        return paramedicStatus;
    }

    public void setParamedicStatus(String paramedicStatus) {
        this.paramedicStatus = paramedicStatus;
    }

    public String getParamedicLongitude() {
        return paramedicLongitude;
    }

    public void setParamedicLongitude(String paramedicLongitude) {
        this.paramedicLongitude = paramedicLongitude;
    }

    public String getParamedicLatitude() {
        return paramedicLatitude;
    }

    public void setParamedicLatitude(String paramedicLatitude) {
        this.paramedicLatitude = paramedicLatitude;
    }
}
