package com.spg.bettercareapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.ViewHolders.ResidentInfoViewHolder;
import com.spg.bettercareapp.model.ResidentListViewHolder;
import com.spg.bettercareapp.model.ResidentViewModel;
import com.spg.bettercareapp.model.RowType;
import com.spg.bettercareapp.views.OnDeleteClickListener;
import com.spg.bettercareapp.views.OnResidentClickListener;
import com.spg.bettercareapp.views.OnResidentInfoClickListener;

import java.util.List;

public class ResidentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    private OnDeleteClickListener listener;
    private OnResidentClickListener residentClickListener;
    private OnResidentInfoClickListener residentInfoClickListener;
    LayoutInflater inflater;
    List<ResidentViewModel> models;

    public ResidentListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<ResidentViewModel> models) {
        this.models = models;
        notifyDataSetChanged();
    }

    public void setOnDeleteClickListener(final OnDeleteClickListener listener) {
        this.listener = listener;
    }

    public void setOnResidentClickListener(final OnResidentClickListener residentClickListener) {
        this.residentClickListener = residentClickListener;
    }

    public void setOnResidentInfoClickListener(final OnResidentInfoClickListener residentInfoClickListener) {
        this.residentInfoClickListener = residentInfoClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == RowType.ADMIN_ROW_TYPE.ordinal()) {
            View view = inflater.inflate(R.layout.resident_custom_view, parent, false);
            return new ResidentListViewHolder(view, listener, residentClickListener);
        } else {
            View view = inflater.inflate(R.layout.resident_info_custom_view, parent, false);
            return new ResidentInfoViewHolder(view, residentInfoClickListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case 0:
                ((ResidentListViewHolder) holder).bind(models.get(position), position);
                break;
            case 1:
                ((ResidentInfoViewHolder) holder).bind(models.get(position), position);
        }

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return models.get(position).get().ordinal();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

}
