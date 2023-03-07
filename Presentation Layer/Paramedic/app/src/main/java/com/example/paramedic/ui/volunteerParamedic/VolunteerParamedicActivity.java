package com.example.paramedic.ui.volunteerParamedic;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paramedic.R;
import com.example.paramedic.adapter.CustomAdapter;
import com.example.paramedic.model.exbandableListView.ChildInfo;
import com.example.paramedic.model.exbandableListView.GroupInfo;
import com.example.paramedic.model.paramedic.Paramedic;
import com.example.paramedic.model.paramedic.ResponseParamedic;
import com.example.paramedic.ui.volunteerParamedic.showRequestParamedic.ShowRequestParamedicFragment;
import com.example.paramedic.ui.volunteerParamedic.updateMyStatus.UpdateStatusParamedicFragment;
import com.example.paramedic.ui.volunteerParamedic.updateMyStatus.UpdateStatusParamedicViewModel;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Timer;
import java.util.TimerTask;

import dagger.hilt.android.AndroidEntryPoint;

import androidx.lifecycle.Observer;

@AndroidEntryPoint
    public class VolunteerParamedicActivity extends AppCompatActivity {
        ParamedicViewModel paramedicViewModel;
        Paramedic paramedic=null;
        private LocationRequest locationRequest;
        private AppBarConfiguration mAppBarConfiguration;
        private HashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
        private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();
        private CustomAdapter listAdapter;
        double latitude;
        double longitude;
        private Timer timer;
        private ExpandableListView simpleExpandableListView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.drawer_layout);


            SharedPreferences sharedPref = getSharedPreferences("sharedParamedicApp", MODE_PRIVATE);
            int userlogin = sharedPref.getInt("userlogin_Id", 0);

            String userloginName = sharedPref.getString("full_name", "");


            paramedicViewModel = new ViewModelProvider(this).get(ParamedicViewModel.class);
            paramedicViewModel.getParamedicByUserloginId(userlogin);
            paramedicViewModel.getParamedicIdByUserLoginLiveDate().observe(this, new Observer<ResponseParamedic>() {
                @Override
                public void onChanged(ResponseParamedic responseParamedic) {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    paramedic = responseParamedic.getData().get(0);
                    editor.putInt("paramedic_id", paramedic.getParamedicId());

                    selectItemFromNav("show requests", "");


                }
            });

            Toolbar toolbar = findViewById(R.id.toolbar);

            toolbar.setTitle("Paramedic");
            setSupportActionBar(toolbar);
            findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    finish();
                }
            });
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);

            TextView textviewPatientNameNav = navigationView.findViewById(R.id.textview_patient_name_nav);

            textviewPatientNameNav.setText(userloginName);
            // Passing each menu ID as a set of Ids because each
            // menu should be considered as top level destinations.
            ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(actionBarDrawerToggle);
            actionBarDrawerToggle.syncState();


            // add data for displaying in expandable list view
            loadData();

            //get reference of the ExpandableListView
            simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
            // create the adapter by passing your ArrayList data
            listAdapter = new CustomAdapter(this, deptList);
            // attach the adapter to the expandable list view
            simpleExpandableListView.setAdapter(listAdapter);

            //expand all the Groups


            // setOnChildClickListener listener for child row click
            simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    //get the group header
                    GroupInfo headerInfo = deptList.get(groupPosition);
                    //get the child info
                    ChildInfo detailInfo = headerInfo.getProductList().get(childPosition);


                    selectItemFromNav(headerInfo.getName(), detailInfo.getName());


                    //display it or do something with it
                     return false;
                }
            });
            // setOnGroupClickListener listener for group heading click
            simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    //get the group header
                    GroupInfo headerInfo = deptList.get(groupPosition);
                    //display it or do something with it
                    selectItemFromNav(headerInfo.getName(), "");


                    return false;
                }
            });




            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                     } else {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }

            }


            locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(5000);
            locationRequest.setFastestInterval(2000);

            timer = new Timer();
            timer.scheduleAtFixedRate(new NewsletterTask(), 0, 4000);



        }



    private void getCurrentLocation() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (isGPSEnabled()) {

                    LocationServices.getFusedLocationProviderClient(getBaseContext())
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(getBaseContext())
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() >0){

                                        int index = locationResult.getLocations().size() - 1;
                                        latitude = locationResult.getLocations().get(index).getLatitude();
                                        longitude = locationResult.getLocations().get(index).getLongitude();
                                        Log.d("tag", ("Latitude: "+ latitude + "\n" + "Longitude: "+ longitude));


                                        paramedic.setParamedicLongitude(String.valueOf(longitude));
                                        paramedic.setParamedicLatitude(String.valueOf(latitude));
                                        paramedicViewModel.updateLocationByParamedicId(paramedic);


                                        //  AddressText.setText("Latitude: "+ latitude + "\n" + "Longitude: "+ longitude);
                                    }
                                }
                            }, Looper.getMainLooper());

                } else {
                    turnOnGPS();
                }

            }
        }
    }
    private void turnOnGPS() {



        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getBaseContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);

                } catch (ApiException e) {


                }
            }
        });

    }
    private boolean isGPSEnabled() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager)getBaseContext().getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
        }


    }


    public class NewsletterTask extends TimerTask {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void run() {

            getCurrentLocation();
//
//           startService(new Intent(getBaseContext(),ServiceUpdateParamedicLocation.class));
//
//            System.out.println("Email sent at: "
//                    + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()),
//                    ZoneId.systemDefault()));
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
     }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        timer.cancel();
        super.onDestroy();
        }

    //select group item and child item
        private void selectItemFromNav(String groupName, String childName){

            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

            if(groupName.compareTo("show requests")==0){

                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, ShowRequestParamedicFragment.newInstance(paramedic.getParamedicId())).commit();

            }


            if(groupName.compareTo("update my status")==0){

                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, UpdateStatusParamedicFragment.newInstance(paramedic.getParamedicId())).commit();

            }

        }
        //method to expand all groups
        private void expandAll() {
            int count = listAdapter.getGroupCount();
            for (int i = 0; i < count; i++){
                simpleExpandableListView.expandGroup(i);
            }

               }
        //method to collapse all groups
        private void collapseAll() {
            int count = listAdapter.getGroupCount();
            for (int i = 0; i < count; i++){
                simpleExpandableListView.collapseGroup(i);
            }
        }
        //load some initial data into out list
        private void loadData(){


            //----------------Student-------------------------
            addGroup("show requests");
            addGroup("update my status");
//        addProduct("Student","delete");
//        addProduct("Student","update");
//        //----------------Teacher-------------------------
//        addProduct("Teacher","insert");
//        addProduct("Teacher","delete");
//        addProduct("Teacher","update");
//
//        //----------------Class-------------------------
//        addProduct("Class","insert");
//        addProduct("Class","delete");
//        addProduct("Class","update");
        }


        //here we maintain our products in various departments
        private void addGroup(String department) {

            int groupPosition = 0;

            //check the hash map if the group already exists
            GroupInfo headerInfo = subjects.get(department);
            //add the group if doesn't exists
            if (headerInfo == null) {
                headerInfo = new GroupInfo();
                headerInfo.setName(department);
                subjects.put(department, headerInfo);
                deptList.add(headerInfo);
            }
        }

        //here we maintain our products in various departments
        private int addProduct(String department, String product){

            int groupPosition = 0;

            //check the hash map if the group already exists
            GroupInfo headerInfo = subjects.get(department);
            //add the group if doesn't exists
            if(headerInfo == null){
                headerInfo = new GroupInfo();
                headerInfo.setName(department);
                subjects.put(department, headerInfo);
                deptList.add(headerInfo);
            }

            //get the children for the group
            ArrayList<ChildInfo> productList = headerInfo.getProductList();
            //size of the children list
            int listSize = productList.size();
            //add to the counter
            listSize++;

            //create a new child and add that to the group
            ChildInfo detailInfo = new ChildInfo();
            detailInfo.setSequence(String.valueOf(listSize));
            detailInfo.setName(product);
            productList.add(detailInfo);
            //find the group position inside the list
            groupPosition = deptList.indexOf(headerInfo);
            return groupPosition;
        }
        @Override
        public boolean onSupportNavigateUp() {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
        }
        
    }