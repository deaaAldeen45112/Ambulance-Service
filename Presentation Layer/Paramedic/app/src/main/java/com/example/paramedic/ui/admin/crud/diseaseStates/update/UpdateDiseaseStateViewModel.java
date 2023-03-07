package com.example.paramedic.ui.admin.crud.diseaseStates.update;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.dieseaseStates.DiseaseStates;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;
import com.example.paramedic.repository.DiseaseStatesRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateDiseaseStateViewModel extends ViewModel {

    private MutableLiveData<Status> updateDiseaseStatesMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseDiseaseStates> readDiseaseStatesMutableLiveData = new MutableLiveData<>();

    private DiseaseStatesRepository diseaseStatesRepository;
    private static final String TAG="UpdateDiseaseStateViMod";

    @ViewModelInject
    public UpdateDiseaseStateViewModel(DiseaseStatesRepository diseaseStatesRepository){
      this.diseaseStatesRepository = diseaseStatesRepository;
    }


    public void updateDiseaseStates(DiseaseStates diseaseState) {

        this.diseaseStatesRepository.updateDiseaseStates(diseaseState)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(status -> {
                            Log.d(TAG, "updateDiseaseStates result :" + status.getStatus());

                            this.updateDiseaseStatesMutableLiveData.setValue(status);


                        },
                        error -> {
                            Log.d(TAG, "updateDiseaseStates Error :" + error);

                        }

                );

    }
    LiveData<Status> getUpdateDiseaseStatesLiveDate() {
        return updateDiseaseStatesMutableLiveData;
    }

    public void getDiseaseStates() {

        this.diseaseStatesRepository.getDiseaseStates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseDiseaseStates -> {
                            Log.d(TAG, "getDiseaseStates: " + responseDiseaseStates.getStatus());

                            this.readDiseaseStatesMutableLiveData.setValue(responseDiseaseStates);


                        },
                        error -> {
                            Log.d(TAG, "getDiseaseStates: Error " + error);

                        }

                );

    }
    LiveData<ResponseDiseaseStates> getDiseaseStatesLiveDate() {
        return readDiseaseStatesMutableLiveData;
    }



}

