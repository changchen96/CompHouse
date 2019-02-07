package com.example.c7_ong.comphouse;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionUtils {
    private static final String COMPHOUSE_BASE_URL = "https://api.companieshouse.gov.uk/search?";
    private static final String QUERY_PARAMETER = "q";
    private static final String ITEMS_PER_PAGE = "items_per_page";
    private static final String START_INDEX = "start_index";
    private static final String LOG_TAG = ConnectionUtils.class.getSimpleName();

    static String getCompanyInfo(String query) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String companyJSONString = null;

        try {
            Uri requestURI = Uri.parse(COMPHOUSE_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAMETER, query)
                    .appendQueryParameter(ITEMS_PER_PAGE, "5")
                    .appendQueryParameter(START_INDEX, "1")
                    .build();

            URL requestURL = new URL(requestURI.toString());

            //Opens the url connection and sets the request
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestProperty("Authorization", "bzhAWZ5HDcTV95WkZ4GJ-Tse-Tt0GzLeZ1tBcvt_");
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //gets the input stream from the connection
            InputStream inputStream = urlConnection.getInputStream();

            //Buffered reader for the input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Used  to hold the incoming response from the API
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
                builder.append("\n");
                if (builder.length() == 0)
                {
                    return null;
                }
            }
            companyJSONString = builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        Log.d(LOG_TAG, companyJSONString);
        return companyJSONString;
    }
}