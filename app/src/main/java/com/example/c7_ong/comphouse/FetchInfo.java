package com.example.c7_ong.comphouse;

import android.os.AsyncTask;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import java.lang.ref.WeakReference;

public class FetchInfo extends AsyncTask <String, Void, String>{
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
}
