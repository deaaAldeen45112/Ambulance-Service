package com.example.paramedic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paramedic.R;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;

import java.util.ArrayList;

public class RequestAmbulanceTwoFilterListAdapter extends RecyclerView.Adapter<RequestAmbulanceTwoFilterListAdapter.RequestViewHolder> {
    private Context context;
    private int resource;
    private ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance>requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulances;
    private IStartActivityListner listner;
    private String requestStatus;


    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setRequestJoinDiseaseStatesJoinPatientJoinUserLogins(ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance> requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulances) {

        this.requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulances = requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulances;
        notifyDataSetChanged();
    }

    public RequestAmbulanceTwoFilterListAdapter(Context context, int resource, ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance>requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulances, IStartActivityListner listner)
    {
        this.context=context;
        this.resource=resource;
        this.requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulances=requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulances;
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
        RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance request= requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulances.get(position);
        //holder.textViewPatientAge.setText("age : "+request.g());
        holder.textViewPatientName.setText("name : "+request.getPatientName());
        holder.textViewDiseaseStateName.setText("disease State Name : "+request.getDiseaseStateName());
        holder.textViewPatientPhone.setText("phone : "+request.getPatientPhone());
        holder.textviewRequestDescribed.setText("request described "+request.getRequestDescribed());
        holder.textviewRequestStatus.setText("request status : "+request.getRequestStatus());





    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulances.size();
    }


    public interface IStartActivityListner{
        void onClick(RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance request);

    }

    class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPatientAge;
        TextView textViewPatientPhone;
        TextView textViewPatientName;
        TextView textViewDiseaseStateName;
        TextView textviewRequestStatus;
        TextView textviewRequestDescribed;

        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);


            //textViewPatientAge=itemView.findViewById(R.id.textview_patient_age);
            textViewDiseaseStateName=itemView.findViewById(R.id.textview_disease_state_name);
            textViewPatientName=itemView.findViewById(R.id.textview_patient_name);
            textViewPatientPhone=itemView.findViewById(R.id.textview_patient_phone);
            textviewRequestStatus=itemView.findViewById(R.id.textview_request_status);
            textviewRequestDescribed=itemView.findViewById(R.id.textview_request_described);

        }
    }

}