package com.example.paramedic.ui.admin.crud.userlogin.update;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.userlogin.ResponseUserlogin;
import com.example.paramedic.model.userlogin.UserLogin;
import com.example.paramedic.repository.UserLoginRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UpdateUserLoginViewModel extends ViewModel {
    private UserLoginRepository userLoginRepository;
    private MutableLiveData<Status> statusUpdateUserLoginMutableLiveDate=new MutableLiveData<>();
    private MutableLiveData<ResponseUserlogin> responseUserloginMutableLiveData=new MutableLiveData<>();
    private  static  final String TAG="UpdateUserLogViewModel";

    @ViewModelInject
    UpdateUserLoginViewModel(UserLoginRepository userLoginRepository){
        this.userLoginRepository=userLoginRepository;
    }


    public void getUserLoginsByRoleName(String roleName){

        userLoginRepository.getUserLoginsByRoleName(roleName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {
                            responseUserloginMutableLiveData.setValue(result);
                            Log.d(TAG,"getUserLoginsByRoleName success : "+result);
                        },
                        error -> Log.d(TAG, "getUserLoginsByRoleName error: "+error));


    }
    public  LiveData<ResponseUserlogin> getUserLoginsByRoleNameLiveDate(){
        return responseUserloginMutableLiveData;
    }

    public void updateUserLogin(UserLogin userLogin){

        userLoginRepository.updateUserLogin(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {

                            statusUpdateUserLoginMutableLiveDate.setValue(result);
                            Log.d(TAG,"updateUserLogin "+result.getStatus());
                        },
                        error -> Log.d(TAG, "updateUserLogin"+error)

                );


    }
    public  LiveData<Status> getStatusUpdateUserLoginLiveDate(){
        return statusUpdateUserLoginMutableLiveDate;
    }
}
