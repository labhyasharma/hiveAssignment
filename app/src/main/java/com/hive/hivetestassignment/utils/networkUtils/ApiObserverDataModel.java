package com.hive.hivetestassignment.utils.networkUtils;

import java.util.List;

public class ApiObserverDataModel<T> {

    private BaseApiResponseDataModel responseDataModel;
    private List<T> baseApiResponseDataModels;
    private final String message;

    public ApiObserverDataModel(BaseApiResponseDataModel responseDataModel, String message) {
        this.responseDataModel = responseDataModel;
        this.message = message;
    }

    public ApiObserverDataModel(List<T> baseApiResponseDataModels, String message) {
        this.baseApiResponseDataModels = baseApiResponseDataModels;
        this.message = message;
    }

    public ApiObserverDataModel(String message) {
        this.message = message;
    }

    public BaseApiResponseDataModel getResponseDataModel() {
        return responseDataModel;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getBaseApiResponseDataModels() {
        return baseApiResponseDataModels;
    }
}
