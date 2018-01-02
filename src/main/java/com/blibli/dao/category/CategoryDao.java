package com.blibli.dao.category;

import com.blibli.dao.My_Connection;
import com.blibli.dao_api.CategoryDaoInterface;
import com.blibli.model.Category;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDao extends My_Connection implements CategoryDaoInterface {

    @Override
    public List<Category> getAllActive(){
        String psql="select * from Category where status = 1";
        List<Category> list= new ArrayList<>();
        try{
            this.makeConnection();
            Statement statement=this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null){
                while (rs.next()){
                    Category category= new Category(
                            rs.getInt("category_id"),
                            rs.getString("category_name"),
                            rs.getString("category_desc"),
                            rs.getInt("status")
                    );
                    list.add(category);
                    System.out.println(category.getStatus());
                }
            }
            this.disconnect();
        }
        catch (Exception e){
            System.out.println("Error while getting data.."+ e.toString());
        }
        return list;
    }
    @Override
    public List<Category> getAllCategory() {
        String psql="select * from Category";
        List<Category> list= new ArrayList<>();
        try{
            this.makeConnection();
            Statement statement=this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null){
                while (rs.next()){
                    Category category= new Category(
                            rs.getInt("category_id"),
                            rs.getString("category_name"),
                            rs.getString("category_desc"),
                            rs.getInt("status")
                    );
                    list.add(category);
                    System.out.println(category.getStatus());
                }
            }
            this.disconnect();
        }
        catch (Exception e){
            System.out.println("Error while getting data.. "+ e.toString());
        }
        return list;
    }
    @Override
    public void softDeleteCategory(int id){

        String psql= "UPDATE Category set status= case when status=1 then 0 when status=0 then 1 end where Category.category_id='"+id+"';";
        try{
            this.makeConnection();
            Statement statement= this.con.createStatement();
            statement.execute(psql);
            this.disconnect();
        }
        catch (Exception e){
            System.out.println("Error while soft deleting.."+e.toString());
        }
    }
    @Override
    public void delete (int id){
        String psql= "Delete from Category where Category.category_id='"+id+"';";
        try{

            this.makeConnection();
            Statement statement= this.con.createStatement();
            statement.executeQuery(psql);
            this.disconnect();
        }
        catch (Exception e){
            System.out.println("Error while deleting.."+e.toString());
        }
    }
    @Override
    public List<Category> search(String searchKey) {
        String psql="select * from category where lower(category_name) like lower('%" + searchKey+ "%') ORDER BY category_id";
        List<Category> categories= new ArrayList<>();

        try {
            this.makeConnection();
            Statement statement = this.con.createStatement();
            ResultSet rs = statement.executeQuery(psql);

            if (rs != null) {
                while (rs.next()) {
                    Category category= new Category(
                            rs.getInt("category_id"),
                            rs.getString("category_name"),
                            rs.getString("category_desc"),
                            rs.getInt("status")
                    );
                    categories.add(category);
                }
            }
            this.disconnect();
        }catch (Exception e){
            System.out.println("Error while searching.. "+e.toString());
        }
        return categories;
    }

    @Override
    public Category getIdCategory(int idCategory){
        String psql="Select * from category where category_id='"+idCategory+"';";
        Category category= new Category();
        try {
            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null){
                while (rs.next()){

                    category.setCategory_id(rs.getInt("category_id"));
                    category.setCategory_name(rs.getString("category_name"));
                    category.setCategory_desc(rs.getString("category_desc"));
                    category.setStatus(rs.getInt("status"));
                }
            }
            this.disconnect();

        }
        catch (Exception e){
            System.out.println("Error while getting id category.. "+e.toString());
        }
        return category;
    }
    @Override
    public void insertCategory(Category C){
        String psql;

        if(C.getCategory_id()==0){
            psql = "Insert into category(category_name, category_desc, status)" +
                    "values (?,?,1)";
            try{
                this.makeConnection();
                PreparedStatement preparedStatement= this.con.prepareStatement(psql);
                preparedStatement.setString(1,C.getCategory_name());
                preparedStatement.setString(2,C.getCategory_desc());
                preparedStatement.executeQuery();
                this.disconnect();
            }
            catch (Exception e){
                System.out.println("Error while inserting category.. "+e.toString());
            }
        }
        else{
            psql="UPDATE category SET category_name =?, category_desc=?, status=? where category_id=?";
            try{
                this.makeConnection();
                PreparedStatement preparedStatement= this.con.prepareStatement(psql);
                preparedStatement.setString(1,C.getCategory_name());
                preparedStatement.setString(2,C.getCategory_desc());
                preparedStatement.setInt(3,C.getStatus());
                preparedStatement.setInt(4,C.getCategory_id());
                preparedStatement.executeUpdate();
                this.disconnect();
            }
            catch (Exception e){
                System.out.println("Error while updating category.. "+e.toString());
            }
        }
    }
}