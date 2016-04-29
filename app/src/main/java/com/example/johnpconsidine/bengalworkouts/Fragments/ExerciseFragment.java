package com.example.johnpconsidine.bengalworkouts.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.johnpconsidine.bengalworkouts.Adapter.MyPageAdapter;
import com.example.johnpconsidine.bengalworkouts.HomeScreen;
import com.example.johnpconsidine.bengalworkouts.R;
import com.example.johnpconsidine.bengalworkouts.Utils;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class ExerciseFragment extends android.support.v4.app.Fragment {
    private static final String TAG = ExerciseFragment.class.getSimpleName();
    private ViewPager pager;
    private int test;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        Log.v(TAG, "Created");
        Log.v(TAG,  "test is " + test);

        test =2;


        //hide action bar


        ((HomeScreen)getActivity()).setTitle("Move Library");

//        if (mainToolbar != null) {
//            mainToolbar.setNavigationIcon(R.drawable.menu);
//
//            mainToolbar.inflateMenu(R.menu.search_menu);
//            mainToolbar.setTitle(getString(R.string.exercises));
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                mainToolbar.setTitleTextColor(getColor(R.color.white));
//            }
//            else {
//                mainToolbar.setTitleTextColor(ContextCompat.getColor(ExcerciseActivity.this, R.color.white));
//            }
//
//        }









        // SET EXERCISE ARRAYS HERE
        String core[] = {"Slow Bike", "Scissor Crunch", "Russian Twist", "Leg Rows", "Free Falls", "Plank", "Side Plank (Right)", "Side Plank (Left)", "Side Crunch", "Penguin Slides"};
        String arms[] = {"Standard Pushups", "Mountain Climbers", "Monkey Business", "Shoulder Press Push Ups", "123 Push Up", "Spidey Push Ups", "Diamond Push Up"};
        String legs[] = {"Squats", "Squat Jumps", "Knee Tuck Jumps", "Pulse Squats", "High Knees", "Squat Thrusts", "Hurdler Step Back Lunge Kick", "Jump Lunges"};
        String fav[] = {"Squat Jumps", "Knee Tuck Jumps", "Pulse Squats", "High Knees", "Squat Thrusts", "Hurdler Stepback Lungekick", "Jump Lunges"};
        String fullbody[] = {"Burpees", "Modified Burpees", "Star Burpees", "8-Count-Body-Builders", "Dead Man Burpees", "Froggy Burpees", "Roll Over Burpees"};
        boolean workingOut = getArguments().getBoolean(Utils.WORKING_OUT);

        //Put the arrays in to the bundles
        Bundle coreBundle = new Bundle();
        coreBundle.putStringArray("exercise", core);
        coreBundle.putBoolean(Utils.WORKING_OUT, workingOut);
        Bundle armsBundle = new Bundle();
        armsBundle.putStringArray("exercise", arms);
        armsBundle.putBoolean(Utils.WORKING_OUT, workingOut);
        Bundle legsBundle = new Bundle();
        legsBundle.putStringArray("exercise", legs);
        legsBundle.putBoolean(Utils.WORKING_OUT, workingOut);
        Bundle favBundle = new Bundle();
        favBundle.putStringArray("exercise", fav);
        favBundle.putBoolean(Utils.WORKING_OUT, workingOut);
        Bundle fullBundle = new Bundle();
        fullBundle.putStringArray("exercise", fullbody);
        fullBundle.putBoolean(Utils.WORKING_OUT, workingOut);
        //Create the fragments and pass the arguments
        if (workingOut)
            ((HomeScreen)getActivity()).hideToolBar();


        ExerciseTab Core = new ExerciseTab();
        ExerciseTab Arms = new ExerciseTab();
        ExerciseTab Legs = new ExerciseTab();
        ExerciseTab Fav = new ExerciseTab();
        ExerciseTab Full = new ExerciseTab();
        //set the arguments to the fragments
        Core.setArguments(coreBundle);
        Arms.setArguments(armsBundle);
        Legs.setArguments(legsBundle);
        Fav.setArguments(favBundle);
        Full.setArguments(fullBundle);

        Log.v(TAG, "All the exercises have been set");


        //Add each of these fragments to the list of fragments
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(Fav);
        fragmentList.add(Legs);
        fragmentList.add(Core);
        fragmentList.add(Arms);
        fragmentList.add(Full);


        //set the list info for each exercise Fragment

        TitlePageIndicator titleIndicator = (TitlePageIndicator) view.findViewById(R.id.workoutTitles);
        //SET VIEW PAGER
        pager = (ViewPager) view.findViewById(R.id.exerciseViewPager);
        //List of fragments we need:

        pager.setAdapter(new MyPageAdapter(getChildFragmentManager(), fragmentList));
        if (titleIndicator != null) {

            titleIndicator.setTitlePadding(200);
            titleIndicator.setViewPager(pager);

            titleIndicator.setFooterColor(0xAAfe9a25);
            titleIndicator.setTextColor(0xAA000000);
            titleIndicator.setSelectedColor(0xAAfe9a25);
            titleIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }

        if (((HomeScreen)getActivity()).isFirstTimeExercises())
            Toast.makeText(getContext(), "Swipe through all different types of exercises!", Toast.LENGTH_LONG).show();
        return view;

    }
    //    public boolean onCreateOptionsMenu(Menu menu) {
//        Log.v(TAG, "Inflated");
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.search_menu, menu);
//        return true;
//    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getArguments().getBoolean(Utils.WORKING_OUT)) {
            //then we are just picking an exercise. so go immedietly back
            ((HomeScreen)getActivity()).goBackAFragment();
        }
        return true;
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