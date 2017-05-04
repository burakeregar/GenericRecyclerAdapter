package com.burakeregar.easiestgenericrecycleradapter.base;

public class GenericAdapterModel {
   private int   mLayout;
   private Class mViewHolder;
   private Class mItemType;

   public GenericAdapterModel(int pLayout, Class pViewHolder, Class pItemType) {
      mLayout = pLayout;
      mViewHolder = pViewHolder;
      mItemType = pItemType;
   }

   public Class getItemType() {
      return mItemType;
   }

   public void setItemType(Class pItemType) {
      mItemType = pItemType;
   }

   public int getLayout() {
      return mLayout;
   }

   public void setLayout(int pLayout) {
      mLayout = pLayout;
   }

   public Class getViewHolder() {
      return mViewHolder;
   }

   public void setViewHolder(Class pViewHolder) {
      mViewHolder = pViewHolder;
   }

    
}
