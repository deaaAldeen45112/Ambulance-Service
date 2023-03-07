package com.example.paramedic.ui.ambulance.paramedicSelection;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.paramedic.R;
import com.example.paramedic.model.paramedic.Paramedic;
import com.example.paramedic.model.paramedic.ParamedicJoinUserlogin;
import com.example.paramedic.model.paramedic.ResponseParamedicJoinUserlogin;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLogin;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;
import com.example.paramedic.ui.ambulance.AmbulanceActivity;
import com.example.paramedic.ui.ambulance.AmbulanceViewModel;
import com.example.paramedic.ui.volunteerParamedic.VolunteerParamedicActivity;
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
import java.util.Timer;
import java.util.TimerTask;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ParamedicSelectionActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance request;
    ParamedicSelectionViewModel paramedicSelectionViewModel;
    ArrayList<ParamedicJoinUserlogin>paramedicJoinUserlogins=new ArrayList<ParamedicJoinUserlogin>();
    private int index=-1;
    private boolean state;
    private Timer timer;
    Marker markerPatient;
    double paramedicLatSelection;
    double paramedicLngSelection;
    String paramedicStatus="yes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        request=(RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance) getIntent().getSerializableExtra("request_object");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        paramedicSelectionViewModel = new ViewModelProvider(this).get(ParamedicSelectionViewModel.class);




        paramedicSelectionViewModel.getParamedicsJoinUserLoginLiveData()
                .observe(this, new Observer<ResponseParamedicJoinUserlogin>() {
                    @Override
                    public void onChanged(ResponseParamedicJoinUserlogin responseParamedicJoinUserlogin) {

                        paramedicJoinUserlogins= (ArrayList<ParamedicJoinUserlogin>) responseParamedicJoinUserlogin.getData();
                        doSomething();
                    }
                });



        Button buttonConfirm=findViewById(R.id.button_confirm);

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state ==true){



                    Request updateRequest=new Request();
                    updateRequest.setRequestId(request.getRequestId());
                    updateRequest.setRequestStatus("assigned paramedic");
                    updateRequest.setParamedicId(paramedicJoinUserlogins.get(index).getParamedicId());
                    paramedicSelectionViewModel.updateParamedicIdAndRequestStatusByRequestId(updateRequest);

                    Paramedic paramedic=new Paramedic();
                    paramedic.setParamedicId(paramedicJoinUserlogins.get(index).getParamedicId());
                    paramedic.setParamedicStatus("no");
                    paramedicSelectionViewModel.updateStatusByParamedicId(paramedic);
                    finish();
                }
            }
        });
        mapFragment.getMapAsync(this);






    }
    public class NewsletterTask extends TimerTask {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {
            paramedicSelectionViewModel.getParamedicsJoinUserLogin(paramedicStatus);

        }
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
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {


                state=false;
                for (int i = 0; i <paramedicJoinUserlogins.size() ; i++) {

                    ParamedicJoinUserlogin paramedic=paramedicJoinUserlogins.get(i);
                    double paramedicLat=Double.parseDouble(paramedic.getParamedicLatitude());
                    double paramedicLng=Double.parseDouble(paramedic.getParamedicLongitude());

                    if(marker.getPosition().latitude==paramedicLat&&marker.getPosition().longitude==paramedicLng){

                        paramedicLatSelection=paramedicLat;
                        paramedicLngSelection=paramedicLng;
                        index=i;
                        state=true;
                        marker.showInfoWindow();
                    }





                }


                return true;
            }
        });
        timer = new Timer();
        timer.scheduleAtFixedRate(new NewsletterTask(), 0, 4000);

    }


    void doSomething(){
       addMarkerForPatient();
       addMarkerForParamedic();



    }

    private void addMarkerForPatient() {
        double patientLat= Double.parseDouble(request.getPatientLatitude());
        double patientLng= Double.parseDouble(request.getPatientLongitude());

        IconGenerator iconFactory = new IconGenerator(this);

        LatLng patientLocation = new LatLng(patientLat, patientLng);
        Marker markerPatient = mMap
                .addMarker(new MarkerOptions()
                        .position(patientLocation)
//                        .title("patient name:")
//                        .snippet(""+request.getPatientName()+"")
                );
        markerPatient.setIcon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon("patient")));
        markerPatient.showInfoWindow();


    }

    private void addMarkerForParamedic() {
        for (int i = 0; i <paramedicJoinUserlogins.size() ; i++) {

            ParamedicJoinUserlogin paramedic=paramedicJoinUserlogins.get(i);
            double paramedicLat=Double.parseDouble(paramedic.getParamedicLatitude());
            double paramedicLng=Double.parseDouble(paramedic.getParamedicLongitude());

            LatLng paramedicLocation = new LatLng(paramedicLat, paramedicLng);
            Marker marker= mMap.addMarker(new MarkerOptions()
                    .position(paramedicLocation)
                    .title("paramedic name : "+paramedic.getFullName())
                    .snippet(paramedic.getPhone()));

            if(paramedicLatSelection==paramedicLat&&paramedicLngSelection==paramedicLng){


                index=i;
                marker.showInfoWindow();
            }


        }
    }

    @Override
    protected void onStop() {
        timer.cancel();
        super.onStop();
    }


}