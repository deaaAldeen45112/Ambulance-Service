package com.example.paramedic.ui.patient.showRequestPatient;

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
import com.example.paramedic.adapter.RequestPatientListAdapter;
import com.example.paramedic.model.patient.Patient;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient;
import com.example.paramedic.model.request.RequestStatus;
import com.example.paramedic.model.request.ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient;
import com.example.paramedic.model.request.ResponseRequestStatus;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowRequestPatientFragment extends Fragment {
    ShowRequestPatientViewModel showRequestPatientViewModel;
    Patient patient=null;
    RequestPatientListAdapter requestListAdapter;
    RecyclerView recyclerViewRequests;
    ArrayList<String>arrayListRequestStatus;




    public static ShowRequestPatientFragment newInstance(int patientId) {


        ShowRequestPatientFragment showRequestPatientFragment=new ShowRequestPatientFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("patient_id",patientId);
        showRequestPatientFragment.setArguments(bundle);
        return showRequestPatientFragment;
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        showRequestPatientViewModel= new ViewModelProvider(this).get(ShowRequestPatientViewModel.class);

        return inflater.inflate(R.layout.fragment_show_request_patient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        requestListAdapter=new RequestPatientListAdapter(getContext(), R.layout.recyclerview_item_request_patient, new ArrayList<>(), new RequestPatientListAdapter.IStartActivityListner() {
            @Override
            public void onClick(Request request) {


            }
        });




        ArrayAdapter arrayAdapterRequestStatus=new ArrayAdapter(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        recyclerViewRequests=view.findViewById(R.id.recycle_view_requests_patient);
        Spinner spinnerRequestStatusPatient =view.findViewById(R.id.spinner_request_status_patient);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());

        recyclerViewRequests.setHasFixedSize(false);
        recyclerViewRequests.setLayoutManager(layoutManager);
        recyclerViewRequests.setAdapter(requestListAdapter);

        spinnerRequestStatusPatient.setAdapter(arrayAdapterRequestStatus);
        showRequestPatientViewModel.getDistinctRequestStatus();
        showRequestPatientViewModel.getDistinctRequestStatusLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseRequestStatus>() {
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

        showRequestPatientViewModel.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndPatientIdLiveDate()
                .observe(getViewLifecycleOwner(), new Observer<ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient>() {
                    @Override
                    public void onChanged(ResponseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient responseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient) {

                        requestListAdapter.setRequestStatus(arrayListRequestStatus.get(spinnerRequestStatusPatient.getSelectedItemPosition()));
                        requestListAdapter.setRequestJoinDiseaseStatesJoinPatientJoinUserLogins((ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient>) responseRequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient.getData());

                        // Toast.makeText(getContext(), ""+responseRequestJoinDiseaseStatesJoinPatientJoinUserLogin.getData().get(0).getPatientLongitude(), Toast.LENGTH_SHORT).show();
                    }
                });
        spinnerRequestStatusPatient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                showRequestPatientViewModel.getRequestsJoinDiseaseStatesJoinPatientJoinUserLoginByRequestStatusAndPatientId(arrayListRequestStatus.get(position),getArguments().getInt("patient_id"));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

       


    }





}







