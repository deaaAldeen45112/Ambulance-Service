package com.example.paramedic.repository;

import static com.example.paramedic.resourses.Constant.AMBULANCE;

import com.example.paramedic.datasource.remote.AmbulanceApiService;
import com.example.paramedic.datasource.remote.DiseaseStatesApiService;
import com.example.paramedic.model.Status;
import com.example.paramedic.model.ambulance.Ambulance;
import com.example.paramedic.model.ambulance.ResponseAmbulance;
import com.example.paramedic.model.patient.ResponsePatient;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public class AmbulanceRepository {


    private AmbulanceApiService ambulanceApiService;

    @Inject
    public AmbulanceRepository(AmbulanceApiService ambulanceApiService) {
        this.ambulanceApiService = ambulanceApiService;
    }

    public Single<ResponseAmbulance> getAmbulance() {
        return ambulanceApiService.getAmbulance();

    }

    public Single<Status> insertAmbulance(Ambulance ambulance) {

        return ambulanceApiService.insertAmbulance(ambulance);
    }

    public Single<Status> updateAmbulance(Ambulance ambulance) {
        return ambulanceApiService.updateAmbulance(ambulance);

    }

    public Single<Status> deleteAmbulance(int ambulanceId) {

        return ambulanceApiService.deleteAmbulance(ambulanceId);
    }
    public Single<ResponseAmbulance> getAmbulanceByUserloginId(int userloginId) {
        return ambulanceApiService.getAmbulanceByUserloginId(userloginId);
    }

}
