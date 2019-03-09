package com.example.c7_ong.comphouse;

import android.graphics.Point;
import android.widget.BaseAdapter;

public class NodeClass{
    private float radius = 50;
    private Point nodePoint;
    int x;
    int y;

    public float getRadius()
    {
        return radius;
    }

    public Point setPoint(int PointX, int PointY)
    {
        this.x = PointX;
        this.y = PointY;
        return nodePoint;
    }

    public int getPointX()
    {
        return x;
    }

    public int getPointY()
    {
        return y;
    }


}
