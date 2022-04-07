package com.spg.bettercareapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.spg.bettercareapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_admin)
    public void onAdminClicked(){
        startActivity(new Intent(this,LoginActivity.class));
    }
}