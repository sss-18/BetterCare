package com.spg.bettercareapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.spg.bettercareapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseShiftActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_shift);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_morning)
    public void onMorningClicked(){
        startActivity(new Intent(this,ResidentDetailsActivity.class));
    }

    @OnClick(R.id.btn_afternoon)
    public void onAfternoonClicked(){
        startActivity(new Intent(this,ResidentDetailsActivity.class));
    }

    @OnClick(R.id.btn_night)
    public void onNightClicked(){
        startActivity(new Intent(this,ResidentDetailsActivity.class));
    }
}