package com.example.paramedic.ui.volunteerParamedic.updateMyStatus;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.ambulance.ResponseAmbulance;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;
import com.example.paramedic.model.paramedic.Paramedic;
import com.example.paramedic.model.paramedic.ResponseParamedicJoinUserlogin;
import com.example.paramedic.model.patient.ResponsePatient;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin;
import com.example.paramedic.model.request.ResponseRequestStatus;
import com.example.paramedic.repository.AmbulanceRepository;
import com.example.paramedic.repository.DiseaseStatesRepository;
import com.example.paramedic.repository.ParamedicRepository;
import com.example.paramedic.repository.PatientRepository;
import com.example.paramedic.repository.RequestRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateStatusParamedicViewModel extends ViewModel {

   private MutableLiveData<Status> updateStatusMutableLiveData = new MutableLiveData<>();

    public static final String TAG = "UpdateStatParaViMod";


    private PatientRepository patientRepository;
    private DiseaseStatesRepository diseaseStatesRepository;
    private RequestRepository requestRepository;
    private AmbulanceRepository ambulanceRepository;
    private ParamedicRepository paramedicRepository;

    @ViewModelInject
    public UpdateStatusParamedicViewModel(PatientRepository patientRepository,
                                         DiseaseStatesRepository diseaseStatesRepository,
                                         RequestRepository requestRepository,
                                         AmbulanceRepository ambulanceRepository,
                                         ParamedicRepository paramedicRepository) {

        this.requestRepository = requestRepository;
        this.patientRepository = patientRepository;
        this.diseaseStatesRepository = diseaseStatesRepository;
        this.ambulanceRepository = ambulanceRepository;
        this.paramedicRepository = paramedicRepository;

    }



    public LiveData<Status> updateStatusLiveData(){


      return updateStatusMutableLiveData;
    }
    public void updateStatusByParamedicId(Paramedic paramedic){

        this.paramedicRepository.updateStatusByParamedicId(paramedic)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(status -> {
                    updateStatusMutableLiveData.setValue(status);
                    Log.d(TAG, "updateStatusByParamedicId: "+status);
                },error->{
                    Log.d(TAG, "updateStatusByParamedicId: "+error);
                });
    }

}

