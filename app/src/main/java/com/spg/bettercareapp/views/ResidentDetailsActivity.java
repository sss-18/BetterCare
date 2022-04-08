package com.spg.bettercareapp.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.adapter.ResidentListAdapter;
import com.spg.bettercareapp.model.ResidentViewModel;
import com.spg.bettercareapp.model.RowType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResidentDetailsActivity extends AppCompatActivity {
    List<ResidentViewModel> models;
    public static final String TAG = "AdminDashboardActivity";
    private int requestCode = 0010;
    ResidentListAdapter adapter;

    @BindView(R.id.residents_list)
    RecyclerView residentsList;

    OnResidentInfoClickListener listener = (model) -> {
        //navigate to care type activity.
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
    }

    private void initAdapter() {
        adapter = new ResidentListAdapter(this);
        adapter.setOnResidentInfoClickListener(listener);
        adapter.addData(getDummyData());
        residentsList.setLayoutManager(new LinearLayoutManager(this));
        residentsList.setAdapter(adapter);
    }


    private List<ResidentViewModel> getDummyData() {
        models = new ArrayList<>();
        models.add(new ResidentViewModel("ross", "30", "test", RowType.CARE_PERSON_ROW_TYPE));
        models.add(new ResidentViewModel("peter", "35", "test", RowType.CARE_PERSON_ROW_TYPE));
        models.add(new ResidentViewModel("mark ", "15", "test", RowType.CARE_PERSON_ROW_TYPE));
        models.add(new ResidentViewModel("meg", "30", "test", RowType.CARE_PERSON_ROW_TYPE));
        models.add(new ResidentViewModel("robert", "30", "test", RowType.CARE_PERSON_ROW_TYPE));
        return models;
    }
}