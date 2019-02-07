package com.example.c7_ong.comphouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText query;
    private TextView companyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        query = (EditText) findViewById(R.id.textBox);
        companyName = (TextView) findViewById(R.id.companyName);
    }

    public void searchQuery(View view) {
        String queryString = query.getText().toString();
        new FetchInfo(companyName).execute(queryString);
    }
}
