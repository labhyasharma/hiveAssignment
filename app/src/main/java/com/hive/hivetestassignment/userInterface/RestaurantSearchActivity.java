package com.hive.hivetestassignment.userInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.hive.hivetestassignment.R;
import com.hive.hivetestassignment.databinding.ActivityMainBinding;
import com.hive.hivetestassignment.utils.BaseAdapter;
import com.hive.hivetestassignment.utils.LinearLayoutManagerWrapperUIUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity to handle the list of restaurants
 *
 * @author Labhya Sharma
 */
public class RestaurantSearchActivity extends AppCompatActivity implements BaseAdapter.Listener<RestaurantDataModel> {

    private RestaurantSearchViewModel mRestaurantSearchViewModel;
    private ActivityMainBinding mActivityMainBinding;
    private boolean isAsc = true;
    private MutableLiveData<List<RestaurantDataModel>> clientDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mActivityMainBinding.setLifecycleOwner(this);
        mRestaurantSearchViewModel = ViewModelProviders.of(this).get(RestaurantSearchViewModel.class);

        mActivityMainBinding.setViewModel(mRestaurantSearchViewModel);
        mRestaurantSearchViewModel.init(this);

        mActivityMainBinding.mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivityMainBinding.mRecyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
        mActivityMainBinding.mRecyclerView.setAdapter(mRestaurantSearchViewModel.getAdapter());

        clientDataList = mRestaurantSearchViewModel.getRestaurantListData();
        clientDataList.observe(this, clientList -> mRestaurantSearchViewModel.setAdapter(clientList, isAsc));

        mRestaurantSearchViewModel.getClientListFetchSuccess().observe(this, apiObserverDataModel -> {
            hideLoader();
            RestaurantTopDataModel restaurantTopDataModel = (RestaurantTopDataModel) apiObserverDataModel.getResponseDataModel();
            if (restaurantTopDataModel != null) {
                @SuppressWarnings("unchecked") List<RestaurantDataModel> clientData = restaurantTopDataModel.getRestaurants();
                mRestaurantSearchViewModel.setDataInViewModel(clientData);
            }
        });

        mRestaurantSearchViewModel.getResponseError().observe(this, error -> {
            hideLoader();
            Toast toast = Toast.makeText(this, error, Toast.LENGTH_LONG);
            toast.show();
        });

        mActivityMainBinding.etsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                showLoader();
                afterEmailTextChanged(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mActivityMainBinding.containerSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clientDataList != null) {
                    if (isAsc) {
                        mRestaurantSearchViewModel.setAdapter(clientDataList.getValue(), false);
                        mActivityMainBinding.ivDownArrow.setRotation(180);
                    } else {
                        mRestaurantSearchViewModel.setAdapter(clientDataList.getValue(), true);
                        mActivityMainBinding.ivDownArrow.setRotation(0);
                    }
                }
                isAsc = !isAsc;
            }
        });

        mRestaurantSearchViewModel.fetchRestaurantList("");
        showLoader();
    }

    @Override
    public void onItemClick(View view, RestaurantDataModel item, int clickType) {
        //TODO Handle the click.
    }

    private void showLoader() {
        mActivityMainBinding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        mActivityMainBinding.progressBar.setVisibility(View.GONE);
    }

    public void afterEmailTextChanged(CharSequence s) {
        mRestaurantSearchViewModel.fetchRestaurantList(s.toString());
    }
}
