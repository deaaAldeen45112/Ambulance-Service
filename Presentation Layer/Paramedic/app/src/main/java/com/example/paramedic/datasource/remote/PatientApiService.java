package com.example.paramedic.datasource.remote;

import static com.example.paramedic.resourses.Constant.PATIENT;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.patient.Patient;
import com.example.paramedic.model.patient.ResponsePatient;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface PatientApiService {


    @GET(PATIENT+"/read.php")
    Single<ResponsePatient> getPatients();
    @GET(PATIENT+"/read_by_userlogin_id.php")
    Single<ResponsePatient> getPatientsByUserloginId(@Query("userloginId")int userloginId);
    @POST(PATIENT+"/create.php")
    Single<Status> insertPatient(@Body Patient patient);
    @PUT(PATIENT+"/update.php")
    Single<Status> updatePatient(@Body Patient patient);
    @DELETE(PATIENT+"/delete.php")
    Single<Status> deletePatient(@Query("patientId") int patientId);


}
