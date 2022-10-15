package com.example.mobilelab;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class QuestionFragment extends Fragment {
    private String answer;
    TextInputEditText questionInput;
    TextView answerTextView;
    private QuestionService service;

    private void onQuestionClick(View v) {
        String question = questionInput.getText().toString();

        if (service == null) return;
        answer = service.getAnswer(question);

        answerTextView.setText(answer);

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
        Log.d("tat","connected");
    }
    @Override
    public void onStop() {
        Log.d("tat","disconnedted");
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