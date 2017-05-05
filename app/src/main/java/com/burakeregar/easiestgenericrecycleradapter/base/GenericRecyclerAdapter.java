package com.burakeregar.easiestgenericrecycleradapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class GenericRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
   public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      RecyclerView.ViewHolder holder   = null;
      if (viewType != -1) {
         GenericAdapterModel lModel = mModels.get(viewType);
         View lView  = inflater.inflate(lModel.getLayout(), parent, false);
         try {
            Class mClass = lModel.getViewHolder();
            holder = (GenericViewHolder) mClass.getConstructor(View.class).newInstance(lView);
         } catch (Exception pE) {
            pE.printStackTrace();
         }
      }
      return holder;
   }

   @Override
   public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      ((GenericViewHolder) holder).bindData(mObjectList.get(position));
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
      if (this.mObjectList == null) {
         this.mObjectList = pObjectList;
      } else {
         mObjectList.clear();
         mObjectList.addAll(pObjectList);
      }
      if (mIsFilterEnabled) {
         mOriginalObjectList = pObjectList;
      }

      notifyDataSetChanged();
   }

   public List getItems() {
      return mObjectList;
   }

   public void addNewRows(List pObjectList) {
      if (this.mObjectList == null) {
         this.mObjectList = pObjectList;
      } else {
         mObjectList.addAll(pObjectList);
      }

      if (mIsFilterEnabled) {
         mOriginalObjectList = mObjectList;
      }

      notifyDataSetChanged();
   }

   public void addNewRows(Object pObject) {
      if (this.mObjectList == null) {
         this.mObjectList = new ArrayList();
      } else {
         mObjectList.add(pObject);
      }

      if (mIsFilterEnabled) {
         mOriginalObjectList = mObjectList;
      }

      notifyDataSetChanged();
   }
   public Filter getFilter() {
      return mFilter;
   }

   public void setFilter(Filter pFilter) {
      mFilter = pFilter;
   }
}
