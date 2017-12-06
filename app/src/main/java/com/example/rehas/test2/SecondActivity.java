package com.example.rehas.test2;

import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements Serializable {

    Bitmap bgbm = null;

    File f = new File(Environment.getExternalStorageDirectory(), "seculogo.jpg");

    String path = f.getAbsolutePath();


    File f1 = new File(path);

    /*public class pInfo implements Serializable {
        public String name;
        public Boolean status;

        pInfo(String name, Boolean status){
            this.name = name;
            this.status = status;
        }
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        Button goToMA = (Button) findViewById(R.id.goToMA);
        Button changeBG = (Button) findViewById(R.id.changeBG);
        Button getAppList = (Button) findViewById(R.id.getAppList);
        Button getAppNames = (Button) findViewById(R.id.getAppNames);

        Log.i("our file path", path);

        bgbm = BitmapFactory.decodeFile(path);


        goToMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        changeBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WallpaperManager wpman = WallpaperManager.getInstance(getApplicationContext());

                Log.i("Wallpaper Setting", "Will Try");

                if (f1.exists()) {

                    Log.i("Our File", "Our File Exists");

                    try {
                        Log.i("Wallpaper Setting", "Trying");

                        wpman.setBitmap(bgbm);
                    } catch (IOException e) {
                        Log.i("Wallpaper Setting", "Failed");

                        e.printStackTrace();
                    }

                }


            }

        });


        getAppList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final PackageManager pm = getPackageManager();
//get a list of installed apps.
                List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                Log.d("Size: ", String.valueOf(packages.size()));
                for (ApplicationInfo packageInfo : packages) {
                    Log.d("info: ", "Installed package :" + packageInfo.packageName);
                    Log.d("info: ", "Source dir : " + packageInfo.sourceDir);
                    Log.d("info: ", "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
                }

                for(ApplicationInfo packageInfo : packages){

                    Log.d("App Name", packageInfo.packageName);

                }

                goToAppViewActivity(packages);

            }
        });

        getAppNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PackageManager pm = getPackageManager();
                List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                ArrayList <packageInformation> packageNamesWithStatus = new ArrayList<>();

                for (ApplicationInfo pInfo : packages){

                    packageInformation pinf = new packageInformation();

                    pinf.setName(pInfo.packageName);
                    pinf.setIsEnabled(pInfo.enabled);

//                    packageInformation item = new pa(packageInformation.packageName, packageInformation.enabled );

                    packageNamesWithStatus.add(pinf);

                    Log.d("pInfo", pinf.getName() + "  " + pinf.getStatus());

                }

                goToAppNameStatusActivity(packageNamesWithStatus);


            }
        });
    }



    private void goToAppViewActivity(List applist) {

        Log.d("Gonderilen App List", applist.toString());

        ArrayList data =  (ArrayList) applist;

        Bundle dataBundle = new Bundle();

        dataBundle.putParcelableArrayList("AppList", data);

        Intent intent = new Intent(this, appviewActivity.class);
        intent.putExtras(dataBundle);

        Log.d("Gonderilen App Listno:1", applist.toArray()[0].toString());

        startActivity(intent);
    }

    private void goToAppNameStatusActivity (ArrayList<packageInformation> appNameStatuslist){
        //ArrayList<packageInformation> data = appNameStatuslist;
        Bundle dataBundle = new Bundle();
        dataBundle.putParcelableArrayList("ansList", appNameStatuslist);
//        dataBundle.putParcelable("Test", appNameStatuslist.get(0));
//        dataBundle.putParcelable("Test2", appNameStatuslist.get(1));

        Intent intent = new Intent(this, appNameStatusActivity.class);
        intent.putExtras(dataBundle);
//        intent.putParcelableArrayListExtra("ansList", appNameStatuslist);
        startActivity(intent);
    }
}