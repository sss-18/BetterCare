package com.spg.bettercareapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Keys;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MealActivity extends AppCompatActivity {
    int id;
    String date;
    String name;
    @BindView(R.id.resident)
    TextView resident;

    @BindView(R.id.today_date)
    TextView todayDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        ButterKnife.bind(this);
        setUp();
    }

    private void setUp(){
        receiveIntent();

        resident.setText(name);
        todayDate.setText(date);
    }
    private void receiveIntent(){
        if(getIntent()!=null && getIntent().getStringExtra(Keys.NAME_KEY)!=null
                && getIntent().getStringExtra(Keys.DATE_KEY)!=null){
            id = getIntent().getIntExtra(Keys.ID_KEY,-1);
            date = getIntent().getStringExtra(Keys.DATE_KEY);
            name = getIntent().getStringExtra(Keys.NAME_KEY);
        }else{
            finish();
        }
    }
}