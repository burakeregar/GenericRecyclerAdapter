package com.burakeregar.easiestgenericrecycleradapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class GenericRecyclerAdapter extends RecyclerView.Adapter<GenericViewHolder> {

    private List itemList = new ArrayList();
    private List originalItemList = new ArrayList();
    private List<GenericAdapterModel> modelList = new ArrayList<>();
    private Filter filter;
    private boolean isFilterEnabled;

    public GenericRecyclerAdapter(ArrayList<GenericAdapterModel> pModels) {
        modelList = pModels;
    }

    public GenericRecyclerAdapter(ArrayList<GenericAdapterModel> pModels, boolean pIsFilterEnabled) {
        modelList = pModels;
        isFilterEnabled = pIsFilterEnabled;
    }

    public GenericRecyclerAdapter(int pLayout, Class pViewHolder, Class pItemType) {
        modelList.add(new GenericAdapterModel(pLayout, pViewHolder, pItemType));
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        GenericViewHolder holder = null;
        if (viewType != -1) {
            GenericAdapterModel lModel = modelList.get(viewType);
            View lView = inflater.inflate(lModel.getLayout(), parent, false);
            try {
                Class mClass = lModel.getViewHolder();
                holder = (GenericViewHolder) mClass.getConstructor(View.class).newInstance(lView);
            } catch (Exception pE) {
                throw new RuntimeException(pE.getMessage());
            }
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.bindData(itemList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        Class<?> lItemClass = itemList.get(position).getClass();
        for (int i = 0; i < modelList.size(); i++) {
            if (modelList.get(i).getItemType().equals(lItemClass)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setList(List list) {
        if (list == null) return;
        itemList.clear();
        itemList.addAll(list);
        handleFilter();
        notifyDataSetChanged();
    }

    public List getItems() {
        return itemList;
    }

    public void addNewRows(List list) {
        if (list == null) return;
        itemList.addAll(list);
        handleFilter();
        notifyDataSetChanged();
    }

    public void addNewRows(Object item) {
        if (item == null) return;
        itemList.add(item);
        handleFilter();
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter pFilter) {
        filter = pFilter;
    }

    private void handleFilter() {
        if (isFilterEnabled) {
            originalItemList = itemList;
        }
    }
}
