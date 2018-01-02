package com.blibli.dao_api;

import com.blibli.model.Store;

import java.util.List;

public interface StoreDaoInterface {

    void insertStore (Store s);
    Store getIdStore(int idStore);
    List<Store> getStore();
}