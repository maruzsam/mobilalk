package com.example.devicerequest;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeviceRequestAdapter<deviceRequestFilter> extends RecyclerView.Adapter<DeviceRequestAdapter.ViewHolder> implements Filterable {

    private ArrayList<DeviceRequestItem> mDeviceRequestItemsData;
    private ArrayList<DeviceRequestItem> mDeviceRequestItemsDataAll;
    private Context mContext;
    private int lastPosition = -1;

    DeviceRequestAdapter(Context context, ArrayList<DeviceRequestItem> itemsData){
        this.mDeviceRequestItemsData = itemsData;
        this.mDeviceRequestItemsDataAll = itemsData;
        this.mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_devices, parent, false));
    }

    @Override
    public void onBindViewHolder( DeviceRequestAdapter.ViewHolder holder, int position) {
        DeviceRequestItem currentItem = mDeviceRequestItemsData.get(position);

        holder.bindTo(currentItem);

        if(holder.getAdapterPosition() > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mDeviceRequestItemsData.size();
    }

    @Override
    public Filter getFilter() {
        return deviceRequestFilter;
    }

    private Filter deviceRequestFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<DeviceRequestItem> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length()==0){
                results.count = mDeviceRequestItemsDataAll.size();
                results.values = mDeviceRequestItemsDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(DeviceRequestItem item : mDeviceRequestItemsDataAll){
                    if (item.getName().toLowerCase().contains(filterPattern)) {

                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            mDeviceRequestItemsData = (ArrayList) filterResults.values;
            notifyDataSetChanged();
        }
    };



    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mNameText;
        private TextView mIntentText;
        private TextView mCodeText;
        private TextView mSubjectText;


        public ViewHolder(View itemView){
            super(itemView);


            mNameText = itemView.findViewById(R.id.nameEditText);
            mIntentText = itemView.findViewById(R.id.intentEditText);
            mCodeText = itemView.findViewById(R.id.codeEditText);
            mSubjectText = itemView.findViewById(R.id.subjectEditText);

           /* itemView.findViewById(R.id.addDeviceRequestBtn).setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Log.d("Activity", "Add clickeld");

                   // ((listDeviceRequestsActivity)mContext).updateAlertIcon();
                }
            });*/

        }

        public void bindTo(DeviceRequestItem currentItem) {
            mNameText.setText(currentItem.getName());
            mIntentText.setText(currentItem.getIntent());
            mCodeText.setText(currentItem.getCode());
            mSubjectText.setText(currentItem.getSubject());


        }
    };

};

