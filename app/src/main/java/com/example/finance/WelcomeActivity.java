package com.example.finance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView welcome = findViewById(R.id.welcome);
        welcome.post(new Runnable() {
            @Override
            public void run() {

                int length = welcome.getMeasuredWidth();

                float angle = 45;

                Shader textShader = new LinearGradient(0, 0, (int) (Math.sin(Math.PI * angle / 180) * length),
                        (int) (Math.cos(Math.PI * angle / 180) * length),

                        new int[]{Color.WHITE, Color.parseColor("#7C56EE")},

                        null,
                        Shader.TileMode.CLAMP);

                welcome.getPaint().setShader(textShader);

                welcome.invalidate();

            }
        });

        Button registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        TextView moveToLogin = findViewById(R.id.move_to_login);
        moveToLogin.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}