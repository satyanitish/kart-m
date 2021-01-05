package com.example.kartm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RiceActivity extends AppCompatActivity {

    TextView title1,descp1;
    ImageView imageView1;
    Button add_to_cart1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rice);
        title1=findViewById(R.id.rice);
        imageView1=findViewById(R.id.imageview);
        descp1=findViewById(R.id.descp);
        add_to_cart1=findViewById(R.id.addtocart1);
        descp1.setText("Vitamins and minerals packed in wheat flour improve skin’s elasticity " +
                "and reduce wrinkles. Rich fiber content in wheat improves digestion and removes damaging " +
                "toxins from the body. Antioxidant ability of this innate ingredient protects your skin from harmful" +
                " UV rays and cancer causing agents.");
    }
}