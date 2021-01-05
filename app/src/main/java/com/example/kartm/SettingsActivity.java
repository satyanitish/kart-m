package com.example.kartm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class SettingsActivity extends AppCompatActivity {
    TextView namep,mobilep,emailp;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    Button editProfile;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        namep=findViewById(R.id.pname);
        emailp=findViewById(R.id.pgmail);
        mobilep=findViewById(R.id.pmobilenumber);
        editProfile=findViewById(R.id.editprofile);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();

        userId=fAuth.getCurrentUser().getUid();

        DocumentReference documentReference=fstore.collection("users").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                mobilep.setText(documentSnapshot.getString("mobile"));
                namep.setText(documentSnapshot.getString("name"));
                emailp.setText(documentSnapshot.getString("email"));
            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SettingsActivity.this,EditActivity.class);
                startActivity(intent);
            }
        });
    }

    public void backMenu1(View view) {
        startActivity(new Intent(SettingsActivity.this,HomeActivity.class));
        finish();
    }
}