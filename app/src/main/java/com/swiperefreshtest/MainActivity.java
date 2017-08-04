package com.swiperefreshtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_system, btn_custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_custom = (Button) findViewById(R.id.ac_custom);
        btn_system = (Button) findViewById(R.id.ac_system);
        btn_custom.setOnClickListener(clickListener);
        btn_system.setOnClickListener(clickListener);
    }

    private Button.OnClickListener clickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.ac_custom:
                    intent = new Intent(MainActivity.this, CustomSwipeRefresh.class);
                    break;

                case R.id.ac_system:
                    intent = new Intent(MainActivity.this, SystemSwipeRefresh.class);
                    break;
            }
            startActivity(intent);
        }
    };
}
