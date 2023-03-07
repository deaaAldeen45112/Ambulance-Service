package com.example.paramedic.ui.register;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paramedic.R;
import com.example.paramedic.model.Status;
import com.example.paramedic.model.userlogin.UserLogin;
import com.example.paramedic.ui.login.LoginActivity;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterActivity extends AppCompatActivity {
    private RegisterViewModel registerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        AppCompatEditText fullName=findViewById(R.id.username);
        AppCompatEditText email=findViewById(R.id.email);
        AppCompatEditText phone=findViewById(R.id.phone);
        AppCompatEditText age=findViewById(R.id.age);
        AppCompatEditText password=findViewById(R.id.password);
        Button insert=findViewById(R.id.insert);
        TextView textViewGoLogin =findViewById(R.id.textView_go_login);
        getSupportActionBar().setTitle("Register");
        textViewGoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });






        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogin userLogin=new UserLogin();
                userLogin.setPassword(password.getText().toString().trim());
                userLogin.setFullName(fullName.getText().toString().trim());
                userLogin.setPhone(phone.getText().toString().trim());
                userLogin.setEmail(email.getText().toString().trim());
                Toast.makeText(RegisterActivity.this, ""+age.getText(), Toast.LENGTH_SHORT).show();
                userLogin.setAge(Integer.parseInt(age.getText().toString().trim()));
                userLogin.setRoleName("Patient");
                registerViewModel.inserUserLogin(userLogin);
            }
        });
        registerViewModel.getStatusInsertUserLogin().observe(this, new Observer<Status>() {
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
//mViewModel.getInsertStudentFormState().observe(getViewLifecycleOwner(), new Observer<InsertStudentFormState>() {
//                @Override
//                public void onChanged(@Nullable InsertStudentFormState insertStudentFormState) {
//                    if (insertStudentFormState == null) {
//                        return;
//                    }
//                    insert.setEnabled(insertStudentFormState.isDataValid());
//                    if (insertStudentFormState.getUsernameError() != null) {
//                        email.setError(getString(insertStudentFormState.getUsernameError()));
//                    }
//                    if (insertStudentFormState.getPasswordError() != null) {
//                        password.setError(getString(insertStudentFormState.getPasswordError()));
//                    }
//                }
//            });

//        email.addTextChangedListener(afterTextChangedListener);
//        password.addTextChangedListener(afterTextChangedListener);
    }
}