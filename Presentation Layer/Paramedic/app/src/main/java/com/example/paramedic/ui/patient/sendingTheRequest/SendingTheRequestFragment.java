package com.example.paramedic.ui.patient.sendingTheRequest;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paramedic.R;
import com.example.paramedic.model.dieseaseStates.DiseaseStates;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;
import com.example.paramedic.model.patient.Patient;
import com.example.paramedic.model.request.Request;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SendingTheRequestFragment extends Fragment {
    SendingTheRequestViewModel sendingTheRequestViewModel;
    Patient patient=null;
    private TextView AddressText;
    private Button LocationButton;
    Spinner spinnerDiseaseStates;
    private LocationRequest locationRequest;
    double latitude;
    double longitude;
    List<DiseaseStates> diseaseStates=new ArrayList<>();


    public static SendingTheRequestFragment newInstance(int patientId) {


        SendingTheRequestFragment sendingTheRequestFragment=new SendingTheRequestFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("patient_id",patientId);
        sendingTheRequestFragment.setArguments(bundle);
        return sendingTheRequestFragment;
    }




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        sendingTheRequestViewModel = new ViewModelProvider(this).get(SendingTheRequestViewModel.class);
        return inflater.inflate(R.layout.activity_patient, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerDiseaseStates=view.findViewById(R.id.spinnerDiseaseStates);
        ArrayAdapter arrayAdapterDiseaseStates=new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item);
        spinnerDiseaseStates.setAdapter(arrayAdapterDiseaseStates);

        Button buttonRequest=view.findViewById(R.id.buttonRequest);

        sendingTheRequestViewModel.getDiseaseStates();
        sendingTheRequestViewModel.getDiseaseStatesLiveDate().observe(getViewLifecycleOwner(), new Observer<ResponseDiseaseStates>() {
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


        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getCurrentLocation();
           }
        });

        //AddressText = view.findViewById(R.id.addressText);
       // LocationButton = view.findViewById(R.id.locationButton);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

//        LocationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });



    }


    private void callRequest(){



        Request request=new Request();
        request.setRequestDescribed("good");
        request.setRequestStatus("waiting");
        request.setDiseaseStateId(diseaseStates.get(spinnerDiseaseStates.getSelectedItemPosition()).getDiseaseStatesId());
        request.setPatientId(getArguments().getInt("patient_id"));
        request.setParamedicId(0);
        request.setPatientLatitude(String.valueOf(latitude));
        request.setPatientLongitude(String.valueOf(longitude));

        sendingTheRequestViewModel.insertRequest(request);
        Toast.makeText(getContext(), "done successfully", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                if (isGPSEnabled()) {

                    getCurrentLocation();

                }else {

                    turnOnGPS();
                }
            }
        }


    }
    private void getCurrentLocation() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(getContext())
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(getActivity())
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() >0){

                                        int index = locationResult.getLocations().size() - 1;
                                        latitude = locationResult.getLocations().get(index).getLatitude();
                                        longitude = locationResult.getLocations().get(index).getLongitude();
                                        Log.d("tag", ("Latitude: "+ latitude + "\n" + "Longitude: "+ longitude));
                                        callRequest();
                                      //  AddressText.setText("Latitude: "+ latitude + "\n" + "Longitude: "+ longitude);
                                    }
                                }
                            }, Looper.getMainLooper());

                } else {
                    turnOnGPS();
                }

            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }
    private void turnOnGPS() {



        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(getContext(), "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(getActivity(), 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }
    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }




}
