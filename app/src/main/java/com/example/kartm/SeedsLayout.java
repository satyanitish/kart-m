package com.example.kartm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SeedsLayout extends AppCompatActivity {
    ListView listView;
    ImageView Layoutsetting;
    SearchView searchView;
    ImageButton back;
    String mTitle[]={"Wheat","Rice","Groundnut", "Rapeseed"," Mustard","Castor","Cotton" };
    String mDescription[]={"Grain & Cereals Production","Rice Production Seeds","Oil Production Seeds","Oil Production Seeds",
    "Oil Production Seeds","Oil Production Seeds","Cotton Production Seeds"};
    int mImages[]={R.drawable.wheat,R.drawable.rice,R.drawable.groundnut,R.drawable.rapeseed,R.drawable.mustard,
            R.drawable.castor,R.drawable.cotton};

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeds_layout);
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
        MyAdapter adapter=new MyAdapter(this,mTitle,mDescription,mImages);
        listView.setAdapter(adapter);
        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String > templist=new ArrayList<>();

                for(String temp:mTitle){
                    if (temp.toLowerCase().contains(newText.toLowerCase())){
                        templist.add(temp);
                    }
                }
                ArrayAdapter<String > adapter=new ArrayAdapter<>(SeedsLayout.this, android.R.layout.simple_list_item_1,templist);
                listView.setAdapter(adapter);
                return true;
            }
        });*/
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
                    String ProductName="Wheat";
                    Toast.makeText(getApplicationContext(),"Grain & Cereals Production",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SeedsLayout.this,Wheat.class);
                    intent.putExtra("wheat",ProductName);
                    startActivity(intent);
                    finish();

                }if(position==1){
                    Toast.makeText(getApplicationContext(),"Rice Production Seeds",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SeedsLayout.this,RiceActivity.class);
                    startActivity(intent);
                }if(position==2){
                    Toast.makeText(getApplicationContext(),"Oil Production Seeds",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SeedsLayout.this,GroundnutActivity.class);
                    startActivity(intent);
                }if(position==3){
                    Toast.makeText(getApplicationContext(),"Oil Production Seeds",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SeedsLayout.this,RapeseedActivity.class);
                    startActivity(intent);
                }if(position==4){
                    Toast.makeText(getApplicationContext(),"Oil Production Seeds",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SeedsLayout.this,MustardActivity.class);
                    startActivity(intent);
                }if(position==5){
                    Toast.makeText(getApplicationContext(),"Oil Production Seeds",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SeedsLayout.this,CastorActivity.class);
                    startActivity(intent);
                }if(position==6){
                    Toast.makeText(getApplicationContext(),"Cotton Production Seeds",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SeedsLayout.this,CottonActivity.class);
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

    class MyAdapter extends ArrayAdapter<String>{

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

        @NonNull
        @Override
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