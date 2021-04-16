package com.example.kartm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FarmingvideoActivity extends AppCompatActivity {

    TextView wvideo,rvideo,gvideo,ravideo,mvideo;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmingvideo);
        wvideo=findViewById(R.id.wheatvideo);
        rvideo=findViewById(R.id.ricevideo);
        gvideo=findViewById(R.id.groundnutvideo);
        ravideo=findViewById(R.id.rapeseedvideo);
        mvideo=findViewById(R.id.mustardvideo);
        wvideo.setText("Wheat cultivating methodology");
        ravideo.setText("Rapeseed crop cultivation");
        rvideo.setText("Rice cultivation");
        gvideo.setText("Groundnut crop cultivation");
        mvideo.setText("Mustard crop cultivation");
    }
}