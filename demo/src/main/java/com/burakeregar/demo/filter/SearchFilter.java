package com.burakeregar.demo.filter;

import android.widget.Filter;

import com.burakeregar.demo.model.ContactModel;
import com.burakeregar.easiestgenericrecycleradapter.base.GenericRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFilter extends Filter {

    private GenericRecyclerAdapter adapter;

    public SearchFilter(GenericRecyclerAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {

        FilterResults results = new FilterResults();

        final List<Object> list = adapter.getOriginalItemList();

        final List<Object> filteredList = new ArrayList<>();

        String filterableString = "";

        for (Object lModel : list) {
            if (lModel instanceof ContactModel) {
                filterableString = ((ContactModel) lModel).getName();
            }
            if (filterableString.contains(constraint.toString())) {
                filteredList.add(lModel);
            }
        }

        results.values = filteredList;
        results.count = filteredList.size();

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setList((List<Object>) results.values);
    }
}