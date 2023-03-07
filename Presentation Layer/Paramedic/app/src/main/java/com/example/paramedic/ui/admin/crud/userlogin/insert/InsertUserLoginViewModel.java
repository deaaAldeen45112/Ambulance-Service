package com.example.paramedic.ui.admin.crud.userlogin.insert;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.userlogin.UserLogin;
import com.example.paramedic.repository.UserLoginRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class InsertUserLoginViewModel extends ViewModel {
    private UserLoginRepository userLoginRepository;
    private MutableLiveData<Status> statusInsertUserLogin=new MutableLiveData<>();

    @ViewModelInject
    InsertUserLoginViewModel(UserLoginRepository userLoginRepository){
        this.userLoginRepository=userLoginRepository;
    }



    public void insertUserLogin(UserLogin userLogin){

        userLoginRepository.insertUserLogin(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result-> {

                            statusInsertUserLogin.setValue(result);
                            Log.d("viwModel",result.getStatus()+"deaa");
                        },
                        error -> Log.d("viwModel", "jjjj  "+error));


    }
    public  LiveData<Status> getStatusInsertUserLogin(){
        return statusInsertUserLogin;
    }
}
