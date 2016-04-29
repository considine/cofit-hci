package com.example.johnpconsidine.bengalworkouts.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.johnpconsidine.bengalworkouts.R;

/**
 * Created by johnpconsidine on 4/23/16.
 */
public class navDrawerAdapter extends BaseAdapter {
    protected Context mContext;
    protected int icondIds[];
    protected String mTexts[];

    protected int currentLocation;

    public navDrawerAdapter(Context context, int iconIds[], String mTexts[], int currentLocation) {
        mContext = context;

        this.currentLocation = currentLocation;
        this.icondIds = iconIds;
        this.mTexts = mTexts;
    }
    public void setClickLocation (int location) {
        currentLocation = location;

    }
    @Override
    public int getCount() {
        return icondIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mTexts[position];
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.navitem, null);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.navIcon);
            holder.mTextView = (TextView) convertView.findViewById(R.id.navText);
            holder.marker = (View) convertView.findViewById(R.id.drawerMarker);
            holder.mNavItemLayout = (LinearLayout) convertView.findViewById(R.id.navItemLayout);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();

        }

        if (position != currentLocation)
            holder.marker.setVisibility(View.INVISIBLE);


        holder.mTextView.setText(mTexts[position]);
        holder.mImageView.setImageResource(icondIds[position]);
        return  convertView;
    }


    private static class ViewHolder {
        TextView mTextView;
        View marker;
        ImageView mImageView;
        LinearLayout mNavItemLayout;
    }

}
