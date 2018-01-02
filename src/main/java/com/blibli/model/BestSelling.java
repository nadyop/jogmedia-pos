package com.blibli.model;

public class BestSelling {
    private String isbn, book_title;
    private  int total_quantity;
    private double total_price;
    public BestSelling(){}

    public BestSelling(String isbn, String book_title, int total_quantity, double total_price) {
        this.isbn = isbn;
        this.book_title = book_title;
        this.total_price=total_price;
        this.total_quantity=total_quantity;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
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
}
