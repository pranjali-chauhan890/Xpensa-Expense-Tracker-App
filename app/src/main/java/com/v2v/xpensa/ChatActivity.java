package com.v2v.xpensa;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.v2v.xpensa.models.ChatMessage;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private EditText inputMessage;
    private ImageButton sendButton;
    private ChatAdapter chatAdapter;
    private ArrayList<ChatMessage> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        try {
            setContentView(R.layout.activity_chatbot);
            Log.d("StockActivity", "UI loaded");
        } catch (Exception e) {
            Log.e("CrashDebug", "Error loading layout: " + e.getMessage());
            Toast.makeText(this, "Crash: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return;
        }


            // ✅ Safe check for ActionBar (prevent crash)
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("Chatbot");
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }

            // Optional: use custom title instead (TextView)
            TextView heading = findViewById(R.id.tvChatbot); // or whatever your TextView ID is
            if (heading != null) {
                heading.setText("Chatbot");
            }


        chatRecyclerView = findViewById(R.id.chatRecyclerView);
        inputMessage = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.btnSend);

        chatList = new ArrayList<>();
        chatList.add(new ChatMessage("Hi, how can I help you?", ChatMessage.SENDER_BOT));
        chatList.add(new ChatMessage("What's the stock price of Apple?", ChatMessage.SENDER_USER));

        chatAdapter = new ChatAdapter(this, chatList);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMsg = inputMessage.getText().toString().trim();
                if (!userMsg.isEmpty()) {
                    chatList.add(new ChatMessage(userMsg, ChatMessage.SENDER_USER));
                    chatAdapter.notifyItemInserted(chatList.size() - 1);
                    inputMessage.setText("");
                    respondToUser(userMsg);
                }
            }
        });
    }

    private void respondToUser(String msg) {
        String reply;
        msg = msg.toLowerCase();

        if (msg.contains("hi") || msg.contains("hello")) {
            reply = "Hello! How can I assist you?";
        } else if (msg.contains("help")) {
            reply = "Sure, tell me what help you need.";
        } else if (msg.contains("bye")) {
            reply = "Goodbye! Have a nice day!";
        } else {
            reply = "I'm still learning. Can you ask something else?";
        }
    }}
