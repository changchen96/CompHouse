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
    }

    public void drawGraph(View view) {
        int height = 1000;
        int width = 1000;
        int OldX = 100;
        int OldY = 70;
        int counter;
        int NewY = OldY;
        int NewX = OldX;
        newNodeClass = new NodeClass();
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.BLACK);
        float circleRadius = newNodeClass.getRadius();
        for (counter = 0; counter < 5; counter++)
        {
            mCanvas.drawCircle(OldX, NewY, circleRadius, mPaint1);
            mCanvas.drawLine(OldX, OldY, NewX, NewY, mPaint2);
            NewY = NewY + 200;
            OldY = NewY;
        }
        mImageView.setImageBitmap(mBitmap);
        view.invalidate();

    }
    @Override
    public void onResume()
    {
        super.onResume();
        drawGraph(mImageView);
    }
}
