package com.example.paramedic.repository;

import android.util.Log;

import com.example.paramedic.datasource.local.UserLoginDao;
import com.example.paramedic.datasource.remote.PatientApiService;
import com.example.paramedic.datasource.remote.UserLoginApiService;
import com.example.paramedic.model.Status;
import com.example.paramedic.model.patient.Patient;
import com.example.paramedic.model.patient.ResponsePatient;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class PatientRepository {
    private PatientApiService patientApiService;

    @Inject
    public PatientRepository(PatientApiService patientApiService) {
        this.patientApiService = patientApiService;
    }

    public Single<ResponsePatient> getPatientsByUserloginId(int userloginId) {
        return patientApiService.getPatientsByUserloginId(userloginId);
    }


    public Single<ResponsePatient> getPatients() {
        return patientApiService.getPatients();
    }

    public Single<Status> insertPatient(Patient patient) {
        return patientApiService.insertPatient(patient);
    }

    public Single<Status> deletePatient(int patientId) {
        return patientApiService.deletePatient(patientId);
    }

    public Single<Status> updatePatient(Patient patient) {
        return patientApiService.updatePatient(patient);
    }
}