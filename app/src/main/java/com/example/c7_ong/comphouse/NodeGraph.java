package com.example.c7_ong.comphouse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class NodeGraph extends View{
    private ArrayList<Officer> tempOfficerList;
    private Context graphContext;
    private Paint mTextPaint;
    private Paint mNodePaint;
    private float mRadius;
    private final Path path;
    private int mLinePos;
    private final float[] mTempResult = new float[2];
    public NodeGraph(Context context) {
        super(context);
        path = new Path();
        init();
    }

    public NodeGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        init();
    }

    public NodeGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        path = new Path();
        init();
    }

    private void init()
    {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mNodePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNodePaint.setColor(Color.BLUE);
        mLinePos = 0;
        tempOfficerList = new ArrayList<>();
        //Log.d("width", mWidth);
    }

    private float[] computeLinePosition(final int pos, final float radius)
    {
        float[] result = mTempResult;
        Double startAngle = Math.PI*(9/8d);
        Double angle = startAngle + (pos * (Math.PI/4));
        result[0] = (float) (radius * Math.cos(angle)) + (getWidth()/2);
        result[1] = (float) (radius * Math.sin(angle)) + (getHeight()/2);
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth()/2, getHeight()/2, 100, mNodePaint);
        final float dotRadius = mRadius + 20;
        Log.d("arraySize", "Size" + tempOfficerList.size());
        for (int i = 0; i < tempOfficerList.size(); i++)
        {
            float[] xyData = computeLinePosition(i, dotRadius);
            float x = xyData[0];
            float y = xyData[1];
            float endX = x + 300;
            float endY = y + 300;
            canvas.drawLine(x,y,endX,endY,mNodePaint);
            canvas.drawCircle(endX,endY,20, mTextPaint);
        }

    }

    public ArrayList<Officer> getTempOfficerList() {
        return tempOfficerList;
    }

    public void setTempOfficerList(ArrayList<Officer> tempOfficerList) {
        this.tempOfficerList = tempOfficerList;
    }
}
