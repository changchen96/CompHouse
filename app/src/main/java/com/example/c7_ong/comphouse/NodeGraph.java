package com.example.c7_ong.comphouse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Toast;

import org.w3c.dom.Node;

import java.util.ArrayList;

public class NodeGraph extends View{
    private ArrayList<Officer> tempOfficerList;
    private ArrayList<NodeClass> coordinateList;
    private float mWidth;
    private float mHeight;
    private Paint mTextPaint;
    private Paint mNodePaint;
    private Paint mOfficerNodePaint;
    private float mRadius;
    private final float[] mTempResult = new float[2];
    private float scaleFactor = 1.f;
    private ScaleGestureDetector scaleDetector;
    private float finalX;
    private float finalY;
    private float scaleStartX;
    private float scaleStartY;
    private Canvas myCanvas;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        mRadius = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
    }

    public NodeGraph(Context context) {
        super(context);
        init(context);
    }

    public NodeGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NodeGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context ctx) {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mOfficerNodePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOfficerNodePaint.setColor(Color.GRAY);
        mNodePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNodePaint.setColor(Color.BLUE);
        tempOfficerList = new ArrayList<>();
        coordinateList = new ArrayList<>();
        //scaleDetector = new ScaleGestureDetector(this, new ScaleListener());
        scaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
        //Log.d("width", mWidth);
    }

    private float[] computeLinePosition(final int pos, final float radius) {
        float[] result = mTempResult;
        int size = tempOfficerList.size();
        //Log.d("arrSize", size+"");
        Double startAngle = Math.PI * (9 / 8d);
        if (size > 30) {
            Double angle = startAngle + (pos * (Math.PI / (size - 15)));
            result[0] = (float) (radius * Math.cos(angle)) + (mWidth / 2);
            result[1] = (float) (radius * Math.sin(angle)) + (mHeight / 2);
        } else if (size > 20 && size < 30) {
            Double angle = startAngle + (pos * (Math.PI / (size - 5)));
            result[0] = (float) (radius * Math.cos(angle)) + (mWidth / 2);
            result[1] = (float) (radius * Math.sin(angle)) + (mHeight / 2);
        } else {
            Double angle = startAngle + (pos * (Math.PI / (size)));
            result[0] = (float) (radius * Math.cos(angle)) + (mWidth / 2);
            result[1] = (float) (radius * Math.sin(angle)) + (mHeight / 2);
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        myCanvas = canvas;
        super.onDraw(myCanvas);
        myCanvas.save();
        myCanvas.scale(scaleFactor, scaleFactor, scaleStartX, scaleStartY);
        final float dotRadius = mRadius + 30;
        //Log.d("arraySize", "Size" + tempOfficerList.size());
        for (int i = 0; i < tempOfficerList.size(); i++) {
            float[] xyData = computeLinePosition(i, dotRadius);
            float x = xyData[0];
            float y = xyData[1];
            NodeClass node = new NodeClass();
            node.setPointX(x);
            node.setPointY(y);
            node.setName(tempOfficerList.get(i).getOfficerName().toString());
            coordinateList.add(node);
            myCanvas.drawLine(mWidth / 2, mHeight / 2, x, y, mTextPaint);
            myCanvas.drawCircle(x, y, 30, mOfficerNodePaint);
            myCanvas.drawText(tempOfficerList.get(i).getOfficerName().toString(), x - 40, y, mTextPaint);
        }
        myCanvas.drawCircle(mWidth / 2, mHeight / 2, 50, mNodePaint);
        myCanvas.restore();
        invalidate();

    }

    public ArrayList<NodeClass> getNodes() {
        return coordinateList;
    }

    public void setTempOfficerList(ArrayList<Officer> tempOfficerList) {
        this.tempOfficerList = tempOfficerList;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleDetector.onTouchEvent(motionEvent);
        final int action = motionEvent.getAction();
        /*switch(action)
        {
            case MotionEvent.ACTION_DOWN:
            {
                final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                finalX = x;
                finalY = y;
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                final float x = motionEvent.getX();
                final float y = motionEvent.getY();
                final float dx = x - finalX;
                final float dy = y - finalY;

                invalidate();
                finalX = x;
                finalY = y;
                break;
            }
        }*/
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 10.0f));
            scaleStartX = detector.getFocusX();
            scaleStartY = detector.getFocusY();
            invalidate();
            return true;
        }
    }


}
