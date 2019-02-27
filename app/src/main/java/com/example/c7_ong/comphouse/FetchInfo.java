package com.example.c7_ong.comphouse;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class FetchInfo{
    private static final String COMPHOUSE_BASE_URL = "https://api.companieshouse.gov.uk/search?";
    private static final String QUERY_PARAMETER = "q";
    private static final String ITEMS_PER_PAGE = "items_per_page";
    private static final String START_INDEX = "start_index";
    private static final String LOG_TAG = FetchInfo.class.getSimpleName();
    private final LinkedList<String> retCompanyList = new LinkedList<>();
    RecyclerView newView = new RecyclerView();
    private Context context;

    public void fetchCompany(String query)
    {
    RequestQueue queue = Volley.newRequestQueue(context);
    String mQuery = query;
    String uri = "https://api.companieshouse.gov.uk/search?q="+mQuery+"&items_per_page=5&start_index=1";
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, uri, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            //Log.d("Response", response.toString());
                //Log.d("Response", response.toString());
        try {
            JSONArray array = response.getJSONArray("items");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject title = array.getJSONObject(i);
                String retrievedTitle = title.getString("title");
                retCompanyList.addLast(retrievedTitle);
                //Log.d("Response", title.getString("title"));
            }
            newView.setCompName(retCompanyList);
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
        //RequestQueue requestQueue = Volley.newRequestQueue(context);
        queue.add(jsonObjectRequest);
    }

    public FetchInfo(Context context)
    {
        this.context = context;
    }


}

