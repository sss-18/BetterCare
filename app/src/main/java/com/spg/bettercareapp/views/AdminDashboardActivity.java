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
import com.spg.bettercareapp.model.ResidentViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdminDashboardActivity extends AppCompatActivity {
    @BindView(R.id.residents_list)
    RecyclerView residentsList;

    List<ResidentViewModel> models;
    public static final String TAG = "AdminDashboardActivity";
    private int requestCode = 0010;
    ResidentListAdapter adapter;

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
        adapter = new ResidentListAdapter(this);
        adapter.setOnDeleteClickListener(listener);
        adapter.setOnResidentClickListener(residentClickListener);
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
                models.add(residentViewModel);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private List<ResidentViewModel> getDummyData() {
        models = new ArrayList<>();
        models.add(new ResidentViewModel("ross", "30", "test"));
        models.add(new ResidentViewModel("peter", "35", "test"));
        models.add(new ResidentViewModel("mark ", "15", "test"));
        models.add(new ResidentViewModel("meg", "30", "test"));
        models.add(new ResidentViewModel("robert", "30", "test"));
        return models;
    }
}