package com.v2v.xpensa;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class StockActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StockAdapter stockAdapter;
    private ArrayList<StockModel> stockList;
    private ImageView backButton;
    private TextView heading;
    private static final String API_KEY = "BaGp9EoaZM41vi8xH0m2ZZMjofNCKqiZ";
    private final String apiUrl = "https://financialmodelingprep.com/api/v3/quote/AAPL,GOOGL,MSFT?apikey=" + API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
            try {
                setContentView(R.layout.activity_stock);
                Log.d("StockActivity", "UI loaded");
            } catch (Exception e) {
                Log.e("CrashDebug", "Error loading layout: " + e.getMessage());
                Toast.makeText(this, "Crash: " + e.getMessage(), Toast.LENGTH_LONG).show();
                return;
            }

        recyclerView = findViewById(R.id.recentStockRecyclerView);
        backButton = findViewById(R.id.backButton);
        heading = findViewById(R.id.tvExpenseTracker);
        SearchView searchView = findViewById(R.id.searchView);

        heading.setText("Stock Data");
        backButton.setOnClickListener(view -> finish());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stockList = new ArrayList<>();
        stockAdapter = new StockAdapter(this, stockList);
        recyclerView.setAdapter(stockAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (stockAdapter != null) {
                    stockAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });

        fetchStockData();
    }

    private void fetchStockData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, apiUrl, null,
                response -> {
                    try {
                        stockList.clear(); // Clear previous data
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject stock = response.getJSONObject(i);
                                String symbol = stock.optString("symbol", "N/A");
                                String name = stock.optString("name", "N/A");
                                String price = String.valueOf(stock.optDouble("price", 0.0));

                                double priceChange = 0;
                                stockList.add(new StockModel(symbol, name, price,priceChange));
                            } catch (Exception e) {
                                Log.e("StockActivity", "Error parsing item at index " + i, e);
                            }
                        }
                        stockAdapter.notifyDataSetChanged();
                    } catch (Exception e) {
                        Log.e("StockActivity", "Error parsing response", e);
                        Toast.makeText(this, "Parsing error", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Log.e("StockActivity", "Network error", error);
                    Toast.makeText(this, "Failed to fetch stock data", Toast.LENGTH_SHORT).show();
                });

        queue.add(jsonArrayRequest);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(StockActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
        return true;
    }
}
