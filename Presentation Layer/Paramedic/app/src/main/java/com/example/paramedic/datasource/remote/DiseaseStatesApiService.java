package com.example.paramedic.datasource.remote;

import static com.example.paramedic.resourses.Constant.DISEASE_STATES;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.dieseaseStates.DiseaseStates;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface DiseaseStatesApiService {
    @GET(DISEASE_STATES+"/read.php")
    Single<ResponseDiseaseStates> getDiseaseStates();
    @GET(DISEASE_STATES+"/read_by_disease_states_id.php")
    Single<ResponseDiseaseStates> getDiseaseStatesByDiseaseStatesId(@Query("diseaseStatesId")int diseaseStatesId);
    @POST(DISEASE_STATES+"/create.php")
    Single<Status> insertDiseaseStates(@Body DiseaseStates diseaseStates);
    @PUT(DISEASE_STATES+"/update.php")
    Single<Status> updateDiseaseStates(@Body DiseaseStates diseaseStates);
    @DELETE(DISEASE_STATES+"/delete.php")
    Single<Status> deleteDiseaseStates(@Query("diseaseStatesId") int diseaseStatesId);

}
