package com.spg.bettercareapp.views;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.adapter.MoodSpinnerAdapter;
import com.spg.bettercareapp.model.CustomMoodModel;
import com.spg.bettercareapp.model.Keys;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoodActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.mood_spinner)
    Spinner moodSpinner;

    @BindView(R.id.resident)
    TextView resident;

    @BindView(R.id.today_date)
    TextView todayDate;
    int id;
    String date;
    String name;

    private String mood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        ButterKnife.bind(this);
        setUp();
    }

    private void setUp() {
        initSpinnerAdapter();
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

    private void initSpinnerAdapter() {
        List<CustomMoodModel> listMood = new ArrayList<>();
        listMood.add(new CustomMoodModel("Happy", 0));
        listMood.add(new CustomMoodModel("Sad", 1));
        listMood.add(new CustomMoodModel("Angry", 2));
        listMood.add(new CustomMoodModel("Confused", 3));
        listMood.add(new CustomMoodModel("Laugh", 4));
        listMood.add(new CustomMoodModel("Sleepy", 5));
        listMood.add(new CustomMoodModel("Smile", 6));
        listMood.add(new CustomMoodModel("Loving", 7));

        MoodSpinnerAdapter adapter = new MoodSpinnerAdapter(this, listMood);

        if (moodSpinner != null) {
            moodSpinner.setAdapter(adapter);
            moodSpinner.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        CustomMoodModel model = (CustomMoodModel) adapterView.getSelectedItem();
        mood = model.getMood();
        //got the selected value
        //use this to save .
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}