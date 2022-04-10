package com.spg.bettercareapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Keys;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseTaskActivity extends AppCompatActivity {

    @BindView(R.id.resident)
    TextView resident;

    @BindView(R.id.today_date)
    TextView todayDate;

    int id;
    String date;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_task);

        ButterKnife.bind(this);

        setUp();
    }

    private void setUp() {
        receiveIntent();

        resident.setText(name);
        todayDate.setText(date);
    }

    private void receiveIntent() {
        if (getIntent() != null && getIntent().getStringExtra(Keys.NAME_KEY) != null
                && getIntent().getStringExtra(Keys.DATE_KEY) != null) {
            id = getIntent().getIntExtra(Keys.ID_KEY, -1);
            date = getIntent().getStringExtra(Keys.DATE_KEY);
            name = getIntent().getStringExtra(Keys.NAME_KEY);
        } else {
            finish();
        }
    }

    @OnClick(R.id.mood)
    public void onMoodClicked() {
        Intent intent = new Intent(this, MoodActivity.class);
        intent.putExtra(Keys.NAME_KEY, name);
        intent.putExtra(Keys.ID_KEY, id);
        intent.putExtra(Keys.DATE_KEY, date);
        startActivity(intent);
    }

    @OnClick(R.id.personal_care)
    public void onPersonalCareClicked() {
        Intent intent = new Intent(this, PersonalCareActivity.class);
        intent.putExtra(Keys.NAME_KEY, name);
        intent.putExtra(Keys.ID_KEY, id);
        intent.putExtra(Keys.DATE_KEY, date);
        startActivity(intent);
    }

    @OnClick(R.id.fluid)
    public void onFluidClicked() {
        Intent intent = new Intent(this, FluidActivity.class);
        intent.putExtra(Keys.NAME_KEY, name);
        intent.putExtra(Keys.ID_KEY, id);
        intent.putExtra(Keys.DATE_KEY, date);
        startActivity(intent);
    }

    @OnClick(R.id.activity)
    public void onActivityClicked() {
        Intent intent = new Intent(this, DailyActivity.class);
        intent.putExtra(Keys.NAME_KEY, name);
        intent.putExtra(Keys.ID_KEY, id);
        intent.putExtra(Keys.DATE_KEY, date);
        startActivity(intent);
    }

    @OnClick(R.id.meal)
    public void onMealClicked() {
        Intent intent = new Intent(this, MealActivity.class);
        intent.putExtra(Keys.NAME_KEY, name);
        intent.putExtra(Keys.ID_KEY, id);
        intent.putExtra(Keys.DATE_KEY, date);
        startActivity(intent);
    }

    @OnClick(R.id.accident)
    public void onAccidentClicked() {
        Intent intent = new Intent(this, AccidentActivity.class);
        intent.putExtra(Keys.NAME_KEY, name);
        intent.putExtra(Keys.ID_KEY, id);
        intent.putExtra(Keys.DATE_KEY, date);
        startActivity(intent);
    }

    @OnClick(R.id.visitation)
    public void onVisitationClicked() {
        Intent intent = new Intent(this, VisitationActivity.class);
        intent.putExtra(Keys.NAME_KEY, name);
        intent.putExtra(Keys.ID_KEY, id);
        intent.putExtra(Keys.DATE_KEY, date);
        startActivity(intent);
    }

    @OnClick(R.id.weakly_catalogue)
    public void onWeaklyCatalogueClicked() {
        Intent intent = new Intent(this, WeaklyCatalogueActivity.class);
        intent.putExtra(Keys.NAME_KEY, name);
        intent.putExtra(Keys.ID_KEY, id);
        intent.putExtra(Keys.DATE_KEY, date);
        startActivity(intent);
    }

    @OnClick(R.id.other)
    public void onOtherClicked() {
        Intent intent = new Intent(this, OtherActivity.class);
        intent.putExtra(Keys.NAME_KEY, name);
        intent.putExtra(Keys.ID_KEY, id);
        intent.putExtra(Keys.DATE_KEY, date);
        startActivity(intent);
    }

    @OnClick(R.id.back_btn)
    public void onBackPressed() {
        super.onBackPressed();
    }
}