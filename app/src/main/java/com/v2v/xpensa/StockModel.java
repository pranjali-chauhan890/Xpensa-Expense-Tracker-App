package com.v2v.xpensa;

public class StockModel {
    private String symbol;
    private String name;
    private String price;
    private double priceChange;

    public String getPrice() {
        return price;
    }
    public String getFormattedPrice() {
        return "₹ " + String.format("%.2f", price);
    }

    public double getPriceChange() {
        return priceChange;
    }

    public StockModel(String symbol, String name, String price, double priceChange) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
        this.priceChange = priceChange;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
