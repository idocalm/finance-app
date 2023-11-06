package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class RegisterDetails extends AppCompatActivity {

    private String currentStep = "Name";

    private LinearLayout nameFrame;
    private LinearLayout currencyFrame;
    private LinearLayout genderFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_details);

        nameFrame = findViewById(R.id.nameLayout);
        currencyFrame = findViewById(R.id.currencyLayout);
        genderFrame = findViewById(R.id.genderLayout);

        currencyFrame.setVisibility(LinearLayout.GONE);
        genderFrame.setVisibility(LinearLayout.GONE);


    }

    private void nextStep() {
        switch (currentStep) {
            case "Name":
                nameFrame.setVisibility(LinearLayout.GONE);
                currencyFrame.setVisibility(LinearLayout.VISIBLE);
                currentStep = "Currency";
                break;
            case "Currency":
                currencyFrame.setVisibility(LinearLayout.GONE);
                genderFrame.setVisibility(LinearLayout.VISIBLE);
                currentStep = "Gender";
                break;


        }
    }
}