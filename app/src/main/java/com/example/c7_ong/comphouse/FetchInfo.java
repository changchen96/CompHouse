package com.example.c7_ong.comphouse;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

public class FetchInfo extends AsyncTask <String, Void, String>{
    RecyclerView newView = new RecyclerView();
    //WeakReference is used to prevent memory leaks
    private WeakReference<TextView> companyName;
    public FetchInfo(TextView companyName)
    {
        this.companyName = new WeakReference<>(companyName);
    }

    @Override
    protected String doInBackground(String... strings) {
        return ConnectionUtils.getCompanyInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try
        {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray compNameArray = jsonObject.getJSONArray("items");
            int i = 0;
            String compName = null;
            while (i < 5)
            {
                JSONObject company = compNameArray.getJSONObject(i);
                try
                {
                    compName = company.getString("title");
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                i++;
            }
            if (compName != null)
            {
                newView.getCompName(compName);
            }
        }
        catch(JSONException e)
        {

        }

    }

}

