package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private EditText classN,distance,time,date,ave500m,temp;
    private Button add,cancel;
    private Bundle extras;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        classN=findViewById(R.id.classN);
        distance=findViewById(R.id.distance);
        time=findViewById(R.id.time);
        date=findViewById(R.id.date);
        ave500m=findViewById(R.id.ave500m);
        temp=findViewById(R.id.temp);
        add=findViewById(R.id.Ok);
        cancel=findViewById(R.id.cancel);
        extras = getIntent().getExtras();
        if (extras != null) {
            String classExtra = extras.getString(SelestActivity.CLASS_EXTRA);
            String distanceExtra = extras.getString(SelestActivity.DISTANCE_EXTRA);
            String timeExtra = extras.getString(SelestActivity.TIME_EXTRA);
            String dateExtra = extras.getString(SelestActivity.DATE_EXTRA);
            String ave500mExtra = extras.getString(SelestActivity.AVE500M_EXTRA);
            String tempExtra = extras.getString(SelestActivity.TEMP_EXTRA);
            id=extras.getLong(SelestActivity.ID);
            classN.setText(classExtra);
            distance.setText(distanceExtra);
            time.setText(timeExtra);
            date.setText(dateExtra);
            ave500m.setText(ave500mExtra);
            temp.setText(tempExtra);
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                data.putExtra(SelestActivity.CLASS_EXTRA, classN.getText().toString());
                data.putExtra(SelestActivity.DISTANCE_EXTRA, distance.getText().toString());
                data.putExtra(SelestActivity.TIME_EXTRA,time.getText().toString());
                data.putExtra(SelestActivity.DATE_EXTRA,date.getText().toString());
                data.putExtra(SelestActivity.AVE500M_EXTRA,ave500m.getText().toString());
                data.putExtra(SelestActivity.TEMP_EXTRA,temp.getText().toString());
                if(extras!=null) data.putExtra(SelestActivity.ID,id);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}