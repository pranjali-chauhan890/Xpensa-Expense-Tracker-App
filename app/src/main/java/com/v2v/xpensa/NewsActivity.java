package com.v2v.xpensa;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    LinearLayout newsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsContainer = findViewById(R.id.newsContainer);
        ImageView backArrow = findViewById(R.id.newsbackButton);

        backArrow.setOnClickListener(v -> finish());

        // Dummy news data
        List<NewsItem> newsList = Arrays.asList(
                new NewsItem(R.drawable.ic_news1, "Tech stocks soar", "Technology stocks are on the rise again."),
                new NewsItem(R.drawable.ic_news2, "AI Advancements", "AI is rapidly evolving, transforming industries."),
                new NewsItem(R.drawable.ic_news3, "Markets Today", "Global markets showed mixed signals today."),
                new NewsItem(R.drawable.ic_news4, "Crypto Update", "Bitcoin and Ethereum gain 4% overnight.")
        );

        for (NewsItem item : newsList) {
            addNewsItem(item);
        }
    }

    private void addNewsItem(NewsItem item) {
        View view = LayoutInflater.from(this).inflate(R.layout.news_item_layout, newsContainer, false);

        ImageView imageView = view.findViewById(R.id.newsImage);
        TextView titleView = view.findViewById(R.id.newsTitle);
        TextView descView = view.findViewById(R.id.newsDesc);

        imageView.setImageResource(item.imageRes);
        titleView.setText(item.title);
        descView.setText(item.description);

        newsContainer.addView(view);

        // Add divider
        View divider = new View(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 1);
        params.setMargins(0, 16, 0, 16);
        divider.setLayoutParams(params);
        divider.setBackgroundColor(Color.LTGRAY);

        newsContainer.addView(divider);
    }

    static class NewsItem {
        int imageRes;
        String title, description;

        NewsItem(int imageRes, String title, String description) {
            this.imageRes = imageRes;
            this.title = title;
            this.description = description;
        }
    }
}