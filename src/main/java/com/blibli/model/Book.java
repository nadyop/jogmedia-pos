package com.blibli.model;

public class Book {

    private int book_id, category_id, status, discount, stok;
    private String isbn, book_title, author, publisher, location;
    private double price_after, price_before;

    public Book() {
    }
    public Book(int book_id, int category_id, String isbn, String book_title, String author, String publisher, String location,int discount, double price_before, double price_after, int stok, int status) {
        this.book_id = book_id;
        this.category_id = category_id;
        this.isbn = isbn;
        this.book_title = book_title;
        this.status = status;
        this.author = author;
        this.publisher = publisher;
        this.location = location;
        this.price_after = price_after;
        this.price_before = price_before;
        this.discount = discount;
        this.setStok(stok);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice_after() {
        return price_after;
    }

    public void setPrice_after(double price_after) {
        this.price_after = price_after;
    }

    public double getPrice_before() {
        return price_before;
    }

    public void setPrice_before(double price_before) {
        this.price_before = price_before;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}