package com.example.rehas.test2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rehas on 12/6/2017.
 */

public class packageInformation implements Parcelable {

    private String name;
    private Integer isEnabled;
    private String status;

    public packageInformation(){
    //No Arg Constructor
    }

    protected packageInformation(Parcel in) {
        readFromParcel(in);
    }

    public void setName (String name){
        this.name = name;
    }
    public void setIsEnabled (Boolean state){
        this.isEnabled = state ? 1:0;
        this.status = state? "Enabled" : "Disabled";
    }

    public String getName(){
        return this.name;
    }
    public String getStatus(){
        return this.status;
    }

    @Override
    public String toString() {
        return this.getName() + " " + this.getStatus();
    }

    public static final Creator<packageInformation> CREATOR = new Creator<packageInformation>() {
        @Override
        public packageInformation createFromParcel(Parcel in) {
            return new packageInformation(in);
        }

        @Override
        public packageInformation[] newArray(int size) {
            return new packageInformation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(isEnabled);
        dest.writeString(status  = isEnabled == 1? "Enabled" : "Disabled");
    }

    public void readFromParcel (Parcel in){
        name = in.readString();
        isEnabled = in.readInt();
        status = in.readString();
    }

}
