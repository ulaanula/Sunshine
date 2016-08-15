package com.example.android.sunshine.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Anna on 27.04.2016.
 */
public class Compas extends View {

    private Paint mCircleBrush;
    private Paint mTextBrush;
    private int mCenterX;
    private int mCenterY;
    private int mFaceRadius;

    private float windSpeed;
    private float windDir;
    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }
    public void setWindDir(float windDir) {
        this.windDir = windDir;
    }


    public Compas(Context context) {
        super(context);
    }

    public Compas(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public Compas(Context context, AttributeSet attrs, int defaultStyle){
        super(context, attrs,defaultStyle);
    }


    @Override
    protected void onMeasure(int wMeasureSpec, int hMeasureSpec){

        int hSpecMode = MeasureSpec.getMode(hMeasureSpec);
        int hSpecSize = MeasureSpec.getSize(hMeasureSpec);
        int myHeight = hSpecSize;

        if(hSpecMode == MeasureSpec.EXACTLY){
            myHeight = hSpecSize;
        }else if(hSpecMode == MeasureSpec.AT_MOST){
            // Wrap Content
        }

        int wSpecMode = MeasureSpec.getMode(wMeasureSpec);
        int wSpecSize = MeasureSpec.getSize(wMeasureSpec);
        int myWidth = wSpecSize;

        if(wSpecMode == MeasureSpec.EXACTLY){
            myWidth = wSpecSize;
        }else if(wSpecMode == MeasureSpec.AT_MOST){
            // Wrap Content
        }

        setMeasuredDimension(myWidth, myHeight);
    }

    private void init(){

        mCircleBrush = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleBrush.setStyle(Paint.Style.FILL_AND_STROKE);
        mCircleBrush.setColor(getResources().getColor(R.color.sunshine_blue));
        mTextBrush = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas){

        super.onDraw(canvas);
        init();

        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        int r;
        if(w > h){
            r = h / 2;
        }else{
            r = w/2;
        }
        mFaceRadius = r;
        mCenterX = r;
        mCenterY = r;

        canvas.drawCircle(w /2, h/2, r, mCircleBrush);
        mCircleBrush.setColor(getResources().getColor(R.color.sunshine_light_blue));
        canvas.drawCircle(w / 2, h / 2, r - 55, mCircleBrush);
        mCircleBrush.setStrokeWidth(8);
        mCircleBrush.setColor(Color.WHITE);

        canvas.drawLine(w / 2, h / 2, (float) (w / 2 + (r - 55) * Math.sin(Math.toRadians(windDir))), (float) (h / 2 - (r - 55) * Math.cos(Math.toRadians(windDir))), mCircleBrush);
        drawDirections(canvas);
    }


    private void drawDirections(Canvas canvas) {

        mTextBrush.setTextSize(40.0f);
        mTextBrush.setTypeface(Typeface.DEFAULT_BOLD);
        mTextBrush.setColor(Color.WHITE);
        mTextBrush.setTextAlign(Paint.Align.CENTER);

        canvas.drawText("N", mCenterX, mCenterY - mFaceRadius + 45.0f, mTextBrush);
        canvas.drawText("W", mCenterX - mFaceRadius + 28.0f, mCenterY, mTextBrush);
        canvas.drawText("E", mCenterX + mFaceRadius - 28.0f, mCenterY, mTextBrush);
        canvas.drawText("S", mCenterX, mCenterY + mFaceRadius - 15.0f, mTextBrush);

    }


}
