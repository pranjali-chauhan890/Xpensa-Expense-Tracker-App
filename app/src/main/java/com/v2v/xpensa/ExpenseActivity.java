package com.v2v.xpensa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.v2v.xpensa.adapters.TransactionAdapter;
import com.v2v.xpensa.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ExpenseActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransactionAdapter adapter;
    private List<Transaction> expenseList;
    private TextView totalExpenseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        recyclerView = findViewById(R.id.expenseRecyclerView);
        totalExpenseText = findViewById(R.id.textTotalExpense);
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Toast.makeText(this, "Back to the DashBoard", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ExpenseActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        expenseList = new ArrayList<>();

        // Dummy data — replace with database fetch
        expenseList.add(new Transaction("Expense", "Groceries", "2025-07-17", 1200));
        expenseList.add(new Transaction("Expense", "Transport", "2025-07-16", 300));
        expenseList.add(new Transaction("Expense", "Dining Out", "2025-07-15", 800));

        adapter = new TransactionAdapter(expenseList);
        recyclerView.setAdapter(adapter);

        updateTotalExpense();
    }

    private void updateTotalExpense() {
        int total = 0;
        for (Transaction transaction : expenseList) {
            total += transaction.getAmount();
        }
        totalExpenseText.setText("Total Spent: ₹" + total);
    }
}