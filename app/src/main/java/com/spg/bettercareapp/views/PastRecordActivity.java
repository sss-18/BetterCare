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
import com.spg.bettercareapp.model.Accident;
import com.spg.bettercareapp.model.Catalogue;
import com.spg.bettercareapp.model.DailyActivity;
import com.spg.bettercareapp.model.Fluid;
import com.spg.bettercareapp.model.Keys;
import com.spg.bettercareapp.model.Meal;
import com.spg.bettercareapp.model.Mood;
import com.spg.bettercareapp.model.OtherComment;
import com.spg.bettercareapp.model.PersonalCare;
import com.spg.bettercareapp.model.ResidentViewModel;
import com.spg.bettercareapp.model.Visit;
import com.spg.bettercareapp.repo.ApiClient;
import com.spg.bettercareapp.repo.ApiInterface;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private int id;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_record);
        ButterKnife.bind(this);

        setUp();
    }

    private void setUp() {
        setInVisibility();
        receiveIntent();
        setUpDate();
        bathing.setChecked(true);
    }

    private void setInVisibility() {
        personalCare.setVisibility(View.GONE);
        fluid.setVisibility(View.GONE);
        meal.setVisibility(View.GONE);
        mood.setVisibility(View.GONE);
        accident.setVisibility(View.GONE);
        daily.setVisibility(View.GONE);
        visitation.setVisibility(View.GONE);
        weaklyCatalogue.setVisibility(View.GONE);
        other.setVisibility(View.GONE);
    }

    private void receiveIntent() {
        if (getIntent() != null && getIntent().getParcelableExtra(Keys.MODEL_KEY) != null) {
            this.model = getIntent().getParcelableExtra(Keys.MODEL_KEY);
            nameValue.setText(model.getName());
            ageValue.setText(model.getAge());
            sexValue.setText(model.getSex());
            roomNoValue.setText(Integer.toString(model.getRoomNo()));
            id = model.getId();
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
                date = makeDateString(day, month, year);
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
    public void onSearchClicked() {
        date = dateValue.getText().toString();
        fetchAccident();
        fetchMeal();
        fetchDailyAct();
        fetchFluid();
        fetchMood();
        fetchOtherComments();
        fetchPersonalCare();
        fetchVisitation();
        fetchWeeklyCatalogue();
    }

    public Boolean intToBool(int number) {
        return number == 1;
    }

    public void fetchAccident() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Accident>> call = apiInterface.getAccidents(Integer.toString(id), date);
        call.enqueue(new Callback<List<Accident>>() {
            @Override
            public void onResponse(Call<List<Accident>> call, Response<List<Accident>> response) {
                Accident accidentResponse = response.body().get(0);
                accident.setVisibility(View.VISIBLE);
                accidentValue.setText(accidentResponse.getNote());
            }

            @Override
            public void onFailure(Call<List<Accident>> call, Throwable t) {
                accident.setVisibility(View.GONE);
            }
        });
    }

    public void fetchMeal() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Meal>> call = apiInterface.getMeals(Integer.toString(id), date);
        call.enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                Meal mealResponse = response.body().get(0);
                meal.setVisibility(View.VISIBLE);
                breakfast.setChecked(intToBool(mealResponse.getBreakfast()));
                snack.setChecked(intToBool(mealResponse.getSnack()));
                lunch.setChecked(intToBool(mealResponse.getLunch()));
                dinner.setChecked(intToBool(mealResponse.getDinner()));
            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
                meal.setVisibility(View.GONE);
            }
        });
    }

    public void fetchMood() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Mood>> call = apiInterface.getMoods(Integer.toString(id), date);
        call.enqueue(new Callback<List<Mood>>() {
            @Override
            public void onResponse(Call<List<Mood>> call, Response<List<Mood>> response) {
                Mood moodResponse = response.body().get(0);
                mood.setVisibility(View.VISIBLE);
                moodValue.setText(moodResponse.getMood_type());
            }

            @Override
            public void onFailure(Call<List<Mood>> call, Throwable t) {
                mood.setVisibility(View.GONE);
            }
        });
    }

    public void fetchOtherComments() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<OtherComment>> call = apiInterface.getComments(Integer.toString(id), date);
        call.enqueue(new Callback<List<OtherComment>>() {
            @Override
            public void onResponse(Call<List<OtherComment>> call, Response<List<OtherComment>> response) {
                OtherComment otherCommentResponse = response.body().get(0);
                other.setVisibility(View.VISIBLE);
                otherValue.setText(otherCommentResponse.getNote());
            }

            @Override
            public void onFailure(Call<List<OtherComment>> call, Throwable t) {
                other.setVisibility(View.GONE);
            }
        });
    }

    public void fetchPersonalCare() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<PersonalCare>> call = apiInterface.getPersonalCare(Integer.toString(id), date);
        call.enqueue(new Callback<List<PersonalCare>>() {
            @Override
            public void onResponse(Call<List<PersonalCare>> call, Response<List<PersonalCare>> response) {
                PersonalCare personalCareResponse = response.body().get(0);
                personalCare.setVisibility(View.VISIBLE);
                bathing.setChecked(intToBool(personalCareResponse.getBathing()));
                hairCare.setChecked(intToBool(personalCareResponse.getHair_care()));
                skinCare.setChecked(intToBool(personalCareResponse.getSkin_care()));
                padChange.setChecked(intToBool(personalCareResponse.getPad_change()));
                oralCare.setChecked(intToBool(personalCareResponse.getOral_care()));
                dressing.setChecked(intToBool(personalCareResponse.getDressing()));
            }

            @Override
            public void onFailure(Call<List<PersonalCare>> call, Throwable t) {
                personalCare.setVisibility(View.GONE);
            }
        });
    }

    public void fetchVisitation() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Visit>> call = apiInterface.getVisits(Integer.toString(id), date);
        call.enqueue(new Callback<List<Visit>>() {
            @Override
            public void onResponse(Call<List<Visit>> call, Response<List<Visit>> response) {
                visitation.setVisibility(View.VISIBLE);
                Visit visitResponse = response.body().get(0);
                visitationValue.setText(visitResponse.getNote());
            }

            @Override
            public void onFailure(Call<List<Visit>> call, Throwable t) {
                visitation.setVisibility(View.GONE);
            }
        });
    }

    public void fetchFluid() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Fluid>> call = apiInterface.getFluids(Integer.toString(id), date);
        call.enqueue(new Callback<List<Fluid>>() {
            @Override
            public void onResponse(Call<List<Fluid>> call, Response<List<Fluid>> response) {
                Fluid fluidRes = response.body().get(0);
                fluid.setVisibility(View.VISIBLE);
                oneFifty.setChecked(intToBool(fluidRes.getMl150()));
                twoFifty.setChecked(intToBool(fluidRes.getMl250()));
                threeFifty.setChecked(intToBool(fluidRes.getMl350()));
                moreThan350.setChecked(intToBool(fluidRes.getMore()));
            }

            @Override
            public void onFailure(Call<List<Fluid>> call, Throwable t) {
                fluid.setVisibility(View.GONE);
            }
        });
    }

    public void fetchDailyAct() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<DailyActivity>> call = apiInterface.getDailyActivity(Integer.toString(id), date);
        call.enqueue(new Callback<List<DailyActivity>>() {
            @Override
            public void onResponse(Call<List<DailyActivity>> call, Response<List<DailyActivity>> response) {
                DailyActivity dailyActivityRes = response.body().get(0);
                daily.setVisibility(View.VISIBLE);
                dailyValue.setText(dailyActivityRes.getNote());
            }

            @Override
            public void onFailure(Call<List<DailyActivity>> call, Throwable t) {
                daily.setVisibility(View.GONE);
            }
        });
    }

    public void fetchWeeklyCatalogue() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Catalogue>> call = apiInterface.getCatalogues(Integer.toString(id), date);
        call.enqueue(new Callback<List<Catalogue>>() {
            @Override
            public void onResponse(Call<List<Catalogue>> call, Response<List<Catalogue>> response) {
                Catalogue catalogueRes = response.body().get(0);
                weaklyCatalogue.setVisibility(View.VISIBLE);
                weaklyCatalogueValue.setText(catalogueRes.getNote());
            }

            @Override
            public void onFailure(Call<List<Catalogue>> call, Throwable t) {
                weaklyCatalogue.setVisibility(View.GONE);
            }
        });
    }
}