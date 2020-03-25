package com.hive.hivetestassignment.utils.networkUtils;

import com.hive.hivetestassignment.userInterface.RestaurantDataModel;
import com.hive.hivetestassignment.userInterface.RestaurantTopDataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Interface to list the APIs.
 *
 * @author Labhya Sharma
 */
public interface APIInterface {

    @GET("search")
    Call<RestaurantTopDataModel> getRestaurantList(@Header("user-key") String userKey, @Query("q") String searchKey);
}
