package com.example.johnpconsidine.bengalworkouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by johnpconsidine on 4/16/16.
 */
public class GroupsAdapter extends BaseAdapter {
    private Context mContext;
    private String items[];
    public GroupsAdapter(Context context, String Items[]) {
        this.items= Items;
        this.mContext = context;
    }
    @Override
    public int getCount() {
        return items.length;

    }

    @Override
    public Object getItem(int position) {
        return  items[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grouprow, null);
            holder = new ViewHolder();
            holder.notificationLayout = (RelativeLayout) convertView.findViewById(R.id.pendingNotificationLayout);

            holder.notificationLayout.setVisibility(View.GONE);

            holder.groupname = (TextView) convertView.findViewById(R.id.groupText);
            holder.groupname.setText(items[position]);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
            holder.groupname.setText(items[position]);

        }



        return convertView;
    }

    private static class ViewHolder {
        TextView groupname;
        RelativeLayout notificationLayout;
    }
}
