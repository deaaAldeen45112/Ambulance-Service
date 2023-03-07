package com.example.paramedic.ui.admin.crud.userlogin.update;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paramedic.R;
import com.example.paramedic.model.Status;
import com.example.paramedic.model.userlogin.ResponseUserlogin;
import com.example.paramedic.model.userlogin.UserLogin;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateUserLoginFragment extends Fragment {
    private UpdateUserLoginViewModel updateUserLoginViewModel;
    private ArrayList<UserLogin> arrayListUserLogins=new ArrayList<>();

    public static UpdateUserLoginFragment newInstance(String roleName) {
        UpdateUserLoginFragment updateUserLoginFragment=new UpdateUserLoginFragment();
        Bundle bundle=new Bundle();
        bundle.putString("role_name",roleName);
        updateUserLoginFragment.setArguments(bundle);
        return updateUserLoginFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        updateUserLoginViewModel = new ViewModelProvider(this).get(UpdateUserLoginViewModel.class);
        return inflater.inflate(R.layout.fragment_update_userlogin, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatEditText fullName=view.findViewById(R.id.username);
        AppCompatEditText email=view.findViewById(R.id.email);
        AppCompatEditText phone=view.findViewById(R.id.phone);
        AppCompatEditText age=view.findViewById(R.id.age);
        AppCompatEditText password=view.findViewById(R.id.password);
        Button update=view.findViewById(R.id.update);
        Spinner spinnerUserLogin=view.findViewById(R.id.spinner_userlogin);



        updateUserLoginViewModel.getUserLoginsByRoleName(getArguments().getString("role_name"));
        updateUserLoginViewModel.getUserLoginsByRoleNameLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseUserlogin>() {
            @Override
            public void onChanged(ResponseUserlogin responseUserlogin) {
                ArrayAdapter arrayAdapterUserLogin=new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

                arrayListUserLogins= (ArrayList<UserLogin>) responseUserlogin.getData();

                ArrayList<String>userLoginNames=new ArrayList<>();
                for(UserLogin userLogin:arrayListUserLogins){
                    userLoginNames.add(userLogin.getFullName());
                }

                arrayAdapterUserLogin.addAll(userLoginNames);
                spinnerUserLogin.setAdapter(arrayAdapterUserLogin);
            }
        });


        spinnerUserLogin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                UserLogin userLogin=arrayListUserLogins.get(position);
                fullName.setText(userLogin.getFullName());
                email.setText(userLogin.getEmail());
                phone.setText(userLogin.getPhone()+"");
                age.setText(userLogin.getAge()+"");
                password.setText(userLogin.getPassword());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogin userLogin=new UserLogin();
                userLogin.setUserloginId(arrayListUserLogins.get(spinnerUserLogin.getSelectedItemPosition()).getUserloginId());
                userLogin.setPassword(password.getText().toString().trim());
                userLogin.setFullName(fullName.getText().toString().trim());
                userLogin.setPhone(phone.getText().toString().trim());
                userLogin.setEmail(email.getText().toString().trim());
                userLogin.setAge(Integer.parseInt(age.getText().toString().trim()));
                userLogin.setRoleName(getArguments().getString("role_name"));
                updateUserLoginViewModel.updateUserLogin(userLogin);
            }
        });
        updateUserLoginViewModel.getStatusUpdateUserLoginLiveDate().observe(getViewLifecycleOwner(), new Observer<Status>() {
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