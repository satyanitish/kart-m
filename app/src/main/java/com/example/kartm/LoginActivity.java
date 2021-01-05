package com.example.kartm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText logEmail,logPassword;
    Button login;
    ProgressBar logprogressBar;
    TextView newUser,forgotpassword;
    FirebaseAuth fAuth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logEmail=findViewById(R.id.logemail);
        logPassword=findViewById(R.id.logpassword);
        login=findViewById(R.id.login);
        logprogressBar=findViewById(R.id.progressBar);
        newUser=findViewById(R.id.newuser);
        forgotpassword=findViewById(R.id.forgotPassword);
        fAuth=FirebaseAuth.getInstance();
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                finish();
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgotPasswordActivity.class));
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=logEmail.getText().toString().trim();
                String Password=logPassword.getText().toString().trim();
                if(Email.isEmpty()){
                    logEmail.setError("Enter Email");
                    logEmail.requestFocus();
                }
                else if(Password.isEmpty() && Password.length()<6){
                    logPassword.setError("Enter Pasword");
                    logPassword.requestFocus();
                }
                else {
                    logprogressBar.setVisibility(View.VISIBLE);
                    fAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"LoggedIn Succesfully ",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            }
                            else {
                                logprogressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(LoginActivity.this,"Login Failed "+task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}