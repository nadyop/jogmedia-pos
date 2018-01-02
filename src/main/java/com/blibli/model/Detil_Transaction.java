package com.blibli.model;

public class Detil_Transaction {
    private int detil_id, quantity;
    private int discountDetil;
    private int transaction_id;
    private int book_id;
    private String book_title;
    private String isbn;
    private double unit_price;

    public Detil_Transaction(String isbn, String book_title,int transaction_id,int book_id,int detil_id, int quantity, int discountDetil, double unit_price) {
        this.detil_id = detil_id;
        this.quantity = quantity;
        this.discountDetil = discountDetil;
        this.unit_price = unit_price;
        this.transaction_id=transaction_id;
        this.book_id=book_id;
        this.isbn=isbn;
        this.book_title=book_title;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getDetil_id() {
        return detil_id;
    }

    public void setDetil_id(int detil_id) {
        this.detil_id = detil_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscountDetil() {
        return discountDetil;
    }

    public void setDiscountDetil(int discountDetil) {
        this.discountDetil = discountDetil;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
