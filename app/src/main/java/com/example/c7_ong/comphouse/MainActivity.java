package com.example.c7_ong.comphouse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText query;
    private TextView companyName;
    private Context mainContext;
    private FetchInfo newFetch;
    private CompView newRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        query = (EditText) findViewById(R.id.textBox);
        companyName = (TextView) findViewById(R.id.companyName);
        newFetch = new FetchInfo(this);
        newRecycler = new CompView(this);
    }

    public void searchQuery(View view) {
        String queryString = query.getText().toString();
        newRecycler.setCompName(queryString);
        Intent intent = new Intent (this, CompView.class);
        startActivityForResult(intent, 0);
    }
}
