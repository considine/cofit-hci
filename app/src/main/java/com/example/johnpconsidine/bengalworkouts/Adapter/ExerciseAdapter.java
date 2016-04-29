package com.example.johnpconsidine.bengalworkouts.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.johnpconsidine.bengalworkouts.R;

import java.util.List;

/**
 * Created by johnpconsidine on 4/22/16.
 */
public class ExerciseAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> items;
    private boolean normal;
    private List<Integer> imageIds;
    public ExerciseAdapter(Context context, List<String> Items, List<Integer> imageIds, boolean normal) {
        this.items= Items;
        this.imageIds = imageIds;
        this.normal = normal;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return items.size();

    }

    @Override
    public Object getItem(int position) {
        return  items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.singlemove, null);
            holder = new ViewHolder();
            holder.icon = (ImageView) convertView.findViewById(R.id.chevronIcon);
            holder.exerciseImage = (ImageView) convertView.findViewById(R.id.exerciseImage);
            holder.exerciseImage.setImageResource(imageIds.get(position));
            holder.exercise = (TextView) convertView.findViewById(R.id.exerciseName);
            holder.exercise.setText(items.get(position));
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
            holder.exercise.setText(items.get(position));
            holder.exerciseImage.setImageResource(imageIds.get(position));

        }

        if (!normal) {
           // holder.no
            holder.icon.setImageResource(R.drawable.plus);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView exercise;
        ImageView exerciseImage;
        ImageView icon;
        RelativeLayout notificationLayout;
    }

}
