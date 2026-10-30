package com.hakodzhak.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        RadioGroup radioGroup = findViewById(R.id.radio_lang);
        RadioButton rbEn = findViewById(R.id.rb_en);
        RadioButton rbAr = findViewById(R.id.rb_ar);

        SharedPreferences prefs = getSharedPreferences("config", MODE_PRIVATE);
        String currentLang = prefs.getString("language", "en");

        if (currentLang.equals("ar")) {
            rbAr.setChecked(true);
        } else {
            rbEn.setChecked(true);
        }

        Button btnSave = findViewById(R.id.btn_save_lang);
        btnSave.setOnClickListener(v -> {
            String lang = rbAr.isChecked() ? "ar" : "en";
            prefs.edit().putString("language", lang).apply();
            
            // إعادة تشغيل التطبيق لتطبيق التغييرات
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
