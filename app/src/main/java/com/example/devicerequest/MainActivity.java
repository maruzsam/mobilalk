package com.example.devicerequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void listDeviceRequests(View view){
        Intent intent = new Intent(this, listDeviceRequestsActivity.class);
        startActivity(intent);
    }
    public void addDeviceRequests(View view){
        Intent intent = new Intent(this, addDeviceRequestsActivity.class);
        startActivity(intent);
    }

    public void deleteDeviceRequests(View view){
        Intent intent = new Intent(this, deleteDeviceRequestsActivity.class);
        startActivity(intent);
    }


    public void updateDeviceRequests(View view){
        Intent intent = new Intent(this, updateDeviceRequestsActivity.class);
        startActivity(intent);
    }




}