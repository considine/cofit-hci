package com.example.johnpconsidine.bengalworkouts.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.johnpconsidine.bengalworkouts.Fragments.NewWorkout;
import com.example.johnpconsidine.bengalworkouts.R;
import com.example.johnpconsidine.bengalworkouts.workoutExercise;

import java.util.List;

/**
 * Created by johnpconsidine on 4/23/16.
 */
public class buildWorkoutAdapter extends BaseAdapter {
    Context mContext;
    List<workoutExercise> mWorkoutExercises;
    NewWorkout.Add mAdd;
    public static final String TAG = buildWorkoutAdapter.class.getSimpleName();

    public buildWorkoutAdapter (Context context, List<workoutExercise> excercises, NewWorkout.Add add) {
        mContext = context;
        mAdd = add;
        mWorkoutExercises = excercises;
        if (mAdd == null) {
            Log.v(TAG, "Well fuck this didn't help");

        }
        else  {
            Log.v(TAG, "Fuck this DID help");
        }

    }

    @Override
    public int getCount() {
        return mWorkoutExercises.size();
    }

    @Override
    public Object getItem(int position) {
        return mWorkoutExercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.singleexercise, null);
            holder.duration = (TextView) convertView.findViewById(R.id.durationTextView);
            holder.icon = (ImageView) convertView.findViewById(R.id.exerciseIcon);
            holder.workoutName = (TextView) convertView.findViewById(R.id.exerciseName);
            holder.duplicateIcon = (ImageView) convertView.findViewById(R.id.duplicate);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();

        }
        Log.v(TAG, holder.icon.getBaseline()+"");
        holder.workoutName.setText(mWorkoutExercises.get(position).getName());
        holder.duration.setText(mWorkoutExercises.get(position).getReps());

        if (mAdd != null ) {
            Log.v("TAG", "madd is not null");
            mAdd.setPosition(position);
            holder.duplicateIcon.setOnClickListener(mAdd);
        }






        return convertView;
    }



    private static class ViewHolder {
        TextView duration;
        ImageView icon;
        ImageView duplicateIcon;
        TextView workoutName;

    }
}
