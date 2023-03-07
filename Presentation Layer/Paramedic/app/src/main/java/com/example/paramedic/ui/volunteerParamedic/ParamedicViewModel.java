package com.example.paramedic.ui.volunteerParamedic;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paramedic.model.ambulance.ResponseAmbulance;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;
import com.example.paramedic.model.paramedic.Paramedic;
import com.example.paramedic.model.paramedic.ResponseParamedic;
import com.example.paramedic.model.paramedic.ResponseParamedicJoinUserlogin;
import com.example.paramedic.model.patient.ResponsePatient;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin;
import com.example.paramedic.repository.AmbulanceRepository;
import com.example.paramedic.repository.DiseaseStatesRepository;
import com.example.paramedic.repository.ParamedicRepository;
import com.example.paramedic.repository.PatientRepository;
import com.example.paramedic.repository.RequestRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ParamedicViewModel extends ViewModel {

    private MutableLiveData<ResponseDiseaseStates> diseaseStatesMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponsePatient> patientIdMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseAmbulance> ambulanceIdMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseParamedic> paramedicIdMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin> requestsByRequestStatusAndParamedicIdMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponseParamedicJoinUserlogin> responseParamedicJoinUserloginMutableLiveData = new MutableLiveData<>();

    public static final String TAG = "ParamedicViewModel";


    private PatientRepository patientRepository;
    private DiseaseStatesRepository diseaseStatesRepository;
    private RequestRepository requestRepository;
    private AmbulanceRepository ambulanceRepository;
    private ParamedicRepository paramedicRepository;

    @ViewModelInject
    public ParamedicViewModel(PatientRepository patientRepository,
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



    public void updateLocationByParamedicId(Paramedic paramedic){

        this.paramedicRepository.updateLocationByParamedicId(paramedic)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseParamedic -> {
                      Log.d(TAG, "updateLocationByParamedicId: " + responseParamedic.getStatus());
                }, error -> {
                    Log.d(TAG, "updateLocationByParamedicId: " + error);
                });
    }

    public void getParamedicByUserloginId(int userloginId) {
        this.paramedicRepository.getParamedicByUserloginId(userloginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseParamedic -> {
                    paramedicIdMutableLiveData.setValue(responseParamedic);
                    Log.d("AmbulanceViewModel", "getAmbulanceByUserLoginId: " + responseParamedic.getStatus());
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


    public LiveData<ResponseParamedic> getParamedicIdByUserLoginLiveDate() {
        return paramedicIdMutableLiveData;
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
   /* public void getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId(String requestStatus, int paramedicId) {


        this.requestRepository
                .getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId
                        (requestStatus, paramedicId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseRequestJoinDiseaseStatesJoinPatientJoinUserLogin -> {
                    requestsByRequestStatusAndParamedicIdMutableLiveData.setValue(responseRequestJoinDiseaseStatesJoinPatientJoinUserLogin);
                    Log.d(TAG, "getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId: " +
                            responseRequestJoinDiseaseStatesJoinPatientJoinUserLogin.getStatus());
                }, error -> {
                    Log.d(TAG, "getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId: " +
                            error);

                });

    }
*/
    LiveData<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin>
    getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicIdLiveData() {

     return requestsByRequestStatusAndParamedicIdMutableLiveData;
    }

    public void getParamedicsJoinUserLogin(String paramedicStatus){

        this.paramedicRepository.getParamedicByJoinUserLogin(paramedicStatus)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseParamedicJoinUserlogin -> {
                    responseParamedicJoinUserloginMutableLiveData.setValue(responseParamedicJoinUserlogin);
                    Log.d(TAG, "getParamedicsJoinUserLogin: "+responseParamedicJoinUserlogin.getStatus());

                },error->{
                    Log.d(TAG, "getParamedicsJoinUserLogin: "+error);

                });

    }
    LiveData<ResponseParamedicJoinUserlogin> getParamedicsJoinUserLoginLiveData(){

        return responseParamedicJoinUserloginMutableLiveData;

    }

}

