package com.hashirbaig.android.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BoxDrawingView extends View{

    private static final String TAG = "BoxDrawingView";
    private static final String KEY_ARRAY_LIST = "KEY_ARRAY_LIST_MINE";
    private static final String KEY_DEF_CONS = "KEY_DEF_CONST_MINE";

    private ArrayList<Box> mBoxes = new ArrayList<>();
    private Box mBox;
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    public BoxDrawingView(Context context) {
        super(context);
        mBoxPaint = new Paint();
        mBoxPaint.setColor(getResources().getColor(R.color.boxColor));

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(getResources().getColor(R.color.boxBackgroundColor));
    }

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBoxPaint = new Paint();
        mBoxPaint.setColor(getResources().getColor(R.color.boxColor));

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(getResources().getColor(R.color.boxBackgroundColor));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        PointF point = new PointF(event.getX(), event.getY());
        String ACTION = "";

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                ACTION = "ACTION_DOWN";
                mBox = new Box(point);
                mBoxes.add(mBox);
                break;
            case MotionEvent.ACTION_MOVE:
                ACTION = "ACTION_MOVE";
                if(mBox != null) {
                    mBox.setCurrentPosition(point);
                    invalidate();

                    if(event.getPointerCount() == 2) {
                        mBox.setAngle((Math.atan((event.getY(1) - event.getY(0)) /
                                (event.getX(1) - event.getX(0))) * 360 / (2 * Math.PI)));
                    }
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                ACTION = "ACTION_CANCEL";
                mBox = null;
                break;
            case MotionEvent.ACTION_UP:
                ACTION = "ACTION_UP";
                mBox = null;
                break;
        }
        Log.i(TAG, ACTION + " at X = " + point.x + " Y = " + point.y);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(mBackgroundPaint);

        for (Box box : mBoxes) {
            float left = Math.min(box.getOrigin().x, box.getCurrentPosition().x);
            float right = Math.max(box.getOrigin().x, box.getCurrentPosition().x);
            float top = Math.min(box.getOrigin().y, box.getCurrentPosition().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrentPosition().y);

            canvas.save();
            canvas.rotate(box.getAngle(), box.getCentreX(), box.getCentreY());
            canvas.drawRect(left, top, right, bottom, mBoxPaint);
            canvas.restore();
        }

    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DEF_CONS, super.onSaveInstanceState());
        bundle.putSerializable(KEY_ARRAY_LIST, mBoxes);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        if(state instanceof Bundle)
            mBoxes = (ArrayList<Box>) bundle.getSerializable(KEY_ARRAY_LIST);
        super.onRestoreInstanceState(bundle.getParcelable(KEY_DEF_CONS));
    }
}
