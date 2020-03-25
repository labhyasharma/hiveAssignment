package com.hive.hivetestassignment.utils.networkUtils;

import com.hive.hivetestassignment.AppRoot;
import com.hive.hivetestassignment.userInterface.RestaurantTopDataModel;

import retrofit2.Call;

import static com.hive.hivetestassignment.utils.Constants.API_KEY;

/**
 * API repository to handle the implementation of
 * the apis and passing the relevant data to them.
 *
 * @author Labhya Sharma
 */
public class APIRepository {

    private static APIRepository mApiRepository;
    private static APIInterface mApiInterface;

    /**
     * Method to get instance of this class
     */
    public static APIRepository getRepositoryInstance() {
        if (mApiRepository == null) {
            mApiRepository = new APIRepository();
        }
        if (mApiInterface == null) {
            mApiInterface = RetrofitClientInstance.getRetrofitInstance(AppRoot.getInstance()).create(APIInterface.class);
        }
        return mApiRepository;
    }

    private APIRepository() {
    }

    public Call<RestaurantTopDataModel> getRestaurantList(String searchKey) {
        return mApiInterface.getRestaurantList(API_KEY, searchKey);
    }
}
