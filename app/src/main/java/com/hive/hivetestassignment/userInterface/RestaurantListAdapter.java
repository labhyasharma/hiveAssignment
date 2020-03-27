package com.hive.hivetestassignment.userInterface;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.hive.hivetestassignment.BR;
import com.hive.hivetestassignment.R;
import com.hive.hivetestassignment.databinding.HeaderLayoutItemBinding;
import com.hive.hivetestassignment.databinding.RestaurantItemBinding;
import com.hive.hivetestassignment.utils.BaseAdapter;
import com.hive.hivetestassignment.utils.BaseViewModel;
import com.hive.hivetestassignment.utils.Constants;

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
        if (restaurantData.get(position).getRestaurant().isHeader()) {
            return Constants.HEADER;
        } else {
            return Constants.LAYOUT;
        }
    }

    @NonNull
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == Constants.LAYOUT) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            RestaurantItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.restaurant_item, parent, false);

            return new RestaurantViewHolder(binding, listener);
        } else {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            HeaderLayoutItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.header_layout_item, parent, false);

            return new HeaderViewHolder(binding, listener);
        }
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

    class HeaderViewHolder extends GenericViewHolder {
        final HeaderLayoutItemBinding binding;

        HeaderViewHolder(HeaderLayoutItemBinding binding, Listener l) {
            super(binding, l);
            this.binding = binding;
        }

        @SuppressWarnings("unused")
        @Override
        public void bind(BaseViewModel baseViewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.executePendingBindings();
            binding.setModel(restaurantData.get(position));
        }
    }
}
