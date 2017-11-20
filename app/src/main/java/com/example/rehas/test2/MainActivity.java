package com.example.rehas.test2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import asyncTasks.*;


public class MainActivity extends AppCompatActivity {

    //dropDown instance  = new dropDown(this);

    View.OnClickListener onClickListener = new View.OnClickListener(){

        @Override
        public void onClick(View view){

            switch (view.getId()){

                case R.id.InstallButton:
                    try {
                        Runtime.getRuntime().exec(new String[]{"su", "-c", "pm  install /storage/sdcard0/testdeleteapp.apk"});
                        Log.i("", "Su anda install kismina girildi");

                    }catch (Exception e){
                        e.printStackTrace();
                        Log.e("",e.getMessage());
                    }

                    break;

                case R.id.UninstallButton:
                    try {
                        Runtime.getRuntime().exec(new String[]{"su", "-c", "pm  uninstall com.example.rehas.testdeleteproject"});

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("", e.getMessage());

                    }

                    break;


                case R.id.enableSettings:

                    new settingsSet(MainActivity.this).execute("Enable");

                    /*String[] apps = {"browser", "settings", "vending", "gallery3d"};

                    for (String app : apps)
                    {
                        String cmd = "pm " + "enable" + " com.android." + app;
                        try
                        {
                            Runtime.getRuntime().exec(new String[]{"su", "-c", cmd}).waitFor();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            e.printStackTrace();
                            Log.e("", e.getMessage());
                        }
                    }*/

                    break;



                case R.id.disableSettings:

                    new settingsSet(MainActivity.this).execute("Disable");


                    /*String[] apps2 = {"browser", "settings", "vending", "gallery3d"};

                    for (String app : apps2)
                    {
                        String cmd = "pm " + "disable" + " com.android." + app;
                        try
                        {
                            Runtime.getRuntime().exec(new String[]{"su", "-c", cmd}).waitFor();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            e.printStackTrace();
                            Log.e("", e.getMessage());
                        }
                    }*/

                    break;

                case R.id.enableDD:

                    new dropDown(MainActivity.this).execute("Enable");


                    /*String str = "settings put secure sysui_qs_tiles wifi,bt,cell,rotation,flashlight,hotspot,location";

                    try {
                        Runtime.getRuntime().exec(new String[]{"su", "-c", str}).waitFor();
                    } catch (Exception e){
                        e.printStackTrace();
                        e.printStackTrace();
                        Log.e("", e.getMessage());
                    }*/

                    break;

                case R.id.disableDD:
                    new dropDown(MainActivity.this).execute("Disable");

                    /*String str2 = "settings put secure sysui_qs_tiles rotation";

                    try {
                        Runtime.getRuntime().exec(new String[]{"su", "-c", str2}).waitFor();
                    } catch (Exception e){
                        e.printStackTrace();
                        e.printStackTrace();
                        Log.e("", e.getMessage());
                    }*/

                    break;

                case R.id.gotosa:
                    goToSecondActivity();
                    break;

                case R.id.asyncTestButton:
                    new asyncTest().execute("");
            }

        }

    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button install_button = (Button) findViewById(R.id.InstallButton);
        Button uninstall_button = (Button) findViewById(R.id.UninstallButton);
        Button enable_button = (Button) findViewById(R.id.enableSettings);
        Button disable_button = (Button) findViewById(R.id.disableSettings);
        Button enableDD_button  = (Button) findViewById(R.id.enableDD);
        Button disableDD_button = (Button) findViewById(R.id.disableDD);
        Button goToSA_button = (Button) findViewById(R.id.gotosa);
        Button asyncTest_button = (Button) findViewById(R.id.asyncTestButton);

        install_button.setOnClickListener(onClickListener);
        uninstall_button.setOnClickListener(onClickListener);
        enable_button.setOnClickListener(onClickListener);
        disable_button.setOnClickListener(onClickListener);
        enableDD_button.setOnClickListener(onClickListener);
        disableDD_button.setOnClickListener(onClickListener);
        goToSA_button.setOnClickListener(onClickListener);
        asyncTest_button.setOnClickListener(onClickListener);


    }

    private void goToSecondActivity() {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private class asyncTest extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {
            int res = 0;

            int j = 2 + (int) (Math.random() * 10);

            Log.i("Our j is currentlt: " , Integer.toString(j));
            //Log.i("Our Math Random is currentlt: " , Integer.toString((int) (Math.random()*10)));

            for (int i = 0; i< j; i++ ){

                Log.i("Our i is currently: ", Integer.toString(i));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                res = i;



            }

            return (Integer.toString(res));
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView asRes = (TextView) findViewById(R.id.asyncResult);
            asRes.setText(s);
        }
    }
}
