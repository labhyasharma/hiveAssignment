package com.hive.hivetestassignment.userInterface;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.hive.hivetestassignment.BR;
import com.hive.hivetestassignment.R;
import com.hive.hivetestassignment.databinding.RestaurantItemBinding;
import com.hive.hivetestassignment.utils.BaseAdapter;
import com.hive.hivetestassignment.utils.BaseViewModel;

import java.util.List;

/**
 * This is the Adapter class to handle the
 * list of Restaurants and display them.
 *
 * @author Labhya Sharma
 */
public class RestaurantListAdapter extends BaseAdapter<RestaurantDataModel> {

    private List<RestaurantDataModel> restaurantData;
    private final RestaurantSearchViewModel viewModel;

    public RestaurantListAdapter(RestaurantSearchViewModel viewModel, List<RestaurantDataModel> restaurantData, BaseAdapter.Listener listener) {
        super(viewModel, restaurantData, listener);
        this.viewModel = viewModel;
        this.restaurantData = restaurantData;
    }
    @Override
    public int getItemCount() {
        return restaurantData == null ? 0 : restaurantData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.restaurant_item;
    }

    @NonNull
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RestaurantItemBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new RestaurantViewHolder(binding, listener);
    }

    void setRestaurantData(List<RestaurantDataModel> restaurantData) {
        this.restaurantData = restaurantData;
    }

    class RestaurantViewHolder extends GenericViewHolder {
        final RestaurantItemBinding binding;

        RestaurantViewHolder(RestaurantItemBinding binding, Listener l) {
            super(binding, l);
            this.binding = binding;
        }

        @SuppressWarnings("unused")
        @Override
        public void bind(BaseViewModel baseViewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.executePendingBindings();
            binding.setListener(listener);
            binding.setModel(restaurantData.get(position));
        }
    }
}
