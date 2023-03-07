package com.example.paramedic.ui.admin.crud.diseaseStates.insert;

import android.nfc.Tag;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.dieseaseStates.DiseaseStates;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;
import com.example.paramedic.model.patient.ResponsePatient;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.repository.DiseaseStatesRepository;
import com.example.paramedic.repository.PatientRepository;
import com.example.paramedic.repository.RequestRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InsertDiseaseStateViewModel extends ViewModel {

    private MutableLiveData<Status> insertDiseaseStatesMutableLiveData = new MutableLiveData<>();

    private DiseaseStatesRepository diseaseStatesRepository;
    private static final String TAG="InsertDiseaseStateViMod";

    @ViewModelInject
    public InsertDiseaseStateViewModel(DiseaseStatesRepository diseaseStatesRepository){
      this.diseaseStatesRepository = diseaseStatesRepository;
    }


    public void insertDiseaseStates(DiseaseStates diseaseState) {

        this.diseaseStatesRepository.insertDiseaseStates(diseaseState)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(status -> {
                            Log.d(TAG, "insertDiseaseStates result :" + status.getStatus());

                            this.insertDiseaseStatesMutableLiveData.setValue(status);


                        },
                        error -> {
                            Log.d(TAG, "insertDiseaseStates Error :" + error);

                        }

                );

    }
    LiveData<Status> getInsertDiseaseStatesLiveDate() {
        return insertDiseaseStatesMutableLiveData;
    }




}

