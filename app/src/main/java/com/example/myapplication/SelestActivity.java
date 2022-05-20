package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.ActivitySelestBinding;

import java.util.ArrayList;

public class SelestActivity extends AppCompatActivity {
    String name,distancen;
    public static final String CLASS_EXTRA="class";
    public static final String DISTANCE_EXTRA="distance";
    public static final String TIME_EXTRA="time";
    public static final String DATE_EXTRA="date";
    public static final String AVE500M_EXTRA="ave500m";
    public static final String TEMP_EXTRA="temp";
    public static final String ID="id";
    private AppBarConfiguration appBarConfiguration;
    private ArrayList<Country> states = new ArrayList<Country>();
    private RecyclerView recyclerView;
    private StateAdapter adapter;
    private ActivitySelestBinding binding;
    private DBCountries db;
    private StateAdapter.OnCountryClickListener onStateClickListener;
    private StateAdapter.OnDeleteClickListener onDeleteClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selest);
        Bundle arguments = getIntent().getExtras();
        if(arguments!=null){
            name=arguments.get("class").toString();
            distancen=arguments.get("distance").toString();

        }
        db=new DBCountries(this);
        recyclerView = findViewById(R.id.list);
        onStateClickListener= (state, position) -> {
            Intent intent=new Intent(SelestActivity.this,AddActivity.class);
            intent.putExtra(CLASS_EXTRA,state.getClass());
            intent.putExtra(DISTANCE_EXTRA,state.getDistance());
            intent.putExtra(TIME_EXTRA,state.getTime());
            intent.putExtra(DATE_EXTRA,state.getDate());
            intent.putExtra(AVE500M_EXTRA,state.getAve500m());
            intent.putExtra(TEMP_EXTRA,state.getTemp());
            intent.putExtra(ID, state.getID());
            mUpdateForResult.launch(intent);
        };
        onDeleteClickListener=(state,position)->{
            long id=state.getID();
            db.Delete(id);
            UpdateUI();
        };
        UpdateUI();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SelestActivity.this,AddActivity.class);
                mStartForResult.launch(intent);
            }
        });


    }
    private void UpdateUI()
    {
        states=db.selectAll();
        adapter = new StateAdapter(this, states,onStateClickListener,onDeleteClickListener);
        recyclerView.setAdapter(adapter);
    }

    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        String distance = intent.getStringExtra(DISTANCE_EXTRA);
                        String time = intent.getStringExtra(TIME_EXTRA);
                        String date = intent.getStringExtra(DATE_EXTRA);
                        String ave500m = intent.getStringExtra(AVE500M_EXTRA);
                        String temp = intent.getStringExtra(TEMP_EXTRA);
                        String classN=intent.getStringExtra(CLASS_EXTRA);
                        db.Insert(classN,distance,time,date,ave500m,temp);
                        UpdateUI();
                    }
                }
            });
    ActivityResultLauncher<Intent> mUpdateForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        String distance = intent.getStringExtra(DISTANCE_EXTRA);
                        String time = intent.getStringExtra(TIME_EXTRA);
                        String date = intent.getStringExtra(DATE_EXTRA);
                        String ave500m = intent.getStringExtra(AVE500M_EXTRA);
                        String temp = intent.getStringExtra(TEMP_EXTRA);
                        String classN=intent.getStringExtra(CLASS_EXTRA);
                        long id=intent.getLongExtra(ID,0);
                        Country country=db.select(id);
                        country.setAve500m(ave500m);
                        country.setDistance(distance);
                        country.setTime(time);
                        country.setDate(date);
                        country.setTemp(temp);
                        country.setClass(classN);
                        db.Update(country);
                        UpdateUI();
                    }
                }
            });
}