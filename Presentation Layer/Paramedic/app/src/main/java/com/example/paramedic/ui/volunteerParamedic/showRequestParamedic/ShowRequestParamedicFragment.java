package com.example.paramedic.ui.volunteerParamedic.showRequestParamedic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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
public class ShowRequestParamedicFragment extends Fragment {
    ShowRequestParamedicViewModel showRequestViewModel;
    Paramedic paramedic=null;
    RequestParamedicListAdapter requestListAdapter;
    RecyclerView recyclerViewRequests;
    ArrayList<String>arrayListRequestStatus;

    public static ShowRequestParamedicFragment newInstance(int paramedicId) {


        ShowRequestParamedicFragment showRequestFragment=new ShowRequestParamedicFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("paramedic_id",paramedicId);
        showRequestFragment.setArguments(bundle);
        return showRequestFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        showRequestViewModel = new ViewModelProvider(this).get(ShowRequestParamedicViewModel.class);

        return inflater.inflate(R.layout.fragment_show_request_paramedic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        requestListAdapter=new RequestParamedicListAdapter(getContext(), R.layout.recyclerview_item_request_paramedic, new ArrayList<>(), new RequestParamedicListAdapter.IStartActivityListner() {
            @Override
            public void onClick(Request request) {

                showRequestViewModel.updateRequestStatusByRequestId(request);

            }
        });




        ArrayAdapter arrayAdapterRequestStatus=new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        recyclerViewRequests=view.findViewById(R.id.recycle_view_requests_paramedic);
        Spinner spinnerRequestStatusParamedic =view.findViewById(R.id.spinner_request_status_paramedic);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());

        recyclerViewRequests.setHasFixedSize(false);
        recyclerViewRequests.setLayoutManager(layoutManager);
        recyclerViewRequests.setAdapter(requestListAdapter);

        spinnerRequestStatusParamedic.setAdapter(arrayAdapterRequestStatus);
        showRequestViewModel.getDistinctRequestStatusForParamedic();
        showRequestViewModel.getDistinctRequestStatusForParamedicLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseRequestStatus>() {
            @Override
            public void onChanged(ResponseRequestStatus responseRequestStatus) {

                arrayListRequestStatus=new ArrayList<>();

                for (RequestStatus requestStatus:responseRequestStatus.getData()){

                    arrayListRequestStatus.add(requestStatus.getRequestStatus());
                }

               // Toast.makeText(getContext(), " "+arrayListRequestStatus.get(0), Toast.LENGTH_SHORT).show();
                arrayAdapterRequestStatus.addAll(arrayListRequestStatus);
                arrayAdapterRequestStatus.notifyDataSetChanged();

            }
        });

        showRequestViewModel.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicIdForParamedicLiveDate()
                .observe(getViewLifecycleOwner(), new Observer<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin>() {
                    @Override
                    public void onChanged(ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLogin responseRequestJoinDiseaseStatesJoinPatientJoinUserLogin) {

                       requestListAdapter.setRequestStatus(arrayListRequestStatus.get(spinnerRequestStatusParamedic.getSelectedItemPosition()));
                       requestListAdapter.setRequestJoinDiseaseStatesJoinPatientJoinUserLogins((ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLogin>) responseRequestJoinDiseaseStatesJoinPatientJoinUserLogin.getData());

                       // Toast.makeText(getContext(), ""+responseRequestJoinDiseaseStatesJoinPatientJoinUserLogin.getData().get(0).getPatientLongitude(), Toast.LENGTH_SHORT).show();
                    }
                });
        spinnerRequestStatusParamedic.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                showRequestViewModel.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicIdForParamedic(arrayListRequestStatus.get(position),getArguments().getInt("paramedic_id"));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        showRequestViewModel.updateRequestStatusLiveData().observe(getViewLifecycleOwner(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")) {
                    showRequestViewModel.
                            getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicIdForParamedic(arrayListRequestStatus
                                    .get(spinnerRequestStatusParamedic
                                            .getSelectedItemPosition()), getArguments().getInt("paramedic_id"));}
            }
        });



    }





    }







