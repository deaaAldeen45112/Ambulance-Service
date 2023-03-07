package com.example.paramedic.ui.ambulance.showRequest;

import android.content.Intent;
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
import com.example.paramedic.adapter.RequestAmbulanceListAdapter;
import com.example.paramedic.model.ambulance.Ambulance;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLogin;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient;
import com.example.paramedic.model.request.RequestStatus;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient;
import com.example.paramedic.model.request.ResponseRequestStatus;
import com.example.paramedic.ui.ambulance.paramedicSelection.ParamedicSelectionActivity;
import com.example.paramedic.ui.ambulance.showLocationOfPatient.ShowLocationOfPatientActivity;

import java.io.Serializable;
import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowRequestAmbulanceFragment extends Fragment {
    ShowRequestAmbulanceViewModel showRequestAmbulanceViewModel;
    Ambulance ambulance=null;
    RequestAmbulanceListAdapter requestListAdapter;
    RecyclerView recyclerViewRequests;
    ArrayList<String>arrayListRequestStatus;




    public static ShowRequestAmbulanceFragment newInstance(int ambulanceId) {

        ShowRequestAmbulanceFragment showRequestAmbulanceFragment =new ShowRequestAmbulanceFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("ambulance_id",ambulanceId);
        showRequestAmbulanceFragment.setArguments(bundle);
        return showRequestAmbulanceFragment;
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        showRequestAmbulanceViewModel = new ViewModelProvider(this).get(ShowRequestAmbulanceViewModel.class);

        return inflater.inflate(R.layout.activity_ambulance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getContext(), "cr fr", Toast.LENGTH_SHORT).show();


        requestListAdapter=new RequestAmbulanceListAdapter(getContext(), R.layout.listview_item_request, new ArrayList<>(), new RequestAmbulanceListAdapter.IStartActivityListner() {
            @Override
            public void onClickSelectParamedic(RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance request) {
                Intent intent=new Intent(getContext(), ParamedicSelectionActivity.class);
                intent.putExtra("request_object",(Serializable) request);
                startActivity(intent);
            }

            @Override
            public void onClickConfirmAmbulance(Request request) {

                showRequestAmbulanceViewModel.updateRequestStatusByRequestId(request);

            }

            @Override
            public void onClickShowLocationOfPatient(String lng, String lat) {

                Intent intent=new Intent(getContext(), ShowLocationOfPatientActivity.class);
                intent.putExtra("patient_lng",lng);
                intent.putExtra("patient_lat",lat);
                startActivity(intent);
            }

            @Override
            public void onClickFinish(Request request) {
                showRequestAmbulanceViewModel.updateRequestStatusByRequestId(request);
            }
        });


                ArrayAdapter arrayAdapterRequestStatus = new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        recyclerViewRequests=view.findViewById(R.id.recycle_view_requests_ambulance);
        Spinner spinnerRequestStatusPatient =view.findViewById(R.id.spinner_request_status_ambulance);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());

        recyclerViewRequests.setHasFixedSize(false);
        recyclerViewRequests.setLayoutManager(layoutManager);
        recyclerViewRequests.setAdapter(requestListAdapter);




       showRequestAmbulanceViewModel.getDistinctRequestStatusLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseRequestStatus>() {
            @Override
            public void onChanged(ResponseRequestStatus responseRequestStatus) {

                arrayAdapterRequestStatus.clear();
                arrayListRequestStatus=new ArrayList<>();

                for (RequestStatus requestStatus:responseRequestStatus.getData()){

                    arrayListRequestStatus.add(requestStatus.getRequestStatus());
                }

                // Toast.makeText(getContext(), " "+arrayListRequestStatus.get(0), Toast.LENGTH_SHORT).show();
                arrayAdapterRequestStatus.addAll(arrayListRequestStatus);
                spinnerRequestStatusPatient.setAdapter(arrayAdapterRequestStatus);

            }
        });

        showRequestAmbulanceViewModel.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusLiveDate()
                .observe(getViewLifecycleOwner(), new Observer<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance>() {
                    @Override
                    public void onChanged(ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance responseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance) {

                        requestListAdapter.setRequestStatus(arrayListRequestStatus.get(spinnerRequestStatusPatient.getSelectedItemPosition()));
                        requestListAdapter.setRequestJoinDiseaseStatesJoinPatientJoinUserLogins((ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance>) responseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance.getData());

                        // Toast.makeText(getContext(), ""+responseRequestJoinDiseaseStatesJoinPatientJoinUserLogin.getData().get(0).getPatientLongitude(), Toast.LENGTH_SHORT).show();
                    }
                });
        spinnerRequestStatusPatient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                 showRequestAmbulanceViewModel.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatus(arrayListRequestStatus.get(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






    }


    @Override
    public void onStart() {
        showRequestAmbulanceViewModel.getDistinctRequestStatus();
        super.onStart();

    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        Toast.makeText(getContext(), "stop fr", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(getContext(), "des fr", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        Toast.makeText(getContext(), "deat fr", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        Toast.makeText(getContext(), "res fr", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        Toast.makeText(getContext(), "pause fr", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        Toast.makeText(getContext(), "create fr", Toast.LENGTH_SHORT).show();
//        super.onCreate(savedInstanceState);
//
//    }
}







