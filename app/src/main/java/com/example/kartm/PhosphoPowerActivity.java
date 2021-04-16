package com.example.kartm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class PhosphoPowerActivity extends AppCompatActivity {
    TextView title1,descp1,tcost,pdesc,dosage;
    ImageView imageView1;
    Button add_to_cart1;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    String product_name="Phospho-Power ";
    String cost="378";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phospho_power);
        title1=findViewById(R.id.wheat);
        imageView1=findViewById(R.id.imageview);
        descp1=findViewById(R.id.descp);
        add_to_cart1=findViewById(R.id.addtocart1);
        tcost=findViewById(R.id.cost);
        pdesc=findViewById(R.id.pdesc);
        dosage=findViewById(R.id.dosage);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        pdesc.setText("\nPhospho- Power is know as Phosphate Solubilizing Bacteria." +
                " It secretes certain organic Acids such as Lactic, Gluconic, fumaric, Succinic," +
                " and acetic acid which convert insoluble tricalcium phosphates and Rock Phosphates in soluble form.");

        dosage.setText("\nTake 2-4 kg. ofPhospho Power mix with 400 kg. of FYM/Vermi compost add 1kg." +
                " Jaggery water and keep it for 5 - 7days in a shade then broad cast over one acre of field.");
        add_to_cart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId=fAuth.getCurrentUser().getUid();
                DocumentReference documentReference=fstore.collection("product").document(userId);
                Map<String,Object> product=new HashMap<>();
                product.put("product",product_name);
                documentReference.set(product).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("TAG", "onSuccess: Product Purchased "+userId);
                    }
                });
                Intent intent=new Intent(getApplicationContext(),BuyingProductActivity.class);
                intent.putExtra("wheat",product_name);
                intent.putExtra("cost",cost);
                startActivity(intent);
            }
        });
    }
}