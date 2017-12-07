package com.example.rehas.test2;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rehas on 12/4/2017.
 */

public class appNameStatusAdapter extends RecyclerView.Adapter<appNameStatusAdapter.appNameStatusHolder> {

    private ArrayList<packageInformation> appNameStatusList;
    public static boolean[] appNScheckStatus;
    public int appListSize;
//    public String appPackageName;
//    public boolean appPackageStatus;
    public packageInformation pInfo;

    public appNameStatusAdapter(ArrayList<packageInformation> appNameStatusList){
        this.appNameStatusList = appNameStatusList;
        this.appListSize = appNameStatusList.size();
        this.appNScheckStatus = new boolean[appListSize];

    }

    public Integer[] getCurrentSelectedItems(){
        Integer[] result = new Integer[appNScheckStatus.length];
        for (int i = 0; i<appNScheckStatus.length; i++) {
            if (appNScheckStatus[i] == true){
                result[i] = 1;
            }else{
                result[i] = 0;
            }
        }
        return result;
    }

    public packageInformation getItemAtPosition(int pos){
        return appNameStatusList.get(pos);
    }

    @Override
    public appNameStatusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View appNameStatusView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.appnamestatuscardview, parent, false);
        return new appNameStatusHolder(appNameStatusView);
    }

    @Override
    public void onBindViewHolder(appNameStatusHolder holder, int position) {

        pInfo = appNameStatusList.get(position); //appNameStatusList.get(position);
//        Log.d("My View Item", pInfo.getName());
        pInfo.setName(appNameStatusList.get(position).getName());
        pInfo.setIsEnabled(appNameStatusList.get(position).
                            getStatus().equalsIgnoreCase("Enabled"));

//        appPackageName = pInfo.getName();
//        appPackageStatus = pInfo.getStatus() == "Enabled" ? true : false;
        holder.appPackageName.setText(pInfo.getName());
        holder.appStatus.setText(pInfo.getStatus());
        holder.appNSCardCheckBox.setChecked(appNScheckStatus[position]);
    }

    @Override
    public int getItemCount() {
        return appNameStatusList.size();
    }

    public static class appNameStatusHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView appPackageName;
        protected TextView appStatus;
        protected CheckBox appNSCardCheckBox;

        public appNameStatusHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            appPackageName = (TextView) itemView.findViewById(R.id.appNameStatusName);
            appStatus  = (TextView) itemView.findViewById(R.id.appNameStatusStatus);
            appNSCardCheckBox = (CheckBox) itemView.findViewById(R.id.appNameStatusCardViewCheck);
        }

        @Override
        public void onClick(View v) {

            appNSCardCheckBox = (CheckBox) itemView.findViewById(R.id.appNameStatusCardViewCheck);
            appNSCardCheckBox.setChecked(!appNSCardCheckBox.isChecked());
            appNScheckStatus[getLayoutPosition()] = appNSCardCheckBox.isChecked();

        }
    }
}