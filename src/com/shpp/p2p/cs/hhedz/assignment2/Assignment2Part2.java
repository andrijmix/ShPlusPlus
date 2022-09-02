package com.shpp.p2p.cs.hhedz.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

public class Assignment2Part2 extends WindowProgram {
    //declare constants for the oval
    final int ovalWidth=130,ovalHeight=130;
    public void run() {
        //draw 4 back ovals at the corners
        setOval(0,0,ovalWidth,ovalHeight);
        setOval(getWidth()-ovalWidth,getHeight()-ovalHeight,ovalWidth,ovalHeight);
        setOval(getWidth()-ovalWidth,0,ovalWidth,ovalHeight);
        setOval(0,getHeight()-ovalHeight,ovalWidth,ovalHeight);
        // draw white rectangle in the center
        drawRect(ovalWidth/2,ovalHeight/2,getWidth()-ovalWidth,getHeight()-ovalHeight);
    }
    /*
        @param v1 The x coordinate of the upper-left corner of the bounding box for the oval.
        @param v2 The y coordinate of the upper-left corner of the bounding box for the oval.
        @param v3 The width of oval
        @param v4 The height of oval
        This method draws white rectangle according to the coordinates that we transmit
         */
    private void drawRect(int i, int i1, int i2, int i3) {
        GRect rect=new GRect(i,i1,i2,i3);
        rect.setFillColor(Color.WHITE);
        rect.setFilled(true);
        rect.setColor(Color.white);
        add(rect);
    }

    /*
    @param x The x coordinate of the upper-left corner of the bounding box for the oval.
    @param y The y coordinate of the upper-left corner of the bounding box for the oval.
    @param width The width of oval
    @param height The height of oval
    This method draws black oval according to the coordinates that we transmit
     */
    void setOval(double x,double y,double width, double height)
    {
        GOval oval=new GOval(x,y,width,height);
        oval.setFillColor(Color.BLACK);
        oval.setFilled(true);
        add(oval);
    }
}