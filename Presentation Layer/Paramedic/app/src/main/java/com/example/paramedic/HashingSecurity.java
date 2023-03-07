package com.example.paramedic;

import android.widget.Toast;

import androidx.annotation.NonNull;

import java.security.MessageDigest;

public class HashingSecurity {


    @NonNull
    public static String hashPassword(String password, String salt){

        String hsString=password+salt;
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] data = md.digest(hsString.getBytes());
            for(int i=0;i<data.length;i++){
                sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
            }
            System.out.println(sb);
        } catch(Exception e) {
            System.out.println(e);
        }
        return  sb.toString();
    }


}
