package com.blibli.model;

public class ReportDetail {
    private String isbn, book_title;
    private double unit_price, subTotal;
    private int quantity;

    public ReportDetail(String isbn, String book_title, double unit_price, double subTotal, int quantity) {
        this.isbn = isbn;
        this.book_title = book_title;
        this.unit_price = unit_price;
        this.subTotal = subTotal;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
