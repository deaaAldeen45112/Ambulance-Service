package com.example.paramedic.ui.patient.sendingTheRequest;

import android.util.Log;
import android.util.Patterns;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paramedic.R;
import com.example.paramedic.datasource.remote.DiseaseStatesApiService;
import com.example.paramedic.datasource.remote.RequestApiService;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;
import com.example.paramedic.model.patient.ResponsePatient;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.userlogin.ResponseUserlogin;
import com.example.paramedic.model.userlogin.UserLogin;
import com.example.paramedic.repository.DiseaseStatesRepository;
import com.example.paramedic.repository.PatientRepository;
import com.example.paramedic.repository.RequestRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SendingTheRequestViewModel extends ViewModel {
    //    private UserLoginRepository userLoginRepository;
    private MutableLiveData<ResponseDiseaseStates> diseaseStatesMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<ResponsePatient> patientIdMutableLiveData = new MutableLiveData<>();
//    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();


    private PatientRepository patientRepository;
    private DiseaseStatesRepository diseaseStatesRepository;
    private RequestRepository requestRepository;

    @ViewModelInject
    public SendingTheRequestViewModel(PatientRepository patientRepository,
                            DiseaseStatesRepository diseaseStatesRepository,
                            RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
        this.patientRepository = patientRepository;
        this.diseaseStatesRepository = diseaseStatesRepository;
    }

    public void getPatientByUserLoginId(int userloginId) {
        this.patientRepository.getPatientsByUserloginId(userloginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responsePatient -> {

                    patientIdMutableLiveData.setValue(responsePatient);
                    Log.d("PatientViewModel", "getPatientByUserLoginId: "+responsePatient.getStatus());
                },error->{
                    Log.d("PatientViewModel", "getPatientByUserLoginId: "+error);
                });
    }
    public void insertRequest(Request request) {
        this.requestRepository.insertRequest(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(status -> {

                            Log.d("PatientViewModel", "insertRequest: " + status);

                        },
                        error -> {
                            Log.d("PatientViewModel", "insertRequest: " + error);


                        }


                );


    }
    public LiveData<ResponsePatient>getPatientIdByUserLoginLiveDate(){
        return patientIdMutableLiveData;
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


//

//    public void login(UserLogin userLogin){
//        Log.d("login start","go "+userLogin.getEmail());
//
//        userLoginRepository.login(userLogin)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(result -> {
//                            listMutableLiveData.setValue(result);
//                            Log.d("viwModel",result.getData().get(0).getPassword()+" deaa");
//
//                        },
//                        error -> Log.d("viwModel", error.getMessage()));
//
//
//    }
//   /* public void insertUserloginLocal(UserLogin userLogin){
//        userLoginRepository.insertA(userLogin);
//    }
//    /*public void deleteAllUserloginLocal(){
//        userLoginRepository.deleteAllUserloginLocal();
//    }
//    public List<UserLogin> getUserLoginsLocal(){
//       return userLoginRepository.getUserLoginsLocal();
//
//    }*/
//    public MutableLiveData<ResponseUserlogin> getUserLoginByPasswordAndEmail() {
//        return listMutableLiveData;
//    }
//    /*public void verificationUserloginByEmail(String email){
//            userLoginRepository.verificationUserloginByEmail(email)
//                    .observeOn(Schedulers.io())
//                    .subscribeOn(Schedulers.io()).subscribe(result-> {
//
//                                Log.d("viwModel",result+" deaa");
//                            },
//                            error -> Log.d("viwModel", "jjjj  "+error.getMessage()));
//
//
//    }
//*/
//    public void loginDataChanged(String username, String password) {
//        if (!isUserNameValid(username)) {
//            loginFormState.setValue(new LoginFormState(R.string.invalid_email, null));
//        } else if (!isPasswordValid(password)) {
//            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
//        } else {
//            loginFormState.setValue(new LoginFormState(true));
//        }
//    }
//
//    // A placeholder username validation check
//    private boolean isUserNameValid(String username) {
//
//
//        if (username == null) {
//            return false;
//        }
//        if (Patterns.EMAIL_ADDRESS.matcher(username).matches()&&!username.trim().isEmpty()) {
//          return  true;
//        } else {
//            return false;
//        }
//    }
//
//    // A placeholder password validation check
//    private boolean isPasswordValid(String password) {
//        return password != null && password.trim().length() > 5;
//    }

}

