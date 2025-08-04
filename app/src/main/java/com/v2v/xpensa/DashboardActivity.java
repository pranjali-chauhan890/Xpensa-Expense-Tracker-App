package com.v2v.xpensa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    TextView welcomeText, emailText;
    ImageView stockDataIcon, expenseIcon, newsIcon, chatbotIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        welcomeText = findViewById(R.id.welcomeText);
        emailText = findViewById(R.id.emailText);

        stockDataIcon = findViewById(R.id.stockDataIcon);
        expenseIcon = findViewById(R.id.expenseIcon);
        newsIcon = findViewById(R.id.newsIcon);
        chatbotIcon = findViewById(R.id.chatbotIcon);

        // Get username and email from intent
        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");

        welcomeText.setText("Welcome, " + username);
        emailText.setText(email);

        // Navigate to StockDataActivity
        stockDataIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Stock Data Tab Opened", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DashboardActivity.this, StockActivity.class);
            startActivity(intent);
        });

        // Navigate to ExpenseActivity
        expenseIcon.setOnClickListener(v -> {
            Toast.makeText(this, "Expense tab Opened", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DashboardActivity.this, ExpenseActivity.class);
            startActivity(intent);
        });

        // Navigate to NewsActivity
        newsIcon.setOnClickListener(v -> {
            Toast.makeText(this, "News tab Opened", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DashboardActivity.this, NewsActivity.class);
            startActivity(intent);
        });

        // Navigate to ChatBotActivity
        chatbotIcon.setOnClickListener(v -> {
            Toast.makeText(this, "ChatBot tab Opened", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DashboardActivity.this, ChatActivity.class);
            startActivity(intent);
        });
    }
}
