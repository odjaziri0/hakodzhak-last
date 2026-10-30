package com.hakodzhak.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Locale;

public class LoginActivity extends Activity {
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // تحميل اللغة المحفوظة
        SharedPreferences prefs = getSharedPreferences("config", MODE_PRIVATE);
        String lang = prefs.getString("language", "en");
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        setContentView(R.layout.activity_login);

        try {
            mediaPlayer = MediaPlayer.create(this, R.raw.hell_sound);
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        } catch (Exception e) {}

        EditText etToken = findViewById(R.id.et_token);
        EditText etChatId = findViewById(R.id.et_chat_id);
        Button btnLogin = findViewById(R.id.btn_login);

        etToken.setText(prefs.getString("botToken", ""));
        etChatId.setText(prefs.getString("chatId", ""));

        btnLogin.setOnClickListener(v -> {
            String token = etToken.getText().toString().trim();
            String chatId = etChatId.getText().toString().trim();

            if (token.isEmpty() || chatId.isEmpty()) {
                Toast.makeText(this, "Please fill data", Toast.LENGTH_SHORT).show();
                return;
            }

            prefs.edit().putString("botToken", token).putString("chatId", chatId).apply();
            
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        });
    }
}
