package com.example.johnpconsidine.bengalworkouts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.johnpconsidine.bengalworkouts.Fragments.DrawerFragment;
import com.example.johnpconsidine.bengalworkouts.Fragments.ExerciseFragment;
import com.example.johnpconsidine.bengalworkouts.Fragments.ExerciseInfo;
import com.example.johnpconsidine.bengalworkouts.Fragments.NewExercise;
import com.example.johnpconsidine.bengalworkouts.Fragments.NewWorkout;
import com.example.johnpconsidine.bengalworkouts.Fragments.ShareWorkout;
import com.example.johnpconsidine.bengalworkouts.Fragments.StatsFragment;
import com.example.johnpconsidine.bengalworkouts.Fragments.WorkoutsFragment;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {
    private static final String TAG = HomeScreen.class.getSimpleName();
    private static final String EXERCISE_FRAG = "exerciseFrag";
    private static final String NEW_WORKOUT = "newWorkout";
    private static final String WORKOUTS_FRAG = "workoutsFrag";
    private static final String NEW_EXERCISE = "StartExercise";
    View coverLeft;
    View coverRight;
    FloatingActionButton fab;
    private ExerciseFragment mExerciseFragment;
    public String getCurrentExercise() {
        return currentExercise;
    }

    public void setCurrentExercise(String currentExercise) {
        this.currentExercise = currentExercise;
    }

    private String currentExercise;
    private String newWorkoutName;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ListView mListView;
    ActionBarDrawerToggle mDrawerToggle;
    private RelativeLayout statsLayout;

    public boolean isFirstTimeExercises() {
        if (firstTimeExercises) {
            firstTimeExercises = false;
            return true;
        }
        else {
            return false;
        }
    }

    private boolean firstTimeExercises = true; //show helpful toast ONLY the first time you open the app

    public List<workoutExercise> getExercisesInWorkout() {
        return ExercisesInWorkout;
    }

    public void addToExercises(workoutExercise exercise) {
        ExercisesInWorkout.add(exercise);
    }

    public void removeFromExercises(int position) {
        Toast.makeText(HomeScreen.this, "Removing exercise", Toast.LENGTH_SHORT).show();
        ExercisesInWorkout.remove(position);
    }

    List<workoutExercise> ExercisesInWorkout;
    private RelativeLayout workoutLayout;

    private RelativeLayout mainDiv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        //GET SCREEN DIMENSINOS
        mExerciseFragment = new ExerciseFragment();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        HomeScreen.this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int screenWidth = displaymetrics.widthPixels;
        ExercisesInWorkout = new ArrayList<>();
        //remove exercise fragment
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(EXERCISE_FRAG);
        if (fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        //Set Toolbar
        mToolbar = (Toolbar) findViewById(R.id.homeToolbar);

        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.menu1);
            mToolbar.setTitle(getString(R.string.app_name));
            setSupportActionBar(mToolbar);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mToolbar.setTitleTextColor(getColor(R.color.white));
            } else {
                mToolbar.setTitleTextColor(ContextCompat.getColor(HomeScreen.this, R.color.white));
            }

        }

        changeMenuIcon(R.menu.search);

        //nav drawer
