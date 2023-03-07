package com.example.paramedic.ui.ambulance.showRequestByTheTwoFilter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paramedic.R;
import com.example.paramedic.adapter.RequestAmbulanceTwoFilterListAdapter;
import com.example.paramedic.model.ambulance.Ambulance;
import com.example.paramedic.model.paramedic.Paramedic;
import com.example.paramedic.model.paramedic.ResponseParamedic;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;
import com.example.paramedic.model.request.RequestStatus;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;
import com.example.paramedic.model.request.ResponseRequestStatus;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowRequestAmbulanceTwoFilterFragment extends Fragment {
    ShowRequestAmbulanceTwoFilterViewModel showRequestAmbulanceViewModel;
    Ambulance ambulance=null;
    RequestAmbulanceTwoFilterListAdapter requestListAdapter;
    RecyclerView recyclerViewRequests;
    ArrayList<String>arrayListRequestStatus;
    ArrayList<Integer>arrayListParamedicId=new ArrayList<>();




    public static ShowRequestAmbulanceTwoFilterFragment newInstance(int ambulanceId) {


        ShowRequestAmbulanceTwoFilterFragment showRequestAmbulanceFragment =new ShowRequestAmbulanceTwoFilterFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("ambulance_id",ambulanceId);
        showRequestAmbulanceFragment.setArguments(bundle);
        return showRequestAmbulanceFragment;
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        showRequestAmbulanceViewModel = new ViewModelProvider(this).get(ShowRequestAmbulanceTwoFilterViewModel.class);

        return inflater.inflate(R.layout.fragment_show_request_ambulance_by_two_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        requestListAdapter=new RequestAmbulanceTwoFilterListAdapter(getContext(), R.layout.recyclerview_item_request_ambulance_tow_filter, new ArrayList<>(), new RequestAmbulanceTwoFilterListAdapter.IStartActivityListner() {

            @Override
            public void onClick(RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance) {


            }
        });



        arrayListParamedicId.add(0);


        ArrayAdapter arrayAdapterRequestStatus=new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter arrayAdapterParamedicId=new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        recyclerViewRequests=view.findViewById(R.id.recycle_view_requests_ambulance);
        Spinner spinnerRequestStatusPatient =view.findViewById(R.id.spinner_request_status_ambulance);
        Spinner spinnerParamedicId=view.findViewById(R.id.spinner_paramedic_id);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());

        recyclerViewRequests.setHasFixedSize(false);
        recyclerViewRequests.setLayoutManager(layoutManager);
        recyclerViewRequests.setAdapter(requestListAdapter);


        spinnerParamedicId.setAdapter(arrayAdapterParamedicId);
        spinnerRequestStatusPatient.setAdapter(arrayAdapterRequestStatus);

        showRequestAmbulanceViewModel.getDistinctRequestStatus();
        showRequestAmbulanceViewModel.getDistinctRequestStatusLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseRequestStatus>() {
            @Override
            public void onChanged(ResponseRequestStatus responseRequestStatus) {

                arrayListRequestStatus=new ArrayList<>();

                for (RequestStatus requestStatus:responseRequestStatus.getData()){

                    if(!(requestStatus.getRequestStatus().equals("ambulance")||requestStatus.getRequestStatus().equals("waiting")))
                    arrayListRequestStatus.add(requestStatus.getRequestStatus());
                }


                // Toast.makeText(getContext(), " "+arrayListRequestStatus.get(0), Toast.LENGTH_SHORT).show();
                arrayAdapterRequestStatus.addAll(arrayListRequestStatus);
                arrayAdapterRequestStatus.notifyDataSetChanged();

            }
        });

        showRequestAmbulanceViewModel.getParamedic();
        showRequestAmbulanceViewModel.getParamedicLiveData().observe(getViewLifecycleOwner(), new Observer<ResponseParamedic>() {
            @Override
            public void onChanged(ResponseParamedic responseParamedic) {

                for (Paramedic paramedic:responseParamedic.getData()){

                    arrayListParamedicId.add(paramedic.getParamedicId());
                }

                // Toast.makeText(getContext(), " "+arrayListRequestStatus.get(0), Toast.LENGTH_SHORT).show();
                arrayAdapterParamedicId.addAll(arrayListParamedicId);
                arrayAdapterParamedicId.notifyDataSetChanged();


            }
        });


        showRequestAmbulanceViewModel.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicIdLiveData()
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


                if(spinnerParamedicId.getSelectedItemPosition()>-1){

                    showRequestAmbulanceViewModel.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId(arrayListRequestStatus.get(position),arrayListParamedicId.get(spinnerParamedicId.getSelectedItemPosition()));

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerParamedicId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

              if(spinnerRequestStatusPatient.getSelectedItemPosition()>-1) {
                  showRequestAmbulanceViewModel.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndParamedicId
                          (arrayListRequestStatus.get(spinnerRequestStatusPatient.getSelectedItemPosition())
                                  , arrayListParamedicId.get(position));
              }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }


    }







