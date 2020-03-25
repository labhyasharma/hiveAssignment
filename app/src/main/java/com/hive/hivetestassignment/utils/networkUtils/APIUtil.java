package com.hive.hivetestassignment.utils.networkUtils;

import com.google.gson.JsonSyntaxException;
import com.hive.hivetestassignment.AppRoot;
import com.hive.hivetestassignment.utils.Constants;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Utility class to parse the error in a
 * well formed message.
 *
 * @author Labhya Sharma
 */
public class APIUtil {

    public static String parseError(Response<?> response) {

        Converter<ResponseBody, APIErrorResponseDataModel> converter;
        try{
            converter = RetrofitClientInstance.getRetrofitInstance(
                    AppRoot.getInstance()).responseBodyConverter(APIErrorResponseDataModel.class, new Annotation[0]);
        }catch (Exception e){
            e.printStackTrace();
            return Constants.SERVER_ERROR;
        }

        APIErrorResponseDataModel error=null;


        if(response==null || response.errorBody()==null){
            return Constants.SERVER_ERROR;
        }


        try {
            error = converter.convert(response.errorBody());
        } catch (IOException | JsonSyntaxException e) {
            e.printStackTrace();
        }

        return Constants.SERVER_ERROR;
    }
}
