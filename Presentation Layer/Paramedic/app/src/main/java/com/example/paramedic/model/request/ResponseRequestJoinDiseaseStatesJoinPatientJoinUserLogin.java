package com.example.paramedic.model.request;

import java.util.List;

public class ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin {


        List<RequestJoinDiseaseStatesJoinPatientJoinUserLogin> data;
        String status;

        public List<RequestJoinDiseaseStatesJoinPatientJoinUserLogin> getData() {
                return data;
        }

        public void setData(List<RequestJoinDiseaseStatesJoinPatientJoinUserLogin> data) {
                this.data = data;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }
}
