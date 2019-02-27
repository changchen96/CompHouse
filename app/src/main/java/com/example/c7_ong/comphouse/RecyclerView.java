package com.example.c7_ong.comphouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class RecyclerView extends AppCompatActivity{
    private LinkedList<String> companyList = new LinkedList<>();
    private String compName;
    private String queryString = null;
    private static final String TAG = "recyclerView";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
    }


}
