package com.example.mobilelab;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class QuestionService extends Service {
    private int sum;
    private String answer;

    private CustomBinder binder =
            new CustomBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    public class CustomBinder extends Binder {
        public QuestionService getService() {
            return QuestionService.this;
        }
    }

    public String getAnswer(String question) {
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

        return answer;
    }

}
