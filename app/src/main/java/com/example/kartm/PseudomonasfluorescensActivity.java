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

public class PseudomonasfluorescensActivity extends AppCompatActivity {
    TextView title1,descp1,tcost,pdesc,dosage,st1;
    ImageView imageView1;
    Button add_to_cart1;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    String product_name="Pseudomonas Fluorescens ";
    String cost="2880";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pseudomonasfluorescens);
        title1=findViewById(R.id.wheat);
        imageView1=findViewById(R.id.imageview);
        descp1=findViewById(R.id.descp);
        add_to_cart1=findViewById(R.id.addtocart1);
        tcost=findViewById(R.id.cost);
        pdesc=findViewById(R.id.pdesc);
        dosage=findViewById(R.id.dosage);
        st1=findViewById(R.id.st1);
        fAuth= FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();
        pdesc.setText("• Diseases controlled Pseudoiforte damping off caused by Pythiium spp. collar rot caused by Rhizoctoniasolani and some other plant diseases like paddy blast.\n" +
                "• Pseudomonas fluorescens produces some antibiotics like 2, 4 diacetylphloreglucinol, hydrogen cynide (HCN) which result in the suppression of various soil borne pathogens.\n");

        dosage.setText("• Seed Treatment, Foliar Spray, Drip Irrrigation\n" );

        st1.setText("• Mix 100 ml of Pseudoiforte with 4% Jaggery or any biological sticking agent solution." +
                "• Mix the seeds required for 1 acre uniformly and dry it in shade.");
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