package com.spg.bettercareapp.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.views.OnDeleteClickListener;
import com.spg.bettercareapp.views.OnResidentClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResidentListViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.name_value)
    TextView nameValue;
    @BindView(R.id.age_value)
    TextView ageValue;
    @BindView(R.id.care_type_value)
    TextView careTypeValue;
    @BindView(R.id.root_view)
    ConstraintLayout rootView;

    private OnDeleteClickListener listener;
    private OnResidentClickListener residentClickListener;
    private int position;
    private ResidentViewModel model;

    public ResidentListViewHolder(@NonNull View itemView, final OnDeleteClickListener listener, final OnResidentClickListener residentClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.listener = listener;
        this.residentClickListener = residentClickListener;
    }

    public void bind(ResidentViewModel model, int position) {
        this.model = model;
        this.position = position;
        if (model != null) {
            nameValue.setText(model.getName());
            ageValue.setText(model.getAge());
            careTypeValue.setText(model.getCareType());
        }
    }

    @OnClick(R.id.btn_delete)
    public void onDeleteClick() {
        listener.onClick(model, position);
    }

    @OnClick(R.id.root_view)
    public void onResidentClick() {
        residentClickListener.onClick(model);
    }
}
