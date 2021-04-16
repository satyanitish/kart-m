package com.example.kartm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BuyingProductActivity extends AppCompatActivity {

    EditText txtname,txtmobile,txtaddress,txtpincode,txtstate;
    TextView pname,tcosts;
    Button save,Buy;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying_product);
        txtaddress=findViewById(R.id.address);
        txtmobile=findViewById(R.id.phone);
        txtname=findViewById(R.id.name);
        txtpincode=findViewById(R.id.pincode);
        txtstate=findViewById(R.id.state);
        pname=findViewById(R.id.pname);
        tcosts=findViewById(R.id.tcosts);
        save=findViewById(R.id.buy);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        pname.setText(getIntent().getStringExtra("wheat"));
        tcosts.setText(getIntent().getStringExtra("cost"));
        String pn=pname.getText().toString();
        String cc=tcosts.getText().toString();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Bname=txtname.getText().toString();
                String Bmobile=txtmobile.getText().toString();
                String Badd=txtaddress.getText().toString();
                String Bpincod=txtpincode.getText().toString();
                String Bstate=txtstate.getText().toString();
                if (Bname.isEmpty()){
                    txtname.setError("Please Enter Name");
                    txtname.requestFocus();
                }
                else if (Bmobile.isEmpty() || Bmobile.length()<10 || Bmobile.length()>10){
                    txtmobile.setError("Please Enter Valid Number");
                    txtmobile.requestFocus();
                }
                else if(Badd.isEmpty()){
                    txtaddress.setError("Please Enter Address");
                    txtaddress.requestFocus();
                }
                else if (Bpincod.isEmpty() || Bpincod.length()<6 || Bpincod.length()>6){
                    txtpincode.setError("Enter Valid Pincode");
                    txtpincode.requestFocus();
                }
                else if(Bstate.isEmpty()){
                    txtstate.setError("Enter Your State");
                    txtstate.requestFocus();
                }
                else {
                    userId=fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference=fstore.collection("billing").document(userId);
                    Map<String,Object> billing=new HashMap<>();
                    billing.put("address",Badd);
                    billing.put("name",Bname);
                    billing.put("phone",Bmobile);
                    billing.put("pincode",Bpincod);
                    billing.put("state",Bstate);
                    documentReference.set(billing).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG", "onSuccess: Billing Address Inserted "+userId);
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(BuyingProductActivity.this,ConfimationOrderActivity.class);
                            intent.putExtra("wheat",pn);
                            intent.putExtra("cost",cc);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

    }
}