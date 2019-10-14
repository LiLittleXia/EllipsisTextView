package com.xq.ellipsis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class EllipsisTextView extends AppCompatTextView {

    private int flag = 3;
    private String text="哈哈哈";
    private int color;
    private float size;
    private Paint paint;
    private int h;
    private int w;

    public EllipsisTextView(Context context) {
        super(context);
    }

    private void init() {
        paint = new Paint();
        paint.setColor(color);
        paint.setTextSize(size);


    }

    public EllipsisTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EllipsisTextView);
        text= typedArray.getString(R.styleable.EllipsisTextView_eText);
        color = typedArray.getColor(R.styleable.EllipsisTextView_eTextColor,Color.WHITE);
        size = typedArray.getDimension(R.styleable.EllipsisTextView_eTextSize,40);
        typedArray.recycle();
        Log.e("lz","asdfasdf");
        init();
    }

    public EllipsisTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("lz","111123123123");

        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Rect bounds = new Rect();
        paint.getTextBounds(text+"。。。", 0, (text+"。。。").length(), bounds);
        h = bounds.height() + getPaddingBottom() + getPaddingTop();
        w = bounds.width() + getPaddingLeft() + getPaddingRight();
        Log.e("lz", "--" + w + "--" + h);

        setMeasuredDimension(w + 10, h + 10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (flag == 3) {
            flag = 1;
            canvas.drawText(text+"。。。", 0, h, paint);
        } else if (flag == 2) {
            flag = 3;
            canvas.drawText(text+"。。", 0, h, paint);
        } else if (flag == 1) {
            flag = 2;
            canvas.drawText(text+"。", 0, h, paint);
        }

        postInvalidateDelayed(1000);
    }
}
