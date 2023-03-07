package com.example.paramedic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paramedic.R;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient;

import java.util.ArrayList;

public class RequestPatientListAdapter extends RecyclerView.Adapter<RequestPatientListAdapter.RequestViewHolder> {
    private Context context;
    private int resource;
    private ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient>
            requestJoinDiseaseStatesJoinPatientJoinUserLoginForPatients;
    private IStartActivityListner listner;
    private String requestStatus;


    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public void setRequestJoinDiseaseStatesJoinPatientJoinUserLogins(
            ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient>
                    requestJoinDiseaseStatesJoinPatientJoinUserLoginForPatients) {

        this.requestJoinDiseaseStatesJoinPatientJoinUserLoginForPatients = requestJoinDiseaseStatesJoinPatientJoinUserLoginForPatients;
        notifyDataSetChanged();
    }

    public RequestPatientListAdapter(Context context, int resource,
                                     ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient>
                                             requestJoinDiseaseStatesJoinPatientJoinUserLoginForPatients,
                                                   IStartActivityListner listner)
    {
        this.context=context;
        this.resource=resource;
        this.requestJoinDiseaseStatesJoinPatientJoinUserLoginForPatients=requestJoinDiseaseStatesJoinPatientJoinUserLoginForPatients;
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
        RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient request= requestJoinDiseaseStatesJoinPatientJoinUserLoginForPatients.get(position);



        holder.textViewParamedicName.setText("paramedic name : "+(request.getParamedicName()==null ? "not specified":request.getParamedicName()));
        holder.textViewDiseaseStateName.setText("disease State Name : "+request.getDiseaseStatesName());
        holder.textViewParamedicPhone.setText("paramedic phone : "+(request.getParamedicPhone()==null ? "not specified":request.getParamedicPhone()));
        holder.textviewRequestDescribed.setText("request described : "+request.getRequestDescribed());
        holder.textviewRequestStatus.setText("request status : "+request.getRequestStatus());




    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return requestJoinDiseaseStatesJoinPatientJoinUserLoginForPatients.size();
    }
    public interface IStartActivityListner{
        void onClick(Request request);

    }

    class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPatientAge;
        TextView textViewParamedicPhone;
        TextView textViewParamedicName;
        TextView textViewDiseaseStateName;
        TextView textviewRequestStatus;
        TextView textviewRequestDescribed;


        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);


            //textViewPatientAge=itemView.findViewById(R.id.textview_patient_age);
            textViewDiseaseStateName=itemView.findViewById(R.id.textview_disease_state_name);
            textViewParamedicName=itemView.findViewById(R.id.textview_paramedic_name);
            textViewParamedicPhone=itemView.findViewById(R.id.textview_paramedic_phone);
            textviewRequestStatus=itemView.findViewById(R.id.textview_request_status);
            textviewRequestDescribed=itemView.findViewById(R.id.textview_request_described);

        }
    }

}