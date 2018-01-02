package com.blibli.model;

public class TempDetil {

    private int bookId;
    private double unitPrice, subTotal;
    private int quantity, discount, id_detil;
    private String book_title, isbn;
    public TempDetil(int id_detil,int bookId, int quantity,double unitPrice,  int discount, String book_title, String isbn, double subTotal) {
        this.id_detil=id_detil;
        this.bookId = bookId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.book_title=book_title;
        this.isbn=isbn;
        this.subTotal=subTotal;
    }

        public TempDetil() {
        }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getId_detil() {
        return id_detil;
    }

    public void setId_detil(int id_detil) {
        this.id_detil = id_detil;
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
