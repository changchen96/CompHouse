package com.example.c7_ong.comphouse;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.widget.Toast;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.CompNameHolder> implements View.OnClickListener{
    private ArrayList<Company> mCompList;
    private LayoutInflater inflater;
    public DataAdapter (Context context, ArrayList<Company> compList)
    {
        inflater = LayoutInflater.from(context);
        this.mCompList = compList;
    }
    @NonNull
    @Override
    public DataAdapter.CompNameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.rec_view_content, viewGroup, false);
        return new CompNameHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.CompNameHolder compNameHolder, int i) {
        Company company = mCompList.get(i);
        compNameHolder.compName.setText(company.getCompanyTitle());
        Log.d("log", company.getCompanyTitle()+"*");
    }

    @Override
    public int getItemCount() {
        Log.d("size", mCompList.size()+"");
        return mCompList.size();
    }

    @Override
    public void onClick(View view) {
        
    }

    class CompNameHolder extends RecyclerView.ViewHolder
    {
        public final TextView compName;
        public CompNameHolder(View itemView)
        {
            super(itemView);
            compName = itemView.findViewById(R.id.word);
        }
    }

    public List<Company> getData()
    {
        return mCompList;
    }

    public void setData(ArrayList<Company> compList)
    {
        this.mCompList = compList;
        notifyDataSetChanged();
    }

}
