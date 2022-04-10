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

public class FluidActivity extends AppCompatActivity {
    int id;
    String date;
    String name;
    @BindView(R.id.resident)
    TextView resident;

    @BindView(R.id.today_date)
    TextView todayDate;

    private boolean oneFifty=false;
    private boolean twoFifty=false;
    private boolean threeFifty=false;
    private boolean moreThanThreeFifty=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluid);
        ButterKnife.bind(this);
        setUp();
    }

    private void setUp(){
        receiveIntent();

        resident.setText(name);
        todayDate.setText(date);

        //make network call and pre-populate the data.
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

    @OnCheckedChanged(R.id.one_fifty)
    public void onChecked150(CompoundButton button, boolean isOneFifty){
        this.oneFifty=isOneFifty;
    }
    @OnCheckedChanged(R.id.two_fifty)
    public void onChecked250(CompoundButton button, boolean isTwoFifty){
        this.twoFifty=isTwoFifty;
    }
    @OnCheckedChanged(R.id.three_fifty)
    public void onChecked350(CompoundButton button, boolean isThreeFifty){
        this.threeFifty=isThreeFifty;
    }
    @OnCheckedChanged(R.id.more_than_350)
    public void onCheckedMoreThan350(CompoundButton button, boolean isMoreThanThreeFifty){
        this.moreThanThreeFifty=isMoreThanThreeFifty;
    }

    @OnClick(R.id.btn_save)
    public void onSaveClicked(){
        //make the network call to save the data.
    }
}