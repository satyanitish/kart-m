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

public class PotashMobilizingBacteriaActivity extends AppCompatActivity {
    TextView title1,descp1,tcost,pdesc,dosage;
    ImageView imageView1;
    Button add_to_cart1;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    String product_name="Potash Mobilizing Bacteria ";
    String cost="3990";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potash_mobilizing_bacteria);
        title1=findViewById(R.id.wheat);
        imageView1=findViewById(R.id.imageview);
        descp1=findViewById(R.id.descp);
        add_to_cart1=findViewById(R.id.addtocart1);
        tcost=findViewById(R.id.cost);
        pdesc=findViewById(R.id.pdesc);
        dosage=findViewById(R.id.dosage);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        pdesc.setText("Potassium (K) is the third major micronutrient essential for plant growth and the majority of potassium in the" +
                " soil exists in insoluble rock and silicate form. Hence liberation of potassium from this insoluble matter is essential " +
                "for up taking Potash (K) by plants. K-Potash Power comprised of highly efficient and potential bacteria, which dissolve " +
                "potassium from insoluble K-bearing minerals and provide to plants.");

        dosage.setText("• Dosage and mode of application: For one acre of crop, mix 2-3 kg or 1-2 L of K-Potash power in 100-150 kg of moist" +
                " compost material, incubate under shade for 12-16 hr or overnight and broadcast before sowing or near plant root zone.\n" +
                "•SEED TREATMENT : Make a paste of 20 mg or 10-20 ml K-Potash power with water apply to 1 kg of seed and shade dry before sowing.\n" +
                "•SEEDLINGS : To 50 L of water, mix 500 gm or 300 ml of K-Potash power, soak the plantation material for 20-30 minutes before plantation.\n"+
                "•SEEDLING BEDS: To 25-30 L of water mix 500 gm or 300 ml of K-Potash power, drench on seedling beds or seedling tray.");
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