package com.example.paramedic.ui.ambulance.showLocationOfPatient;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.paramedic.R;
import com.example.paramedic.model.paramedic.Paramedic;
import com.example.paramedic.model.paramedic.ParamedicJoinUserlogin;
import com.example.paramedic.model.paramedic.ResponseParamedicJoinUserlogin;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;
import com.example.paramedic.ui.ambulance.paramedicSelection.ParamedicSelectionViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowLocationOfPatientActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String patientLng="";
    String patientLat="";
    ArrayList<ParamedicJoinUserlogin>paramedicJoinUserlogins=new ArrayList<ParamedicJoinUserlogin>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        patientLng=(String) getIntent().getStringExtra("patient_lng");
        patientLat=(String) getIntent().getStringExtra("patient_lat");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


        Button buttonConfirm=findViewById(R.id.button_confirm);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;
        doSomething();
    }


    void doSomething(){

        double patientLat= Double.parseDouble(this.patientLat);
        double patientLng= Double.parseDouble(this.patientLng);

        IconGenerator iconFactory = new IconGenerator(this);

        LatLng patientLocation = new LatLng(patientLat, patientLng);
        Marker markerPatient = mMap
               .addMarker(new MarkerOptions()
                       .position(patientLocation)

               );
        markerPatient.setIcon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("patient")));
        markerPatient.showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLng(patientLocation));




    }

}