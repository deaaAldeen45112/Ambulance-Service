package com.example.paramedic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paramedic.MapsActivity;
import com.example.paramedic.R;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLogin;

import java.util.ArrayList;
import java.util.List;

public class RequestParamedicListAdapter extends RecyclerView.Adapter<RequestParamedicListAdapter.RequestViewHolder> {
    private Context context;
    private int resource;
    private ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLogin>requestJoinDiseaseStatesJoinPatientJoinUserLogins;
    private IStartActivityListner listner;
    private String requestStatus;


    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setRequestJoinDiseaseStatesJoinPatientJoinUserLogins(ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLogin> requestJoinDiseaseStatesJoinPatientJoinUserLogins) {

        this.requestJoinDiseaseStatesJoinPatientJoinUserLogins = requestJoinDiseaseStatesJoinPatientJoinUserLogins;
        notifyDataSetChanged();
    }

    public RequestParamedicListAdapter(Context context, int resource, ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLogin>requestJoinDiseaseStatesJoinPatientJoinUserLogins,IStartActivityListner listner)
    {
        this.context=context;
        this.resource=resource;
        this.requestJoinDiseaseStatesJoinPatientJoinUserLogins=requestJoinDiseaseStatesJoinPatientJoinUserLogins;
        this.listner=listner;
    }


    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(resource,parent,false);
        RequestViewHolder requestViewHolder=new RequestViewHolder(view);
        return requestViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        RequestJoinDiseaseStatesJoinPatientJoinUserLogin request= requestJoinDiseaseStatesJoinPatientJoinUserLogins.get(position);
        holder.textViewPatientAge.setText("patient age : "+request.getUserLoginAge());
        holder.textViewPatientName.setText("patient name : "+request.getUserLoginName());
        holder.textViewDiseaseStateName.setText("disease State Name : "+request.getDiseaseStateName());
        holder.textViewPatientPhone.setText("patient phone : "+request.getUserLoginPhone());
        holder.textviewRequestDescribed.setText("request described "+request.getRequestDescribed());
        holder.textviewRequestStatus.setText("request status : "+request.getRequestStatus());

        if(requestStatus.equals("assigned paramedic")) {
            holder.buttonFinish.setVisibility(View.VISIBLE);
        }
        else {
            holder.buttonFinish.setVisibility(View.GONE);
        }
        holder.buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request updateRequest=new Request();
                updateRequest.setRequestId(request.getRequestId());
                updateRequest.setRequestStatus("success");

                listner.onClick(updateRequest);

            }
        });



    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return requestJoinDiseaseStatesJoinPatientJoinUserLogins.size();
    }


    public interface IStartActivityListner{
        void onClick(Request request);

    }

    class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPatientAge;
        TextView textViewPatientPhone;
        TextView textViewPatientName;
        TextView textViewDiseaseStateName;
        TextView textviewRequestStatus;
        TextView textviewRequestDescribed;
        Button   buttonFinish;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);


            textViewPatientAge=itemView.findViewById(R.id.textview_patient_age);
            textViewDiseaseStateName=itemView.findViewById(R.id.textview_disease_state_name);
            textViewPatientName=itemView.findViewById(R.id.textview_patient_name);
            textViewPatientPhone=itemView.findViewById(R.id.textview_patient_phone);
            textviewRequestStatus=itemView.findViewById(R.id.textview_request_status);
            textviewRequestDescribed=itemView.findViewById(R.id.textview_request_described);
            buttonFinish=itemView.findViewById(R.id.button_finish);
        }
    }

}