package com.example.kartm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class OrganicFertilizerActivity extends AppCompatActivity {
    ListView listView;
    ImageButton Layoutsetting,back;
    SearchView searchView;
    String mTitle[]={"Phospho Power ","Azotobacter","Rhizo Power Rhizobium Inoculant", "Potash Mobilizing Bacteria (KMB)",
            " Vam Bio Promotor","Pseudomonas fluorescens"};
    String mDescription[]={"Phospho Power ","Azotobacter","Rhizo Power Rhizobium Inoculant", "Potash Mobilizing Bacteria (KMB)",
            " Vam Bio Promotor","Pseudomonas fluorescens"};
    int mImages[]={R.drawable.phosphopower,R.drawable.azotobacter,R.drawable.rhizopowerrhizobiuminoculant,R.drawable.potashmobilizingbacteria,
            R.drawable.vambiopromotor,R.drawable.pseudomonasfluorescens};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organic_fertilizer);
        listView=findViewById(R.id.listview);
        searchView=findViewById(R.id.searchProduct);
        Layoutsetting=findViewById(R.id.Layoutsettings);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
        OrganicFertilizerActivity.MyAdapter adapter=new OrganicFertilizerActivity.MyAdapter(this,mTitle,mDescription,mImages);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    String ProductName="Compact Tractor";
                    Toast.makeText(getApplicationContext(),"Phospho Power",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),PhosphoPowerActivity.class);
                    startActivity(intent);
                }if(position==1){
                    Toast.makeText(getApplicationContext(),"Azotobacter",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),AzotobacterActivity.class);
                    startActivity(intent);

                }if(position==2){
                    Toast.makeText(getApplicationContext(),"Brush Harvest cutter",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),RhizoPowerActivity.class);
                    startActivity(intent);

                }if(position==3){
                    Toast.makeText(getApplicationContext(),"Potash Mobilizing Bacteria",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),PotashMobilizingBacteriaActivity.class);
                    startActivity(intent);

                }if(position==4){
                    Toast.makeText(getApplicationContext(),"Vam Bio Promotor",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),VamBioPromotorActivity.class);
                    startActivity(intent);

                }if(position==5){
                    Toast.makeText(getApplicationContext(),"4 Stroke Power Sprayer",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),PseudomonasfluorescensActivity.class);
                    startActivity(intent);

                }
            }
        });

        Layoutsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImage[];
        MyAdapter(Context c, String title[] , String descrption[], int imgs[]){
            super(c,R.layout.row,R.id.title,title);
            this.context=c;
            this.rTitle=title;
            this.rDescription=descrption;
            this.rImage=imgs;
        }
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=layoutInflater.inflate(R.layout.row,parent,false);
            ImageView images=row.findViewById(R.id.image);
            TextView myTitle=row.findViewById(R.id.title);
            TextView myDesc=row.findViewById(R.id.descrption);

            images.setImageResource(rImage[position]);
            myTitle.setText(rTitle[position]);
            myDesc.setText(rDescription[position]);


            return row;
        }
    }
}