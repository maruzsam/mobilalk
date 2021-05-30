package com.example.devicerequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class addDeviceRequestsActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_requests);
/*
        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");
*/

    }

}