//        ListView leftDrawer = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.navDrawerLayout);

        //set the width of the navigation drawer to three fourths of the screen
        RelativeLayout drawerHolder = (RelativeLayout) findViewById(R.id.drawerHolder);
        DrawerLayout.LayoutParams drawerParams = (android.support.v4.widget.DrawerLayout.LayoutParams) drawerHolder.getLayoutParams();
        drawerParams.width = 3 * screenWidth / 4;
        drawerHolder.setLayoutParams(drawerParams);


        setDrawerToggle();
        mDrawerLayout.closeDrawers();

        hideMenu();
        //add border drawable
        statsLayout = (RelativeLayout) findViewById(R.id.statsLayout);
        workoutLayout = (RelativeLayout) findViewById(R.id.workoutLayout);
        workoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWorkoutsFragment();
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawerHolder, new DrawerFragment(), "drawerFrag")
                .addToBackStack(null)
                .commit();


        mainDiv = (RelativeLayout) findViewById(R.id.mainDiv);
        mListView = (ListView) findViewById(R.id.groupListView);
        assert mainDiv != null;

        //SET SQUARES OF LAYOUTS
        RelativeLayout.LayoutParams statsParams = (RelativeLayout.LayoutParams) statsLayout.getLayoutParams();
        mainDiv.getLayoutParams().height = (screenWidth / 2);
        statsLayout.getLayoutParams().width = (screenWidth / 2);
        workoutLayout.getLayoutParams().width = (screenWidth / 2);
        //icons
        coverLeft = (View) findViewById(R.id.topShadowLeft);
        coverRight = (View) findViewById(R.id.topShadowRight);
        RelativeLayout iconLeft = (RelativeLayout) findViewById(R.id.topPhotoLeft);
        RelativeLayout iconRight = (RelativeLayout) findViewById(R.id.topPhotoRight);
        iconLeft.getLayoutParams().width = (screenWidth / 2);
        iconRight.getLayoutParams().width = (screenWidth / 2);
        iconRight.getLayoutParams().height =(screenWidth/2);
        iconLeft.getLayoutParams().height =(screenWidth/2);


        //shadows
        coverLeft.getLayoutParams().width = (screenWidth / 2);
        coverRight.getLayoutParams().width = (screenWidth / 2);
        coverRight.getLayoutParams().height =(screenWidth/2);
        coverLeft.getLayoutParams().height =(screenWidth/2);


//        coverRight.setBackground(ContextCompat.getDrawable(HomeScreen.this, R.drawable.workout));
//        coverLeft.setBackground(ContextCompat.getDrawable(HomeScreen.this, R.drawable.workout));

        RelativeLayout.LayoutParams coverRightParams = (RelativeLayout.LayoutParams) coverRight.getLayoutParams();

        coverRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        RelativeLayout.LayoutParams iconRightParams = (RelativeLayout.LayoutParams) iconRight.getLayoutParams();
        iconRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        statsParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);


        setTitle("BENGAL WORKOUTS");
        //LOAD IMAGES
        loadimage(workoutLayout, R.drawable.workoutphoto);
        loadimage(statsLayout, R.drawable.stats1);
        //set Menu Animatino

        // statsLayout.setMinimumWidth(screenWidth/2);
        //workoutLayout.setMinimumWidth(screenWidth/2);
        GradientDrawable border = new GradientDrawable();
        border.setColor(0xFFFFFFFF); //white background
        border.setStroke(1, 0xFFa4a4a4); //black border with full opacity


        //Set adapter
        Resources res = getResources();
        GroupsAdapter groupsAdapter = new GroupsAdapter(HomeScreen.this, res.getStringArray(R.array.workouts));
        mListView.setAdapter(groupsAdapter);

        setMenu();
        setListeners();


