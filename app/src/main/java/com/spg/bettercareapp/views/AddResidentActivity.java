package com.spg.bettercareapp.views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.adapter.SpinnerAdapter;
import com.spg.bettercareapp.model.Keys;
import com.spg.bettercareapp.model.ResidentDetail;
import com.spg.bettercareapp.model.ResidentViewModel;
import com.spg.bettercareapp.model.Sex;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class AddResidentActivity extends AppCompatActivity {
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

    private boolean isEnabled = false;
    private DatePickerDialog datePickerDialog;
    ResidentDetail residentDetail = new ResidentDetail();
    public static final String TAG = "AddResidentActivity";
    private int currYear,selYear;
    OnGenderClickListener listener = (gender) -> {
        residentDetail.setSex(gender);
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
        dateOfBirthValue.setText(getTodaysDate());
        nameError.setVisibility(View.GONE);
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        currYear=year;
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateOfBirthValue.setText(date);
                selYear=year;
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
            String name = nameValue.getText().toString();
            String date = dateOfBirthValue.getText().toString();
            String roomNo = roomNoValue.getText().toString();
            residentDetail.setName(name);
            residentDetail.setDateOfBirth(date);
            residentDetail.setRoomNo(roomNo);
            ResidentViewModel model = new ResidentViewModel(name,Integer.toString(currYear-selYear),"test");
            Log.i(TAG, "onSaveClick: " + residentDetail.toString()+" model->"+model);
            //make network call to save the data
            //if success then
            Intent intent = new Intent(this,AdminDashboardActivity.class);
            intent.putExtra(Keys.ADD_RESIDENT_KEY,model);
            setResult(0020,intent);
            finish();
            //if failure
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG);
        }
    }
}