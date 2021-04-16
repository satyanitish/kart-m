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

public class RhizoPowerActivity extends AppCompatActivity {
    TextView title1,descp1,tcost,pdesc,dosage;
    ImageView imageView1;
    Button add_to_cart1;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    String product_name="Rhizo Powder ";
    String cost="2890";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhizo_power);
        title1=findViewById(R.id.wheat);
        imageView1=findViewById(R.id.imageview);
        descp1=findViewById(R.id.descp);
        add_to_cart1=findViewById(R.id.addtocart1);
        tcost=findViewById(R.id.cost);
        pdesc=findViewById(R.id.pdesc);
        dosage=findViewById(R.id.dosage);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        pdesc.setText("Rhizobium Inoculant (culture) is a bio fertilizer for growth & germination of plants like groundnut," +
                " soyabean, tower, black gram and all pulses plants.");

        dosage.setText("• 50g. of jaggery / sugar with 1 litre water should be boiled well and then cooled. 200 gms. of Rhizobium culture shall be added to that liquid and made smooth paste.\n" +
                "•This paste can be applied smoothly for 15 to 20 kgs. of seeds and dry them in shadow for proper results.\n" +
                "•Rhizo-Power serves as a broad spectrum bio fertilizer for all pulses crops. Additionally it helps in synthesizing antibiotic substances, which control various fungal, bacterial and viral diseases of plants.");
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