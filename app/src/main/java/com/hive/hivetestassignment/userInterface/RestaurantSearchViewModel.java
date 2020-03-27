package com.hive.hivetestassignment.userInterface;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hive.hivetestassignment.utils.BaseAdapter;
import com.hive.hivetestassignment.utils.BaseViewModel;
import com.hive.hivetestassignment.utils.Constants;
import com.hive.hivetestassignment.utils.LinearLayoutManagerWrapperUIUtil;
import com.hive.hivetestassignment.utils.networkUtils.APIRepository;
import com.hive.hivetestassignment.utils.networkUtils.APIUtil;
import com.hive.hivetestassignment.utils.networkUtils.ApiObserverDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Viewmodel class to handle the business logic
 * of Restaurant list.
 *
 * @author Labhya Sharma
 */
public class RestaurantSearchViewModel extends BaseViewModel {

    private RestaurantListAdapter adapter;
    private List<RestaurantDataModel> restaurantListData = new ArrayList<>();
    private MutableLiveData<List<RestaurantDataModel>> restaurantList;
    private final MutableLiveData<String> searchText=new MutableLiveData<>();

    private Callback<RestaurantTopDataModel> apiGetRestaurantListCallBack;
    private final MutableLiveData<ApiObserverDataModel> clientListFetchSuccess = new MutableLiveData<>();

    public void init( BaseAdapter.Listener listener) {
        adapter = new RestaurantListAdapter( this,restaurantListData,listener);
    }

    public RestaurantSearchViewModel() {
        apiGetRestaurantListCallBack = new Callback<RestaurantTopDataModel>() {
            @Override
            @SuppressWarnings("unchecked")
            public void onResponse(@NonNull Call<RestaurantTopDataModel> call, @NonNull Response<RestaurantTopDataModel> response) {
                if (response.isSuccessful()) {
                    clientListFetchSuccess.setValue(new ApiObserverDataModel(response.body(), ""));
                } else {
                    String errorMessage = APIUtil.parseError(response);
                    setResponseError(errorMessage);
                }
            }

            @Override
            public void onFailure(@NonNull Call<RestaurantTopDataModel> call, @NonNull Throwable t) {
                handleFailure(t);
                setResponseError(Constants.SERVER_ERROR);
            }
        };

    }

    /**
     * This function is used to hit the Api.
     */
    public void fetchRestaurantList(String searchKey) {
        APIRepository.getRepositoryInstance().getRestaurantList(searchKey).enqueue(apiGetRestaurantListCallBack);
    }

    public MutableLiveData<List<RestaurantDataModel>> getRestaurantListData(){
        if(restaurantList==null){
            restaurantList=new MutableLiveData<>();
        }
        return restaurantList;
    }

    public RestaurantListAdapter getAdapter() {
        return adapter;
    }

    /**
     * This function is set the data to adapter after the
     * sorting. Also, iterating the map and creating the
     * header based on their key.
     */
    public void setAdapter(List<RestaurantDataModel> restaurantList, boolean isAsc){
        List<RestaurantDataModel> newRestaurantList = new ArrayList<>();

        for (Map.Entry<String, List<RestaurantDataModel>> entry : filterList(restaurantList).entrySet() ) {
            newRestaurantList.add(new RestaurantDataModel(new RestaurantDetailDataModel(true),entry.getKey()));

            if (isAsc) Collections.sort(entry.getValue()); else Collections.sort(entry.getValue(),Collections.reverseOrder());
            newRestaurantList.addAll(entry.getValue());
        }

        this.adapter.setRestaurantData(newRestaurantList);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<String> getSearchText() {
        return searchText;
    }

    public MutableLiveData<ApiObserverDataModel> getClientListFetchSuccess() {
        return clientListFetchSuccess;
    }

    /**
     * This method is used fetch data from server
     */
    public void setDataInViewModel(List<RestaurantDataModel> restaurantData) {
        if (restaurantList == null) {
            restaurantList = new MutableLiveData<>();
        }
        restaurantList.setValue(restaurantData);
        restaurantListData = restaurantData;
    }

    /**
     * This method is used to filter out the cuisines
     * and based on that using a Hashmap to store the
     * related restaurant objects based on cuisines as key.
     *
     * @param restaurantList list of restaurant
     */
    private HashMap<String, List<RestaurantDataModel>> filterList(List<RestaurantDataModel> restaurantList) {

        HashMap<String, List<RestaurantDataModel>> filteredMap = new HashMap<>();
        for (RestaurantDataModel restaurantDataModel : restaurantList) {
            String cuisine = restaurantDataModel.getRestaurant().getCuisines();
            if (cuisine.contains(",") && cuisine.split(",")[0] != null) {
                cuisine = cuisine.split(",")[0];
            }
            if (!filteredMap.containsKey(cuisine)) {
                List<RestaurantDataModel> filteredRestaurantList = new ArrayList<>();
                filteredRestaurantList.add(restaurantDataModel);
                filteredMap.put(cuisine,filteredRestaurantList);
            } else {
                List<RestaurantDataModel> filteredRestaurantList = filteredMap.get(cuisine);
                filteredRestaurantList.add(restaurantDataModel);
            }
        }
        return filteredMap;
    }
}
