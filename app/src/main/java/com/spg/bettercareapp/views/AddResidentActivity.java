package com.spg.bettercareapp.views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.adapter.CareTypeAdapter;
import com.spg.bettercareapp.adapter.SpinnerAdapter;
import com.spg.bettercareapp.model.Keys;
import com.spg.bettercareapp.model.Resident;
import com.spg.bettercareapp.model.ResidentViewModel;
import com.spg.bettercareapp.model.RowType;
import com.spg.bettercareapp.model.Sex;
import com.spg.bettercareapp.repo.ApiClient;
import com.spg.bettercareapp.repo.ApiInterface;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddResidentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.sex_value)
    Spinner spinner;

    @BindView(R.id.name_value)
    EditText nameValue;

    @BindView(R.id.name_error)
    TextView nameError;

    @BindView(R.id.date_of_birth_value)
    Button dateOfBirthValue;

    @BindView(R.id.room_no_value)
    EditText roomNoValue;

    @BindView(R.id.btn_save)
    Button save;

    @BindView(R.id.care_type_value)
    Spinner careType;

    private boolean isEnabled = false;
    private DatePickerDialog datePickerDialog;
    public static final String TAG = "AddResidentActivity";

    private int currYear, selYear;
    private String gender;
    private String roomNo;
    private String name;
    private String careTypeValue;


    OnGenderClickListener listener = (gender) -> {
        this.gender = gender;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resident);
        ButterKnife.bind(this);


        setUp();
    }

    private void setUp() {
        Sex.initGender();

        SpinnerAdapter adapter = new SpinnerAdapter(this, R.layout.spinner_custom_view, Sex.getGender());
        adapter.setOnGenderClickListener(listener);
        spinner.setAdapter(adapter);

        save.setEnabled(isEnabled);

        nameValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                validate(editable.toString());
            }
        });

        initDatePicker();
        initCareType();
        dateOfBirthValue.setText(getTodaysDate());
        roomNoValue.setText("0000");
        nameError.setVisibility(View.GONE);
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        currYear = year;
        selYear = currYear;
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateStringForDB(year, month, day);
                dateOfBirthValue.setText(date);
                selYear = year;
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
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String makeDateStringForDB(int day, int month, int year) {
        return day + "-" + month + "-" + year;
    }


    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    private void initCareType() {
        List<String> careValue = Arrays.asList("Single", "Double");
        CareTypeAdapter adapter = new CareTypeAdapter(this, careValue);
        if (careType != null) {
            careType.setAdapter(adapter);
            careType.setOnItemSelectedListener(this);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        careTypeValue = (String) adapterView.getSelectedItem();

        //This is the place where the care type is being set

        Log.i(TAG, "onItemSelected: " + careTypeValue);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @OnClick(R.id.date_of_birth_value)
    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    @OnFocusChange(R.id.name_value)
    public void onNameFocusChanged(boolean focus) {
        String name = nameValue.getText().toString().trim();
        if (focus) {
            nameError.setVisibility(View.GONE);
        } else {
            validate(name);
        }
    }

    private void validate(String name) {
        if (name.trim().equals("")) {
            isEnabled = false;
            showError(true);
        } else {
            isEnabled = true;
            showError(false);
        }
    }

    private void showError(boolean isError) {
        if (isError) {
            nameError.setVisibility(View.VISIBLE);
            save.setEnabled(false);
        } else {
            nameError.setVisibility(View.GONE);
            save.setEnabled(true);
        }

    }

    @OnClick(R.id.btn_save)
    public void onSaveClick() {
        save.setEnabled(isEnabled);
        if (isEnabled) {
            name = nameValue.getText().toString();
            roomNo = roomNoValue.getText().toString();
            String date = dateOfBirthValue.getText().toString();
            // TODO : Update this with real time data from user
            saveRequest(name, date, careTypeValue, gender, roomNo);
        }
    }

    public void saveRequest(String name, String dob, String careType, String sex, String roomNo) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Resident>> call = apiInterface.setResident(name, dob, careType, sex, roomNo);
        call.enqueue(new Callback<List<Resident>>() {
            @Override
            public void onResponse(Call<List<Resident>> call, Response<List<Resident>> response) {
                Log.d("Add Resident-Activity", "Save Successfully");
                Resident resident = response.body().get(0);
                ResidentViewModel model;
                if (resident != null) {
                    model = new ResidentViewModel(resident.getName(),
                            Integer.toString(currYear - selYear),
                            resident.getCare_type(),
                            resident.getResident_id(),
                            resident.getSex(),
                            resident.getRoom_no(),
                            RowType.ADMIN_ROW_TYPE);
                } else {
                    model = new ResidentViewModel(name,
                            Integer.toString(currYear - selYear),
                            "test",
                            10,
                            gender,
                            Integer.parseInt(roomNo),
                            RowType.ADMIN_ROW_TYPE);
                }

                //if success then
                Intent intent = new Intent(AddResidentActivity.this, AdminDashboardActivity.class);
                // TODO: key = resident.id
                intent.putExtra(Keys.ADD_RESIDENT_KEY, model);
                setResult(0020, intent);
                finish();
            }

            @Override
            public void onFailure(Call<List<Resident>> call, Throwable t) {
                Log.d("Add Resident-Activity", "Save Failed " + t);
                Toast.makeText(AddResidentActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.back_btn)
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.spinner_image)
    public void onCareTypeClicked() {
        careType.performClick();
    }


}