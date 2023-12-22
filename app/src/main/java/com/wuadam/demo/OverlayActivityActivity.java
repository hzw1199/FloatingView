package com.wuadam.demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.wuadam.floatingview.FloatingView;
import com.wuadam.floatingview.FloatingViewConfig;

public class OverlayActivityActivity extends AppCompatActivity {
    private SeekBar seekPaddingLeft;
    private SeekBar seekPaddingTop;
    private SeekBar seekPaddingRight;
    private SeekBar seekPaddingBottom;
    private AppCompatSpinner spinnerGravity;

    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    private FloatingViewConfig.GRAVITY gravity = FloatingViewConfig.GRAVITY.LEFT_CENTER;

    private FloatingView floatingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay_system_activity);

        seekPaddingLeft = findViewById(R.id.seek_padding_left);
        seekPaddingTop = findViewById(R.id.seek_padding_top);
        seekPaddingRight = findViewById(R.id.seek_padding_right);
        seekPaddingBottom = findViewById(R.id.seek_padding_bottom);
        spinnerGravity = findViewById(R.id.spinner_gravity);

        seekPaddingLeft.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekPaddingTop.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekPaddingRight.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekPaddingBottom.setOnSeekBarChangeListener(onSeekBarChangeListener);

        spinnerGravity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FloatingViewConfig.GRAVITY gravityTmp = FloatingViewConfig.GRAVITY.values()[position];
                if (gravity.equals(gravityTmp)) {
                    return;
                }
                gravity = gravityTmp;
                showFloatingView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        showFloatingView();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (floatingView != null) {
            floatingView.hide();
        }
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (seekBar == seekPaddingLeft) {
                paddingLeft = progress;
            } else if (seekBar == seekPaddingTop) {
                paddingTop = progress;
            } else if (seekBar == seekPaddingRight) {
                paddingRight = progress;
            } else if (seekBar == seekPaddingBottom) {
                paddingBottom = progress;
            }
            showFloatingView();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(OverlayActivityActivity.this, "FloatingView clicked", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener onDoubleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(OverlayActivityActivity.this, "FloatingView double clicked", Toast.LENGTH_SHORT).show();
            floatingView.full();
        }
    };

    private void showFloatingView() {
        FloatingViewConfig config = new FloatingViewConfig.Builder()
                .setPaddingLeft(paddingLeft)
                .setPaddingTop(paddingTop)
                .setPaddingRight(paddingRight)
                .setPaddingBottom(paddingBottom)
                .setGravity(gravity)
                .setScaleHandlerId(R.id.fl)
                .build();
        if (floatingView != null) {
            floatingView.hide();
        }
        floatingView = new FloatingView(OverlayActivityActivity.this, R.layout.view_floating, config);
        floatingView.showOverlayActivity();
        floatingView.setOnClickListener(onClickListener);
        floatingView.setOnDoubleClickListener(onDoubleClickListener);
    }
}
