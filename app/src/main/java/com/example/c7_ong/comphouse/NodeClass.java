package com.example.c7_ong.comphouse;

import android.graphics.Point;
import android.widget.BaseAdapter;

public class NodeClass{
    private float radius = 50;
    float x;
    float y;
    String name;
    public float getRadius()
    {
        return radius;
    }

    public void setPointX(float PointX)
    {
        this.x = PointX;
    }

    public void setPointY(float PointY)
    {
        this.y = PointY;
    }

    public void setName(String officerName)
    {
        this.name = officerName;
    }
    public float getPointX()
    {
        return x;
    }

    public float getPointY()
    {
        return y;
    }

    public String getName()
    {
        return name;
    }

}
