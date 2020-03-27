package com.hive.hivetestassignment.userInterface;

public class RestaurantDataModel implements Comparable{
    private RestaurantDetailDataModel restaurant;
    private String header;

    public RestaurantDataModel(RestaurantDetailDataModel restaurant,String header) {
        this.restaurant = restaurant;
        this.header = header;
    }

    public RestaurantDetailDataModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDetailDataModel restaurant) {
        this.restaurant = restaurant;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public int compareTo(Object restaurantDataModel) {
        int compareage= Integer.parseInt(((RestaurantDataModel)restaurantDataModel).getRestaurant().getAverage_cost_for_two());
        /* For Ascending order*/
        return Integer.parseInt(this.restaurant.getAverage_cost_for_two())-compareage;
    }
}

