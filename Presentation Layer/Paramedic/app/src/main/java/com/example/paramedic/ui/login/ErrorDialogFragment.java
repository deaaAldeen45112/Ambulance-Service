package com.example.paramedic.ui.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ErrorDialogFragment extends DialogFragment {
    private  static final String ARG_TITLE="title";
    private  static final String ARG_ICON="icon";
    private  static final String ARG_MESSAGE="message";

    private String title;
    private String message;
    private int icon;


    public static  ErrorDialogFragment newInstance(String title,String message,int icon){

        Bundle bundle=new Bundle();
        bundle.putString(ARG_TITLE,title);
        bundle.putInt(ARG_ICON,icon);
        bundle.putString(ARG_MESSAGE,message);

        ErrorDialogFragment fragment=new ErrorDialogFragment();
        fragment.setArguments(bundle);
        return  fragment;



    }



    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder alertDialog=new AlertDialog.Builder(getContext());

        alertDialog.setTitle(getArguments().getString(ARG_TITLE));
        alertDialog.setMessage(getArguments().getString(ARG_MESSAGE));
        alertDialog.setIcon(getArguments().getInt(ARG_ICON));

        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"yes",Toast.LENGTH_SHORT);
            }
        });




        return alertDialog.create();
    }

    public ErrorDialogFragment() {
        // Required empty public constructor
    }
    onFragmentListner listner;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        /*if(context instanceof onFragmentListner){

            listner=(onFragmentListner) context;
            listner.onClickListner("deaa asd");
        }

        else {

            throw new ClassCastException("there is not context instanceof onFragmentListenr");
        }
        Toast.makeText(context, context.toString(), Toast.LENGTH_SHORT).show();
    */}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* Bundle args=getArguments();
        if(args!=null){
            title=args.getString(ARG_TITLE);
            message=args.getString(ARG_MESSAGE);
            icon=args.getInt(ARG_ICON);

        }*/
    }
    interface  onFragmentListner{

        void onClickListner(String name);

    }



}
