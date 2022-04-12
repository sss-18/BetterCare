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
import com.spg.bettercareapp.model.RowChangeModel;
import com.spg.bettercareapp.model.Keys;
import com.spg.bettercareapp.model.Resident;
import com.spg.bettercareapp.model.ResidentViewModel;
import com.spg.bettercareapp.model.RowType;
import com.spg.bettercareapp.repo.ApiClient;
import com.spg.bettercareapp.repo.ApiInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        deleteResident(Integer.toString(model.getId()), position);
    };
    OnResidentClickListener residentClickListener = (model) -> {
        Log.i(TAG, "residentClickListener: clicked");
        Intent intent = new Intent(this,PastRecordActivity.class);
        intent.putExtra(Keys.MODEL_KEY,model);
        startActivity(intent);
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
        fetchResidentData();
        //adapter.addData(getDummyData());
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
        models.add(new ResidentViewModel("ross", "30", "test",0,"male",0001,RowType.ADMIN_ROW_TYPE));
        models.add(new ResidentViewModel("peter", "35", "test", 1,"male",0001,RowType.ADMIN_ROW_TYPE));
        models.add(new ResidentViewModel("mark ", "15", "test", 2,"male",0001,RowType.ADMIN_ROW_TYPE));
        models.add(new ResidentViewModel("meg", "30", "test", 3,"male",0001,RowType.ADMIN_ROW_TYPE));
        models.add(new ResidentViewModel("robert", "30", "test", 4,"male",0001,RowType.ADMIN_ROW_TYPE));
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
                    DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
                    DateFormat formatter1 = new SimpleDateFormat("dd.MM.yyyy");
                    String date = null;
                    try {
                        date = formatter1.format(formatter.parse(resident.getDob().toString()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String str[] = date.split("\\.");
                    int year = Integer.parseInt(str[2]);

                    Calendar calendar = Calendar.getInstance();
                    int currYear = calendar.get(Calendar.YEAR);


                    models.add(new ResidentViewModel(resident.getName(), Integer.toString(currYear-year),
                            resident.getCare_type(), resident.getResident_id(), resident.getSex(),
                            resident.getRoom_no(), RowType.ADMIN_ROW_TYPE));

                }
                adapter.addData(models);
            }

            @Override
            public void onFailure(Call<List<Resident>> call, Throwable t) {
                Log.d("Resident-Activity", "Fetched Successfully Failed");
            }
        });
    }

    public void deleteResident(String resident_id, int position) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<RowChangeModel>> call = apiInterface.deleteResident(resident_id);
        call.enqueue(new Callback<List<RowChangeModel>>() {
            @Override
            public void onResponse(Call<List<RowChangeModel>> call, Response<List<RowChangeModel>> response) {
                if(response.isSuccessful()){
                    Log.d("Resident-Activity", "Deleted Successfully");
                    models.remove(position);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<List<RowChangeModel>> call, Throwable t) {
                Log.d("Resident-Activity", "Deleted Failed");
            }
        });
    }
}