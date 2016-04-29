package com.example.johnpconsidine.bengalworkouts.Fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.johnpconsidine.bengalworkouts.Adapter.WorkoutAdapter;
import com.example.johnpconsidine.bengalworkouts.HomeScreen;
import com.example.johnpconsidine.bengalworkouts.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutsFragment extends android.support.v4.app.Fragment {


    public WorkoutsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=  inflater.inflate(R.layout.fragment_workouts, container, false);
        // Inflate the layout for this fragment
        ((HomeScreen)getActivity()).hideCover();
        //get workouts
        String workouts[] = {"666", "hell", "Leg Day", "Recovery", "Wetzel Insanity"};
        String groups[] = new String[workouts.length];
        String durations[] = new String[workouts.length];
        int iconIds[] = new int [workouts.length];
        int it = 0;
        for (String workout : workouts) {
            groups[it] = "Friends";
            durations[it] = "20 MIN";
            iconIds[it] = R.drawable.timer;
            it++;
        }





        ListView workoutsList = (ListView) view.findViewById(R.id.workoutsListView);
        WorkoutAdapter workoutAdapter = new WorkoutAdapter(getContext(), workouts, groups, durations, iconIds);
        workoutsList.setAdapter(workoutAdapter);












        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeScreen)getActivity()).hideCover();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((HomeScreen)getActivity()).hideCover();
    }
}
