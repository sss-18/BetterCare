package com.spg.bettercareapp.views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Keys;
import com.spg.bettercareapp.model.ResidentViewModel;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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


    @BindView(R.id.date_value)
    Button dateValue;

    @BindView(R.id.name_value)
    TextView nameValue;

    @BindView(R.id.age_value)
    TextView ageValue;

    @BindView(R.id.sex_value)
    TextView sexValue;

    @BindView(R.id.room_no_value)
    TextView roomNoValue;

    private DatePickerDialog datePickerDialog;
    private ResidentViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_record);
        ButterKnife.bind(this);

        setUp();
    }

    private void setUp() {
        //setInVisibility();
        receiveIntent();
        setUpDate();
        bathing.setChecked(true);
    }

    private void setInVisibility() {
        personalCare.setVisibility(View.GONE);
        ;
        fluid.setVisibility(View.GONE);
        meal.setVisibility(View.GONE);
        mood.setVisibility(View.GONE);
        accident.setVisibility(View.GONE);
        daily.setVisibility(View.GONE);
        visitation.setVisibility(View.GONE);
        weaklyCatalogue.setVisibility(View.GONE);
        other.setVisibility(View.GONE);
    }
    private void receiveIntent(){
        if(getIntent()!=null && getIntent().getParcelableExtra(Keys.MODEL_KEY)!=null){
            this.model = getIntent().getParcelableExtra(Keys.MODEL_KEY);
            nameValue.setText(model.getName());
            ageValue.setText(model.getAge());
            sexValue.setText(model.getSex());
            roomNoValue.setText(Integer.toString(model.getRoomNo()));
        }
    }
    private void setUpDate() {
        initDatePicker();
        dateValue.setText(getTodaysDate());
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateValue.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        String date = Integer.toString(year);
        if (month < 10) date = date + "-0" + Integer.toString(month);
        else date = date + "-" + Integer.toString(month);

        if (day < 10) date = date + "-0" + Integer.toString(day);
        else date = date + "-" + Integer.toString(day);

        return date;
    }

    @OnClick(R.id.date_value)
    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    @OnClick(R.id.btn_search)
    public void onSearchClicked(){

    }
}