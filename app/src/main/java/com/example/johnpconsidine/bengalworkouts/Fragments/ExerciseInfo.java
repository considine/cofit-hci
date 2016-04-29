package com.example.johnpconsidine.bengalworkouts.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.johnpconsidine.bengalworkouts.HomeScreen;
import com.example.johnpconsidine.bengalworkouts.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseInfo extends android.support.v4.app.Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //((HomeScreen)getActivity()).setTitle("");
        ((HomeScreen)getActivity()).setDrawerIcon(R.drawable.back);

        return inflater.inflate(R.layout.fragment_exercise_info, container, false);
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
