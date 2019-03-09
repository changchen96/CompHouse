package com.example.c7_ong.comphouse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CompView extends AppCompatActivity{
    private ArrayList<Company> newCompList;
    private String compName;
    private RecyclerView mView;
    private DataAdapter dataAdapter;
    private static final String TAG = "recyclerView";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        compName = getIntent().getStringExtra("query");
        //Log.d("log", compName);
        mView = findViewById(R.id.recyclerview);
        newCompList = new ArrayList<>();
        mView.setLayoutManager(new LinearLayoutManager(this));
        fetchCompany(compName);
    }

    public CompView()
    {

    }
        private void fetchCompany(String query)
        {
            RequestQueue queue = Volley.newRequestQueue(CompView.this);
            String mQuery = query;
            String uri = "https://api.companieshouse.gov.uk/search/companies?q="+mQuery+"";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray array = response.getJSONArray("items");
                        for (int i = 0; i < array.length(); i++)
                        {
                            JSONObject data = array.getJSONObject(i);
                            Company company = new Company();
                            String retrievedTitle = data.getString("title");
                            String retrievedNumber = data.getString("company_number");
                            company.setCompanyTitle(retrievedTitle);
                            company.setCompanyNumber(retrievedNumber);
                            newCompList.add(company);
                            //Log.d("compList", newCompList.size()+"");
                        }
                        dataAdapter = new DataAdapter(getApplicationContext(), newCompList);
                        mView.setAdapter(dataAdapter);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Volley", error.toString());
                }
            })
            {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError
                {
                    Map<String, String> authParam = new HashMap<String, String>();
                    authParam.put("Authorization","bzhAWZ5HDcTV95WkZ4GJ-Tse-Tt0GzLeZ1tBcvt_");
                    return authParam;
                }
            };
            queue.add(jsonObjectRequest);
        }


    }
