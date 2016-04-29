package com.example.johnpconsidine.bengalworkouts.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.johnpconsidine.bengalworkouts.Adapter.ExerciseAdapter;
import com.example.johnpconsidine.bengalworkouts.HomeScreen;
import com.example.johnpconsidine.bengalworkouts.R;
import com.example.johnpconsidine.bengalworkouts.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExerciseTab extends android.support.v4.app.Fragment  implements AdapterView.OnItemClickListener{
    private static final String TAG = ExerciseTab.class.getSimpleName();
    ListView mListView;
    public List<String> exercises;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        mListView = (ListView) view.findViewById(R.id.exerciseListView);

        int imageIDs[] = new int[5];
        imageIDs[0] = R.drawable.default_move;
        imageIDs[1] = R.drawable.default_move1;
        imageIDs[2] = R.drawable.default_move2;
        imageIDs[3] = R.drawable.default_move3;
        imageIDs[4] = R.drawable.default_move4;
        //set ids
        List<Integer> imageIds = new ArrayList<>();

        String exerciseArray[] = getArguments().getStringArray("exercise");
//        assert (exerciseArray != null);
        Log.v(TAG, "The fifth item is " + exerciseArray[2]);

        //set names
        exercises = new ArrayList<>();
        exercises = Arrays.asList(exerciseArray);
//        Log.v(TAG, "exercises is " + exerciseArray);
        int iterator = 0;
        for (String exercise : exercises) {
            imageIds.add(imageIDs[iterator % 5]);
            iterator++;
        }
        ExerciseAdapter adapter;
        if (getArguments().getBoolean(Utils.WORKING_OUT)) {
            adapter = new ExerciseAdapter(getContext(), exercises, imageIds, false);
        }
        else {
            adapter = new ExerciseAdapter(getContext(), exercises, imageIds, true);
        }
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.v(TAG, "CLC");
        if (getArguments().getBoolean(Utils.WORKING_OUT)) {
            //save the current exercise

            //go back to the next workout

            ((HomeScreen)getActivity()).setCurrentExercise(exercises.get(position));

            //workoutExercise  ((HomeScreen)getActivity()).getCurrentExercise();
                    ((HomeScreen) getActivity()).setNewWorkoutFrag(true);
        }
        else {
            ((HomeScreen)getActivity()).setMoveInfo(exercises.get(position));
        }
    }

}
