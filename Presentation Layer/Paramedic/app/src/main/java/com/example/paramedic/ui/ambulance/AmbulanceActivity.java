package com.example.paramedic.ui.ambulance;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paramedic.R;
import com.example.paramedic.adapter.CustomAdapter;
import com.example.paramedic.model.ambulance.Ambulance;
import com.example.paramedic.model.ambulance.ResponseAmbulance;
import com.example.paramedic.model.exbandableListView.ChildInfo;
import com.example.paramedic.model.exbandableListView.GroupInfo;
import com.example.paramedic.ui.ambulance.showRequest.ShowRequestAmbulanceFragment;
import com.example.paramedic.ui.ambulance.showRequestByTheTwoFilter.ShowRequestAmbulanceTwoFilterFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AmbulanceActivity extends AppCompatActivity {
    AmbulanceViewModel ambulanceViewModel;
    Ambulance ambulance=null;

    RecyclerView recyclerViewRequests;
    private AppBarConfiguration mAppBarConfiguration;
    private HashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();
    private CustomAdapter listAdapter;
    private ExpandableListView simpleExpandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);




        SharedPreferences sharedPref =getSharedPreferences("sharedParamedicApp",MODE_PRIVATE);
        int userlogin=sharedPref.getInt("userlogin_Id",0);

        String userloginName=sharedPref.getString("full_name","");


        ambulanceViewModel = new ViewModelProvider(this).get(AmbulanceViewModel.class);
        ambulanceViewModel.getAmbulanceByUserLoginId(userlogin);
        ambulanceViewModel.getAmbulanceIdByUserLoginLiveDate().observe(this, new Observer<ResponseAmbulance>() {
            @Override
            public void onChanged(ResponseAmbulance responseAmbulance) {

                SharedPreferences.Editor editor = sharedPref.edit();
                ambulance=responseAmbulance.getData().get(0);
                editor.putInt("ambulance_id",ambulance.getAmbulanceId());
                selectItemFromNav("show Request","");

            }
        });



        setSupportActionBar(findViewById(R.id.toolbar));
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        Toolbar toolbar= findViewById(R.id.toolbar);
        toolbar.setTitle("Ambulance");
        setSupportActionBar(toolbar);
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

        if(groupName.compareTo("show Request")==0){

            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, ShowRequestAmbulanceFragment.newInstance(ambulance.getAmbulanceId())).commit();


        }

        if(groupName.compareTo("Show Request For each Paramedic")==0){

            fragmentTransaction.replace(R.id.nav_host_fragment_content_main, ShowRequestAmbulanceTwoFilterFragment.newInstance(ambulance.getAmbulanceId())).commit();

        }
//        if(groupName.compareTo("Teacher")==0&&childName.compareTo("delete")==0){
//
//            DeleteTeacher deleteTeacher=new DeleteTeacher();
//            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,deleteTeacher).commit();
//        }
//        if(groupName.compareTo("Teacher")==0&&childName.compareTo("update")==0){
//
//            UpdateTeacher updateTeacher=new UpdateTeacher();
//            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,updateTeacher).commit();
//        }
//        if(groupName.compareTo("Class")==0&&childName.compareTo("insert")==0){
//
//            InsertClass insertClass=new InsertClass();
//            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,insertClass).commit();
//        }
//        if(groupName.compareTo("Class")==0&&childName.compareTo("update")==0){
//
//            UpdateClass updateClass=new UpdateClass();
//            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,updateClass).commit();
//        }
//        if(groupName.compareTo("Class")==0&&childName.compareTo("delete")==0){
//
//            DeleteClass deleteClass=new DeleteClass();
//            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,deleteClass).commit();
//
//        }
//
//        if(groupName.compareTo("Student")==0&&childName.compareTo("insert")==0){
//
//            InsertStudent insertStudent=new InsertStudent();
//            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,insertStudent).commit();
//
//        }
//        if(groupName.compareTo("Student")==0&&childName.compareTo("delete")==0){
//
//            DeleteStudent deleteStudent=new DeleteStudent();
//            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,deleteStudent).commit();
//
//        }
//
//        if(groupName.compareTo("Student")==0&&childName.compareTo("update")==0){
//
//            UpdateStudent updateStudent=new UpdateStudent();
//            fragmentTransaction.replace(R.id.nav_host_fragment_content_main,updateStudent).commit();
//
//        }


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
        addGroup("show Request");
        addGroup("Show Request For each Paramedic");
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


//    @Override
//    protected void onDestroy() {
//        Toast.makeText(this, "des", Toast.LENGTH_SHORT).show();
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onStop() {
//        Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
//        super.onStop();
//    }
//
//    @Override
//    protected void onPause() {
//        Toast.makeText(this, "pause", Toast.LENGTH_SHORT).show();
//        super.onPause();
//    }
//
//    @Override
//    protected void onStart() {
//        Toast.makeText(this, "start", Toast.LENGTH_SHORT).show();
//        super.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        Toast.makeText(this, "resume", Toast.LENGTH_SHORT).show();
//        super.onResume();
//    }
}