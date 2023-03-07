package com.example.paramedic.datasource.remote;

import static com.example.paramedic.resourses.Constant.DISEASE_STATES;
import static com.example.paramedic.resourses.Constant.PARAMEDIC;
import static com.example.paramedic.resourses.Constant.PATIENT;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.paramedic.Paramedic;
import com.example.paramedic.model.paramedic.ResponseParamedic;
import com.example.paramedic.model.paramedic.ResponseParamedicJoinUserlogin;
import com.example.paramedic.model.patient.ResponsePatient;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ParamedicApiService {

    @GET(PARAMEDIC+"/read.php")
    Single<ResponseParamedic> getParamedic();
   /* @GET(PARAMEDIC+"/read_by_disease_states_id.php")
    Single<ResponseParamedic> getParamedicByParamedicId(@Query("diseaseStatesId")int diseaseStatesId);
    */


    @GET(PARAMEDIC+"/read_paramedic_join_userlogin.php")
    Single<ResponseParamedicJoinUserlogin> getParamedicByJoinUserLogin(@Query("paramedicStatus")String paramedicStatus);
    @GET(PARAMEDIC+"/read_by_userlogin_id.php")
    Single<ResponseParamedic> getParamedicByUserloginId(@Query("userloginId")int userloginId);
    @POST(PARAMEDIC+"/create.php")
    Single<Status> insertParamedic(@Body Paramedic paramedic);

    @PUT(PARAMEDIC+"/update_status_by_paramedic_id.php")
    Single<Status> updateStatusByParamedicId(@Body Paramedic paramedic);


    @PUT(PARAMEDIC+"/update_location_by_paramedic_id.php")
    Single<Status> updateLocationByParamedicId(@Body Paramedic paramedic);

    @PUT(PARAMEDIC+"/update.php")
    Single<Status> updateParamedic(@Body Paramedic paramedic);

    @DELETE(PARAMEDIC+"/delete.php")
    Single<Status> deleteParamedic(@Query("paramedicId") int  paramedicId);

}
