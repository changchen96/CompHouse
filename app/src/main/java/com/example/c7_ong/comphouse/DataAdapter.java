package com.example.c7_ong.comphouse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import android.content.Context;
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private List<Company> compList;
    private Context context;
    //private final LayoutInflater mInflater;

    public DataAdapter (Context context, List<Company> compList)
    {
        this.context = context;
        this.compList = compList;
    }
    @NonNull
    @Override
    public DataAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mCompView = LayoutInflater.from(context).inflate(R.layout.activity_recycler_view, viewGroup, false);
        return new DataViewHolder(mCompView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.DataViewHolder dataViewAdapter, int i) {
        Company company = compList.get(i);
        dataViewAdapter.compName.setText(company.getCompanyTitle());
    }

    @Override
    public int getItemCount() {
        return compList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder
    {
        public TextView compName;
        public DataViewHolder(View compView)
        {
            super(compView);
            compName = compView.findViewById(R.id.word);
        }
    }
}
