package com.spg.bettercareapp.views;

import com.spg.bettercareapp.model.ResidentDetail;
import com.spg.bettercareapp.model.ResidentViewModel;

public interface OnDeleteClickListener {
    void onClick(ResidentViewModel model, int position);
}
