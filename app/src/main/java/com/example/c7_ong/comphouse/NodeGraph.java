package com.example.c7_ong.comphouse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class NodeGraph extends View {
    private ArrayList<Officer> tempOfficerList;
    private Context graphContext;
    private float mWidth;
    private float mHeight;
    private Paint mTextPaint;
    private Paint mNodePaint;
    private float mRadius;
    private int mLinePos;
    private final float[] mTempResult = new float[2];
    public NodeGraph(Context context) {
        super(context);
        init();
    }

    public NodeGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NodeGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
    }

    private float[] computeLinePosition(final int pos, final float radius)
    {
        float[] result = mTempResult;
        Double startAngle = Math.PI*(9/8d);
        Double angle = startAngle + (pos * (Math.PI/4));
        result[0] = (float) (radius * Math.cos(angle)) + (mWidth/2);
        result[1] = (float) (radius * Math.sin(angle)) + (mHeight/2);
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        //int officerCount = count;
        int count = 5;
        canvas.drawCircle(mWidth/2, mHeight/2, mRadius, mNodePaint);
        final float dotRadius = mRadius + 20;
        for (int i = 0; i < tempOfficerList.size(); i++)
        {
            float[] xyData = computeLinePosition(i, dotRadius);
            float x = xyData[0];
            float y = xyData[1];
            float endX = x + 100;
            float endY = y + 100;
            canvas.drawLine(x,y,endX, endY, mTextPaint);
        }
        final float radius = mRadius - 35;
        float[] xyData = computeLinePosition(mLinePos, radius);
        float x = xyData[0];
        float y = xyData[1];
        canvas.drawCircle(x,y,20, mNodePaint);

    }

    public ArrayList<Officer> getTempOfficerList() {
        return tempOfficerList;
    }

    public void setTempOfficerList(ArrayList<Officer> tempOfficerList) {
        this.tempOfficerList = tempOfficerList;
    }
}
