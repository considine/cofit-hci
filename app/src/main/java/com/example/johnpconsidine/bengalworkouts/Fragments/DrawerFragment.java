package com.example.johnpconsidine.bengalworkouts.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.johnpconsidine.bengalworkouts.Adapter.navDrawerAdapter;
import com.example.johnpconsidine.bengalworkouts.HomeScreen;
import com.example.johnpconsidine.bengalworkouts.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DrawerFragment extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG = DrawerFragment.class.getSimpleName();
    private  String texts[];
    private int itemIds[];
    private ListView lv;
    private int currentLoc;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer, container, false);
        //Get the texts, associate them with an image, set the adapter.
        texts = getResources().getStringArray(R.array.navItems);
        //map them with an item
        itemIds = new int[texts.length];
        Map<String, Integer> ItemToId = new HashMap<>();
        ItemToId.put(getString(R.string.home), R.drawable.house);
        ItemToId.put(getString(R.string.workouts), R.drawable.workout);
        ItemToId.put(getString(R.string.stats), R.drawable.stats);
        ItemToId.put(getString(R.string.groups), R.drawable.social);
        ItemToId.put(getString(R.string.move), R.drawable.move_library);
        int it = 0;
        for (String text : texts) {
            itemIds[it] = ItemToId.get(text);
            it++;
        }
        //marks what screen you're in in the drawer
        currentLoc = Arrays.asList(texts).indexOf(getString(R.string.home));

        //inflate Nav Drawer
        lv = (ListView) view.findViewById(R.id.drawerListView);
        navDrawerAdapter adapter = new navDrawerAdapter(getContext(), itemIds, texts, currentLoc);
        lv.setAdapter(adapter);
        lv.setClickable(true);
        lv.setOnItemClickListener(this);
        
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        navDrawerAdapter adapter = new navDrawerAdapter(getContext(), itemIds, texts, currentLoc);
        adapter.setClickLocation(position);
        lv.setAdapter(adapter);
        if (texts[position].equals(getString(R.string.move))) {

            ((HomeScreen)getActivity()).setExerciseFragment(false);

        }
        else if (texts[position].equals(getString(R.string.home))) {

            Intent intent = ((HomeScreen)getActivity()).getIntent();
            Log.v(TAG, "Starting over");
            ((HomeScreen)getActivity()).finish();
            startActivity(intent);


        }
        else if (texts[position].equals(getString(R.string.workouts))) {
            ((HomeScreen)getActivity()).setWorkoutsFragment();
        }
        else if (texts[position].equals(getString(R.string.groups))) {
            ((HomeScreen)getActivity()).goToGroups();
        }

    }
}
