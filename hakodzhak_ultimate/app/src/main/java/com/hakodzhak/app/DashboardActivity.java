package com.hakodzhak.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class DashboardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button btnStart = findViewById(R.id.btn_start);
        Button btnKill = findViewById(R.id.btn_kill);
        Button btnSettings = findViewById(R.id.btn_settings);

        btnStart.setOnClickListener(v -> {
            startService(new Intent(this, MyService.class));
            Toast.makeText(this, "Virus Activated", Toast.LENGTH_SHORT).show();
        });

        btnKill.setOnClickListener(v -> {
            stopService(new Intent(this, MyService.class));
            Toast.makeText(this, "Virus Stopped", Toast.LENGTH_SHORT).show();
        });
        
        btnSettings.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });
    }
}
