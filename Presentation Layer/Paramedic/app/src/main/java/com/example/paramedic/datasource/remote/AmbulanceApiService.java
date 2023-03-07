package com.example.paramedic.datasource.remote;

import static com.example.paramedic.resourses.Constant.AMBULANCE;
import static com.example.paramedic.resourses.Constant.DISEASE_STATES;
import static com.example.paramedic.resourses.Constant.PATIENT;
import static com.example.paramedic.resourses.Constant.USER_LOGIN;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.ambulance.Ambulance;
import com.example.paramedic.model.ambulance.ResponseAmbulance;
import com.example.paramedic.model.patient.ResponsePatient;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface AmbulanceApiService {

    @GET(AMBULANCE+"/read.php")
    Single<ResponseAmbulance> getAmbulance();
   /* @GET(AMBULANCE+"/read_by_disease_states_id.php")
    Single<ResponseAmbulance> getAmbulanceByAmbulanceId(@Query("ambulanceId")int ambulanceId);
    */
    @GET(AMBULANCE+"/read_by_userlogin_id.php")
    Single<ResponseAmbulance> getAmbulanceByUserloginId(@Query("userloginId")int userloginId);
    @POST(AMBULANCE+"/create.php")
    Single<Status> insertAmbulance(@Body Ambulance ambulance);
    @PUT(AMBULANCE+"/update.php")
    Single<Status> updateAmbulance(@Body Ambulance ambulance);
    @DELETE(AMBULANCE+"/delete.php")
    Single<Status> deleteAmbulance(@Query("ambulanceId") int ambulanceId);

}
