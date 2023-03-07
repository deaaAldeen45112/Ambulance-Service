package com.example.paramedic.ui.admin.crud.diseaseStates.delete;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
public class DeleteDiseaseStateFragment extends Fragment {
    DeleteDiseaseStateViewModel deleteDiseaseStateViewModel ;
    List<DiseaseStates> diseaseStates=new ArrayList<>();
    
    public static DeleteDiseaseStateFragment newInstance() {


        DeleteDiseaseStateFragment readDiseaseStateFragment=new DeleteDiseaseStateFragment();
        return readDiseaseStateFragment;
    }
    

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        deleteDiseaseStateViewModel = new ViewModelProvider(this).get(DeleteDiseaseStateViewModel.class);
        return inflater.inflate(R.layout.fragment_delete_disease_states, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Spinner spinnerDiseaseStates=view.findViewById(R.id.spinner_disease_states);
        Button  deleteButton=view.findViewById(R.id.button_delete);

        ArrayAdapter arrayAdapterDiseaseStates=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item);
        spinnerDiseaseStates.setAdapter(arrayAdapterDiseaseStates);
        
        deleteDiseaseStateViewModel.getDiseaseStates();
        deleteDiseaseStateViewModel.getDiseaseStatesLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseDiseaseStates>() {
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

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDiseaseStateViewModel.
                        deleteDiseaseStates
                                (diseaseStates.get
                                        (spinnerDiseaseStates.
                                                getSelectedItemPosition()).
                                        getDiseaseStatesId());
            }
        });
 


    }



   
 




}
