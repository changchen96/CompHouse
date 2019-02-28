package com.example.c7_ong.comphouse;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class CompView extends AppCompatActivity{
    private String compName;
    private Context recyclerContext;
    private Company mComp;
    private RecyclerView mView;
    private DataAdapter dataAdapter;
    private static final String TAG = "recyclerView";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getIntent();
        //compName = getIntent().getStringExtra()
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        //query the data
        FetchInfo newFetchCompany = new FetchInfo(recyclerContext);
        newFetchCompany.fetchCompany(compName);
        Company recComp = new Company();
        mView = findViewById(R.id.recyclerview);
        dataAdapter = new DataAdapter(this, recComp.getCompanyList());
        mView.setAdapter(dataAdapter);
        mView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setCompName (String name)
    {
        this.compName = name;
    }

    public String getCompName()
    {
        return compName;
    }

    public CompView()
    {

    }

}
