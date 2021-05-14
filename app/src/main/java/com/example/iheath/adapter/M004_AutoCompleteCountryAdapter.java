package com.example.iheath.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.iheath.R;
import com.example.iheath.entities.ThucPham;

import java.util.ArrayList;
import java.util.List;

public class M004_AutoCompleteCountryAdapter extends ArrayAdapter<ThucPham> {
    private List<ThucPham> thucPhamListFull;
    public M004_AutoCompleteCountryAdapter(@NonNull Context context, @NonNull List<ThucPham> thucPhamList) {
        super(context, 0, thucPhamList);
        thucPhamListFull = new ArrayList<>(thucPhamList);
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return countryFilter;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.m004_thucpham_autocomplete_row, parent, false
            );
        }
        TextView textViewName = convertView.findViewById(R.id.text_view_name);
        ThucPham thucPham = getItem(position);
        if (thucPham != null) {
            textViewName.setText(thucPham.getName());
        }
        return convertView;
    }
    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<ThucPham> suggestions = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(thucPhamListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ThucPham item : thucPhamListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }
            results.values = suggestions;
            results.count = suggestions.size();
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((ThucPham) resultValue).getName();
        }
    };
}