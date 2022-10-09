package com.example.mobilelab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class QuestionActivity extends AppCompatActivity {


    private int sum;
    private String answer;

    private void onQuestionClick(View v) {
        TextInputEditText questionInput = findViewById(R.id.question_input);
        String question = questionInput.getText().toString();
        sum = 0;

        for(char ch: question.toCharArray()) {
            sum += ch;
        }

        sum = sum % 7;

        answer = "Не знаю";
        switch (sum) {
            case(1):
                answer = "Так";
                break;
            case(2):
                answer = "Ні";
                break;
            case(3):
                answer = "Можливо";
                break;
            case(4):
                answer = "Цілком вірогідно";
                break;
            case(5):
                answer = "Малоймовірно";
                break;
            case(6):
                answer = "Не знаю";
                break;
            default:
                break;
        }

        TextView answerTextView = findViewById(R.id.answer_text);
        answerTextView.setText(answer);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        findViewById(R.id.question_button).setOnClickListener(button -> {
            this.onQuestionClick(button);
        });

    }
}