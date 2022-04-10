package com.spg.bettercareapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.CustomMoodModel;

import java.util.List;

public class MoodSpinnerAdapter extends ArrayAdapter<CustomMoodModel> {
    public MoodSpinnerAdapter(@NonNull Context context, @NonNull List<CustomMoodModel> customMoodModels) {
        super(context, 0, customMoodModels);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_mood_spinner,parent,false);
        }
        CustomMoodModel item = getItem(position);
        TextView mood_view = convertView.findViewById(R.id.mood_view);
        if(item!=null){
            mood_view.setText(item.getMood());
        }
        return convertView;
    }
}
