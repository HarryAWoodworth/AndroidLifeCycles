package com.blueshroom.harry.androidlifecycles;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    // The data being presented (an array of Strings)
    private String[] data;

    // Provide a reference to the views for each data item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Each data item is a String in a TextView
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    public void updateData(String[] _data) {
        data = _data;
        notifyDataSetChanged();
    }

    // Constructor
    public RecyclerAdapter(String[] _data) {
        data = _data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // Create a new view
        TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // Create and return the ViewHolder
        ViewHolder viewHolder = new ViewHolder(textView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(data[position]);
    }

    // Return the size of data (each data point being an item)
    @Override
    public int getItemCount() { return data.length; }
}
