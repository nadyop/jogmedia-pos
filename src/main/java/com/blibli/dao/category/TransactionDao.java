package com.blibli.dao.category;

import com.blibli.dao.My_Connection;
import com.blibli.dao_api.TransactionInterface;
import com.blibli.model.*;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDao extends My_Connection implements TransactionInterface {
    @Override
    public void saveTransaction(Transaction transaction){
        String psql;
        psql = "Insert into transaction(transaction_id,employee_id,total_pembelian,tanggal_transaksi)" +
                " values (?,?,?,now())";

        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.con.prepareStatement(psql);
            preparedStatement.setInt(1,transaction.getTransaction_id());
            preparedStatement.setInt(2, transaction.getEmployee_id());
            preparedStatement.setDouble(3, transaction.getTotal_pembelian());

            preparedStatement.executeUpdate();
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while saving.. "+e.toString());
        }
    }
    @Override
    public void saveDetailTransaction(Detil_Transaction detil){
        String psql;
        psql = "Insert into detil_transaction(transaction_id,book_id,quantity,unit_price, discount)"+
                " values (?,?,?,?,?)";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement= this.con.prepareStatement(psql);
            preparedStatement.setInt(1,detil.getTransaction_id());
            preparedStatement.setInt(2,detil.getBook_id());
            preparedStatement.setInt(3,detil.getQuantity());
            preparedStatement.setDouble(4,detil.getUnit_price());
            preparedStatement.setInt(5,detil.getDiscountDetil());
            preparedStatement.execute();
            this.disconnect();
        }catch (Exception e){
            System.out.println("Error while saving.. "+e.toString());
        }
    }
    @Override
    public void deleteFromTempDetailByKeyword(int idDetil){
        String psql= "Delete from temp_detil where id_detil='"+idDetil+"';";
        try {
            this.makeConnection();
            Statement statement=this.con.createStatement();
            statement.executeQuery(psql);
            this.disconnect();
        }catch (Exception e){
            System.out.println("Error while deleting.. "+e.toString());
        }
    }
    @Override
    public List<Detil_Transaction> getAllDetilTransactionByKeyword(int id){

        String psql =
                " select isbn, book_title, detil_id,transaction_id,book.book_id,quantity," +
                "(unit_price*quantity) as subTotal, detil_transaction.discount from Detil_transaction join book\n" +
                "on detil_transaction.book_id = book.book_id where  transaction_id ='"+id+"';";

        List<Detil_Transaction> detil_transactions =  new ArrayList<>();
        try{
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet rs = statement.executeQuery(psql);

            if (rs != null) {
                while (rs.next()) {
                    Detil_Transaction detil_transaction = new Detil_Transaction(
                            rs.getString("isbn"),
                            rs.getString("book_title"),
                            rs.getInt("transaction_id"),
                            rs.getInt("book_id"),
                            rs.getInt("detil_id"),
                            rs.getInt("quantity"),
                            rs.getInt("discount"),
                            rs.getDouble("subTotal")
                    );
                    detil_transactions.add(detil_transaction);
                }
            }
            this.disconnect();
        }catch (Exception e){
            System.out.println("Error while getting .."+e.toString());
        }
        return detil_transactions;
    }
    @Override
    public List<Book> searchCashier(String searchKey) {
        String psql="select * from book where  status=1 AND stok!=0 AND  LOWER(isbn) LIKE LOWER('%" + searchKey+ "%') ORDER BY book_id";
        List<Book> books= new ArrayList<>();

        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet rs = statement.executeQuery(psql);

            if (rs != null) {
                while (rs.next()) {
                    Book book= new Book(
                            rs.getInt("book_id"),
                            rs.getInt("category_id"),
                            rs.getString("isbn"),
                            rs.getString("book_title"),
                            rs.getString("author"),
                            rs.getString("publisher"),
                            rs.getString("location"),
                            rs.getInt("discount"),
                            rs.getDouble("price_before"),
                            rs.getDouble("price_after"),
                            rs.getInt("stok"),
                            rs.getInt("status")
                    );
                    books.add(book);
                }
            }
            this.disconnect();
        }catch (Exception e){
            System.out.println("Error while searching .."+e.toString());
        }
        return books;
    }
    @Override
    public void updateTempDetil(double tempUnitPrice, int qty, int id){
        String psql;
        psql = "Update temp_Detil SET quantity=?, unit_price=? where id_detil=?";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement= this.con.prepareStatement(psql);
            preparedStatement.setInt(1,qty);
            preparedStatement.setDouble(2,tempUnitPrice);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
            this.disconnect();
        }catch (Exception e){
            System.out.println("Error while updating .."+e.toString());
        }
    }
    @Override
    public void saveTempDetilTransaction(TempDetil tempDetil){
        String psql;
        psql = "Insert into temp_Detil(book_id, quantity, unit_price, discount)"+
                " values (?,?,?,?)";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement= this.con.prepareStatement(psql);
            preparedStatement.setInt(1,tempDetil.getBookId());
            preparedStatement.setInt(2,tempDetil.getQuantity());
            preparedStatement.setDouble(3,tempDetil.getUnitPrice());
            preparedStatement.setInt(4,tempDetil.getDiscount());
            preparedStatement.executeUpdate();
            this.disconnect();

        }catch (Exception e){
            System.out.println("Error while saving.."+e.toString());
        }
    }
    @Override
    public List<TempDetil> getAllTempDetilSaved(){

        String psql=" select id_detil,book.isbn,temp_detil.book_id, quantity, unit_price, temp_detil.discount, book_title," +
                " ( quantity * unit_price) as subTotal from temp_detil join book using(book_id)";
        List<TempDetil> temp = new ArrayList<>();
        try {
            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null) {
                while (rs.next()) {
                    TempDetil tempDetil= new TempDetil(
                            rs.getInt("id_detil"),
                            rs.getInt("book_id"),
                            rs.getInt("quantity"),
                            rs.getDouble("unit_price"),
                            rs.getInt("discount"),
                            rs.getString("book_title"),
                            rs.getString("isbn"),
                            rs.getDouble("subTotal")
                    );
                    temp.add(tempDetil);
                }
            }
        }
        catch (Exception e){
            System.out.println("Error while getting data.. "+e.toString());
        }
        return  temp;
    }
    @Override
    public TempDetil getIdTempDetilbyNomorIdDetil(int idTemp){

        String psql= "select id_detil, book.isbn, temp_detil.book_id, quantity, unit_price," +
                     " temp_detil.discount, book_title, (quantity*unit_price) as subTotal " +
                     "from temp_detil join book using(book_id) where id_detil='"+idTemp+"';";

        TempDetil tempDetil= new TempDetil();
        try{
            this.makeConnection();
            Statement statement=this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null) {
                while (rs.next()) {
                    tempDetil.setId_detil(rs.getInt("id_detil"));
                    tempDetil.setBookId(rs.getInt("book_id"));
                    tempDetil.setQuantity(rs.getInt("quantity"));
                    tempDetil.setUnitPrice(rs.getDouble("unit_price"));
                    tempDetil.setDiscount(rs.getInt("discount"));
                    tempDetil.setBook_title(rs.getString("book_title"));
                    tempDetil.setIsbn(rs.getString("isbn"));
                    tempDetil.setSubTotal(rs.getDouble("subTotal"));
                }
            }
            this.disconnect();
        }catch (Exception e){
            System.out.println("Error while get id.. "+e.toString());
        }
        return  tempDetil;
    }
    @Override
    public TempDetil getIdTempDetil(int idTemp){
        String psql= " select id_detil,book.isbn,temp_detil.book_id, quantity, unit_price," +
                     " temp_detil.discount, book_title, (quantity*unit_price) as subTotal" +
                     " from temp_detil join book using(book_id) where temp_detil.book_id="+idTemp+";";
        TempDetil tempDetil= new TempDetil();
        try{
            this.makeConnection();
            Statement statement=this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null) {
                while (rs.next()) {
                    tempDetil.setId_detil(rs.getInt("id_detil"));
                    tempDetil.setBookId(rs.getInt("book_id"));
                    tempDetil.setQuantity(rs.getInt("quantity"));
                    tempDetil.setUnitPrice(rs.getDouble("unit_price"));
                    tempDetil.setDiscount(rs.getInt("discount"));
                    tempDetil.setBook_title(rs.getString("book_title"));
                    tempDetil.setIsbn(rs.getString("isbn"));
                    tempDetil.setSubTotal(rs.getDouble("subTotal"));

                }
            }
            this.disconnect();
         }catch (Exception e){
            System.out.println("Error while getiing.."+e.toString());
        }
        return  tempDetil;
    }

    @Override
    public void updatingStok(int id, int qty){
        String psql= "update book set stok = stok + '"+ qty +"' where book_id='"+id+"'";
        try {
            this.makeConnection();
            Statement statement = con.createStatement();
            statement.execute(psql);
            this.disconnect();
        }catch (Exception e){
            System.out.println("Error while updating.."+e.toString());
        }

    }
    @Override
    public int getIdTransaction(){
        String psql=" select nextval('transaction_transaction_id_seq')";
        int idTemp=0;
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery(psql);
            if (resultSet != null)  {
                while (resultSet.next())    {
                    idTemp = resultSet.getInt("nextval");
                }
            }
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while get nextval.."+e.toString());
        }
        return idTemp;
    }
    @Override
    public void updateTransaction(int transaction_id, double total){
        String psql;
        psql = "UPDATE transaction SET total_pembelian=? WHERE transaction_id=?";
        try {
            this.makeConnection();
            PreparedStatement preparedStatement = this.con.prepareStatement(psql);
            preparedStatement.setDouble(1,total);
            preparedStatement.setInt(2,transaction_id);
            preparedStatement.executeUpdate();

            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while update transaction .."+e.toString());
        }

    }
    @Override
    public void deleteTempDetil(){
        String psql= "delete from temp_detil";
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            statement.execute(psql);
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while deleting.. "+e.toString());
        }
    }

    @Override
    public void createTableTransaction() {
        String psql="create table if not exists TRANSACTION(TRANSACTION_ID serial not null,EMPLOYEE_ID int not null,total_pembelian numeric(19,2),tanggal_transaksi timestamp,constraint PK_TRANSACTION primary key (TRANSACTION_ID),constraint fk_empId foreign key(employee_id) references employee(employee_id))";
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            statement.execute(psql);
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while creating table transaction.. "+e.toString());
        }
    }

    @Override
    public void createTableTemp() {
        String psql="create table if not exists temp_detil(id_detil serial not null,BOOK_ID integer not null,QUANTITY integer,UNIT_PRICE numeric(19,2),DISCOUNT integer,constraint pk_doublePK primary key (id_detil,book_id))";
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            statement.execute(psql);
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while creating table temp_detil.. "+e.toString());
        }
    }

    @Override
    public void createTableDetilTransaction() {
        String psql="create table if not exists DETIL_TRANSACTION(DETIL_ID serial not null,TRANSACTION_ID integer not null,BOOK_ID integer not null,QUANTITY integer,UNIT_PRICE numeric(19,2),DISCOUNT integer,constraint PK_DETIL_TRANSACTION primary key (DETIL_ID),constraint fk_transId foreign key(transaction_id) references transaction(transaction_id),constraint fk_bookId foreign key(book_id) references book(book_id))";
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            statement.execute(psql);
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while creating table detil_transaction.. "+e.toString());
        }
    }

    @Override
    public List<Store> getDataStore(){

        String psql= "select * from store";
        List<Store> store = new ArrayList<>();
        try{
            this.makeConnection();
            Statement statement=this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null) {
                while (rs.next()) {
                    Store store1 =  new Store(
                    rs.getInt( "store_id"),
                    rs.getString("store_name"),
                    rs.getString("address"),
                    rs.getString("npwp"),
                    rs.getString("post_code"),
                    rs.getString("email")
                    );
                    store.add(store1);
                }
            }
            this.disconnect();
        }catch (Exception e){
            System.out.println("Error while getting.."+e.toString());
        }
        return  store;
    }

    public int getIdEmployee(String searchKey){
        String psql=" select employee_id from employee where employee_uname = '"+searchKey+"'";
        int idTemp=0;
        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet resultSet = statement.executeQuery(psql);
            if (resultSet != null)  {
                while (resultSet.next())    {
                    idTemp = resultSet.getInt("employee_id");
                }
            }
            this.disconnect();
        } catch (Exception e) {
            System.out.println("Error while get nextval.."+e.toString());
        }
        return idTemp;
    }
}
