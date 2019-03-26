package com.example.c7_ong.comphouse;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class drawGraph extends AppCompatActivity {
    private ArrayList<Officer> mOfficerList;
    private String compNumber;
    private NodeGraph mNodeGraph;
    private Button share;
    private File imagePath;
    private static final int PERMISSION_REQUEST_CODE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_graph);
        compNumber = getIntent().getStringExtra("compNo");
        mOfficerList = new ArrayList<>();
        fetchOfficers(compNumber);
        mNodeGraph = findViewById(R.id.graphView); //calls onDraw and draws the nodegraph
        share = (Button) findViewById(R.id.share);
        //checks sharing permissions
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkPermission())
                {
                    Bitmap screenshot = getImage();
                    saveImage(screenshot);
                }
                else
                {
                    if (checkPermission())
                    {
                        requestPermissionAndContinue();
                    }
                    else
                    {
                        Bitmap screenshot = getImage();
                        saveImage(screenshot);
                    }
                }
            }
        });
    }
    //function for checking sharing permission
    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ;
    }
    //function to request permission and share
    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Permission needed");
                alertBuilder.setMessage("Do you want to let the app store data in your device?");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(drawGraph.this, new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(drawGraph.this, new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            Bitmap screenshot = getImage();
            saveImage(screenshot);
        }
    }

    //function to get officer info from the API
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
                        String retrievedOfficerName = data.getString("name"); //gets the officer name and puts it to a string
                        officer.setOfficerName(retrievedOfficerName);
                        mOfficerList.add(officer);
                    }
                    mNodeGraph.setTempOfficerList(mOfficerList);
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

    //function to save a screenshot of the canvas
public Bitmap getImage()
{
    View screenshot = findViewById(R.id.graphView);
    screenshot.setBackgroundColor(Color.WHITE);
    screenshot.setDrawingCacheEnabled(true);
    return screenshot.getDrawingCache();
}

//function to share the image
private void saveImage (Bitmap image)
{
    Uri uri = null;
    try
    {
        imagePath = new File(Environment.getExternalStorageDirectory()+ "/nodeGraph.jpeg");
        FileOutputStream stream = new FileOutputStream(imagePath);
        image.compress(Bitmap.CompressFormat.JPEG, 90, stream);
        stream.flush();
        stream.close();
        uri = Uri.fromFile(imagePath);
    }
    catch (IOException e)
    {
        Log.d("Error", "IOException occured!");
    }
    Intent shareIntent = new Intent();
    shareIntent.setAction(Intent.ACTION_SEND);
    shareIntent.setType("image/jpeg");
    shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(imagePath.toString()));
    startActivity(Intent.createChooser(shareIntent, "Share image"));
}





}
