package com.spg.bettercareapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.adapter.ResidentListAdapter;
import com.spg.bettercareapp.model.Keys;
import com.spg.bettercareapp.model.Resident;
import com.spg.bettercareapp.model.ResidentViewModel;
import com.spg.bettercareapp.model.RowType;
import com.spg.bettercareapp.repo.ApiClient;
import com.spg.bettercareapp.repo.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDashboardActivity extends AppCompatActivity {
    @BindView(R.id.residents_list)
    RecyclerView residentsList;

    List<ResidentViewModel> models;
    public static final String TAG = "AdminDashboardActivity";
    private int requestCode = 0010;
    ResidentListAdapter adapter;
    private List<ResidentViewModel> model = new ArrayList<>();

    OnDeleteClickListener listener = (model, position) -> {
        Log.i(TAG, ":OnDeleteClickListener clicked");
        models.remove(position);
        adapter.notifyDataSetChanged();
    };
    OnResidentClickListener residentClickListener = (model) -> {
        Log.i(TAG, "residentClickListener: clicked");
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        ButterKnife.bind(this);
        setUp();
    }

    private void setUp() {

        initAdapter();
    }

    private void initAdapter() {
        adapter = new ResidentListAdapter(this, model);
        adapter.setOnDeleteClickListener(listener);
        adapter.setOnResidentClickListener(residentClickListener);
        // TODO : Uncomment once done with development
        //fetchResidentData();
        adapter.addData(getDummyData());
        residentsList.setLayoutManager(new LinearLayoutManager(this));
        residentsList.setAdapter(adapter);
    }

    @OnClick(R.id.btn_add_resident)
    public void onAddResidentClicked(){
        startActivityForResult(new Intent(this,AddResidentActivity.class),requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode==requestCode && resultCode==0020){
            if(data.getParcelableExtra(Keys.ADD_RESIDENT_KEY)!=null){
                ResidentViewModel residentViewModel = data.getParcelableExtra(Keys.ADD_RESIDENT_KEY);
                residentViewModel.set(RowType.ADMIN_ROW_TYPE);
                models.add(residentViewModel);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private List<ResidentViewModel> getDummyData() {
        models = new ArrayList<>();
        models.add(new ResidentViewModel("ross", "30", "test", 0,RowType.ADMIN_ROW_TYPE));
        models.add(new ResidentViewModel("peter", "35", "test", 1,RowType.ADMIN_ROW_TYPE));
        models.add(new ResidentViewModel("mark ", "15", "test", 2,RowType.ADMIN_ROW_TYPE));
        models.add(new ResidentViewModel("meg", "30", "test", 3,RowType.ADMIN_ROW_TYPE));
        models.add(new ResidentViewModel("robert", "30", "test", 4,RowType.ADMIN_ROW_TYPE));
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
                    models.add(new ResidentViewModel(resident.getName(), resident.getDob().toString(), resident.getCare_type(), RowType.ADMIN_ROW_TYPE));
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