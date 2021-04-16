package com.example.kartm;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    LinearLayout seedslayout,machinerylayout,organiclayout,farmingguidence,newsfeed;
    ImageView Homesetting;
    ImageView langchange,settings;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadLocale();
        //LoadOptions();
        setContentView(R.layout.activity_home);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        seedslayout=findViewById(R.id.seedslayout);
        machinerylayout=findViewById(R.id.machinerylayout);
        organiclayout=findViewById(R.id.organiclayout);
        farmingguidence=findViewById(R.id.farmingguidence);
        Homesetting=findViewById(R.id.Homesettings);
        langchange=findViewById(R.id.langchange);
        langchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LangDialogbox();
            }
        });
        /*settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSellingOption();
            }
        });*/
        Homesetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        seedslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(HomeActivity.this,SeedsLayout.class);
                startActivity(i);
            }
        });
        machinerylayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MachineLayoutActivity.class);
                startActivity(intent);
            }
        });
        organiclayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),OrganicFertilizerActivity.class);
                startActivity(intent);
            }
        });
        farmingguidence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,FarmingvideoActivity.class);
                startActivity(intent);
            }
        });

    }
    private void LangDialogbox() {
        final String[] langList={"తెలుగు ","हिन्दी ","English"};
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(HomeActivity.this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(langList, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(i==0){
                    setLocale("te");
                    recreate();
                }
                else if(i==1){
                    setLocale("hi");
                    recreate();
                }
                else if(i==2){
                    setLocale("en");
                    recreate();
                }
                dialog.dismiss();
            }
        });
        AlertDialog mDialog=mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("LSettings",MODE_PRIVATE).edit();
        editor.putString("My_lang",lang);
        editor.apply();
    }
    public void LoadLocale(){
        SharedPreferences pref=getSharedPreferences("LSettings", Activity.MODE_PRIVATE);
        String language=pref.getString("My_lang","");
        setLocale(language);
    }



//

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }
}