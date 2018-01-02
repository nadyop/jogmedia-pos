package com.blibli.model;

public class Store {
    private int store_id;
    private String store_name, address, npwp, post_code, email;
    public Store(){}
    public int getStore_id() {
        return store_id;
    }

    public Store(int store_id, String store_name, String address, String npwp, String post_code, String email) {
        this.store_id = store_id;
        this.store_name = store_name;
        this.address = address;
        this.npwp = npwp;
        this.post_code = post_code;
        this.email = email;
    }
    public Store(String store_name, String address, String npwp, String post_code, String email) {
        this.store_name = store_name;
        this.address = address;
        this.npwp = npwp;
        this.post_code = post_code;
        this.email = email;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}