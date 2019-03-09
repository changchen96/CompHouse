package com.example.c7_ong.comphouse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
import java.util.Map;


public class drawGraph extends AppCompatActivity {
    private ArrayList<Officer> mOfficerList;
    private String compNumber;
    private NodeGraph mNodeGraph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_graph);
        compNumber = getIntent().getStringExtra("compNo");
        mOfficerList = new ArrayList<>();
        mNodeGraph = findViewById(R.id.graphView);
        fetchOfficers(compNumber);
    }

    private void fetchOfficers(String companyNo)
    {
        RequestQueue officerQueue = Volley.newRequestQueue(this);
        String compNoQuery = companyNo;
        String uri = "https://api.companieshouse.gov.uk/company/"+compNoQuery+"/officers";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONArray officerArray = response.getJSONArray("items");
                    for (int i = 0; i < officerArray.length(); i++)
                    {
                        JSONObject data = officerArray.getJSONObject(i);
                        Officer officer = new Officer();
                        String retrievedOfficerName = data.getString("name");
                        officer.setOfficerName(retrievedOfficerName);
                        mOfficerList.add(officer);
                        //Log.d("officerName", mOfficerList.toString());
                    }
                    Log.d("officerListSize", mOfficerList.size()+"");
                    mNodeGraph.setTempOfficerList(mOfficerList);
                    mNodeGraph.invalidate();
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
        officerQueue.add(jsonObjectRequest);
    }

}
