package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
{
    private ArrayList<HashMap<String, String>> mData;
    private LayoutInflater mInflater;
    private IMyItemClickListener mClickListener;

    public MyRecyclerViewAdapter(Context context, ArrayList<HashMap<String, String>> data)
    {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.data_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        HashMap<String, String> person = mData.get(i);

        viewHolder.myTextViewID.setText(person.get("ID"));
        viewHolder.myTextViewName.setText(person.get("NAME"));
        viewHolder.myTextViewAge.setText(person.get("AGE"));
        viewHolder.myTextViewAddress.setText(person.get("ADDRESS"));

    }

    @Override
    public int getItemCount()
    {
      return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView myTextViewID;
        TextView myTextViewName;
        TextView myTextViewAge;
        TextView myTextViewAddress;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            myTextViewID = itemView.findViewById(R.id.textViewID);
            myTextViewName = itemView.findViewById(R.id.textViewName);
            myTextViewAge = itemView.findViewById(R.id.textViewAge);
            myTextViewAddress = itemView.findViewById(R.id.textViewAddress);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
           if(mClickListener != null)
               mClickListener.onMyItemClick(v, getAdapterPosition());
        }
    }

    HashMap<String, String> getItem(int id)
    {
        return mData.get(id);
    }

    public void setClickListener(IMyItemClickListener itemClickListener)
    {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this interface (method) to respond to click events
    public interface IMyItemClickListener
    {
        void onMyItemClick(View view, int position);
    }
}
