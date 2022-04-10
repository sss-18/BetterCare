package com.spg.bettercareapp.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.ResidentViewModel;
import com.spg.bettercareapp.views.OnResidentInfoClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResidentInfoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.name_value)
    TextView nameValue;
    @BindView(R.id.age_value)
    TextView ageValue;
    @BindView(R.id.care_type_value)
    TextView careTypeValue;
    @BindView(R.id.gender_value)
    TextView genderValue;
    @BindView(R.id.room_no_value)
    TextView roomNoValue;
    private int position;
    private ResidentViewModel model;
    final OnResidentInfoClickListener residentInfoClickListener;

    public ResidentInfoViewHolder(@NonNull View itemView, final OnResidentInfoClickListener residentInfoClickListener) {
        super(itemView);
        this.residentInfoClickListener = residentInfoClickListener;
        ButterKnife.bind(this, itemView);
    }

    public void bind(ResidentViewModel model, int position) {
        this.model = model;
        this.position = position;
        if (model != null) {
            nameValue.setText(model.getName());
            ageValue.setText(model.getAge());
            careTypeValue.setText(model.getCareType());
            genderValue.setText(model.getSex());
            roomNoValue.setText(Integer.toString(model.getRoomNo()));
        }
    }

    @OnClick(R.id.root_view)
    public void onResidentClick() {
        residentInfoClickListener.onClick(model);
    }

}
