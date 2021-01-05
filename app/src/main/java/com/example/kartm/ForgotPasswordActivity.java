package com.example.kartm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText txtResetEmail;
    Button resetButton;
    ProgressBar ResetprogressBar;
    FirebaseAuth firebaseAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        txtResetEmail=findViewById(R.id.txtreset);
        resetButton =findViewById(R.id.resetButton);
        ResetprogressBar=findViewById(R.id.resetprogressBar);
        firebaseAuth=FirebaseAuth.getInstance();
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ResetEmailPassword=txtResetEmail.getText().toString().trim();
                if(ResetEmailPassword.isEmpty()){
                    txtResetEmail.setError("Enter Email");
                    txtResetEmail.requestFocus();
                }
                else {
                    ResetprogressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.sendPasswordResetEmail(ResetEmailPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Reset Link Sent to your Email ",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                finish();
                                ResetprogressBar.setVisibility(View.INVISIBLE);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                ResetprogressBar.setVisibility(View.VISIBLE);
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }
}