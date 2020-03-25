package com.hive.hivetestassignment.userInterface;

import com.hive.hivetestassignment.utils.networkUtils.BaseApiResponseDataModel;

import java.util.List;

/**
 * Data model class to handle list
 *
 * @author Labhya Sharma
 */
public class RestaurantTopDataModel extends BaseApiResponseDataModel {
    private List<RestaurantDataModel> restaurants;

    public List<RestaurantDataModel> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<RestaurantDataModel> restaurants) {
        this.restaurants = restaurants;
    }
}
