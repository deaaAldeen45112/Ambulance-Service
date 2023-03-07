package com.example.paramedic.ui.admin.crud.userlogin.delete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paramedic.R;
import com.example.paramedic.model.userlogin.ResponseUserlogin;
import com.example.paramedic.model.userlogin.UserLogin;
import com.example.paramedic.ui.admin.crud.userlogin.insert.InsertUserLoginFragment;
import com.example.paramedic.ui.admin.crud.userlogin.insert.InsertUserLoginViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DeleteUserLoginFragment extends Fragment {

    private DeleteUserLoginViewModel deleteUserLoginViewModel;
    private ArrayList<UserLogin> arrayListUserLogins=new ArrayList<>();
    public static DeleteUserLoginFragment newInstance(String roleName) {
        DeleteUserLoginFragment deleteUserLoginFragment=new DeleteUserLoginFragment();
        Bundle bundle=new Bundle();
        bundle.putString("role_name",roleName);
        deleteUserLoginFragment.setArguments(bundle);
        return deleteUserLoginFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        deleteUserLoginViewModel = new ViewModelProvider(this).get(DeleteUserLoginViewModel.class);
        return inflater.inflate(R.layout.fragment_delete_userlogin, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinnerUserLogin=view.findViewById(R.id.spinner_userlogin);
        Button  buttonDelete=view.findViewById(R.id.button_delete);

        deleteUserLoginViewModel.getUserLoginsByRoleName(getArguments().getString("role_name"));
        deleteUserLoginViewModel.getUserLoginsByRoleNameLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseUserlogin>() {
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



        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUserLoginViewModel.deleteUserLogin(
                        arrayListUserLogins.
                                get(spinnerUserLogin
                                        .getSelectedItemPosition()).getUserloginId());


            }
        });

    }




    }
