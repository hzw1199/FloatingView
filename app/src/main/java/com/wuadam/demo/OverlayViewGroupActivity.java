package com.wuadam.demo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.wuadam.floatingview.FloatingView;
import com.wuadam.floatingview.FloatingViewConfig;

public class OverlayViewGroupActivity extends AppCompatActivity {
    private SeekBar seekPaddingLeft;
    private SeekBar seekPaddingTop;
    private SeekBar seekPaddingRight;
    private SeekBar seekPaddingBottom;
    private AppCompatSpinner spinnerGravity;
    private FrameLayout lyViewGroup;

    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    private FloatingViewConfig.GRAVITY gravity = FloatingViewConfig.GRAVITY.LEFT_CENTER;

    private FloatingView floatingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlay_viewgroup);

        seekPaddingLeft = findViewById(R.id.seek_padding_left);
        seekPaddingTop = findViewById(R.id.seek_padding_top);
        seekPaddingRight = findViewById(R.id.seek_padding_right);
        seekPaddingBottom = findViewById(R.id.seek_padding_bottom);
        spinnerGravity = findViewById(R.id.spinner_gravity);
        lyViewGroup = findViewById(R.id.ly_view_group);

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

        lyViewGroup.post(new Runnable() {
            @Override
            public void run() {
                showFloatingView();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
            Toast.makeText(OverlayViewGroupActivity.this, "FloatingView clicked", Toast.LENGTH_SHORT).show();
        }
    };

    private void showFloatingView() {
        FloatingViewConfig config = new FloatingViewConfig.Builder()
                .setPaddingLeft(paddingLeft)
                .setPaddingTop(paddingTop)
                .setPaddingRight(paddingRight)
                .setPaddingBottom(paddingBottom)
                .setGravity(gravity)
                .setDisplayWidth(lyViewGroup.getWidth())
                .setDisplayHeight(lyViewGroup.getHeight())
                .build();
        if (floatingView != null) {
            floatingView.hide();
        }
        floatingView = new FloatingView(OverlayViewGroupActivity.this, R.layout.view_floating, config);
        floatingView.showOverlayViewGroup(lyViewGroup);
        floatingView.setOnClickListener(onClickListener);
    }
}
