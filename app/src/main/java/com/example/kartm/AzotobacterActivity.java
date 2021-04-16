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

public class AzotobacterActivity extends AppCompatActivity {
    TextView title1,descp1,tcost,pdesc,dosage,st;
    ImageView imageView1;
    Button add_to_cart1;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    String product_name="Azotobacter  ";
    String cost="955";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_azotobacter);
        title1=findViewById(R.id.wheat);
        imageView1=findViewById(R.id.imageview);
        descp1=findViewById(R.id.descp);
        add_to_cart1=findViewById(R.id.addtocart1);
        tcost=findViewById(R.id.cost);
        pdesc=findViewById(R.id.pdesc);
        dosage=findViewById(R.id.dosage);
        st=findViewById(R.id.st);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        pdesc.setText("\nAzotobacter is a N fixing bio fertilizer." +
                " It fixes Atmospheric Nitrogen and produces B vitamin, Indole acetic acid, Gibberellins and Cytokinnisetc" +
                " which leads to plants healthy growth. Also it increases the activity of beneficial Rhizosphere Bacteria.");

        dosage.setText("\nTake 4 kg. of AZO power mix with 200-400kg of any organic manure along with Jaggery" +
                " water and keep it for one week in shade then broad cast over one acre of field at the time of last plough or sowing.");

        st.setText("\nTake 10 gm. of Azo power add little water and make it paste then apply to 1 kg. of seed.");
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