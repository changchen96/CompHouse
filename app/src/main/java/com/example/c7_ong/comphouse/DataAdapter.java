package com.example.c7_ong.comphouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.CompNameHolder> {
    private ArrayList<Company> mCompList;
    private LayoutInflater inflater;
    private final Context compContext;
    public DataAdapter (Context context, ArrayList<Company> compList)
    {
        this.compContext = context;
        inflater = LayoutInflater.from(context);
        this.mCompList = compList;
    }
    @NonNull
    @Override
    public DataAdapter.CompNameHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.rec_view_content, viewGroup, false);
        //itemView.setOnClickListener(compClick);
        return new CompNameHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.CompNameHolder compNameHolder, int i) {
        Company company = mCompList.get(i);
        compNameHolder.compName.setText(company.getCompanyTitle());
        compNameHolder.compNum.setText(company.getCompanyNumber());
    }

    @Override
    public int getItemCount() {
        //Log.d("size", mCompList.size()+"");
        return mCompList.size();
    }


    class CompNameHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView compName;
        public final TextView compNum;
        public CompNameHolder(View itemView)
        {
            super(itemView);
            compName = itemView.findViewById(R.id.word);
            compNum = itemView.findViewById(R.id.number);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int mPosition = getLayoutPosition();
            Company onClickComp = mCompList.get(mPosition);
            String compTitle = onClickComp.getCompanyTitle().toString();
            String compNo = onClickComp.getCompanyNumber().toString();
            Toast.makeText(compContext, compTitle + " Clicked!", Toast.LENGTH_SHORT).show();
            Intent graph = new Intent(compContext, drawGraph.class);
            graph.putExtra("compNo", compNo);
            compContext.startActivity(graph);
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
