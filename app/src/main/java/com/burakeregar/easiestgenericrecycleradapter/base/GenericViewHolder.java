package com.burakeregar.easiestgenericrecycleradapter.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class GenericViewHolder<M> extends RecyclerView.ViewHolder {
   public GenericViewHolder(View itemView) {
      super(itemView);
   }

   public abstract void bindData(M element);
}
