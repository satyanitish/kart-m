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

public class MachineLayoutActivity extends AppCompatActivity {

    ListView listView;
    ImageButton Layoutsetting,back;
    SearchView searchView;

    String mTitle[]={"Compact Tractor","Rice Harvest Machine","Brush Harvest cutter", "Mini Rice Mill"," Mobile Mini Rice Mill","4 Stroke Power Sprayer",
            "Cotton Stalk Crusher Machine" };
    String mDescription[]={"Compact Tractor","Rice Harvest Machine","Brush Harvest cutter","Mini Rice Mill",
            "Mobile Mini Rice Mill","4 Stroke Power Sprayer","Cotton Stalk Crusher Machine"};
    int mImages[]={R.drawable.compactttractor,R.drawable.ricedaalharvest,R.drawable.brushharvestcutter,R.drawable.miniricemill,R.drawable.ricedaalharvest,
            R.drawable.brassred,R.drawable.cottonstalker};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_layout);
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
        MachineLayoutActivity.MyAdapter adapter=new MachineLayoutActivity.MyAdapter(this,mTitle,mDescription,mImages);
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
                    Toast.makeText(getApplicationContext(),"Compact Tractor",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),CompactTractorActivity.class);
                    startActivity(intent);
                }if(position==1){
                    Toast.makeText(getApplicationContext(),"Rice Harvest Machine",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),RiceHarvestMachineActivity.class);
                    startActivity(intent);

                }if(position==2){
                    Toast.makeText(getApplicationContext(),"Brush Harvest cutter",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),BrushHarvestMachineActivity.class);
                    startActivity(intent);

                }if(position==3){
                    Toast.makeText(getApplicationContext(),"Mini Rice Mill",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MiniRiceMillActivity.class);
                    startActivity(intent);

                }if(position==4){
                    Toast.makeText(getApplicationContext(),"Mobile Mini Rice Mill",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MobileRiceMillActivity.class);
                    startActivity(intent);

                }if(position==5){
                    Toast.makeText(getApplicationContext(),"4 Stroke Power Sprayer",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),BrassActivity.class);
                    startActivity(intent);

                }if(position==6){
                    Toast.makeText(getApplicationContext(),"Cotton Stalk Crusher Machine",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),CottonCrusherActivity.class);
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