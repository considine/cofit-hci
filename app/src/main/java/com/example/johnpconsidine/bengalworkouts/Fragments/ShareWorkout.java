package com.example.johnpconsidine.bengalworkouts.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.johnpconsidine.bengalworkouts.HomeScreen;
import com.example.johnpconsidine.bengalworkouts.R;

public class ShareWorkout extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share_workout, container, false);
        ((HomeScreen)getActivity()).showMenu();
        Spinner dropdown = (Spinner)view.findViewById(R.id.spinner1);
        String[] items = new String[]{"SWIM TEAM", "WORKOUT BUDDIES", "ZAHM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        TextView send = (TextView)view.findViewById(R.id.sendText);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Message Sent!");
                builder.setMessage("You have sent your workout!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((HomeScreen)getActivity()).finish();
                        startActivity(((HomeScreen) getActivity()).getIntent());
                    }
                });
                builder.show();


            }
        });
        ((HomeScreen)getActivity()).hideCover();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        ((HomeScreen)getActivity()).hideCover();
        super.onAttach(context);
    }
    @Override
    public void onResume() {
        super.onResume();
        ((HomeScreen)getActivity()).hideCover();
    }
}