package com.example.paramedic.ui.patient;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paramedic.MainActivity;
import com.example.paramedic.R;
import com.example.paramedic.adapter.CustomAdapter;
import com.example.paramedic.model.dieseaseStates.DiseaseStates;
import com.example.paramedic.model.dieseaseStates.ResponseDiseaseStates;
import com.example.paramedic.model.exbandableListView.ChildInfo;
import com.example.paramedic.model.exbandableListView.GroupInfo;
import com.example.paramedic.model.paramedic.ResponseParamedic;
import com.example.paramedic.model.patient.Patient;
import com.example.paramedic.model.patient.ResponsePatient;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.ui.login.LoginViewModel;
import com.example.paramedic.ui.patient.sendingTheRequest.SendingTheRequestFragment;
import com.example.paramedic.ui.patient.sendingTheRequest.SendingTheRequestViewModel;
import com.example.paramedic.ui.patient.showRequestPatient.ShowRequestPatientFragment;
import com.example.paramedic.ui.volunteerParamedic.ParamedicViewModel;
import com.example.paramedic.ui.volunteerParamedic.showRequestParamedic.ShowRequestParamedicFragment;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PatientActivity extends AppCompatActivity {
    PatientViewModel patientViewModel;
    Patient patient=null;
    private HashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();
    private CustomAdapter listAdapter;
    private AppBarConfiguration mAppBarConfiguration;
    private ExpandableListView simpleExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);


        SharedPreferences sharedPref =getSharedPreferences("sharedParamedicApp",MODE_PRIVATE);
        int userlogin=sharedPref.getInt("userlogin_Id",0);
        String userloginName=sharedPref.getString("full_name","");

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        patientViewModel.getPatientByUserLoginId(userlogin);
        patientViewModel.getPatientIdByUserLoginLiveDate().observe(this, new Observer<ResponsePatient>() {
            @Override
            public void onChanged(ResponsePatient responsePatient) {
                patient=responsePatient.getData().get(0);
                selectItemFromNav("Sending The Request","");



            }
        });



        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("Patient");
        setSupportActionBar(toolbar);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        TextView textviewPatientNameNav=navigationView.findViewById(R.id.textview_patient_name_nav);

        textviewPatientNameNav.setText(userloginName);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
                ChildInfo detailInfo =  headerInfo.getProductList().get(childPosition);


                selectItemFromNav(headerInfo.getName(),detailInfo.getName());



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
                 selectItemFromNav(headerInfo.getName(),"");


                return false;
            }
        });


    }

    //select group item and child item
    private void selectItemFromNav(String groupName, String childName){

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();

        if(groupName.compareTo("Sending The Request")==0){

             fragmentTransaction.replace(R.id.nav_host_fragment_content_main, SendingTheRequestFragment.newInstance(patient.getPatientId())).commit();

        }

        if(groupName.compareTo("Show The Requests")==0){

             fragmentTransaction.replace(R.id.nav_host_fragment_content_main, ShowRequestPatientFragment.newInstance(patient.getPatientId())).commit();

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
        addGroup("Sending The Request");
        addGroup("Show The Requests");
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