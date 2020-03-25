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
import java.util.List;

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

    public void setAdapter(List<RestaurantDataModel> restaurantList, boolean isAsc){
        if (isAsc) Collections.sort(restaurantList); else Collections.sort(restaurantList,Collections.reverseOrder());
        this.adapter.setRestaurantData(restaurantList);
        this.adapter.notifyDataSetChanged();
    }

    public MutableLiveData<String> getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText.setValue(searchText);
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
}
