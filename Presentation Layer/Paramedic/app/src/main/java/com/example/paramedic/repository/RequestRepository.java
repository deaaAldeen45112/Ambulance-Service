package com.example.paramedic.repository;

import static com.example.paramedic.resourses.Constant.REQUEST;

import com.example.paramedic.datasource.remote.DiseaseStatesApiService;
import com.example.paramedic.datasource.remote.RequestApiService;
import com.example.paramedic.model.Status;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.ResponseRequest;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient;
import com.example.paramedic.model.request.ResponseRequestStatus;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public class RequestRepository {

    private RequestApiService requestApiService;

    @Inject
    public RequestRepository(RequestApiService requestApiService) {
        this.requestApiService = requestApiService;
    }


    public Single<ResponseRequest> getRequests() {
        return requestApiService.getRequests();
    }

    // paramedic
    public Single<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin>
    getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicIdForParamedic(String requestStatus, int paramedicId) {
        return requestApiService.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicIdForParamedic(requestStatus, paramedicId);

    }

    // ambulance the Request Status And ParamedicId filter

    public Single<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance>
    getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId(String requestStatus, int paramedicId) {
        return requestApiService.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId(requestStatus, paramedicId);

    }

    // ambulance the Request Status filter

    public Single<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance>
    getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatus(String requestStatus) {
        return requestApiService.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatus(requestStatus);

    }

    // patient the Request Status and patient id  filter

    public Single<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient>
    getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndPatientId(String requestStatus,int patientId) {
        return requestApiService.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndPatientId(requestStatus,patientId);

    }

    // paramedic

    public Single<ResponseRequestStatus>
    getDistinctRequestStatusForParamedic() {
        return requestApiService.getDistinctRequestStatusForParamedic();

    }

   // ambulance and patient
    public Single<ResponseRequestStatus>
    getDistinctRequestStatus() {
        return requestApiService.getDistinctRequestStatus();

    }


    public Single<Status> insertRequest(Request request) {
        return requestApiService.insertRequest(request);
    }

    public Single<Status> updateRequest(Request request) {
        return requestApiService.updateRequest(request);
    }

    public Single<Status> updateRequestStatusByRequestId (Request request) {
        return requestApiService.updateRequestStatusByRequestId(request);
    }
    public Single<Status> deleteRequest(int requestId) {
        return requestApiService.deleteRequest(requestId);

    }


    public Single<Status> updateParamedicIdAndRequestStatusByRequestId(Request request) {
        return requestApiService.updateParamedicIdAndRequestStatusByRequestId(request);

    }

}
