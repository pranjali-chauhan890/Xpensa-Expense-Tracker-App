package com.v2v.xpensa;

import com.v2v.xpensa.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionDao {

    private static final List<Transaction> transactionList = new ArrayList<>();

    public static void addTransaction(Transaction transaction) {
        transactionList.add(0, transaction); // Add at top for recent first
    }

    public static List<Transaction> getAllTransactions() {
        return transactionList;
    }

    public static void clearTransactions() {
        transactionList.clear();
    }
}
