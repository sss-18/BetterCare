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

public class CareTypeAdapter extends ArrayAdapter<String> {
    public CareTypeAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, 0, objects);
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

    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_care_type, parent, false);
        }
        String item = getItem(position);
        TextView mood_view = convertView.findViewById(R.id.care_type_view);
        if (item != null) {
            mood_view.setText(item);
        }
        return convertView;
    }
}
