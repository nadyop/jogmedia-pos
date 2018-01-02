package com.blibli.dao_api;

import com.blibli.model.BestSelling;
import com.blibli.model.Detil_Transaction;
import com.blibli.model.ReportDetail;
import java.util.Date;
import java.util.List;

public interface ReportInterface {
    double getIncomeWeek();
    double getIncomeMonth();
    double getIncomeYear();
    int getItemOutOfStock();
    List<Detil_Transaction> getAllDetailTransaction();
    List<BestSelling> getAllBestSellingWeekly();
    List<BestSelling> getAllBestSellingMonthly();
    List<BestSelling> getAllBestSellingYearly();
    List<ReportDetail> getDetailTransactionByDate(Date dateFrom, Date dateUntil);
}
