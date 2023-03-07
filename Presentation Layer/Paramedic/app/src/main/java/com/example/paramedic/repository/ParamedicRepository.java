package com.example.paramedic.repository;

import static com.example.paramedic.resourses.Constant.PARAMEDIC;

import com.example.paramedic.datasource.remote.DiseaseStatesApiService;
import com.example.paramedic.datasource.remote.ParamedicApiService;
import com.example.paramedic.model.Status;
import com.example.paramedic.model.paramedic.Paramedic;
import com.example.paramedic.model.paramedic.ResponseParamedic;
import com.example.paramedic.model.paramedic.ResponseParamedicJoinUserlogin;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public class ParamedicRepository {
    private ParamedicApiService paramedicApiService;

    @Inject
    public ParamedicRepository(ParamedicApiService paramedicApiService) {
        this.paramedicApiService = paramedicApiService;
    }


    public Single<ResponseParamedic> getParamedic() {

        return paramedicApiService.getParamedic();

    }

    /* @GET(PARAMEDIC+"/read_by_disease_states_id.php")
     Single<ResponseParamedic> getParamedicByParamedicId(@Query("diseaseStatesId")int diseaseStatesId);
     */
    public Single<Status> insertParamedic(Paramedic paramedic) {

        return paramedicApiService.insertParamedic(paramedic);

    }

    public Single<Status> updateParamedic(Paramedic paramedic) {

        return paramedicApiService.updateParamedic(paramedic);
    }

    public Single<ResponseParamedicJoinUserlogin> getParamedicByJoinUserLogin(String paramedicStatus) {

        return paramedicApiService.getParamedicByJoinUserLogin(paramedicStatus);
    }

    public Single<Status> deleteParamedic(int paramedicId) {

        return paramedicApiService.deleteParamedic(paramedicId);
    }
    public Single<Status> updateStatusByParamedicId(Paramedic paramedic) {

        return paramedicApiService.updateStatusByParamedicId(paramedic);
    }
    public Single<Status> updateLocationByParamedicId(Paramedic paramedic) {

        return paramedicApiService.updateLocationByParamedicId(paramedic);
    }

    public Single<ResponseParamedic> getParamedicByUserloginId(int userlogin) {

        return paramedicApiService.getParamedicByUserloginId(userlogin);
    }
}
