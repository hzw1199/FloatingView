package com.wuadam.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.wuadam.floatingview.FloatingView;
import com.wuadam.floatingview.FloatingViewConfig;

public class AdvancedControlActivity extends AppCompatActivity {
    private SeekBar seekPaddingLeft;
    private SeekBar seekPaddingTop;
    private SeekBar seekPaddingRight;
    private SeekBar seekPaddingBottom;
    private AppCompatSpinner spinnerGravity;

    private int paddingLeft, paddingTop, paddingRight, paddingBottom;
    private FloatingViewConfig.GRAVITY gravity = FloatingViewConfig.GRAVITY.LEFT_CENTER;

    private FloatingView floatingView;
    private static final int OVERLAY_PERMISSION_REQ_CODE = 1234;

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

        showFloatingView();
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

    private void doShowFloatingView() {
        FloatingViewConfig config = new FloatingViewConfig.Builder()
                .setPaddingLeft(paddingLeft)
                .setPaddingTop(paddingTop)
                .setPaddingRight(paddingRight)
                .setPaddingBottom(paddingBottom)
                .setGravity(gravity)
                .build();
        if (floatingView != null) {
            floatingView.hide();
        }
        LayoutInflater mInflater = LayoutInflater.from(AdvancedControlActivity.this);
        View rootView = mInflater.inflate(R.layout.view_advanced_control, null, false);
        floatingView = new FloatingView(AdvancedControlActivity.this, rootView, config);

        final View vTop = rootView.findViewById(R.id.v_top);
        final View vMiddle = rootView.findViewById(R.id.v_middle);
        final View vBottom = rootView.findViewById(R.id.v_bottom);
        final TextView tvTime = rootView.findViewById(R.id.tv_time);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == vTop) {
                    Toast.makeText(AdvancedControlActivity.this, "Top clicked", Toast.LENGTH_SHORT).show();
                } else if (v == vMiddle) {
                    Toast.makeText(AdvancedControlActivity.this, "Middle clicked", Toast.LENGTH_SHORT).show();
                } else if (v == vBottom) {
                    Toast.makeText(AdvancedControlActivity.this, "Bottom clicked", Toast.LENGTH_SHORT).show();
                }
                tvTime.setText(String.valueOf(System.currentTimeMillis()));
            }
        };

        vTop.setOnClickListener(onClickListener);
        vMiddle.setOnClickListener(onClickListener);
        vBottom.setOnClickListener(onClickListener);

        floatingView.showOverlaySystem(AdvancedControlActivity.this, "need permission");
    }

    /**
     * 检查是否有悬浮窗权限
     */
    private static boolean checkFloatingWindowPermission(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context);
        }
        return true;
    }

    /**
     * 请求悬浮窗权限
     */
    public void requestFloatingWindowPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + getPackageName()));
            try {
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            } catch (Exception e) {
                e.printStackTrace();
                // 跳转到应用设置页面
                intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
        }
    }

    /**
     * 在Activity中处理权限请求结果
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                boolean hasPermission = Settings.canDrawOverlays(this);
                if (!hasPermission) {
                    Toast.makeText(AdvancedControlActivity.this, "need permission", Toast.LENGTH_SHORT).show();
                } else {
                    doShowFloatingView();
                }
            }
        }
    }

    private void showFloatingView() {
        if (!checkFloatingWindowPermission(this)) {
            requestFloatingWindowPermission();
        } else {
            doShowFloatingView();
        }
    }
}
