package com.spg.bettercareapp.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Keys;
import com.spg.bettercareapp.model.RowChangeModel;
import com.spg.bettercareapp.repo.ApiClient;
import com.spg.bettercareapp.repo.ApiInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FluidActivity extends AppCompatActivity {
    int id;
    String date;
    String name;
    @BindView(R.id.resident)
    TextView resident;

    @BindView(R.id.today_date)
    TextView todayDate;

    private boolean oneFifty = false;
    private boolean twoFifty = false;
    private boolean threeFifty = false;
    private boolean moreThanThreeFifty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluid);
        ButterKnife.bind(this);
        setUp();
    }

    private void setUp() {
        receiveIntent();

        resident.setText(name);
        todayDate.setText(date);

        //make network call and pre-populate the data.
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

    @OnCheckedChanged(R.id.one_fifty)
    public void onChecked150(CompoundButton button, boolean isOneFifty) {
        this.oneFifty = isOneFifty;
    }

    @OnCheckedChanged(R.id.two_fifty)
    public void onChecked250(CompoundButton button, boolean isTwoFifty) {
        this.twoFifty = isTwoFifty;
    }

    @OnCheckedChanged(R.id.three_fifty)
    public void onChecked350(CompoundButton button, boolean isThreeFifty) {
        this.threeFifty = isThreeFifty;
    }

    @OnCheckedChanged(R.id.more_than_350)
    public void onCheckedMoreThan350(CompoundButton button, boolean isMoreThanThreeFifty) {
        this.moreThanThreeFifty = isMoreThanThreeFifty;
    }

    @OnClick(R.id.btn_save)
    public void onSaveClicked() {
        //make the network call to save the data.
        // TODO uncomment once done
        //insertData();
    }

    @OnClick(R.id.back_btn)
    public void onBackPressed() {
        super.onBackPressed();
    }

    public String boolToInt(Boolean bool) {
        if (bool) return "1";
        else return "0";
    }

    public void insertData() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<RowChangeModel>> call = apiInterface.setFluid(date, Integer.toString(id), boolToInt(oneFifty), boolToInt(twoFifty), boolToInt(threeFifty),
                boolToInt(moreThanThreeFifty));
        call.enqueue(new Callback<List<RowChangeModel>>() {
            @Override
            public void onResponse(Call<List<RowChangeModel>> call, Response<List<RowChangeModel>> response) {
                Log.d("Fluid Activity","Successful");
                Toast.makeText(FluidActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<RowChangeModel>> call, Throwable t) {
                Log.d("Fluid Activity","Failed");
                Toast.makeText(FluidActivity.this, "Saved Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}