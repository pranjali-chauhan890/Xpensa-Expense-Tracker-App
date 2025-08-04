package com.v2v.xpensa;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.StockViewHolder> implements Filterable {

    private final Context context;
    private List<StockModel> stockList;
    private final List<StockModel> stockListFull; // For filtering

    public StockAdapter(Context context, List<StockModel> stockList) {
        this.context = context;
        this.stockList = stockList;
        this.stockListFull = new ArrayList<>(stockList); // Copy for filtering
    }

    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_stock, parent, false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
        StockModel stock = stockList.get(position);

        holder.symbolTextView.setText(stock.getSymbol());
        holder.nameTextView.setText(stock.getName());

        String price = stock.getPrice();
        double change = stock.getPriceChange();

        String arrow = change > 0 ? "▲" : change < 0 ? "▼" : "-";
        int color = change > 0 ? Color.parseColor("#1B5E20") : // Green
                change < 0 ? Color.parseColor("#D32F2F") :     // Red
                        Color.parseColor("#757575");           // Neutral

        String priceText = String.format("₹ %.2f %s", price, arrow);
        holder.priceTextView.setText(priceText);
        holder.priceTextView.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public static class StockViewHolder extends RecyclerView.ViewHolder {
        TextView symbolTextView, nameTextView, priceTextView;

        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            symbolTextView = itemView.findViewById(R.id.textSymbol);
            nameTextView = itemView.findViewById(R.id.textName);
            priceTextView = itemView.findViewById(R.id.textPrice);
        }
    }

    // 🔍 Search Filter
    @Override
    public Filter getFilter() {
        return stockFilter;
    }

    private final Filter stockFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<StockModel> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(stockListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (StockModel item : stockListFull) {
                    if (item.getSymbol().toLowerCase().contains(filterPattern)
                            || item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            stockList.clear();
            stockList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}