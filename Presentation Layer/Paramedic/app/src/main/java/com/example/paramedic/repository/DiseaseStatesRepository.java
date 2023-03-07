package com.example.paramedic.repository;

import static com.example.paramedic.resourses.Constant.DISEASE_STATES;

import android.util.Log;

import com.example.paramedic.datasource.local.UserLoginDao;
import com.example.paramedic.datasource.remote.DiseaseStatesApiService;
import com.example.paramedic.datasource.remote.UserLoginApiService;
import com.example.paramedic.model.Status;
import com.example.paramedic.model.dieseaseStates.DiseaseStates;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public class DiseaseStatesRepository {
    private DiseaseStatesApiService diseaseStatesApiService;

    @Inject
    public DiseaseStatesRepository(DiseaseStatesApiService diseaseStatesApiService) {
        this.diseaseStatesApiService = diseaseStatesApiService;
    }

    public Single<ResponseDiseaseStates> getDiseaseStates(){

        return diseaseStatesApiService.getDiseaseStates();
    }

    public Single<ResponseDiseaseStates> getDiseaseStatesByDiseaseStatesId(@Query("diseaseStatesId") int diseaseStatesId){

        return diseaseStatesApiService.getDiseaseStatesByDiseaseStatesId(diseaseStatesId);

    }

    public Single<Status> insertDiseaseStates(@Body DiseaseStates diseaseStates){
        return diseaseStatesApiService.insertDiseaseStates(diseaseStates);


    }

    public Single<Status> updateDiseaseStates(@Body DiseaseStates diseaseStates){
       return diseaseStatesApiService.updateDiseaseStates(diseaseStates);
    }

    public Single<Status> deleteDiseaseStates(int  diseaseStatesId){
      return  diseaseStatesApiService.deleteDiseaseStates(diseaseStatesId);

    }
}
