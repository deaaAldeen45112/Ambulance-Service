package com.example.paramedic.model.paramedic;

public class ParamedicJoinUserlogin {

    private int paramedicId;
    private int userloginId;
    private String paramedicStatus;
    private String paramedicLongitude;
    private String paramedicLatitude;

    private String fullName;
    private String email;
    private String phone;
    private int age;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
