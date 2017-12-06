package com.example.rehas.test2;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class appNameStatusActivity extends AppCompatActivity {

    private RecyclerView appNSRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_name_status);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Button goBackToSa = (Button) findViewById(R.id.goBackToSA);
        goBackToSa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle incoming = getIntent().getExtras();
//        ArrayList<Parcelable> incoming = getIntent().getParcelableArrayListExtra("ansList");
        Log.d("MyIncomingBundle", incoming.toString());
//        Log.d("MyIncomingBundle2", incoming.getParcelableArrayList("ansList").toString());
//        ArrayList incoming2 = getIntent().getParcelableArrayListExtra("MyBundle");
        ArrayList appNSList = incoming.getParcelableArrayList("ansList");
//        ArrayList appNSList = incoming;
//        packageInformation item = (packageInformation) appNSList.get(0);
//        Log.d("My Item : ", item.toString());
//        Log.d("appNSList : ", appNSList.toString());
//        packageInformation test = incoming.getParcelable("Test");
//        packageInformation test2 = incoming.getParcelable("Test2");

//        Log.d("Testing parcelsss  ", incoming2.get(0).toString());

//        Log.d("Testing Parcels", test.toString());
//        Log.d("Testing Parcels", test2.toString());

        appNSRV = (RecyclerView) findViewById(R.id.appNSRV);
        //appNSRV.setHasFixedSize(true);

//        appNameStatusAdapter aNSA = new appNameStatusAdapter(appNSList);
//        aNSA.notifyDataSetChanged();

        appNSRV.setAdapter(new appNameStatusAdapter(appNSList));

//        LinearLayoutManager llm2 = new LinearLayoutManager(this);
//        llm2.setOrientation(LinearLayoutManager.VERTICAL);

        appNSRV.setLayoutManager(new LinearLayoutManager(getBaseContext()));


        Log.d("Parcelable List", appNSList.toString());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
