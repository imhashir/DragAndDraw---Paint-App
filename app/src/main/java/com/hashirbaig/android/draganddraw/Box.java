package com.hashirbaig.android.draganddraw;

import android.graphics.PointF;

import java.io.Serializable;

public class Box implements Serializable{

    private PointF mInitialPosition;
    private PointF mCurrentPosition;

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
}
