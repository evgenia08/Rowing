package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class DistanseActivity extends AppCompatActivity {
    String[] distancen = {"100m", "250m", "500m","1000m","1500m","2000m","3000m","6000m"};
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_distanse);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            name=arguments.get(MainActivity.EXTRA_NAME).toString();
        }
        ListView distance = findViewById(R.id.distance);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, distancen);
        distance.setAdapter(adapter);
        distance.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (savedInstanceState == null) {
                    Intent intent=new Intent(DistanseActivity.this,SelestActivity.class);
                    intent.putExtra("class",name);
                    intent.putExtra("distance",distancen[position]);
                    startActivity(intent);
                }

            }
        });
    }
}