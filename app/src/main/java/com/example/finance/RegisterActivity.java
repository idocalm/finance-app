package com.example.finance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        EditText emailField = findViewById(R.id.email);
        EditText passwordField = findViewById(R.id.password);

        TextView register = findViewById(R.id.register);
        register.post(new Runnable() {
            @Override
            public void run() {

                int length = register.getMeasuredWidth();

                float angle = 45;

                Shader textShader = new LinearGradient(0, 0, (int) (Math.sin(Math.PI * angle / 180) * length),
                        (int) (Math.cos(Math.PI * angle / 180) * length),

                        new int[]{Color.WHITE, Color.parseColor("#7C56EE")},

                        null,
                        Shader.TileMode.CLAMP);

                register.getPaint().setShader(textShader);

                register.invalidate();

            }
        });

        Button registerButton = findViewById(R.id.join);

        registerButton.setOnClickListener(v -> {
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("Auth", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                updateUI(user);

                            } else {
                                Log.w("Auth", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed. - " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });


    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(RegisterActivity.this, RegisterDetails.class);
            startActivity(intent);
        }
    }
}