package com.spg.bettercareapp.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Keys;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class PersonalCareActivity extends AppCompatActivity {
    int id;
    String date;
    String name;
    @BindView(R.id.resident)
    TextView resident;

    @BindView(R.id.today_date)
    TextView todayDate;

    private String TAG = "PersonalCareActivity";
    private boolean isBathing = false;
    private boolean isSkinCare = false;
    private boolean isOralCare = false;
    private boolean isDressing = false;
    private boolean isPadChange = false;
    private boolean isHairCare = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_care);
        ButterKnife.bind(this);

        setUp();
    }

    private void setUp() {
        receiveIntent();

        resident.setText(name);
        todayDate.setText(date);

        //make network call and pre-populate the data
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

    @OnCheckedChanged(R.id.bathing)
    public void onCheckedBathing(CompoundButton button, boolean isBathing) {
        this.isBathing = isBathing;
    }

    @OnCheckedChanged(R.id.skin_care)
    public void onCheckedSkinCare(CompoundButton button, boolean isSkinCare) {
        this.isSkinCare = isSkinCare;
    }

    @OnCheckedChanged(R.id.oral_care)
    public void onOralCare(CompoundButton button, boolean isOralCare) {
        this.isOralCare = isOralCare;
    }

    @OnCheckedChanged(R.id.dressing)
    public void onDressing(CompoundButton button, boolean isDressing) {
        this.isDressing = isDressing;
    }

    @OnCheckedChanged(R.id.pad_change)
    public void onPadChange(CompoundButton button, boolean isPadChange) {
        this.isPadChange = isPadChange;
    }

    @OnCheckedChanged(R.id.hair_care)
    public void onHairChange(CompoundButton button, boolean isHairCare) {
        this.isHairCare = isHairCare;
        Log.i(TAG, "onHairChange: " + this.isHairCare + "pad change " + isPadChange + "oral care " + isOralCare + "bathing " + isBathing);
    }

    @OnClick(R.id.btn_save)
    public void onSaveClicked() {
        //make the network call to save the s
    }

    @OnClick(R.id.back_btn)
    public void onBackPressed() {
        super.onBackPressed();
    }
}