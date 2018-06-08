package com.blueshroom.harry.androidlifecycles;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*

    Written by Harry Woodworth

    A simple app demonstrating to those interested in the Android Lifecycle when each
    lifecycle state is called and in what order.

    Uses: SharedPreferences, Toasts, and a RecyclerView

 */

public class MainActivity extends AppCompatActivity
{
    // Shared preferences key, var, string key, and editor.
    private static final String PREFERENCE_KEY = "3g2v38492083d029df34";
    SharedPreferences sharedpreferences;
    private static final String STATELIST = "State_List";
    SharedPreferences.Editor editor;

    // The data we are saving
    private ArrayList<String> data;
    private String[] data_strarr;

    // RecyclerView stuff
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // Button
    @BindView(R.id.clear_button) Button clear_button;

    // Called when the app is opened and created
    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind using Butterknife (For Button)
        ButterKnife.bind(this);

        // Open shared preferences and init data
        sharedpreferences = getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
        data = new ArrayList<>();

        // Preferences Editor
        editor = sharedpreferences.edit();

        // Get the data from SharedPreferences as a String
        String data_str = sharedpreferences.getString(STATELIST,"");

        // Delimit it by ',' to insert into data
        for(String str: data_str.split(",")) data.add(str);

        // Add 'onCreate'
        data.add("Create");

        // Set mRecyclerView to a fixed size
        mRecyclerView.setHasFixedSize(true);

        //mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // Create a Linear Layout Manager and set it to the recyclerView
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Create an Adapter and set it to the recyclerView
        data_strarr = data.toArray(new String[0]);
        mAdapter = new RecyclerAdapter(data_strarr);
        mRecyclerView.setAdapter(mAdapter);

        Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
    }

    // Called when the app is started after being created/restarted
    @OnLifecycleEvent(Lifecycle.Event.ON_START) public void onStart()
    {
        super.onStart();
        data.add("Start");
        saveState();
        Toast.makeText(this, "Started", Toast.LENGTH_SHORT).show();
    }

    // Called when the app is brought back in focus
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME) public void onResume()
    {
        super.onResume();
        data.add("Resume");
        saveState();
        Toast.makeText(this, "Resumed", Toast.LENGTH_SHORT).show();
    }

    // Called when the app is brought out of focus but still visible
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE) public void onPause()
    {
        super.onPause();
        data.add("Pause");
        saveState();
        Toast.makeText(this, "Paused", Toast.LENGTH_SHORT).show();
    }

    // Called when the app is brought out of focus and is no longer visible
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP) public void onStop()
    {
        super.onStop();
        data.add("Stop");
        saveState();
        Toast.makeText(this, "Stopped", Toast.LENGTH_SHORT).show();
    }

    // Called when the app is destroyed
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) public void onDestroy()
    {
        super.onDestroy();
        data.add("Destroy");
        saveState();
        Toast.makeText(this, "Destroyed", Toast.LENGTH_SHORT).show();
    }

    // Clear data and update recyclerView
    @OnClick(R.id.clear_button) void clearRecyclerView(View view)
    {
        data.clear();
        saveState();
        Toast.makeText(this, "RecycleView Cleared", Toast.LENGTH_SHORT).show();
    }

    // Saves to SharedPreferences using Editor
    private void saveState()
    {
        // Build the String from data, appending ',' between each String
        data_strarr = data.toArray(new String[0]);
        StringBuilder sb = new StringBuilder();
        for (String str : data_strarr)
            sb.append(str).append(",");

        // Put it into SharedPreferences under 'STATELIST'
        editor.putString(STATELIST,sb.toString());

        // Commit the edit
        editor.commit();
        mAdapter.updateData(data_strarr);
    }
}
