package com.example.c7_ong.comphouse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;


public class drawGraph extends AppCompatActivity {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint1;
    private Paint mPaint2;
    private NodeClass newNodeClass;
    private NodeGraph mNodeGraph;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_graph);
        mNodeGraph = findViewById(R.id.graphView);
        Intent graphIntent = this.getIntent();
        Bundle graphBundle = graphIntent.getExtras();
        ArrayList<Officer> officerList = (ArrayList<Officer>)graphBundle.getSerializable("officer");
        mNodeGraph.setTempOfficerList(officerList);
        mNodeGraph.invalidate();
        //newGraph.draw(mCanvas, officerList.size());
    }



}
