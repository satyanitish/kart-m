package com.example.kartm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class ConfimationOrderActivity extends AppCompatActivity implements PaymentResultListener {

    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;
    String cost;
    String TAG="Payment Error";
    Button confirmorder;
    TextView Cname,Cmobile,Cstate,Cpincode,Caddress,ProductName,Quantity,Ccost;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Checkout.preload(getApplicationContext());
        setContentView(R.layout.activity_confimation_order);
        ProductName=findViewById(R.id.ProductNames);
        Cname=findViewById(R.id.Cname);
        Cmobile=findViewById(R.id.Cmobile);
        Cstate=findViewById(R.id.Cemail);
        Cpincode=findViewById(R.id.Cpincode);
        Caddress=findViewById(R.id.Caddress);
        confirmorder=findViewById(R.id.confirmorder);
        Ccost=findViewById(R.id.cost);
        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();
        ProductName.setText(getIntent().getStringExtra("wheat"));
        Ccost.setText(getIntent().getStringExtra("cost"));
        confirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
        userId=fAuth.getCurrentUser().getUid();

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

    public void startPayment(){
        Checkout checkout=new Checkout();
        //checkout.setImage(R,drawable.logo);
        final Activity activity=this;
        try{
            JSONObject options=new JSONObject();
            options.put("name","KartMe");
            options.put("descrption","order #10123987");
            options.put("currency","INR");
            cost=Ccost.getText().toString();
            double tcost=Double.parseDouble(cost);
            tcost=tcost*100;
            options.put("amount",tcost);
            JSONObject preFill=new JSONObject();
            preFill.put("contact",Cmobile);
            options.put("preFill",preFill);
            checkout.open(activity,options);
        }
        catch (Exception e){
            Toast.makeText(activity,"Error payment occured "+e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"Payment Successful ",Toast.LENGTH_SHORT).show();
        //
        String message="Your purchased a product from KartMe "+ProductName;
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext()
        )
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle("Order Confirmed from KartMe")
                .setContentText(message)
                .setAutoCancel(true);
        Intent intent=new Intent(getApplicationContext(),OrdersListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("message",message);

        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
        int pcheck= ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS);
        if(pcheck==PackageManager.PERMISSION_GRANTED){
            Orderedmessage();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},111);
        }

        Intent intent1=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent1);
    }

    private void Orderedmessage() {
        String Mobile=Cmobile.getText().toString().trim();
        String messageT="Successfully purchased a product from KartMe.\n " +
                "Delivering in 3 days.\n " +
                "Our Delivery person will reach you soon out.";
        if (!Cmobile.getText().toString().equals("")||Cmobile.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(Mobile, null, messageT, null, null);
            Toast.makeText(this, "Check Message", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:
                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                     Orderedmessage();
                }
                else{
                    Toast.makeText(this,"Permission Not granted",Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.e("OnPaymentError","Exception on payment Error",e);
        }
    }
}