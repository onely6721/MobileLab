package com.example.mobilelab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.questionButton)
                .setOnClickListener(button -> {
                    Intent  intent = new Intent(this, QuestionActivity.class);
                    startActivity(intent);
                });
        findViewById(R.id.userButton)
                .setOnClickListener(button -> {
                    Intent  intent = new Intent(this, UserActivity.class);
                    startActivity(intent);
                });
        findViewById(R.id.exitButton)
                .setOnClickListener(button -> {
                    finish();
                    System.exit(0);
                });
    }
}