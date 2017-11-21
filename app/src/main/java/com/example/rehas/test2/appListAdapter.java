package com.example.rehas.test2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Rehas on 11/21/2017.
 */

public class appListAdapter extends RecyclerView.Adapter <appListAdapter.appViewHolder> {

    private List appList;

    public appListAdapter(List appList) {
        this.appList = appList;
        this.appCount = appList.size();
    }

    public String appFullName;
    public String appName;
    public int appCount;

    @Override
    public appViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View appListView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.appcardview, parent, false);

        return new appViewHolder(appListView);
    }

    @Override
    public void onBindViewHolder(appViewHolder appNameHolder, int position) {
        appFullName =  appList.get(position).toString();
        appNameHolder.appFullName.setText(appFullName);

        appName = appList.get(position).
                             toString().
                             substring(appFullName.indexOf(' '));
        appNameHolder.appName.setText(appName);

        //appCount = appList.size();
        //appNameHolder.appCount.setText((String.valueOf(appCount)));

    }

    @Override
    public int getItemCount() {
        return appList.size();
    }


    public static class appViewHolder extends RecyclerView.ViewHolder {

        protected TextView appName;
        protected TextView appFullName;
        //protected TextView appCount;

        public appViewHolder(View itemView) {
            super(itemView);

            appName = (TextView) itemView.findViewById(R.id.appName);
            appFullName = (TextView) itemView.findViewById(R.id.appFullName);
            //appCount = (TextView) itemView.findViewById(R.id.appCount);

        }
    }
}