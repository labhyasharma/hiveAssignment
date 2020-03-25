package com.hive.hivetestassignment.utils;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

public class LinearLayoutManagerWrapperUIUtil extends LinearLayoutManager {
    public LinearLayoutManagerWrapperUIUtil(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }
}