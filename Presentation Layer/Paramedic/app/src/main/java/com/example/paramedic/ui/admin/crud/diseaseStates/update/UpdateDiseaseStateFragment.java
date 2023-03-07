package com.example.paramedic.ui.admin.crud.diseaseStates.update;

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
import com.example.paramedic.ui.admin.crud.diseaseStates.insert.InsertDiseaseStateViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class UpdateDiseaseStateFragment extends Fragment {
    UpdateDiseaseStateViewModel updateDiseaseStateViewModel ;
    private List<DiseaseStates> diseaseStates;

    public static UpdateDiseaseStateFragment newInstance() {


        UpdateDiseaseStateFragment updateDiseaseStateFragment=new UpdateDiseaseStateFragment();
        return updateDiseaseStateFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        updateDiseaseStateViewModel = new ViewModelProvider(this).get(UpdateDiseaseStateViewModel.class);
        return inflater.inflate(R.layout.fragment_update_disease_states, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        EditText editTextDiseaseStateName=view.findViewById(R.id.editText_disease_state_name);
        Button buttonUpdateDiseaseState=view.findViewById(R.id.button_update_disease_state);
        Spinner spinnerDiseaseStates=view.findViewById(R.id.spinner_disease_states);
        ArrayAdapter arrayAdapterDiseaseStates=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item);
        spinnerDiseaseStates.setAdapter(arrayAdapterDiseaseStates);

        updateDiseaseStateViewModel.getDiseaseStates();
        updateDiseaseStateViewModel.getDiseaseStatesLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseDiseaseStates>() {
            @Override
            public void onChanged(ResponseDiseaseStates responseDiseaseStates) {
                diseaseStates=responseDiseaseStates.getData();
                List<String> diseaseStatesName=new ArrayList<String>();
                for (DiseaseStates diseaseState:diseaseStates) {
                    diseaseStatesName.add(diseaseState.getDiseaseStatesName());
                }
                arrayAdapterDiseaseStates.clear();
                arrayAdapterDiseaseStates.addAll(diseaseStatesName);
                arrayAdapterDiseaseStates.notifyDataSetChanged();



            }
        });

        buttonUpdateDiseaseState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DiseaseStates diseaseState=new DiseaseStates();
                String diseaseStateName=editTextDiseaseStateName.getText().toString();
                diseaseState.setDiseaseStatesName(diseaseStateName);
                diseaseState.setDiseaseStatesId(diseaseStates.get(spinnerDiseaseStates
                                .getSelectedItemPosition())
                        .getDiseaseStatesId());

                updateDiseaseStateViewModel.updateDiseaseStates(diseaseState);

            }
        });


   

 


    }



   
 




}
