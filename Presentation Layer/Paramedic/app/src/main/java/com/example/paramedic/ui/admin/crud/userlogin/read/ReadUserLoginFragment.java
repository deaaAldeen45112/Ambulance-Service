package com.example.paramedic.ui.admin.crud.userlogin.read;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paramedic.R;
import com.example.paramedic.adapter.ReadUserLoginsListAdapter;
import com.example.paramedic.model.userlogin.ResponseUserlogin;
import com.example.paramedic.model.userlogin.UserLogin;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReadUserLoginFragment extends Fragment {

    private ReadUserLoginViewModel readUserLoginViewModel;
    private ArrayList<UserLogin> arrayListUserLogins=new ArrayList<>();

    public static ReadUserLoginFragment newInstance(String roleName) {
        ReadUserLoginFragment readUserLoginFragment=new ReadUserLoginFragment();
        Bundle bundle=new Bundle();
        bundle.putString("role_name",roleName);
        readUserLoginFragment.setArguments(bundle);
        return readUserLoginFragment;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        readUserLoginViewModel = new ViewModelProvider(this).get(ReadUserLoginViewModel.class);
        return inflater.inflate(R.layout.fragment_read_userlogin, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerViewUserLogin=view.findViewById(R.id.recyclerview_read_userlogin);
        readUserLoginViewModel.getUserLoginsByRoleName(getArguments().getString("role_name"));

        ReadUserLoginsListAdapter readUserLoginsListAdapter=new ReadUserLoginsListAdapter(getContext(),R.layout.recyclerview_item_read_userlogin);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerViewUserLogin.setHasFixedSize(false);
        recyclerViewUserLogin.setLayoutManager(layoutManager);
        recyclerViewUserLogin.setAdapter(readUserLoginsListAdapter);

        readUserLoginViewModel.getUserLoginsByRoleNameLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseUserlogin>() {
            @Override
            public void onChanged(ResponseUserlogin responseUserlogin) {

               arrayListUserLogins= (ArrayList<UserLogin>) responseUserlogin.getData();
               readUserLoginsListAdapter.setArrayListUserLogins(arrayListUserLogins);


            }
        });




        
    }




    }
