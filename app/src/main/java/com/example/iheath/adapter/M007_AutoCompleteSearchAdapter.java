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
import com.example.iheath.entities.RoomDatabase.RSS;

import java.util.ArrayList;
import java.util.List;

public class M007_AutoCompleteSearchAdapter extends ArrayAdapter<RSS> {
    private List<RSS> rssListFull;
    public M007_AutoCompleteSearchAdapter(@NonNull Context context, @NonNull List<RSS> rssList) {
        super(context, 0, rssList);
        rssListFull = new ArrayList<>(rssList);
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return rssFilter;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.m007_1_item_search,parent, false
            );
        }
        TextView textViewName = convertView.findViewById(R.id.text_view_name);
        RSS rss= getItem(position);
        if (rss != null) {
            textViewName.setText(rss.getTitle());
        }
        return convertView;
    }
    private Filter rssFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<RSS> suggestions = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(rssListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (RSS item : rssListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
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
            return ((RSS) resultValue).getTitle();
        }
    };
}