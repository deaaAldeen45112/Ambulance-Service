package com.example.paramedic.dp;

import android.util.Log;


import com.example.paramedic.datasource.remote.AmbulanceApiService;
import com.example.paramedic.datasource.remote.DiseaseStatesApiService;
import com.example.paramedic.datasource.remote.ParamedicApiService;
import com.example.paramedic.datasource.remote.PatientApiService;
import com.example.paramedic.datasource.remote.RequestApiService;
import com.example.paramedic.datasource.remote.UserLoginApiService;
import com.example.paramedic.resourses.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModel {


    @Provides
    @Singleton
    public static UserLoginApiService provideUserLoginApiService(){
        Log.d("TAG", "provide: ");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(UserLoginApiService.class);
    }
    @Provides
    @Singleton
    public static PatientApiService providePatientApiService(){
        Log.d("TAG", "provide: ");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(PatientApiService.class);
    }

    @Provides
    @Singleton
    public static DiseaseStatesApiService provideDiseaseStatesApiService(){
        Log.d("TAG", "provide: ");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(DiseaseStatesApiService.class);
    }
    @Provides
    @Singleton
    public static AmbulanceApiService provideAmbulanceApiService(){
        Log.d("TAG", "provide: ");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(AmbulanceApiService.class);
    }
    @Provides
    @Singleton
    public static ParamedicApiService provideParamedicApiService(){
        Log.d("TAG", "provide: ");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ParamedicApiService.class);
    }

    @Provides
    @Singleton
    public static RequestApiService provideRequestApiService(){
        Log.d("TAG", "provide: ");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return  new Retrofit.Builder()
                .baseUrl(Constant.url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(RequestApiService.class);
    }


}
