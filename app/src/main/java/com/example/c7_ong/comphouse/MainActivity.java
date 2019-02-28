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
    private CompView newComp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        query = (EditText) findViewById(R.id.textBox);
        companyName = (TextView) findViewById(R.id.companyName);
        newComp = new CompView();
    }

    public void searchQuery(View view) {
        String queryString = query.getText().toString();
        newComp.setCompName(queryString);
        Intent intent = new Intent (this, CompView.class);
        intent.putExtra("query", queryString);
        startActivityForResult(intent, 0);
    }
}
