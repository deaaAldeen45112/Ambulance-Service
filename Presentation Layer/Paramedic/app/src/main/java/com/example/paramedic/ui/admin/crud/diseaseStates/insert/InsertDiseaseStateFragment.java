package com.example.paramedic.ui.admin.crud.diseaseStates.insert;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.paramedic.ui.admin.crud.diseaseStates.read.ReadDiseaseStateViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InsertDiseaseStateFragment extends Fragment {
    InsertDiseaseStateViewModel insertDiseaseStateViewModel ;
    
    public static InsertDiseaseStateFragment newInstance() {


        InsertDiseaseStateFragment insertDiseaseStateFragment=new InsertDiseaseStateFragment();
        return insertDiseaseStateFragment;
    }
    

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        insertDiseaseStateViewModel = new ViewModelProvider(this).get(InsertDiseaseStateViewModel.class);
        return inflater.inflate(R.layout.fragment_insert_disease_states, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        EditText editTextDiseaseStateName=view.findViewById(R.id.editText_disease_state_name);
        Button buttonInsertDiseaseState=view.findViewById(R.id.button_insert_disease_state);

        buttonInsertDiseaseState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DiseaseStates diseaseStates=new DiseaseStates();
                String diseaseStateName=editTextDiseaseStateName.getText().toString();
                diseaseStates.setDiseaseStatesName(diseaseStateName);
                insertDiseaseStateViewModel.insertDiseaseStates(diseaseStates);

            }
        });


   

 


    }



   
 




}
