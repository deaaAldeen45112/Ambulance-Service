package com.example.paramedic.datasource.remote;

import static com.example.paramedic.resourses.Constant.REQUEST;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.ResponseRequest;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient;
import com.example.paramedic.model.request.ResponseRequestStatus;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RequestApiService {


    @GET(REQUEST + "/read.php")
    Single<ResponseRequest> getRequests();



    //ambulance one filter
    @GET(REQUEST + "/read_request_join_diseaseStates_join_patient_join_userlogin_by_request_status.php")
    Single<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance>
    getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatus(@Query("requestStatus") String requestStatus);

    //ambulance two filter
    @GET(REQUEST + "/read_request_join_diseaseStates_join_patient_join_userlogin_by_request_status_and_paramedic_id.php")
    Single<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance>
    getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId(@Query("requestStatus") String requestStatus,@Query("paramedicId") int paramedicId);

    // paramedic
    @GET(REQUEST + "/read_request_join_diseaseStates_join_patient_join_userlogin_by_request_status_and_paramedic_id_for_paramedic.php")
    Single<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin>
    getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicIdForParamedic(@Query("requestStatus") String requestStatus,@Query("paramedicId") int paramedicId);

    //patient
    @GET(REQUEST + "/read_request_join_diseaseStates_join_patient_join_userlogin_by_request_status_and_patient_id.php")
    Single<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient>
    getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndPatientId(@Query("requestStatus") String requestStatus,@Query("patientId") int patientId);

    //paramedic
    @GET(REQUEST + "/read_distinct_request_status_for_paramedic.php")
    Single<ResponseRequestStatus>
    getDistinctRequestStatusForParamedic();

    //ambulance and patient
    @GET(REQUEST + "/read_distinct_request_status.php")
    Single<ResponseRequestStatus>
    getDistinctRequestStatus();


    @PUT(REQUEST + "/update_request_by_paramedic_id_request_status.php")
    Single<Status> updateParamedicIdAndRequestStatusByRequestId(@Body Request request);


    @POST(REQUEST + "/create.php")
    Single<Status> insertRequest(@Body Request request);
    @PUT(REQUEST + "/update.php")
    Single<Status> updateRequest(@Body Request request);

    @PUT(REQUEST + "/update_request_status_by_request_id.php")
    Single<Status> updateRequestStatusByRequestId(@Body Request request);

    @DELETE(REQUEST + "/delete.php")
    Single<Status> deleteRequest(@Query("requestId") int requestId);


}
