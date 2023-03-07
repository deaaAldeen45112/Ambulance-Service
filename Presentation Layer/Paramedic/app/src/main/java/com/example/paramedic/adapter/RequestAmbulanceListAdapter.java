package com.example.paramedic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paramedic.R;
import com.example.paramedic.model.request.Request;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLogin;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance;
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLoginForPatient;

import java.util.ArrayList;

public class RequestAmbulanceListAdapter extends RecyclerView.Adapter<RequestAmbulanceListAdapter.RequestViewHolder> {
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

    public RequestAmbulanceListAdapter(Context context, int resource, ArrayList<RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance>requestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulances, IStartActivityListner listner)
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

        holder.buttonSelectParamedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onClickSelectParamedic(request);
            }
        });

        if(requestStatus.equals("ambulance")){


            holder.buttonSelectParamedic.setVisibility(View.GONE);
            holder.buttonConfirmAmbulance.setVisibility(View.GONE);
            holder.buttonFinishAmbulance.setVisibility(View.VISIBLE);
            holder.textviewDoneBy.setText("");
        }

        else if (requestStatus.equals("success")){
            if(request.getParamedicId()==0){
            holder.textviewDoneBy.setText("done by : ambulance");

            }
            else {
                holder.textviewDoneBy.setText("done by : "+request.getParamedicName());

            }
            holder.buttonSelectParamedic.setVisibility(View.GONE);
            holder.buttonConfirmAmbulance.setVisibility(View.GONE);
            holder.buttonFinishAmbulance.setVisibility(View.GONE);


        }

        else if(requestStatus.equals("waiting")){

            holder.buttonSelectParamedic.setVisibility(View.VISIBLE);
            holder.buttonConfirmAmbulance.setVisibility(View.VISIBLE);
            holder.buttonFinishAmbulance.setVisibility(View.GONE);
            holder.textviewDoneBy.setText("");
        }
        else if(requestStatus.equals("assigned paramedic")){

            holder.buttonSelectParamedic.setVisibility(View.GONE);
            holder.buttonConfirmAmbulance.setVisibility(View.GONE);
            holder.buttonFinishAmbulance.setVisibility(View.GONE);
            holder.textviewDoneBy.setText("");
        }

       holder.buttonConfirmAmbulance.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Request updateRequestConfirmAmbulance=new Request();
               updateRequestConfirmAmbulance.setRequestId(request.getRequestId());
               updateRequestConfirmAmbulance.setRequestStatus("ambulance");

               listner.onClickConfirmAmbulance(updateRequestConfirmAmbulance);
           }
       });
       holder.buttonFinishAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request updateRequestFinish=new Request();
                updateRequestFinish.setRequestId(request.getRequestId());
                updateRequestFinish.setRequestStatus("success");
                listner.onClickFinish(updateRequestFinish);
            }
        });

       holder.buttonShowLocationOfPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listner.onClickShowLocationOfPatient(request.getPatientLongitude(),request.getPatientLatitude());
            }
        });


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
        void onClickSelectParamedic(RequestJoinDiseaseStatesJoinPatientJoinUserLoginForAmbulance request);
        void onClickConfirmAmbulance(Request request);
        void onClickShowLocationOfPatient(String lng, String lat);
        void onClickFinish(Request request);
    }

    class RequestViewHolder extends RecyclerView.ViewHolder {
        TextView textviewDoneBy;
        TextView textViewPatientPhone;
        TextView textViewPatientName;
        TextView textViewDiseaseStateName;
        TextView textviewRequestStatus;
        TextView textviewRequestDescribed;
        Button buttonSelectParamedic;
        Button buttonShowLocationOfPatient;
        Button buttonConfirmAmbulance;
        Button buttonFinishAmbulance;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);


            //textViewPatientAge=itemView.findViewById(R.id.textview_patient_age);
            textViewDiseaseStateName=itemView.findViewById(R.id.textview_disease_state_name);
            textViewPatientName=itemView.findViewById(R.id.textview_patient_name);
            textViewPatientPhone=itemView.findViewById(R.id.textview_patient_phone);
            textviewRequestStatus=itemView.findViewById(R.id.textview_request_status);
            textviewRequestDescribed=itemView.findViewById(R.id.textview_request_described);
            textviewDoneBy=itemView.findViewById(R.id.textview_done_by);
            buttonSelectParamedic=itemView.findViewById(R.id.button_select_paramedic);
            buttonShowLocationOfPatient=itemView.findViewById(R.id.button_show_location_of_patient);
            buttonConfirmAmbulance=itemView.findViewById(R.id.button_confirm_ambulance);
            buttonFinishAmbulance=itemView.findViewById(R.id.button_finish_ambulance);

        }
    }

}