//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        assert fab != null;
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                ExerciseFragment exercise = (ExerciseFragment)getSupportFragmentManager().findFragmentByTag(EXERCISE_FRAG);
//                WorkoutsFragment newworkout = (WorkoutsFragment)getSupportFragmentManager().findFragmentByTag(WORKOUTS_FRAG);
//                if (exercise != null && exercise.isVisible()) {
//                    startNewExercise();
//
//                }
//                if (newworkout != null && newworkout.isVisible() && getExercisesInWorkout().isEmpty()) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(HomeScreen.this);
//                    EditText input = new EditText(HomeScreen.this);
//                    builder.setTitle("Create a new workout");
//                    builder.setMessage("Please choose your workout name");
//                    builder.setView(input);
//                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            showInstruction("Click the green button to add an exercise. Hold it to switch the type of exercise");
//                        }
//                    });
//                    builder.show();
//
//
//                    setNewWorkoutFrag(false);
//                }
//                else {
//                    Toast.makeText(HomeScreen.this, "To create a new workout, go to workouts. To create a new exercise, go to move library!", Toast.LENGTH_LONG).show();
//                   // setNewWorkoutFrag(false);
//                }
//
//            }
//        });
//
//
//
//    }

      //  setTransparency();

    }
    private void loadimage(RelativeLayout layout, int imageId) {
        layout.setBackground(ResourcesCompat.getDrawable(getResources(), imageId, null));

    }
    public void changeMenuIcon (int id) {
        mToolbar.inflateMenu(id);
    }
    @Override
    protected void onResume() {
        super.onResume();
        coverLeft.setVisibility(View.VISIBLE);
        coverRight.setVisibility(View.VISIBLE);
    }

    private void setDrawerToggle() {
         mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }


    public void setExerciseFragment (boolean howLong) {
        mDrawerLayout.closeDrawers();
        Bundle bundle = new Bundle();
        if (howLong) {
         //   fab.setVisibility(View.INVISIBLE);
        }
        else {
            //fab.setVisibility(View.VISIBLE);
        }
        bundle.putBoolean(Utils.WORKING_OUT, howLong);
        ExerciseFragment exerciseFragment = new ExerciseFragment();
        exerciseFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragHolder,exerciseFragment, EXERCISE_FRAG)
                .addToBackStack(null)
                .commit();

    }

    public void setWorkoutsFragment() {
        //fab.setVisibility(View.VISIBLE);
        mDrawerLayout.closeDrawers();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragHolder, new WorkoutsFragment(), WORKOUTS_FRAG)
                .addToBackStack(null)
                .commit();
    }
    public void setNewWorkoutFrag (boolean workingOut) {
       // fab.setVisibility(View.INVISIBLE);
        mDrawerLayout.closeDrawers();
        NewWorkout newWorkout = new NewWorkout();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Utils.WORKING_OUT, workingOut);
        newWorkout.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragHolder, newWorkout, NEW_WORKOUT)
                .addToBackStack(null)
                .commit();
    }

    public void goToGroups () {
        mDrawerLayout.closeDrawers();
        hideCover();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragHolder, new StatsFragment(), "stats")
                .addToBackStack(null)
                .commit();
    }

    public void shareWorkout() {
        mDrawerLayout.closeDrawers();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragHolder, new ShareWorkout(), WORKOUTS_FRAG)
                .addToBackStack(null)
                .commit();
    }
    public void startNewExercise() {

        mDrawerLayout.closeDrawers();
//        fab.setVisibility(View.INVISIBLE);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragHolder, new NewExercise(), NEW_EXERCISE)
                .addToBackStack(null)
                .commit();
    }
    public void goBackAFragment() {
        getFragmentManager().popBackStack();
    }

    public void setMoveInfo (String info) {
        mDrawerLayout.closeDrawers();
        setTitle(info);
//        fab.setVisibility(View.INVISIBLE);
        getSupportFragmentManager()

                .beginTransaction()
                .replace(R.id.fragHolder, new ExerciseInfo(), "moveInfo")
                .addToBackStack(null)
                .commit();

    }

    public void hideToolBar () {
        mToolbar.setVisibility(View.GONE);
    }
    public void showToolBar () {
        mToolbar.setVisibility(View.VISIBLE);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(getIntent());
        return false;
    }


    public void hideMenu () {
        mToolbar.getMenu().findItem(R.id.empty).setVisible(false);
    }
    public void showMenu() {
        mToolbar.getMenu().findItem(R.id.empty).setVisible(true);
    }
    public void menuClick (MenuItem item) {
        Log.v(TAG, "CLICKED!");

            item.setEnabled(false);

        shareWorkout();


    }



    public void showInstruction (String instruction) {
        Snackbar.make(findViewById(android.R.id.content), instruction, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    public void setDrawerIcon (int id) {
        mToolbar.setNavigationIcon(id);

        if (id == R.drawable.back) {
            //disable nav
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().popBackStack();
                    setDrawerIcon(R.drawable.menu1);
                    //mToolbar.s
                    setDrawerToggle();

                }
            });
        }
        else {
            mToolbar.setNavigationOnClickListener(null);
        }
    }




    public void setTitle(String title) {
        title = title.toUpperCase();
        mToolbar.setTitle(title);
    }
    public void setMenu () {
        final FloatingActionMenu fabMenu = (FloatingActionMenu) findViewById(R.id.menu);
        assert fabMenu != null;




        fabMenu.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //before doing anything else, see what fragment we're in
                WorkoutsFragment newWorkout = (WorkoutsFragment) getSupportFragmentManager().findFragmentByTag(WORKOUTS_FRAG);
                com.github.clans.fab.FloatingActionButton fab1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab1);
                com.github.clans.fab.FloatingActionButton fab2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab2);
                ExerciseFragment newExercise = (ExerciseFragment) getSupportFragmentManager().findFragmentByTag(EXERCISE_FRAG);
                if (newWorkout != null && newWorkout.isVisible()) {
                    //goto new exercise fragment
                   setNewWorkoutFrag(false);

                  return;
                    //fab1.

                } else if (newExercise != null && newExercise.isVisible()) {
                    Log.v(TAG, "SHOULD BE GOING");
                    startNewExercise();
                    return;
                }
                final View view = (View) findViewById(R.id.shadowView);
                Log.v(TAG, "The visibility is " + view.getVisibility());
                if (view == null)
                    return;
                if (view.getVisibility() == View.VISIBLE) {
                    closeMenu();

                } else {
                    view.setAlpha(.9f);
                    fabMenu.open(true);
                    view.setVisibility(View.VISIBLE);
                }
                Log.v(TAG, "CLICKEDkingdom come");
            }
        });


    }

    public void closeMenu () {
        final View view = (View) findViewById(R.id.shadowView);
        final FloatingActionMenu menu = (FloatingActionMenu) findViewById(R.id.menu);
        if (menu == null)
            return;
        menu.close(true);
        if (view == null) {
            return;
        }
        view.animate()
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                });
    }
    public void setListeners () {
        com.github.clans.fab.FloatingActionButton fab1 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab1);
        if (fab1 == null)
            return;
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewExercise();
                closeMenu();
                //toggle Menu

            }
        });

        com.github.clans.fab.FloatingActionButton fab2 = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fab2);
        if (fab2 == null)
            return;
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go to new workout Fragment
                setNewWorkoutFrag(false);
                closeMenu();

            }
        });
    }


    public void hideCover () {
        View layout = (View) findViewById(R.id.topShadowRight);
        View layout1 = (View) findViewById(R.id.topShadowLeft);
        RelativeLayout layout2 = (RelativeLayout) findViewById(R.id.topPhotoLeft);
        RelativeLayout layout3 = (RelativeLayout) findViewById(R.id.topPhotoRight);


        if (layout == null || layout1 == null || layout2 == null || layout3 == null)
            return;
        layout.setVisibility(View.GONE);
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
    }
    public void hideScreenShadow(View view) {
        closeMenu();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //set screen just in case
        coverLeft.setVisibility(View.VISIBLE);
        coverRight.setVisibility(View.VISIBLE);
        RelativeLayout layout2 = (RelativeLayout) findViewById(R.id.topPhotoLeft);
        RelativeLayout layout3 = (RelativeLayout) findViewById(R.id.topPhotoRight);
        if (layout2 == null)
            return super.onKeyDown(keyCode, event);
        if (layout3 ==null)
            return super.onKeyDown(keyCode, event);
        layout2.setVisibility(View.VISIBLE);
        layout3.setVisibility(View.VISIBLE);

        return super.onKeyDown(keyCode, event);

    }
}
