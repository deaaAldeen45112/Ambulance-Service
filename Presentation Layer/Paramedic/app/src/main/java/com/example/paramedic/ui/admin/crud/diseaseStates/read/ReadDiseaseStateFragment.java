package com.example.paramedic.ui.admin.crud.diseaseStates.read;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paramedic.R;
import com.example.paramedic.model.dieseaseStates.DiseaseStates;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReadDiseaseStateFragment extends Fragment {
    ReadDiseaseStateViewModel readDiseaseStateViewModel ;
    List<DiseaseStates> diseaseStates=new ArrayList<>();
    
    public static ReadDiseaseStateFragment newInstance() {


        ReadDiseaseStateFragment readDiseaseStateFragment=new ReadDiseaseStateFragment();
        return readDiseaseStateFragment;
    }
    

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        readDiseaseStateViewModel = new ViewModelProvider(this).get(ReadDiseaseStateViewModel.class);
        return inflater.inflate(R.layout.fragment_read_disease_states, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner spinnerDiseaseStates=view.findViewById(R.id.spinner_disease_states);
        ArrayAdapter arrayAdapterDiseaseStates=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item);
        spinnerDiseaseStates.setAdapter(arrayAdapterDiseaseStates);
        
        readDiseaseStateViewModel.getDiseaseStates();
        readDiseaseStateViewModel.getDiseaseStatesLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseDiseaseStates>() {
            @Override
            public void onChanged(ResponseDiseaseStates responseDiseaseStates) {
                diseaseStates=responseDiseaseStates.getData();
                List<String>diseaseStatesName=new ArrayList<String>();
                for (DiseaseStates diseaseState:diseaseStates) {
                    diseaseStatesName.add(diseaseState.getDiseaseStatesName());
                }
                arrayAdapterDiseaseStates.clear();
                arrayAdapterDiseaseStates.addAll(diseaseStatesName);
                arrayAdapterDiseaseStates.notifyDataSetChanged();



            }
        });

   

 


    }



   
 




}
