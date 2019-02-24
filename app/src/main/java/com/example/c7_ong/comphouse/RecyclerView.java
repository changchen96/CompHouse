package com.example.c7_ong.comphouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedList;

public class RecyclerView extends AppCompatActivity {
    private final LinkedList<String> companyList = new LinkedList<>();
    MainActivity mainActivity = new MainActivity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        for (int i = 0; i < 5; i++)
        {
            mainActivity.getCompanies();

        }
    }
}
