package com.spg.bettercareapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Keys;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class MealActivity extends AppCompatActivity {
    int id;
    String date;
    String name;
    private boolean breakfast=false;
    private boolean lunch=false;
    private boolean snack=false;
    private boolean dinner=false;

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
    @OnCheckedChanged(R.id.breakfast)
    public void onCheckedBreakfast(CompoundButton button, boolean isBreakfast){
        this.breakfast=isBreakfast;
    }
    @OnCheckedChanged(R.id.lunch)
    public void onCheckedLunch(CompoundButton button, boolean isLunch){
        this.lunch=isLunch;
    }
    @OnCheckedChanged(R.id.snack)
    public void onCheckedSnack(CompoundButton button, boolean isSnack){
        this.snack=isSnack;
    }
    @OnCheckedChanged(R.id.dinner)
    public void onCheckedDinner(CompoundButton button, boolean isDinner){
        this.dinner=isDinner;
    }

    @OnClick(R.id.btn_save)
    public void onSaveClicked(){
        //make the network call to save the data.
    }
}