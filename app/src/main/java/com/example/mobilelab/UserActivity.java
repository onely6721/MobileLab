package com.example.mobilelab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class UserActivity extends AppCompatActivity {
    private TextView firstNameTextView;
    private TextView secondNameTextView;
    private TextView ageTextView;
    private TextView sexTextView;

    private TextInputEditText firstNameInput;
    private TextInputEditText secondNameInput;
    private TextInputEditText dateInput;

    private boolean sex;

    private int mYear, mMonth, mDay;


    private void onSaveClick() {
        String firstName = firstNameInput.getText().toString();
        String secondName = secondNameInput.getText().toString();
        String birthday = dateInput.getText().toString();

        ageTextView.setText("You birthday: " + birthday);
        firstNameTextView.setText("You first name:" + firstName);
        secondNameTextView.setText("You second name:" + secondName);

        String sexText;

        if(sex == true ) {
            sexText = "Female";
        } else {
            sexText = "Male";
        }

        sexTextView.setText("You sex:" + sexText);


    }

    private void onSwitchSexClick(View v) {
        RadioButton rb = (RadioButton)v;
        switch (rb.getId()) {
            case R.id.male_radio_button:
                this.sex = false;
                break;
            case R.id.female_radio_button:
                this.sex = true;
                break;
            default:
                break;
        }

    }

    private void onChooseDateClick(View v) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        dateInput.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        firstNameTextView = findViewById(R.id.first_name_text);
        secondNameTextView = findViewById(R.id.second_name_text);
        ageTextView = findViewById(R.id.birthday_text);
        sexTextView = findViewById(R.id.sex_info_text);


        firstNameInput = findViewById(R.id.first_name_input);
        secondNameInput = findViewById(R.id.second_name_input);
        dateInput = findViewById(R.id.date_input);


        firstNameTextView.setText("You first name:");
        secondNameTextView.setText("You second name:");
        ageTextView.setText("You birthday:");
        sexTextView.setText("You sex:");

        findViewById(R.id.save_button).setOnClickListener(button -> {
            this.onSaveClick();
        });

        findViewById(R.id.choose_birthday_button).setOnClickListener(button -> {
            this.onChooseDateClick(button);
        });

        findViewById(R.id.male_radio_button).setOnClickListener(radioButton -> {
            this.onSwitchSexClick(radioButton);
        });
        findViewById(R.id.female_radio_button).setOnClickListener(radioButton -> {
            this.onSwitchSexClick(radioButton);
        });

    }
}