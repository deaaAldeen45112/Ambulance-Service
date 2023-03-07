package com.example.paramedic.ui.admin.crud.diseaseStates.delete;

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

public class DeleteDiseaseStateViewModel extends ViewModel {
  
    private MutableLiveData<Status> deleteDiseaseStatesMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseDiseaseStates> readDiseaseStatesMutableLiveData = new MutableLiveData<>();

    private DiseaseStatesRepository diseaseStatesRepository;
    private static final String TAG="DeleteDiseaseStateViMod";

    @ViewModelInject
    public DeleteDiseaseStateViewModel(DiseaseStatesRepository diseaseStatesRepository){
      this.diseaseStatesRepository = diseaseStatesRepository;
    }

   
    public void deleteDiseaseStates(int  diseaseStateId) {

        this.diseaseStatesRepository.deleteDiseaseStates(diseaseStateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(status -> {
                            Log.d(TAG, "deleteDiseaseStates result :" + status.getStatus());

                            this.deleteDiseaseStatesMutableLiveData.setValue(status);


                        },
                        error -> {
                            Log.d(TAG, "deleteDiseaseStates Error :" + error);

                        }

                );

    }
    LiveData<Status> getDeleteDiseaseStatesLiveDate() {
        return deleteDiseaseStatesMutableLiveData;
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

