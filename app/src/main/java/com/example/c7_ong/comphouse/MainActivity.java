package com.example.c7_ong.comphouse;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private EditText query;
    private TextView companyName;
    private Context mainContext;
    private FetchInfo newFetch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        query = (EditText) findViewById(R.id.textBox);
        companyName = (TextView) findViewById(R.id.companyName);
        newFetch = new FetchInfo(this);
    }

    public void searchQuery(View view) {
        String queryString = query.getText().toString();
        newFetch.fetchCompany(queryString);
//        Intent intent = new Intent (this, RecyclerView.class);
//        startActivityForResult(intent, 0);
    }
}
