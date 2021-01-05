package com.example.kartm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ConfimationOrderActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    String st;
    TextView Cname,Cmobile,Cstate,Cpincode,Caddress,ProductName,Quantity;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confimation_order);
        ProductName=findViewById(R.id.ProductName);
        Quantity=findViewById(R.id.Quantity);
        Cname=findViewById(R.id.Cname);
        Cmobile=findViewById(R.id.Cmobile);
        Cstate=findViewById(R.id.Cemail);
        Cpincode=findViewById(R.id.Cpincode);
        Caddress=findViewById(R.id.Caddress);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        userId=fAuth.getCurrentUser().getUid();

        st=getIntent().getExtras().getString("wheat");
        ProductName.setText(st);
        DocumentReference documentReference=fstore.collection("billing").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                Cname.setText(documentSnapshot.getString("name"));
                Cmobile.setText(documentSnapshot.getString("phone"));
                Cstate.setText(documentSnapshot.getString("state"));
                Cpincode.setText(documentSnapshot.getString("pincode"));
                Caddress.setText(documentSnapshot.getString("address"));
            }
        });

    }
}