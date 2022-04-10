package com.spg.bettercareapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.adapter.ResidentListAdapter;
import com.spg.bettercareapp.model.Keys;
import com.spg.bettercareapp.model.Resident;
import com.spg.bettercareapp.model.ResidentInfoViewModel;
import com.spg.bettercareapp.model.ResidentViewModel;
import com.spg.bettercareapp.model.RowType;
import com.spg.bettercareapp.repo.ApiClient;
import com.spg.bettercareapp.repo.ApiInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResidentDetailsActivity extends AppCompatActivity {
    List<ResidentViewModel> models;
    public static final String TAG = "AdminDashboardActivity";
    private int requestCode = 0010;
    private String todayDate;
    ResidentListAdapter adapter;
    private List<ResidentViewModel> model = new ArrayList<>();

    @BindView(R.id.residents_list)
    RecyclerView residentsList;

    OnResidentInfoClickListener listener = (model) -> {
        //navigate to care type activity.
        Intent intent = new Intent(this,ChooseTaskActivity.class);
        intent.putExtra(Keys.NAME_KEY,model.getName());
        intent.putExtra(Keys.ID_KEY,model.getId());
        intent.putExtra(Keys.DATE_KEY,todayDate);
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident_details);

        ButterKnife.bind(this);
        setUp();
    }

    private void setUp() {
        initAdapter();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        todayDate = makeDateString(day,month,year);
    }
    private String makeDateString(int day, int month, int year){
        String date = Integer.toString(year);
        if(month<10) date = date+"-0"+Integer.toString(month);
        else date =date + "-"+Integer.toString(month);

        if(day<10) date = date+"-0"+Integer.toString(day);
        else date = date+"-"+Integer.toString(day);

        return date;

    }

    private void initAdapter() {
        adapter = new ResidentListAdapter(this, model);
        adapter.setOnResidentInfoClickListener(listener);
        // TODO : Uncomment once done with development
        //fetchResidentData();
        adapter.addData(getDummyData());
        residentsList.setLayoutManager(new LinearLayoutManager(this));
        residentsList.setAdapter(adapter);
    }


    private List<ResidentViewModel> getDummyData() {
        models = new ArrayList<>();
        models.add(new ResidentViewModel("ross", "30", "test", 0, RowType.CARE_PERSON_ROW_TYPE));
        models.add(new ResidentViewModel("peter", "35", "test", 1,RowType.CARE_PERSON_ROW_TYPE));
        models.add(new ResidentViewModel("mark ", "15", "test", 2,RowType.CARE_PERSON_ROW_TYPE));
        models.add(new ResidentViewModel("meg", "30", "test", 3,RowType.CARE_PERSON_ROW_TYPE));
        models.add(new ResidentViewModel("robert", "30", "test", 4,RowType.CARE_PERSON_ROW_TYPE));
        return models;
    }

    private void fetchResidentData() {
        models = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Resident>> call = apiInterface.getResidents();
        call.enqueue(new Callback<List<Resident>>() {
            @Override
            public void onResponse(Call<List<Resident>> call, Response<List<Resident>> response) {
                Log.d("Resident-Activity", "Fetched Successfully Resident");
                List<Resident> residents = response.body();

                for (Resident resident : residents) {
                    models.add(new ResidentViewModel(resident.getName(), resident.getDob().toString(), resident.getCare_type(), RowType.CARE_PERSON_ROW_TYPE));
                }
                adapter.addData(models);
            }

            @Override
            public void onFailure(Call<List<Resident>> call, Throwable t) {
                Log.d("Resident-Activity", "Fetched Successfully Failed");
            }
        });
    }
}