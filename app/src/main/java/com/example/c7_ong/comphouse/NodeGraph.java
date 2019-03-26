package com.example.c7_ong.comphouse;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
    private float dragX;
    private float dragY;
    private float scaleStartX;
    private float scaleStartY;
    private float afterDragX;
    private float afterDragY;
    private float dx;
    private float dy;
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

    //initialize the instance variabls
    private void init(Context ctx) {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mOfficerNodePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOfficerNodePaint.setColor(Color.GRAY);
        mNodePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mNodePaint.setColor(Color.BLUE);
        tempOfficerList = new ArrayList<>();
        scaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    //function to calculate the angle between lines to the officer nodes
    private float[] computeLinePosition(final int pos, final float radius) {
        float[] result = mTempResult;
        int size = tempOfficerList.size(); //gets the size of the array
        Double startAngle = Math.PI * (9 / 8d);
        if (size > 30) {
            Double angle = startAngle + (pos * (Math.PI / (size-15)));
            result[0] = (float) (radius * Math.cos(angle)) + (finalX);
            result[1] = (float) (radius * Math.sin(angle)) + (finalY);
        } else if (size > 20 && size < 30) {
            Double angle = startAngle + (pos * (Math.PI / (size - 5)));
            result[0] = (float) (radius * Math.cos(angle)) + (finalX);
            result[1] = (float) (radius * Math.sin(angle)) + (finalY);
        } else {
            Double angle = startAngle + (pos * (Math.PI / (size)));
            result[0] = (float) (radius * Math.cos(angle)) + (finalX);
            result[1] = (float) (radius * Math.sin(angle)) + (finalY);
        }
        return result;
    }


    //onDraw draws the entire graph when called
    @Override
    protected void onDraw(Canvas canvas) {
        myCanvas = canvas;
        super.onDraw(myCanvas);
        myCanvas.save();
        myCanvas.translate(dx,dy);
        myCanvas.scale(scaleFactor, scaleFactor, scaleStartX, scaleStartY);
        final float dotRadius = mRadius + 30;
        if(finalX == 0 && finalY == 0)
        {
            finalX = mWidth/2;
            finalY = mHeight/2;
            //Draw the nodes and the lines according to the array size for the officers retrieved from the API
            for (int i = 0; i < tempOfficerList.size(); i++) {
                float[] xyData = computeLinePosition(i, dotRadius);
                float x = xyData[0];
                float y = xyData[1];
                myCanvas.drawLine(finalX, finalY, x, y, mTextPaint); // draw line from centre to the officer node
                myCanvas.drawCircle(x, y, 30, mOfficerNodePaint); //officer node is drawn here
                myCanvas.drawText(tempOfficerList.get(i).getOfficerName().toString(), x - 40, y, mTextPaint);//used to arrange the officer names in the bottom parts of the graph so it doesn't overlap
            }
            myCanvas.drawCircle(finalX, finalY, 50, mNodePaint); //draw the centre node which represents the company in blue
            myCanvas.restore();
            invalidate();
        }
        else
        {
            //Draw the nodes and the lines according to the array size for the officers retrieved from the API
            for (int i = 0; i < tempOfficerList.size(); i++) {
                float[] xyData = computeLinePosition(i, dotRadius);
                float x = xyData[0];
                float y = xyData[1];
                myCanvas.drawLine(finalX, finalY, x, y, mTextPaint);// draw line from centre to the officer node
                myCanvas.drawCircle(x, y, 30, mOfficerNodePaint);//officer node is drawn here
                if (i > 24 && i < tempOfficerList.size()) //used to arrange the officer names in the bottom parts of the graph so it doesn't overlap
                {
                    myCanvas.drawText(tempOfficerList.get(i).getOfficerName().toString(), x - 40, y+20, mTextPaint);
                }
                else
                {
                    myCanvas.drawText(tempOfficerList.get(i).getOfficerName().toString(), x - 40, y, mTextPaint);
                }
            }
            myCanvas.drawCircle(finalX, finalY, 50, mNodePaint);//draw the centre node which represents the company in blue
            myCanvas.restore();
            invalidate();
        }

    }
    //gets the value of the arrayList from another class and puts them into a local arrayList variable
    public void setTempOfficerList(ArrayList<Officer> tempOfficerList) {
        this.tempOfficerList = tempOfficerList;
    }

    //Function for ontouch events on the canvas
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleDetector.onTouchEvent(motionEvent);
        final int action = motionEvent.getAction();
        switch(action)
        {
            case MotionEvent.ACTION_DOWN:
            {
                final float x = motionEvent.getX(); //get the touched x coordinate
                final float y = motionEvent.getY(); //get the touched y coordinate
                dragX = x; //sets x coordinate for dragging
                dragY = y; //sets y coordinate for dragging
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {
                final float x = motionEvent.getX(); //get the touched x coordinate
                final float y = motionEvent.getY(); //get the touched y coordinate
                dx = x - dragX; //get the difference in x coordinates after dragging
                dy = y - dragY; //get the difference in y coordinates after dragging
                afterDragX = dx; //set the final dragged x coordinates
                afterDragY = dy; //set the final dragged x coordinates
                //invalidate();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                //invalidate();
                break;
            }
            case MotionEvent.ACTION_CANCEL:
            {
                break;
                //invalidate();
            }
            case MotionEvent.ACTION_POINTER_UP:
            {
                break;
                //invalidate();
            }
        }
        return true;
    }

    //code for scaling for the canvas
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
