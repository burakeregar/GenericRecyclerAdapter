package com.burakeregar.easiestgenericrecycleradapter.base;

import java.util.ArrayList;

public class GenericAdapterBuilder {
   private ArrayList<GenericAdapterModel> lModels          = new ArrayList<>();
   private boolean                        mIsFilterEnabled = false;

   public GenericAdapterBuilder() {}

   public GenericAdapterBuilder addModel(GenericAdapterModel model) {
      lModels.add(model);
      return this;
   }

   public GenericAdapterBuilder addModel(int pLayout, Class<?> pViewHolder, Class<?> pItemType) {
      lModels.add(new GenericAdapterModel(pLayout, pViewHolder, pItemType));
      return this;
   }

   public GenericRecyclerAdapter execute() {
      return mIsFilterEnabled ? new GenericRecyclerAdapter(lModels, true)
                              : new GenericRecyclerAdapter(lModels);
   }

   public GenericAdapterBuilder setFilterEnabled() {
      mIsFilterEnabled = true;
      return this;
   }
}
