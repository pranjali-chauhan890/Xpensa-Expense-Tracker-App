package com.v2v.xpensa.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "transactions")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "amount")
    private int amount;

    // Constructor
    public Transaction(String type, String description, String date, int amount) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.amount = amount;
    }

    // Getter and Setter for id
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Other Getters
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public int getAmount() { return amount; }

    // Setters (optional if you want immutability)
    public void setType(String type) { this.type = type; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setAmount(int amount) { this.amount = amount; }
}
