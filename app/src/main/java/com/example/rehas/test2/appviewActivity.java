package com.example.rehas.test2;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class appviewActivity extends AppCompatActivity {

    private RecyclerView appRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appview);

        TextView appCountText = (TextView) findViewById(R.id.appCount);


        Button goToSA = (Button) findViewById(R.id.gotoSecondActivityButton);
        goToSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle incomingAppBundle = getIntent().getExtras();
        List appList = incomingAppBundle.getParcelableArrayList("AppList");

        appCountText.setText(String.valueOf(appList.size()));


        appRecyclerView = (RecyclerView) findViewById(R.id.appListRecyclerView);
        appRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        appRecyclerView.setLayoutManager(llm);

        appRecyclerView.setAdapter(new appListAdapter(appList));

        Log.d("Bundles", incomingAppBundle.toString());
        Log.d("Bundles2", incomingAppBundle.getParcelableArrayList("AppList").toString());


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


    }

}
