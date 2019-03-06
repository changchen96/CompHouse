package com.example.c7_ong.comphouse;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class drawGraph extends AppCompatActivity {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint1;
    private Paint mPaint2;
    private NodeClass newNodeClass;
    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_graph);
        mImageView = (ImageView) findViewById(R.id.graphView);
        drawGraph(mImageView);
    }

    public void drawGraph(View view) {


    }
    @Override
    public void onResume()
    {
        super.onResume();
        drawGraph(mImageView);
    }
}
