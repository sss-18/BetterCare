package com.spg.bettercareapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spg.bettercareapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PastRecordActivity extends AppCompatActivity {
    @BindView(R.id.personal_care)
    ConstraintLayout personalCare;
    @BindView(R.id.bathing)
    CheckBox bathing;
    @BindView(R.id.skin_care)
    CheckBox skinCare;
    @BindView(R.id.oral_care)
    CheckBox oralCare;
    @BindView(R.id.hair_care)
    CheckBox hairCare;
    @BindView(R.id.dressing)
    CheckBox dressing;
    @BindView(R.id.pad_change)
    CheckBox padChange;


    @BindView(R.id.fluid)
    ConstraintLayout fluid;
    @BindView(R.id.one_fifty)
    CheckBox oneFifty;
    @BindView(R.id.two_fifty)
    CheckBox twoFifty;
    @BindView(R.id.three_fifty)
    CheckBox threeFifty;
    @BindView(R.id.more_than_350)
    CheckBox moreThan350;



    @BindView(R.id.meal)
    ConstraintLayout meal;
    @BindView(R.id.breakfast)
    CheckBox breakfast;
    @BindView(R.id.snack)
    CheckBox snack;
    @BindView(R.id.lunch)
    CheckBox lunch;
    @BindView(R.id.dinner)
    CheckBox dinner;

    @BindView(R.id.mood)
    LinearLayout mood;
    @BindView(R.id.mood_value)
    TextView moodValue;

    @BindView(R.id.accident)
    LinearLayout accident;
    @BindView(R.id.accident_value)
    TextView accidentValue;

    @BindView(R.id.daily)
    LinearLayout daily;
    @BindView(R.id.daily_value)
    TextView dailyValue;

    @BindView(R.id.visitation)
    LinearLayout visitation;
    @BindView(R.id.visitation_value)
    TextView visitationValue;

    @BindView(R.id.weakly_catalogue)
    LinearLayout weaklyCatalogue;
    @BindView(R.id.weakly_catalogue_value)
    TextView weaklyCatalogueValue;

    @BindView(R.id.other)
    LinearLayout other;
    @BindView(R.id.other_value)
    TextView otherValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_record);
        ButterKnife.bind(this);

        setUp();
    }

    private void setUp(){
        //setInVisibility();

    }
    private void setInVisibility(){
        personalCare.setVisibility(View.GONE); ;
        fluid.setVisibility(View.GONE);
        meal.setVisibility(View.GONE);
        mood.setVisibility(View.GONE);
        accident.setVisibility(View.GONE);
        daily.setVisibility(View.GONE);
        visitation.setVisibility(View.GONE);
        weaklyCatalogue.setVisibility(View.GONE);
        other.setVisibility(View.GONE);
    }
}