package com.blibli.controller;


import com.blibli.model.Detil_Transaction;
import com.blibli.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class ReportController {
    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService){
        this.reportService=reportService;
    }
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public String report(Model model){
        model = reportService.generalLedgerWeekly(model);
        return "manager/show/report";
    }

    @RequestMapping(value = "/report-month", method = RequestMethod.GET)
    public String reportMonth(Model model){

        model = reportService.generalLedgerMonthly(model);
        return "manager/show/report-month";
    }

    @RequestMapping(value = "/report-year", method = RequestMethod.GET)
    public String reportYear(Model model){
        model =reportService.generalLedgerYearly(model);
        return "manager/show/report-year";
    }
    @RequestMapping(value = "/report-detail", method = RequestMethod.GET)
    public String reportDetail(){

        return "manager/show/report-detail";
    }
    @RequestMapping(value = "/report-detail/search", method = RequestMethod.GET)
    public String reportDetail1(Model model, @ModelAttribute(value = "dateFrom") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateFrom, @ModelAttribute(value="dateUntil") @DateTimeFormat(pattern="yyyy-MM-dd") Date dateUntil){

        model= reportService.getAllDetilTransactionByDate(model, dateFrom, dateUntil);
        return "manager/show/report-detail";
    }
}
