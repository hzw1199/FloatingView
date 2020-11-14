package com.wuadam.floatingview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @Description:
 * @Author: zongheng.wu
 * @Date: 2020/11/14 1:40 PM
 */
public class FloatingClickableView extends FrameLayout {
    public FloatingClickableView(@NonNull Context context) {
        super(context);
    }

    public FloatingClickableView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FloatingClickableView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FloatingClickableView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    float[] temp = new float[]{0, 0};
    private final int THRESHOLD = 10;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                temp[0] = motionEvent.getRawX();
                temp[1] = motionEvent.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = (int)(motionEvent.getRawX() - temp[0]);
                int offsetY = (int)(motionEvent.getRawY() - temp[1]);
                // 如果确定不是点击，那么开始拦截，包括拦截最后的ACTION_UP，不传递给子View，防止拖动后松开，子View依然回调click
                if (Math.abs(offsetX) > THRESHOLD || Math.abs(offsetY) > THRESHOLD) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return onTouchEvent(motionEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // 优先处理拖动，防止被子View拦截导致无法拖动
        if (onTouchListener != null) {
            onTouchListener.onTouch(this, motionEvent);
        }
        return false;
    }

    private OnTouchListener onTouchListener;

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }
}
