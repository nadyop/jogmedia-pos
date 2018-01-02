package com.blibli.dao_api;

import com.blibli.model.*;

import java.util.List;

public interface TransactionInterface {

    void saveTransaction(Transaction transaction);
    void updateTransaction(int transaction_id,double total);
    void saveDetailTransaction(Detil_Transaction detil_transaction);
    void deleteFromTempDetailByKeyword(int idDetil);
    void saveTempDetilTransaction(TempDetil tempDetil);
    void updateTempDetil(double tempUnitPrice, int qty, int id);
    void updatingStok(int id, int qty);
    void deleteTempDetil();
    void createTableTransaction();
    void createTableTemp();
    void createTableDetilTransaction();

    List<Book> searchCashier(String searchKey);
    List<TempDetil> getAllTempDetilSaved();
    List<Store> getDataStore();
    List<Detil_Transaction> getAllDetilTransactionByKeyword(int id);

    TempDetil getIdTempDetil(int idTemp);
    TempDetil getIdTempDetilbyNomorIdDetil(int idTemp);
    int getIdTransaction();
    int getIdEmployee(String searchKey);
}
