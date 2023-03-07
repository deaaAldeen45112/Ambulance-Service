package com.example.paramedic.datasource.remote;


import static com.example.paramedic.resourses.Constant.USER_LOGIN;

import com.example.paramedic.model.Status;
import com.example.paramedic.model.userlogin.ResponseUserlogin;
import com.example.paramedic.model.userlogin.UserLogin;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface UserLoginApiService {

    @GET(USER_LOGIN+"/read_single_by_roleName.php")
    Single<ResponseUserlogin> getUserLoginsByRoleName(@Query("roleName") String roleName);
    @GET(USER_LOGIN+"/read.php")
    Single<ResponseUserlogin> getUserLogins();
    @GET(USER_LOGIN+"/read_single.php")
    Single<ResponseUserlogin> getUserLoginByUserloginId(@Query("userloginId") int userloginId);
    @POST(USER_LOGIN+"/create.php")
    Single<Status> insertUserLogin(@Body UserLogin userLogin);
    @PUT(USER_LOGIN+"/update.php")
    Single<Status>  updateUserLogin(@Body UserLogin userLogin);
    @DELETE(USER_LOGIN+"/delete.php")
    Single<Status>  deleteUserLogin(@Query("userloginId") int userloginId);
    @POST(USER_LOGIN+"/read_email_password.php")
    Single<ResponseUserlogin> login(@Body UserLogin userLogin);
    @GET(USER_LOGIN+"/read_email.php")
    Single<Status> verificationUserloginByEmail(@Query("email") String email);



}
