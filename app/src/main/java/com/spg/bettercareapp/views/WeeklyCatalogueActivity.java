package com.spg.bettercareapp.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
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
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeeklyCatalogueActivity extends AppCompatActivity {
    int id;
    String date;
    String name;
    @BindView(R.id.resident)
    TextView resident;

    @BindView(R.id.today_date)
    TextView todayDate;

    @BindView(R.id.note)
    EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weakly_catalogue);
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

    @OnClick(R.id.back_btn)
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.btn_save)
    public void onSaveClicked() {
        insertData();
    }

    public void insertData() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<RowChangeModel>> call = apiInterface.setCatalogue(date, Integer.toString(id), note.getText().toString());
        call.enqueue(new Callback<List<RowChangeModel>>() {
            @Override
            public void onResponse(Call<List<RowChangeModel>> call, Response<List<RowChangeModel>> response) {
                Log.d("Fluid Activity","Successful");
                Toast.makeText(WeeklyCatalogueActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<RowChangeModel>> call, Throwable t) {
                Log.d("Fluid Activity","Failed");
                Toast.makeText(WeeklyCatalogueActivity.this, "Saved Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}