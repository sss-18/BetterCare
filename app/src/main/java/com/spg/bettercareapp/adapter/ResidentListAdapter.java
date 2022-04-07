package com.spg.bettercareapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.ResidentViewModel;
import com.spg.bettercareapp.views.OnDeleteClickListener;
import com.spg.bettercareapp.views.OnResidentClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResidentListAdapter extends RecyclerView.Adapter<ResidentListAdapter.ResidentListViewHolder> {

    Context context;
    private OnDeleteClickListener listener;
    private OnResidentClickListener residentClickListener;
    LayoutInflater inflater;
    List<ResidentViewModel> models;

    public ResidentListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<ResidentViewModel> models) {
        this.models = models;
    }

    public void setOnDeleteClickListener(final OnDeleteClickListener listener) {
        this.listener = listener;
    }

    public void setOnResidentClickListener(final OnResidentClickListener residentClickListener) {
        this.residentClickListener = residentClickListener;
    }

    @NonNull
    @Override
    public ResidentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.resident_custom_view, parent, false);
        ResidentListViewHolder viewHolder = new ResidentListViewHolder(view, listener,residentClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResidentListViewHolder holder, int position) {
        holder.bind(models.get(position), position);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

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
        private  OnResidentClickListener residentClickListener;
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
            nameValue.setText(model.getName());
            ageValue.setText(model.getAge());
            careTypeValue.setText(model.getCareType());
        }

        @OnClick(R.id.btn_delete)
        public void onDeleteClick() {
            listener.onClick(model, position);
        }

        @OnClick(R.id.root_view)
        public void onResidentClick(){
            residentClickListener.onClick(model);
        }
    }
}
