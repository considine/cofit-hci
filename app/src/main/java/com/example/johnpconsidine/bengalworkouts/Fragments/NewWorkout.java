package com.example.johnpconsidine.bengalworkouts.Fragments;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnpconsidine.bengalworkouts.Adapter.buildWorkoutAdapter;
import com.example.johnpconsidine.bengalworkouts.HomeScreen;
import com.example.johnpconsidine.bengalworkouts.R;
import com.example.johnpconsidine.bengalworkouts.Utils;
import com.example.johnpconsidine.bengalworkouts.workoutExercise;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewWorkout extends android.support.v4.app.Fragment {
    private static final  String TAG = NewWorkout.class.getSimpleName();
    private ImageButton addRest;
    private ImageButton imageButton;

    private List<String> FakeExercises;
    private List<String> FakeDurations;
    private List<Integer> FakeIds;



    private ListView mListView;
    private String imageTag = "timer";
    private boolean timeWorkout;
    //store data in homescreen- otherwise it will disappear
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_workout, container, false);
        timeWorkout = false;
        ((HomeScreen)getActivity()).hideCover();
        ((HomeScreen)getActivity()).showMenu();
       // ImageView newWorkoutButton = (ImageView)
        Log.v("AT", "the bool is " + getArguments().getBoolean(Utils.WORKING_OUT));
        imageButton = (ImageButton) view.findViewById(R.id.newExercise);
        imageButton.setTag(imageTag);
        mListView = (ListView) view.findViewById(R.id.exerciseWorkouts);

        ((HomeScreen)getActivity()).setDrawerIcon(R.drawable.back);


        FakeExercises = new ArrayList<>();
        FakeDurations = new ArrayList<>();
        FakeIds = new ArrayList<>();
        FakeIds.add(1);
        FakeIds.add(1);
        FakeIds.add(1);


        //buildWorkoutAdapter adapter = new buildWorkoutAdapter(getContext(), ((HomeScreen)getActivity()).getExercisesInWorkout(), new Add());
        //mListView.setAdapter(adapter);
        resetAdapter();
        mListView.setDivider(null);
        mListView.setDividerHeight(0);
        //mListView.setAdapter(adapter);

        addRest = (ImageButton) view.findViewById(R.id.addRestButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ((HomeScreen)getActivity()).setCurrentExercise(new workoutExercise(timeWorkout));
                ((HomeScreen)getActivity()).setExerciseFragment(true);
            }
        });
        imageButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (imageButton.getTag().equals("timer")) {
                    imageButton.setImageResource(R.drawable.reps_grey);
                    imageButton.setTag("rest");
                    timeWorkout = true;
                    Toast.makeText(getContext(), "Rep Based", Toast.LENGTH_SHORT).show();
                } else {
                    imageButton.setImageResource(R.drawable.timer_white);
                    imageButton.setTag("timer");
                    timeWorkout = false;
                    Toast.makeText(getContext(), "Timer Based", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        addMoves();


        return view;
    }

    public void addMoves () {
        if (!getArguments().getBoolean(Utils.WORKING_OUT)) {

            return;

        }

        ((HomeScreen)getActivity()).showToolBar();
//

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final EditText input = new EditText(getContext());
        final LinearLayout wholeLayout = new LinearLayout(getContext());
        wholeLayout.setOrientation(LinearLayout.VERTICAL);
        final LinearLayout topLayout=  new LinearLayout(getContext());
        topLayout.setOrientation(LinearLayout.HORIZONTAL);
        topLayout.setWeightSum(2);
        final TextView reps = new TextView(getContext());
        final TextView time = new TextView(getContext());

        reps.setText("REPS");
        reps.setGravity(Gravity.CENTER_HORIZONTAL);
        reps.setPaintFlags(reps.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        reps.setTextSize(20);

        time.setText("TIME");
        time.setGravity(Gravity.CENTER_HORIZONTAL);
        time.setTextSize(20);
        topLayout.addView(reps);
        topLayout.addView(time);
        wholeLayout.addView(topLayout);
        input.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        wholeLayout.addView(input);
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                time.setPaintFlags(time.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                reps.setPaintFlags(reps.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
                reps.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            }
        });
        reps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reps.setTextColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
                reps.setPaintFlags(reps.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

                time.setPaintFlags(time.getPaintFlags() & (~Paint.UNDERLINE_TEXT_FLAG));
                time.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            }
        });

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, 100);
        lp.weight=1;
        reps.setLayoutParams(lp);
        time.setLayoutParams(lp);


        builder.setView(wholeLayout);
        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //add the workout
                workoutExercise exercise = new workoutExercise(true);
                exercise.setName(((HomeScreen) getActivity()).getCurrentExercise());
                exercise.setReps(input.getText().toString());
                ((HomeScreen)getActivity()).addToExercises(exercise);
                //mListView.setAdapter(new buildWorkoutAdapter(getContext(), ((HomeScreen) getActivity()).getExercisesInWorkout(), new Add()));
                resetAdapter();
                mListView.setOnItemLongClickListener(mOnItemLongClickListener);
                mListView.setOnItemClickListener(mOnItemClickListener);
                mListView.setOnDragListener(mOnDragListener);
            }
        });

        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        //builder.setView(input);
        builder.show();


    }
    AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getContext(), "If you want to delete an exercise, hold it down. ", Toast.LENGTH_SHORT).show();

        }
    };

    public  class Add implements View.OnClickListener {

        int position;
        public void setPosition (int position) {
            this.position = position;
        }
        @Override
        public void onClick(View v) {
            ((HomeScreen)getActivity()).addToExercises(((HomeScreen)getActivity()).getExercisesInWorkout().get(position));
            resetAdapter();
            Log.v(TAG, "The position is " + position);
        }
    }

    AdapterView.OnDragListener mOnDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            Log.v("TAG", "The view is v" + v);
            return true;
        }
    };
    AdapterView.OnItemLongClickListener mOnItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            ((HomeScreen)getActivity()).removeFromExercises(position);
            resetAdapter();
            return false;
        }
    };

    public void resetAdapter() {
        mListView.setAdapter(new buildWorkoutAdapter(getContext(), ((HomeScreen) getActivity()).getExercisesInWorkout(), new Add()));
    }


    @Override
    public void onAttach(Context context) {
        ((HomeScreen)getActivity()).hideCover();
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        ((HomeScreen)getActivity()).hideCover();
        super.onResume();
    }
}
