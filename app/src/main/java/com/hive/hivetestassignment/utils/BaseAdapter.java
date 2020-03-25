package com.hive.hivetestassignment.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * This class is base class of all Base Adapter for recycler view in the application
 * Work as a pipeline as between java and xml file by providing direct click
 *
 * @author Labhya Sharma
 */
public abstract class BaseAdapter<j> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements BaseDiffCallback.Listener<j>{

    protected List<j> list;

    public interface Listener<J> {
        @SuppressWarnings("all")
        void onItemClick(View view, J item, int clickType);
    }

    protected final Listener listener;

    private final BaseViewModel baseViewModel;

    protected BaseAdapter(BaseViewModel baseViewModel,List<j> list, Listener listener) {
        this.baseViewModel = baseViewModel;
        this.listener = listener;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((GenericViewHolder) holder).bind(baseViewModel,position);
    }

    public void swapItems(List<j> actors) {
        final DiffUtil.Callback diffCallback = getCallback(actors);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.list.clear();

        this.list.addAll(actors);
        diffResult.dispatchUpdatesTo(this);
    }

    @SuppressWarnings("unchecked")
    private DiffUtil.Callback getCallback(List actors) {
        return new BaseDiffCallback<j>(this.list, actors, this);
    }

    /**
     * Generic View holder for base adapter
     */
    public static abstract class GenericViewHolder extends RecyclerView.ViewHolder {
        protected final Listener listener;

        @SuppressWarnings("unused")
        public GenericViewHolder(ViewDataBinding binding, Listener l) {
            super(binding.getRoot());
            this.listener = l;
        }

        @SuppressWarnings("unused")
        public abstract void bind(BaseViewModel baseViewModel,  Integer position);

    }

    @Override
    public boolean areItemAreSame(j oldItem, j newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(j oldItem, j newItem) {
        return false;
    }
}
