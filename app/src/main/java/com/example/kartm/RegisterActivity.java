package com.example.kartm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText txtEmail,txtName,txtMobile,txtPassword,txtCPassword;
    Button Register;
    TextView AlRegistered;
    FirebaseAuth fAuth;
    ProgressBar progressBar1;
    FirebaseFirestore fstore;
    String userId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtEmail =findViewById(R.id.email);
        txtName=findViewById(R.id.name);
        txtMobile=findViewById(R.id.mobile);
        txtPassword=findViewById(R.id.password);
        txtCPassword=findViewById(R.id.cpassword);
        AlRegistered=findViewById(R.id.alregistered);
        Register=findViewById(R.id.register);
        progressBar1=findViewById(R.id.progressBar);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        if (fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            finish();
        }
        AlRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=txtEmail.getText().toString().trim();
                String Name=txtName.getText().toString().trim();
                String Mobile=txtMobile.getText().toString().trim();
                String Password=txtPassword.getText().toString().trim();
                String CPassword=txtCPassword.getText().toString().trim();
                if(Email.isEmpty()){
                    txtEmail.setError("Enter Email");
                    txtEmail.requestFocus();
                }
                else if (Name.isEmpty()){
                    txtName.setError("Enter Name");
                    txtName.requestFocus();
                }
                else if (Mobile.isEmpty() && Mobile.length()!=10){
                    txtMobile.setError("Invalid Mobile Number");
                    txtMobile.requestFocus();
                }
                else if (Password.isEmpty() && Password.length()<6){
                    txtPassword.setError("Invalid Password or Password should have atleast 6 Characters");
                    txtPassword.requestFocus();
                }
                else if(!CPassword.equals(Password)){
                    txtCPassword.setError("Mismatch Password");
                    txtCPassword.requestFocus();
                }
                else{
                    progressBar1.setVisibility(View.VISIBLE);
                    fAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this,"Registered Succesfully",Toast.LENGTH_SHORT).show();
                                userId=fAuth.getCurrentUser().getUid();
                                DocumentReference documentReference=fstore.collection("users").document(userId);
                                Map<String,Object> user=new HashMap<>();
                                user.put("email",Email);
                                user.put("name",Name);
                                user.put("mobile",Mobile);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("TAG", "onSuccess: User profile is created "+userId);
                                    }
                                });
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                            }
                            else {
                                progressBar1.setVisibility(View.INVISIBLE);
                                Toast.makeText(RegisterActivity.this,"Registeration Failed "+task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}