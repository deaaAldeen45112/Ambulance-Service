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
import com.example.paramedic.model.request.RequestJoinDiseaseStatesJoinPatientJoinUserLogin;
import com.example.paramedic.model.userlogin.UserLogin;

import java.util.ArrayList;

public class ReadUserLoginsListAdapter extends RecyclerView.Adapter<ReadUserLoginsListAdapter.UserLoginViewHolder> {
private Context context;
private int resource;
private ArrayList<UserLogin> arrayListUserLogins=new ArrayList<>();


public void setArrayListUserLogins(ArrayList<UserLogin> userLogins) {
        this.arrayListUserLogins = userLogins;
        notifyDataSetChanged();
        }

public ReadUserLoginsListAdapter(Context context, int resource)
        {
        this.context=context;
        this.resource=resource;

        }


    @NonNull
    @Override
    public UserLoginViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view =LayoutInflater.from(context).inflate(resource,parent,false);
      UserLoginViewHolder userLoginViewHolder=new UserLoginViewHolder(view);


      return userLoginViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserLoginViewHolder holder, int position) {

        UserLogin userLogin= arrayListUserLogins.get(position);
        holder.textViewEmail.setText("email : "+userLogin.getEmail());
        holder.textViewName.setText("name : "+userLogin.getFullName());
        holder.textViewAge.setText("age : "+userLogin.getAge()+"");
        holder.textViewPhone.setText("phone : "+userLogin.getPhone()+"");


    }

    @Override
public long getItemId(int position) {
        return 0;
        }

@Override
public int getItemCount() {
        return arrayListUserLogins.size();
        }


class UserLoginViewHolder extends RecyclerView.ViewHolder {
    TextView textViewAge;
    TextView textViewPhone;
    TextView textViewName;
    TextView textViewEmail;



    public UserLoginViewHolder(@NonNull View itemView) {
        super(itemView);


        textViewAge=itemView.findViewById(R.id.textview_age);
        textViewName=itemView.findViewById(R.id.textview_name);
        textViewPhone=itemView.findViewById(R.id.textview_phone);
        textViewEmail=itemView.findViewById(R.id.textview_email);
         }
}

}


/*
*
*/