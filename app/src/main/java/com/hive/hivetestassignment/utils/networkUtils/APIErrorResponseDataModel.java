package com.hive.hivetestassignment.utils.networkUtils;

/**
 * Data Model class to handle the error message.
 *
 * @author Labhya Sharma
 */
public class APIErrorResponseDataModel {
    private String code;
    private String message;
    private String details;

    public APIErrorResponseDataModel(){

    }

    public APIErrorResponseDataModel(String code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
