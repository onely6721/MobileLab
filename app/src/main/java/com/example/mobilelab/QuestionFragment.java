package com.example.mobilelab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.concurrent.ExecutorService;

public class QuestionFragment extends Fragment {
    public static final String TAG =
            QuestionFragment.class.getSimpleName();

    private int sum;
    private String answer;
    TextInputEditText questionInput;
    TextView answerTextView;
    private QuestionService service;

    private ExecutorService executorService;
    private Handler handler = new Handler();

    private void onQuestionClick(View v) {
        executorService.submit(() -> {
            try {
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
                handler.post(() -> {
                    answerTextView.setText(answer);
                });
            } catch (Exception e) {
                Log.e(TAG, "Error!", e);
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedState) {
        // layout fragment_content.xml will be displayed
        return inflater.inflate(
                R.layout.activity_question,
                container,
                false
        );
    }


    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedState) {
        super.onViewCreated(view, savedState);
        view.findViewById(R.id.question_button).setOnClickListener(button -> {
            this.onQuestionClick(button);
        });
        questionInput = view.findViewById(R.id.question_input);
        answerTextView = view.findViewById(R.id.answer_text);

    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(getActivity(), QuestionService.class);
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    public void onStop() {
        super.onStop();
        getActivity().unbindService(serviceConnection);
    }
    private ServiceConnection serviceConnection =
            new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name,
                                               IBinder binder) {
                    service = ((QuestionService.CustomBinder) binder).getService();
                }
                @Override
                public void onServiceDisconnected(ComponentName name) {
                    service = null;
                }
            };

}