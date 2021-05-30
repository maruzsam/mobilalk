package com.example.devicerequest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class listDeviceRequestsActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

    private RecyclerView mRecyclerView;
    private ArrayList<DeviceRequestItem> mItemList;
    private DeviceRequestAdapter mAdapter;

    private int gridNumber = 1;
    private boolean viewRow = true;
    private int deviceRequestItems = 0;

    private FrameLayout redCircle;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_devices);
/*
        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");
*/


        mRecyclerView = findViewById(R.id.recyclerView);
       // mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        mItemList = new ArrayList<>();

        mAdapter = new DeviceRequestAdapter(this, mItemList);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");


        // mRecyclerView.setAdapter(mAdapter);

        intializeData();

    }

    private void intializeData(){
        String[] itemsList = getResources().getStringArray(R.array.device_item_names);
        String[] itemsIntent = getResources().getStringArray(R.array.device_item_intents);
        String[] itemsCode = getResources().getStringArray(R.array.device_item_codes);
        String[] itemsSubject = getResources().getStringArray(R.array.device_item_subjects);

        //mItemList.clear();

        for (int i = 0; i<itemsList.length; i++){

            mItemList.add(new DeviceRequestItem(itemsList[i], itemsIntent[i], itemsCode[i], itemsSubject[i]));
        }

        //mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.device_request_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);


                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.view_selector:
                if(viewRow){
                    changeSpanCount(item, R.drawable.ic_view, 1);
                } else {
                    changeSpanCount(item, R.drawable.ic_view, 2);
                }
                return true;
            case R.id.add_button:
                return true;
            case R.id.list_button:
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    private void changeSpanCount(MenuItem item, int drawableId, int spanCount){
        viewRow = !viewRow;
        item.setIcon(drawableId);
        GridLayoutManager layoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
        layoutManager.setSpanCount(spanCount);
    }

/*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //ez kérdéses
        final MenuItem alertMenuItem = menu.findItem(R.id.add_button);
        FrameLayout rootView = (FrameLayout) alertMenuItem.getActionView();

        redCircle = (FrameLayout) rootView.findViewById(R.id.view_selector);
        contentTextView = (TextView) rootView.findViewById(R.id.view_selector);

      rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOptionsItemSelected(alertMenuItem);
            }
        });

       return super.onPrepareOptionsMenu(menu);
    }*/

    public void updateAlertIcon(){
        deviceRequestItems = (deviceRequestItems +1);
        if(deviceRequestItems > 0){
            contentTextView.setText(String.valueOf(deviceRequestItems));
        } else {
            contentTextView.setText(
                    ""
            );
        }
    }
}