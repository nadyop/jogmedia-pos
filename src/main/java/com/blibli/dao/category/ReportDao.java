package com.blibli.dao.category;

import com.blibli.dao.My_Connection;
import com.blibli.dao_api.ReportInterface;
import com.blibli.model.BestSelling;
import com.blibli.model.Detil_Transaction;
import com.blibli.model.ReportDetail;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportDao extends My_Connection implements ReportInterface {

    @Override
    public int getItemOutOfStock(){
        String psql="select count(*) as item from book where stok=0";
        int tempItem=0;
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery(psql);
            if (resultSet != null)  {
                while (resultSet.next())    {
                    tempItem= resultSet.getInt("item");
                }
            }
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while get income.."+e.toString());
        }
        return tempItem;
    }
    @Override
    public double getIncomeWeek(){
        String psql="  select  sum(total_pembelian) as total from transaction where tanggal_transaksi > now() - interval '1 week' ";
        double tempIncome=0;
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery(psql);
            if (resultSet != null)  {
                while (resultSet.next())    {
                    tempIncome = resultSet.getDouble("total");
                }
            }
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while get income.."+e.toString());
        }
        return tempIncome;
    }

    @Override
    public double getIncomeMonth(){
        String psql="  select  sum(total_pembelian) as total from transaction where tanggal_transaksi > now() - interval '1 month' ";
        double tempIncome=0;
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery(psql);
            if (resultSet != null)  {
                while (resultSet.next())    {
                    tempIncome = resultSet.getDouble("total");
                }
            }
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while get income.."+e.toString());
        }
        return tempIncome;
    }
    @Override
    public double getIncomeYear(){
        String psql="  select  sum(total_pembelian) as total from transaction where tanggal_transaksi > now() - interval '1 year' ";
        double tempIncome=0;
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery(psql);
            if (resultSet != null)  {
                while (resultSet.next())    {
                    tempIncome = resultSet.getDouble("total");
                }
            }
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while get income.."+e.toString());
        }
        return tempIncome;
    }
    @Override
    public List<Detil_Transaction> getAllDetailTransaction(){

        String psql=" select transaction_id,book_id,quantity, unit_price,discount from detil_transaction ";
        List<Detil_Transaction> temp = new ArrayList<>();
        try {
            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null) {
                while (rs.next()) {

                    Detil_Transaction tempDetil= new Detil_Transaction(
                            rs.getString("-"),
                            rs.getString("-"),
                            rs.getInt("transaction_id"),
                            rs.getInt("book_id"),
                            rs.getInt("detil_id"),
                            rs.getInt("quantity"),
                            rs.getInt("discount"),
                            rs.getDouble("unit_price")
                    );
                    temp.add(tempDetil);
                }
            }
        }
        catch (Exception e){
            System.out.println("Error while get all detail.."+e.toString());
        }
        return  temp;
    }
    @Override
    public List<BestSelling> getAllBestSellingWeekly(){
        String psql="select isbn,book_title, sum(quantity) as total_quantity, sum(quantity*unit_price) as total_price" +
                " from detil_transaction join book\n" +
                "on book.book_id = detil_transaction.book_id\n" +
                "join transaction\n" +
                "on detil_transaction.transaction_id = transaction.transaction_id\n" +
                "where tanggal_transaksi > now() - interval '1 week' \n" +
                "group by book.book_id  order by sum(quantity) desc limit 10";
        List<BestSelling> temp = new ArrayList<>();
        try {
            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null) {
                while (rs.next()) {
                    BestSelling bestSelling = new BestSelling(
                            rs.getString("isbn"),
                            rs.getString("book_title"),
                            rs.getInt("total_quantity"),
                            rs.getDouble("total_price")
                    );
                    temp.add(bestSelling);
                }
            }
        }
        catch (Exception e){
            System.out.println("Error while get best selling.."+e.toString());
        }
        return  temp;
    }
    @Override
    public List<BestSelling> getAllBestSellingMonthly(){

        String psql="select isbn,book_title, sum(quantity) as total_quantity, sum(quantity*unit_price) as total_price" +
                " from detil_transaction join book\n" +
                "on book.book_id = detil_transaction.book_id\n" +
                "join transaction\n" +
                "on detil_transaction.transaction_id = transaction.transaction_id\n" +
                "where tanggal_transaksi > now() - interval '1 month' \n" +
                "group by book.book_id  order by sum(quantity) desc limit 10";
        List<BestSelling> temp = new ArrayList<>();
        try {
            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null) {
                while (rs.next()) {
                    BestSelling bestSelling = new BestSelling(
                            rs.getString("isbn"),
                            rs.getString("book_title"),
                            rs.getInt("total_quantity"),
                            rs.getDouble("total_price")
                    );
                    temp.add(bestSelling);
                }
            }
        }
        catch (Exception e){
            System.out.println("Error while get best selling.."+e.toString());
        }
        return  temp;
    }
    @Override
    public List<BestSelling> getAllBestSellingYearly(){
        String psql="select isbn,book_title, sum(quantity) as total_quantity, sum(quantity*unit_price) as total_price " +
                "from detil_transaction join book\n" +
                "on book.book_id = detil_transaction.book_id\n" +
                "join transaction\n" +
                "on detil_transaction.transaction_id = transaction.transaction_id\n" +
                "where tanggal_transaksi > now() - interval '1 year' \n" +
                "group by book.book_id  order by sum(quantity) desc limit 10";
        List<BestSelling> temp = new ArrayList<>();
        try {
            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null) {
                while (rs.next()) {
                    BestSelling bestSelling = new BestSelling(
                            rs.getString("isbn"),
                            rs.getString("book_title"),
                            rs.getInt("total_quantity"),
                            rs.getDouble("total_price")
                    );
                    temp.add(bestSelling);
                }
            }
        }
        catch (Exception e){
            System.out.println("Error while get best selling.."+e.toString());
        }
        return  temp;
    }
    @Override
    public List<ReportDetail> getDetailTransactionByDate(Date dateFrom, Date dateUntil)
    {

        String psql=" select isbn, book_title, unit_price, quantity, (unit_price * quantity) as SubTotal " +
                "from book join detil_transaction\n" +
                "on book.book_id = detil_transaction.book_id\n" +
                "join transaction\n" +
                "on detil_transaction.transaction_id = transaction.transaction_id\n" +
                "where tanggal_transaksi between '"+dateFrom+"' AND '"+dateUntil+"'";
        List<ReportDetail> temp = new ArrayList<>();
        try {
            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null) {
                while (rs.next()) {
                    ReportDetail reportDetail= new ReportDetail(
                            rs.getString("isbn"),
                            rs.getString("book_title"),
                            rs.getDouble("unit_price"),
                            rs.getDouble("SubTotal"),
                            rs.getInt("quantity")
                    );

                    temp.add(reportDetail);
                }
            }
        }
        catch (Exception e){
            System.out.println("Error while get best selling.."+e.toString());
        }
        return  temp;
    }
}
