package com.wuadam.demo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_system) {
            Intent intent = new Intent(MainActivity.this, OverlaySystemActivity.class);
            startActivity(intent);
        }
        if (id == R.id.btn_activity) {
            Intent intent = new Intent(MainActivity.this, OverlayActivityActivity.class);
            startActivity(intent);
        }
        if (id == R.id.btn_viewgroup) {
            Intent intent = new Intent(MainActivity.this, OverlayViewGroupActivity.class);
            startActivity(intent);
        }
    }
}
