package com.burakeregar.easiestgenericrecycleradapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class GenericRecyclerAdapter extends RecyclerView.Adapter<GenericViewHolder> {

    public List mObjectList;
    public List mOriginalObjectList;
    private ArrayList<GenericAdapterModel> mModels;
    private Filter mFilter;
    private boolean mIsFilterEnabled;

    public GenericRecyclerAdapter(ArrayList<GenericAdapterModel> pModels) {
        mModels = pModels;
    }

    public GenericRecyclerAdapter(ArrayList<GenericAdapterModel> pModels, boolean pIsFilterEnabled) {
        mModels = pModels;
        mIsFilterEnabled = pIsFilterEnabled;
    }

    public GenericRecyclerAdapter(int pLayout, Class pViewHolder, Class pItemType) {
        mModels = new ArrayList<>();
        mModels.add(new GenericAdapterModel(pLayout, pViewHolder, pItemType));
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        GenericViewHolder holder = null;
        if (viewType != -1) {
            GenericAdapterModel lModel = mModels.get(viewType);
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
        holder.bindData(mObjectList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        Class<?> lItemClass = mObjectList.get(position).getClass();
        for (int i = 0; i < mModels.size(); i++) {
            if (mModels.get(i).getItemType().equals(lItemClass)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return mObjectList != null ? mObjectList.size() : 0;
    }

    public void setList(List pObjectList) {
        if(pObjectList == null)
            return;
        createIfNull();
        this.mObjectList.clear();
        mObjectList.addAll(pObjectList);
        if (mIsFilterEnabled) {
            mOriginalObjectList = pObjectList;
        }
        notifyDataSetChanged();
    }

    public List getItems() {
        return mObjectList;
    }

    public void addNewRows(List pObjectList) {
        if(pObjectList == null)
            return;
        createIfNull();
        mObjectList.addAll(pObjectList);
        if (mIsFilterEnabled) {
            mOriginalObjectList = mObjectList;
        }
        notifyDataSetChanged();
    }

    public void addNewRows(Object pObject) {
        if(pObject == null)
            return;
        createIfNull();
        mObjectList.add(pObject);
        if (mIsFilterEnabled) {
            mOriginalObjectList = mObjectList;
        }
        notifyDataSetChanged();
    }

    private void createIfNull(){
        if (this.mObjectList == null) {
            this.mObjectList = new ArrayList();
        }
    }

    public Filter getFilter() {
        return mFilter;
    }

    public void setFilter(Filter pFilter) {
        mFilter = pFilter;
    }
}
