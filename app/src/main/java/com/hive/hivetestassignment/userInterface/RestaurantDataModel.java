package com.hive.hivetestassignment.userInterface;

public class RestaurantDataModel implements Comparable{
    private RestaurantDetailDataModel restaurant;

    public RestaurantDetailDataModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDetailDataModel restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public int compareTo(Object restaurantDataModel) {
        int compareage= Integer.parseInt(((RestaurantDataModel)restaurantDataModel).getRestaurant().getAverage_cost_for_two());
        /* For Ascending order*/
        return Integer.parseInt(this.restaurant.getAverage_cost_for_two())-compareage;
    }
}

