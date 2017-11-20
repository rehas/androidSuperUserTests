package com.example.rehas.test2;

import android.app.WallpaperManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    Bitmap bgbm = null;

    File f = new File(Environment.getExternalStorageDirectory(), "seculogo.jpg");

    String path = f.getAbsolutePath();


    File f1 = new File(path);






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        Button goToMA = (Button) findViewById(R.id.goToMA);
        Button changeBG = (Button) findViewById(R.id.changeBG);
        Button getAppList = (Button) findViewById(R.id.getAppList);

        Log.i("our file path", path);

        bgbm = BitmapFactory.decodeFile(path);


        goToMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        changeBG.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                WallpaperManager wpman = WallpaperManager.getInstance(getApplicationContext());

                Log.i("Wallpaper Setting", "Will Try");

                if (f1.exists()){

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


        getAppList.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final PackageManager pm = getPackageManager();
//get a list of installed apps.
                List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
                Log.d("Size: ", String.valueOf(packages.size()));
                for (ApplicationInfo packageInfo : packages) {
                    Log.d("info: ","Installed package :" + packageInfo.packageName);
                    Log.d("info: ","Source dir : " + packageInfo.sourceDir);
                    Log.d("info: ","Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
                }
            }
        });
    }
}
