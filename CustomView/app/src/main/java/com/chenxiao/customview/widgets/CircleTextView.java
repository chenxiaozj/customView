package com.chenxiao.customview.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.chenxiao.customview.R;


/**
 * Created by chenxiao on 14-10-22.
 */
public class CircleTextView extends View {
    private int mTitleTextColor;
    private int mBackGroundColor;
    private float mTitleTextSize;
    private String mTitleText;
    private Paint mPaint;
    private Paint mBackGroudPaint;
    private Rect mBound;
    int mTextWidth;
    int mTextHeight;
    int mRadius;

    public CircleTextView(Context context) {
        super(context);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView);
        mTitleTextColor = typedArray.getColor(R.styleable.CircleTextView_textColor, Color.BLUE);
        mTitleTextSize = typedArray.getDimension(R.styleable.CircleTextView_textSize, 20);
        mBackGroundColor = typedArray.getColor(R.styleable.CircleTextView_backGroundColor, Color.BLUE);
        mTitleText = typedArray.getString(R.styleable.CircleTextView_text);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);

        mBackGroudPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        mBound = new Rect();
        if (TextUtils.isEmpty(mTitleText)) {
            mTitleText = "Nothing";
        }
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);

        mTextWidth = (int) mPaint.measureText(mTitleText);
        mTextHeight = (int) Math.abs(mPaint.getFontMetrics().top);

        mRadius = mTextWidth > mTextHeight ? mTextWidth / 2 : mTextHeight / 2;

    }

    public int getTitleTextColor() {
        return mTitleTextColor;
    }

    public void setTitleTextColor(int titleTextColor) {
        this.mTitleTextColor = titleTextColor;
        invalidate();
    }

    public int getBackGroundColor() {
        return mBackGroundColor;
    }

    public void setBackGroundColor(int backGroundColor) {
        this.mBackGroundColor = backGroundColor;
        invalidate();
    }

    public float getTitleTextSize() {
        return mTitleTextSize;
    }

    public void setTitleTextSize(float titleTextSize) {
        this.mTitleTextSize = titleTextSize;
        updateValues(titleTextSize);
        requestLayout();
    }

    private void updateValues(float titleTextSize) {
        mPaint.setTextSize(titleTextSize);
        if (TextUtils.isEmpty(mTitleText)) {
            mTitleText = "Nothing";
        }
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mBound);
        mTextWidth = (int) mPaint.measureText(mTitleText);
        mTextHeight = (int) Math.abs(mPaint.getFontMetrics().top);
        mRadius = mTextWidth > mTextHeight ? mTextWidth / 2 : mTextHeight / 2;
    }

    public String getTitleText() {
        return mTitleText;
    }

    public void setTitleText(String titleText) {
        this.mTitleText = titleText;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(
                Math.max(getSuggestedMinimumWidth(),
                        resolveSize(getPaddingLeft() + 2 * mRadius
                                        + getPaddingRight(),
                                widthMeasureSpec)),
                Math.max(getSuggestedMinimumHeight(),
                        resolveSize(getPaddingTop() + 2 * mRadius
                                        + getPaddingBottom(),
                                heightMeasureSpec)));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBackGroudPaint.setColor(mBackGroundColor);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, getMeasuredWidth() / 2, mBackGroudPaint);

        mPaint.setColor(mTitleTextColor);
        canvas.drawText(mTitleText, getWidth() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }

}
