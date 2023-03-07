package com.example.paramedic.ui.admin.crud.userlogin.delete;

import android.nfc.Tag;
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

public class DeleteUserLoginViewModel extends ViewModel {
    private UserLoginRepository userLoginRepository;
    private MutableLiveData<Status> statusDeleteUserLoginMutableLiveData=new MutableLiveData<>();
    private MutableLiveData<ResponseUserlogin> responseUserloginMutableLiveData=new MutableLiveData<>();
    private  static  final String TAG="DeleteUserLogViewModel";

    @ViewModelInject
    DeleteUserLoginViewModel(UserLoginRepository userLoginRepository){
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


    public void deleteUserLogin(int userLoginId){

        userLoginRepository.deleteUserLogin(userLoginId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {
                            statusDeleteUserLoginMutableLiveData.setValue(result);
                            Log.d(TAG,"deleteUserLogin success : "+result);
                        },
                        error -> Log.d(TAG, "deleteUserLogin error: "+error));


    }
    public  LiveData<Status> statusDeleteUserLoginLiveData(){
        return statusDeleteUserLoginMutableLiveData;
    }
}
