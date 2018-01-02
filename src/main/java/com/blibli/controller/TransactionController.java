package com.blibli.controller;


import com.blibli.services.StoreService;
import com.blibli.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransactionController {
    private final TransactionService transactionService;
    private final StoreService storeService;

    @Autowired
    public TransactionController(TransactionService transactionService,StoreService storeService) {
        this.transactionService = transactionService;
        this.storeService = storeService;
    }

    @RequestMapping("/transaction")
    public String transaction(Model model ){
        model=transactionService.managementTransaction(model);
        return "cashier/transaction";
    }

    @RequestMapping(value = "/transaction/search", method = RequestMethod.GET)
    public String search(Model model, @ModelAttribute("searchKey") String searchKey){
        model = transactionService.searchTransactionByKeyword(model,searchKey);
        return "cashier/transaction";
    }

    @RequestMapping(value="/transaction/search/buy/{id}" , method = RequestMethod.POST )
    public String search1(@PathVariable("id") int id, @ModelAttribute("quantity") int quantity, Model model){
        model= transactionService.moveBookToCart(model,id,quantity);
        return "redirect:/transaction";
    }

    @RequestMapping(value = "/transaction/hapus/{id}",method = RequestMethod.GET)
    public String hapusDataCategory(@PathVariable("id") int id){
        transactionService.deleteBookFromCart(id);
        return "redirect:/transaction";
    }

    @RequestMapping(value = "/nota", method = RequestMethod.GET)
    public String manageTransaction(Model model, Authentication authentication){

        model = transactionService.manageTransactionPayment(model, authentication);
        return "cashier/nota";
    }
}
