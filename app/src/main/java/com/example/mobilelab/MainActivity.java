package com.example.mobilelab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.questionButton)
                .setOnClickListener(button -> {
                    QuestionFragment questionFragment = new QuestionFragment();
                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.container, questionFragment)
                            .commit();
                });
        findViewById(R.id.userButton)
                .setOnClickListener(button -> {
                    UserFragment userFragment = new UserFragment();
                    for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.container, userFragment)
                            .commit();
                });
        findViewById(R.id.exitButton)
                .setOnClickListener(button -> {
                    finish();
                    System.exit(0);
                });
    }
}