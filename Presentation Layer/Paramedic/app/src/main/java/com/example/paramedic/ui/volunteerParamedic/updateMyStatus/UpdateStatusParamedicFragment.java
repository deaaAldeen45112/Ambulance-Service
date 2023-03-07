package com.example.paramedic.ui.volunteerParamedic.updateMyStatus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paramedic.R;
import com.example.paramedic.adapter.RequestParamedicListAdapter;
import com.example.paramedic.model.Status;
import com.example.paramedic.model.paramedic.Paramedic;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLogin;
import com.example.paramedic.model.request.RequestStatus;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin;
import com.example.paramedic.model.request.ResponseRequestStatus;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateStatusParamedicFragment extends Fragment {
    UpdateStatusParamedicViewModel updateStatusParamedicViewModel;

    public static UpdateStatusParamedicFragment newInstance(int paramedicId) {


        UpdateStatusParamedicFragment updateStatusParamedicFragment=new UpdateStatusParamedicFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("paramedic_id",paramedicId);
        updateStatusParamedicFragment.setArguments(bundle);
        return updateStatusParamedicFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        updateStatusParamedicViewModel= new ViewModelProvider(this).get(UpdateStatusParamedicViewModel.class);

        return inflater.inflate(R.layout.fragment_update_status_paramedic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Switch switchUpdateStatusParamedic=view.findViewById(R.id.switch_update_status_paramedic);

        Paramedic paramedic = new Paramedic();
        paramedic.setParamedicId(getArguments().getInt("paramedic_id"));

        switchUpdateStatusParamedic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    paramedic.setParamedicStatus("yes");
                }
                else {
                    paramedic.setParamedicStatus("no");

                }
                updateStatusParamedicViewModel.updateStatusByParamedicId(paramedic);

            }
        });











    }





    }







