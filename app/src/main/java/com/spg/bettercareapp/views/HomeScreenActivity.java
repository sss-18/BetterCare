package com.spg.bettercareapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Keys;

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
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(Keys.LOGIN_KEY,"Admin");
        startActivity(intent);
    }

    @OnClick(R.id.btn_care_staff)
    public void onCareStaffClicked(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(Keys.LOGIN_KEY,"Care Staff");
        startActivity(intent);
    }
}