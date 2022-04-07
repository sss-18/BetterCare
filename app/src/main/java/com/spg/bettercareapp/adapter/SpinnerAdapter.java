package com.spg.bettercareapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.spg.bettercareapp.R;
import com.spg.bettercareapp.model.Sex;
import com.spg.bettercareapp.views.OnGenderClickListener;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Sex> {

    private LayoutInflater inflater;
    private OnGenderClickListener listener;
    public static final String TAG = "SpinnerAdapter";
    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Sex> objects) {
        super(context, resource, objects);
        inflater = LayoutInflater.from(context);
    }
    public void setOnGenderClickListener(final OnGenderClickListener onGenderClickListener){
        this.listener = onGenderClickListener;
    }
    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.spinner_custom_view, null, false);
        Sex sex = getItem(i);
        TextView genderTitle = (TextView) rowView.findViewById(R.id.gender_title);
        LinearLayout root = (LinearLayout) rowView.findViewById(R.id.root);
        genderTitle.setText(sex.getSex());
        Log.i(TAG, "getDropDownView: "+sex.getSex());

        return rowView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) view = inflater.inflate(R.layout.spinner_custom_view, viewGroup, false);
        Sex sex = getItem(i);
        TextView genderTitle = (TextView) view.findViewById(R.id.gender_title);
        LinearLayout root = (LinearLayout) view.findViewById(R.id.root);
        genderTitle.setText(sex.getSex());
        Log.i(TAG, "getView: "+sex.getSex());
        listener.onClick(sex.getSex());
        return view;
    }

}
