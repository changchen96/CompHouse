package com.example.c7_ong.comphouse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewAdapter> {
    @NonNull
    @Override
    public DataAdapter.DataViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.DataViewAdapter dataViewAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DataViewAdapter extends RecyclerView.ViewHolder
    {
        public final TextView dataView;
        final DataAdapter dataAdapter;
        public DataViewAdapter(View itemView, DataViewAdapter adapter)
        {
            super(itemView);
            {
                //dataView = itemView.findViewById(R.id.word);
            }
        }
    }
}
