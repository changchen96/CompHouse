package com.example.c7_ong.comphouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

public class RecyclerView extends AppCompatActivity {
    private final LinkedList<String> companyList = new LinkedList<>();
    private EditText mainQuery;
    private TextView company;
    MainActivity mainActivity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mainQuery = (EditText) findViewById(R.id.textBox);
        company = (TextView) findViewById(R.id.companyName);
        for (int i = 0; i < 5; i++)
        {
            String queryString = mainQuery.getText().toString();
            new FetchInfo(company).execute(queryString);

        }
    }
}
