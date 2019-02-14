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
    private Paint mPaint;
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
        int height = view.getHeight();
        int width = view.getWidth();
        int x = 1;
        int y = 1;
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint.setColor(Color.BLUE);
        float circleRadius = newNodeClass.getRadius();
        for (y = 1; y < 6; y++) {
            mCanvas.drawCircle(x, y, circleRadius, mPaint);
        }
        view.invalidate();

    }
}
