package com.blibli.services;

import com.blibli.dao_api.BookInterface;
import com.blibli.dao_api.TransactionInterface;
import com.blibli.model.Book;
import com.blibli.model.Detil_Transaction;
import com.blibli.model.TempDetil;
import com.blibli.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionInterface transactionInterface;
    @Autowired
    BookInterface bookDaoInterface;

    public Model managementTransaction(Model model){
        model.addAttribute("tempDetil",transactionInterface.getAllTempDetilSaved());
        double temp=0;
        List<TempDetil> tempDetils = transactionInterface.getAllTempDetilSaved();
        for (TempDetil tempDetil : tempDetils)  {

            temp += (double) (tempDetil.getQuantity() * tempDetil.getUnitPrice());

        }
        model.addAttribute("total",temp);

        return  model;
    }
    public Model searchTransactionByKeyword(Model model, String searchKey){
        model.addAttribute("tempDetil", transactionInterface.getAllTempDetilSaved());
        model.addAttribute("book", transactionInterface.searchCashier(searchKey));
        double temp=0;
        List<TempDetil> tempDetils = transactionInterface.getAllTempDetilSaved();
        for (TempDetil tempDetil : tempDetils)  {
            temp += (double) (tempDetil.getQuantity() * tempDetil.getUnitPrice());

        }
        model.addAttribute("total",temp);
        return model;
    }
    public Model moveBookToCart(Model model, int id, int quantity){

        Book book = bookDaoInterface.getIdBook(id);
        int qty;
        TempDetil tempDetilTemp= transactionInterface.getIdTempDetil(id);
        if( quantity>0 && tempDetilTemp.getBookId()!=0 && book.getStok()>=quantity ) {
            qty= -1 * quantity;
            transactionInterface.updatingStok(book.getBook_id(),qty);
            quantity += tempDetilTemp.getQuantity();
            transactionInterface.updateTempDetil(tempDetilTemp.getUnitPrice(),quantity, tempDetilTemp.getId_detil());
        }
        else if( quantity>0 && tempDetilTemp.getBookId()==0 && book.getStok()>=quantity  ) {
            qty= -1 * quantity;
            transactionInterface.updatingStok(book.getBook_id(),qty);
            TempDetil tempDetil1= new TempDetil(id, book.getBook_id(),quantity,book.getPrice_after(), book.getDiscount(),book.getBook_title(),book.getIsbn(),1);
            transactionInterface.saveTempDetilTransaction(tempDetil1);
        }
        return model;
    }
    public void deleteBookFromCart(int id){
        TempDetil tempDetil1= transactionInterface.getIdTempDetilbyNomorIdDetil(id);
        transactionInterface.updatingStok(tempDetil1.getBookId(),tempDetil1.getQuantity());
        transactionInterface.deleteFromTempDetailByKeyword(id);
    }
    public Model manageTransactionPayment(Model model, Authentication authentication){

        double countTotal=0.0;
        int idEmp=0;
        int idTransNext = transactionInterface.getIdTransaction();
        idEmp = transactionInterface.getIdEmployee(authentication.getName());
        List<TempDetil> listTempDetil = transactionInterface.getAllTempDetilSaved();


        model.addAttribute("store", transactionInterface.getDataStore());

        Transaction transaction=new Transaction(idTransNext,idEmp, 0.0,  new Date());
        transactionInterface.saveTransaction(transaction);
        System.out.println("transaction id= "+transaction.getTransaction_id());
        for (TempDetil temp : listTempDetil){
            Detil_Transaction detil= new Detil_Transaction("-","-",transaction.getTransaction_id(),temp.getBookId(),0,temp.getQuantity(),temp.getDiscount(),temp.getUnitPrice());
            transactionInterface.saveDetailTransaction(detil);
            countTotal += ( temp.getUnitPrice() * temp.getQuantity() );
        }
        transactionInterface.updateTransaction(transaction.getTransaction_id(),countTotal);
        transactionInterface.deleteTempDetil();

        model.addAttribute("store",transactionInterface.getDataStore());
        model.addAttribute("detils",transactionInterface.getAllDetilTransactionByKeyword(transaction.getTransaction_id()));
        model.addAttribute("total",countTotal);
        return model;
    }
}
