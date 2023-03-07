package com.example.paramedic.ui.admin.crud.userlogin.insert;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paramedic.R;
import com.example.paramedic.model.Status;
import com.example.paramedic.model.userlogin.UserLogin;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InsertUserLoginFragment extends Fragment {
    private InsertUserLoginViewModel insertUserLoginViewModel;
    public static InsertUserLoginFragment newInstance(String roleName) {
        InsertUserLoginFragment insertUserLoginFragment=new InsertUserLoginFragment();
        Bundle bundle=new Bundle();
        bundle.putString("role_name",roleName);
        insertUserLoginFragment.setArguments(bundle);
        return insertUserLoginFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        insertUserLoginViewModel = new ViewModelProvider(this).get(InsertUserLoginViewModel.class);
        return inflater.inflate(R.layout.fragment_insert_userlogin, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatEditText fullName=view.findViewById(R.id.username);
        AppCompatEditText email=view.findViewById(R.id.email);
        AppCompatEditText phone=view.findViewById(R.id.phone);
        AppCompatEditText age=view.findViewById(R.id.age);
        AppCompatEditText password=view.findViewById(R.id.password);
        Button insert=view.findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogin userLogin=new UserLogin();
                userLogin.setPassword(password.getText().toString().trim());
                userLogin.setFullName(fullName.getText().toString().trim());
                userLogin.setPhone(phone.getText().toString().trim());
                userLogin.setEmail(email.getText().toString().trim());
                userLogin.setAge(Integer.parseInt(age.getText().toString().trim()));
                userLogin.setRoleName(getArguments().getString("role_name"));
                insertUserLoginViewModel.insertUserLogin(userLogin);
            }
        });
        insertUserLoginViewModel.getStatusInsertUserLogin().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {

            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
//                        mViewModel.loginDataChanged(email.getText().toString(),
//                                password.getText().toString());
            }
        };



    }




}