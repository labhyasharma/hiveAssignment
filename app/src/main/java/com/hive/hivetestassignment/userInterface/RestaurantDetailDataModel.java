package com.hive.hivetestassignment.userInterface;

public class RestaurantDetailDataModel {
    private String id;
    private String name;
    private String average_cost_for_two;
    private UserRatingDataModel user_rating;
    private String cuisines;
    private boolean header;

    public RestaurantDetailDataModel(boolean header) {
        this.header = header;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAverage_cost_for_two() {
        return average_cost_for_two;
    }

    public UserRatingDataModel getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(UserRatingDataModel user_rating) {
        this.user_rating = user_rating;
    }

    public void setAverage_cost_for_two(String average_cost_for_two) {
        this.average_cost_for_two = average_cost_for_two;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public boolean isHeader() {
        return header;
    }

    public void setHeader(boolean header) {
        this.header = header;
    }
}
