package com.example.kartm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Wheat extends AppCompatActivity {

    TextView title1,descp1,tcost,ttcost,voseed1,turnover1,Typesoil1,temp1,organic;
    ImageView imageView1;
    Button add_to_cart1;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    String product_name="Wheat";
    String cost="299";
    TextView count;
    int c=0;
    ImageButton increment,decrement;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheat);
        title1=findViewById(R.id.wheat);
        imageView1=findViewById(R.id.imageview);
        descp1=findViewById(R.id.descp);
        add_to_cart1=findViewById(R.id.addtocart1);
        count=findViewById(R.id.count);
        increment=findViewById(R.id.increment);
        decrement=findViewById(R.id.decrement);
        voseed1=findViewById(R.id.voseed1);
        turnover1=findViewById(R.id.turnover1);
        temp1=findViewById(R.id.temp1);
        organic=findViewById(R.id.organic1);
        Typesoil1=findViewById(R.id.Typesoil1);
        tcost=findViewById(R.id.cost);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        /*descp1.setText("\n Vitamins and minerals packed in wheat flour improve skin’s elasticity " +
                "and reduce wrinkles. Rich fiber content in wheat improves digestion and removes damaging " +
                "toxins from the body. Antioxidant ability of this innate ingredient protects your skin from harmful" +
                " UV rays and cancer causing agents.");*/

        /*voseed1.setText("The main varieties of wheat grown in India are as follows VL-832,VL-804, HS-365, HS-240," +
                " HD2687,WH-147, WH-542, PBW-343, WH-896(d), PDW-233(d), UP-2338, PBW-502, Shresth (HD 2687)," +
                " Aditya (HD 2781), HW-2044, HW-1085, NP-200(di), HW-741.");*/

        //turnover1.setText("25 to 30 quintal");

        //Typesoil1.setText("Black soil");

        //temp1.setText("Major wheat growing states in India are Uttar Pradesh, Punjab, Haryana, Madhya Pradesh, Rajasthan," +
          //      " Bihar and Gujarat.(23`C to 13`).");

        /*organic.setText("The soil pH is in the range of 6.0 to 7.0 then it is good for growing wheat.\n" +
                "-Treatment of soil is also necessary. You can treat the soil with Azatobacter (2.5 Kilograms) +" +
                " Phophetica culture (2.5 Kilograms) + Tycoderma powder (2.5 Kilograms). Mix all these ingredients " +
                "with 125 Kilograms of Farm Yard Manure. You can apply this at the last time of ploughing.\n");*/
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c++;
                count.setText(""+c);

            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c<1){
                    count.setText("1");
                }
                else {
                    c--;
                    count.setText("" + c);


                }
            }
        });

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