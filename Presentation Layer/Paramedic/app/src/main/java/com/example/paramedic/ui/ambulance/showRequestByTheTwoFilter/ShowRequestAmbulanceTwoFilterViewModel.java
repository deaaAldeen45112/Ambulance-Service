package com.example.paramedic.ui.ambulance.showRequestByTheTwoFilter;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.ambulance.ResponseAmbulance;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;
import com.example.paramedic.model.paramedic.ResponseParamedic;
import com.example.paramedic.model.paramedic.ResponseParamedicJoinUserlogin;
import com.example.paramedic.model.patient.ResponsePatient;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;
import com.example.paramedic.model.request.ResponseRequestStatus;
import com.example.paramedic.repository.AmbulanceRepository;
import com.example.paramedic.repository.DiseaseStatesRepository;
import com.example.paramedic.repository.ParamedicRepository;
import com.example.paramedic.repository.PatientRepository;
import com.example.paramedic.repository.RequestRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ShowRequestAmbulanceTwoFilterViewModel extends ViewModel {

    private MutableLiveData<ResponseDiseaseStates> diseaseStatesMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponsePatient> patientIdMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseAmbulance> ambulanceIdMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance> responseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulanceMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseParamedic> responseParamedicMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseRequestStatus> responseDistinctRequestStatus = new MutableLiveData<>();
    private MutableLiveData<Status> updateRequestStatusMutableLiveData = new MutableLiveData<>();

    public static final String TAG = "ShowRequestAmbViMod";


    private PatientRepository patientRepository;
    private DiseaseStatesRepository diseaseStatesRepository;
    private RequestRepository requestRepository;
    private AmbulanceRepository ambulanceRepository;
    private ParamedicRepository paramedicRepository;

    @ViewModelInject
    public ShowRequestAmbulanceTwoFilterViewModel(PatientRepository patientRepository,
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

    public void getAmbulanceByUserLoginId(int userloginId) {
        this.ambulanceRepository.getAmbulanceByUserloginId(userloginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseAmbulance -> {
                    ambulanceIdMutableLiveData.setValue(responseAmbulance);
                    Log.d("AmbulanceViewModel", "getAmbulanceByUserLoginId: " + responseAmbulance.getStatus());
                }, error -> {
                    Log.d("AmbulanceViewModel", "getAmbulanceByUserLoginId: " + error);
                });
    }

    public void getRequests() {
        this.requestRepository.getRequests()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseRequest -> {
                            Log.d(TAG, "getRequests: " + responseRequest.getStatus());
                        },
                        error -> {
                            Log.d(TAG, "getRequests: " + error);

                        }


                );


    }

    public LiveData<ResponseAmbulance> getAmbulanceIdByUserLoginLiveDate() {
        return ambulanceIdMutableLiveData;
    }
    public void getDiseaseStates() {

        this.diseaseStatesRepository.getDiseaseStates()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseDiseaseStates -> {
                            Log.d("PatientViewModel", "getDiseaseStates: " + responseDiseaseStates.getStatus());

                            this.diseaseStatesMutableLiveData.setValue(responseDiseaseStates);


                        },
                        error -> {
                            Log.d("PatientViewModel", "getDiseaseStates: Error " + error);

                        }

                );

    }

    LiveData<ResponseDiseaseStates> getDiseaseStatesLiveDate() {
        return diseaseStatesMutableLiveData;
    }

    public void getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId(String requestStatus,int paramedicId) {


        this.requestRepository
                .getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId(requestStatus,paramedicId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance -> {
                    responseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulanceMutableLiveData.setValue(responseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance);
                    Log.d(TAG, "getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId: " +
                            responseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance.getStatus());
                }, error -> {
                    Log.d(TAG, "getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId: " +
                            error);

                });

    }

    LiveData<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance>
    getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicIdLiveData() {

        return responseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulanceMutableLiveData;
    }

    public void getParamedic(){

        this.paramedicRepository.getParamedic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseParamedic -> {
                    responseParamedicMutableLiveData.setValue(responseParamedic);
                    Log.d(TAG, "getParamedic: "+responseParamedic.getStatus());

                },error->{
                    Log.d(TAG, "getParamedic: "+error);

                });

    }
    LiveData<ResponseParamedic> getParamedicLiveData(){

        return responseParamedicMutableLiveData;

    }

    public void getDistinctRequestStatus(){
        this.requestRepository.getDistinctRequestStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseRequestStatus -> {
                    responseDistinctRequestStatus.setValue(responseRequestStatus);
                    Log.d(TAG, "getDistinctRequestStatus: "+responseRequestStatus.getStatus());
                },error->{

                    Log.d(TAG, "getDistinctRequestStatus: "+error);
                });


    }
    LiveData<ResponseRequestStatus>getDistinctRequestStatusLiveDate(){

        return responseDistinctRequestStatus;
    }

    public void updateRequestStatusByRequestId(Request request){

        this.requestRepository.updateRequestStatusByRequestId(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(status -> {
                    updateRequestStatusMutableLiveData.setValue(status);
                    Log.d(TAG, "updateRequestStatusByRequestId: "+status);
                }, error->{
                    Log.d(TAG, "updateRequestStatusByRequestId: "+error);
                });
    }
    public LiveData<Status> updateRequestStatusLiveData(){


        return updateRequestStatusMutableLiveData;
    }

}

