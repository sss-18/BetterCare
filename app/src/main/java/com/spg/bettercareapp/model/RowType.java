package com.spg.bettercareapp.model;

public enum RowType {
    ADMIN_ROW_TYPE("adminRowType"),
    CARE_PERSON_ROW_TYPE("carePersonRowType");

    private String rowType;
    RowType(String rowType) {
        this.rowType = rowType;
    }
}
