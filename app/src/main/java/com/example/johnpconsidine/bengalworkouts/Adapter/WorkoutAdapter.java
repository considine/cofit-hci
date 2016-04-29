package com.example.johnpconsidine.bengalworkouts.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.johnpconsidine.bengalworkouts.R;

/**
 * Created by johnpconsidine on 4/23/16.
 */
public class WorkoutAdapter extends BaseAdapter {
    protected   Context mContext;
    protected String workouts[];
    protected String groups[];
    protected View.OnClickListener mOnClickListener;
    protected String durations[];
    protected int iconIds[];
    public WorkoutAdapter (Context context, String workouts[], String groups[], String durations[], int iconIds[]) {
        mContext = context;
        this.workouts = workouts;
        this.groups = groups;
        this.durations = durations;
        this.iconIds = iconIds;
    }
    public void addAdapter (View.OnClickListener listener) {
        mOnClickListener = listener;
    }
    @Override
    public int getCount() {
        return workouts.length;
    }

    @Override
    public Object getItem(int position) {
        return workouts[position];
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.workoutitem, null);
            holder.workoutName = (TextView) convertView.findViewById(R.id.workoutName);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.workoutName.setText(workouts[position]);
        if (mOnClickListener!= null) {
            View.OnClickListener listener = mOnClickListener;

        }


        return convertView;
    }

    private static class ViewHolder {
        private TextView workoutName;
        private TextView groupName;
        private ImageView typeIcon;
        private TextView duration;
        private ImageView chevronIcon;

    }
}
