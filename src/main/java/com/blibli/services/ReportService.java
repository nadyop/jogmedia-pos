package com.blibli.services;


import com.blibli.dao_api.ReportInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Date;


@Service
public class ReportService {
    @Autowired
    ReportInterface reportInterface;

    @Autowired
    TransactionService transactionService;

    public Model showAllDetail(Model model){
        model.addAttribute("detailTransaction", reportInterface.getAllDetailTransaction());
        return model;
    }

    public Model generalLedgerWeekly(Model model){
        model.addAttribute("bestSellingWeekly", reportInterface.getAllBestSellingWeekly());
        model.addAttribute("incomeWeek",reportInterface.getIncomeWeek());
        model.addAttribute("itemOutStock", reportInterface.getItemOutOfStock());
        return model;
    }
    public Model generalLedgerMonthly(Model model){
        model.addAttribute("incomeMonth",reportInterface.getIncomeMonth());
        model.addAttribute("bestSellingMontly",reportInterface.getAllBestSellingMonthly());
        model.addAttribute("itemOutStock", reportInterface.getItemOutOfStock());
        return model;
    }
    public Model generalLedgerYearly(Model model){
        model.addAttribute("incomeYear",reportInterface.getIncomeYear());
        model.addAttribute("bestSellingYearly",reportInterface.getAllBestSellingYearly());
        model.addAttribute("itemOutStock", reportInterface.getItemOutOfStock());
        return model;
    }
    public Model getAllDetilTransactionByDate(Model model, Date dateFrom, Date dateUntil){

        model.addAttribute("detil", reportInterface.getDetailTransactionByDate(dateFrom,dateUntil));
        System.out.println("sukses masuk service dgn tanggan from="+dateFrom);
        System.out.println("sukses masuk service dgn tanggan Until="+dateUntil);
        return model;
    }
}
