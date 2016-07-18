package com.hashirbaig.android.draganddraw;

import android.graphics.PointF;

import java.io.Serializable;

public class Box implements Serializable{

    private PointF mInitialPosition;
    private PointF mCurrentPosition;
    private double angle;

    public Box(PointF initialPosition) {
        mInitialPosition = initialPosition;
    }

    public void setCurrentPosition(PointF currentPosition) {
        mCurrentPosition = currentPosition;
    }


    public PointF getCurrentPosition() {
        return mCurrentPosition;
    }

    public PointF getOrigin() {
        return mInitialPosition;
    }

    public float getAngle() {
        return ((float) angle);
    }

    public float getCentreX() {
        return (Math.abs(mCurrentPosition.x - mInitialPosition.x));
    }

    public float getCentreY() {
        return (Math.abs(mCurrentPosition.y - mInitialPosition.y));
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
