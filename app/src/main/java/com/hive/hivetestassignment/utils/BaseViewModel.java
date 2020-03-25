package com.hive.hivetestassignment.utils;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    private final MutableLiveData<String> responseErrorModel = new MutableLiveData<>();

    protected void handleFailure(Throwable t) {
        t.printStackTrace();
    }

    public MutableLiveData<String> getResponseError() {
        return responseErrorModel;
    }

    public void setResponseError(String responseError){
        responseErrorModel.setValue(responseError);

    }
}
