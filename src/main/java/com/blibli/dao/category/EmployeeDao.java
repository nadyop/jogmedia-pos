package com.blibli.dao.category;

import com.blibli.dao.My_Connection;
import com.blibli.dao_api.EmployeeDaoInterface;
import com.blibli.model.Employee;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao extends My_Connection implements EmployeeDaoInterface {
    @Override
    public List<Employee> getAllEmployee(){
        String psql ="Select * from Employee";
        System.out.println("Show Employee");
        List<Employee> list=new ArrayList<>();
        try{
            this.makeConnection();
            Statement statement=this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null){
                System.out.println("getAll\t:");
                while (rs.next()){
                    System.out.println("\t"+rs.getInt("employee_id"));
                    Employee employee= new Employee(

                            rs.getInt("employee_id"),
                            rs.getString("employee_name"),
                            rs.getString("employee_uname"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getInt("status")
                    );
                    list.add(employee);
                }
            }
            this.disconnect();
        }
        catch (Exception e){
            System.out.println("Terdapat kesalahan eror : "+e);
        }
        return list;
    }


    @Override
    public void insertEmployee(Employee E){
        String psql;
        System.out.println(E.getEmployee_id());
        if(E.getEmployee_id()!=0){
            try{
                psql="update Employee set employee_name=?, employee_uname=?, password=?,role=?,status=? where employee_id=?";
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashPass = passwordEncoder.encode(E.getPassword());
                this.makeConnection();
                PreparedStatement preparedStatement= this.con.prepareStatement(psql);
                preparedStatement.setString(1,E.getEmployee_name());
                preparedStatement.setString(2,E.getEmployee_uname());
                preparedStatement.setString(3,hashPass);
                preparedStatement.setString(4,E.getRole());
                preparedStatement.setInt(5,E.getStatus());
                preparedStatement.setInt(6,E.getEmployee_id());
                preparedStatement.executeUpdate();

                this.disconnect();

            }
            catch (Exception e){
                System.out.println("Eror while updating.."+e);
            }
        }
        else{
            try{
                psql="INSERT  INTO Employee(employee_name, employee_uname, password, role, status) VALUES (?,?,?,?,1)";
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String hashPass = passwordEncoder.encode(E.getPassword());
                this.makeConnection();
                PreparedStatement preparedStatement= this.con.prepareStatement(psql);
                preparedStatement.setString(1,E.getEmployee_name());
                preparedStatement.setString(2,E.getEmployee_uname());
                preparedStatement.setString(3,hashPass);
                preparedStatement.setString(4,E.getRole());
                preparedStatement.executeUpdate();

                this.disconnect();
            }
            catch (Exception e){
                System.out.println("Eror while inserting.."+e);
            }
        }
    }
    @Override
    public List<Employee> search(String searchKey){
        //select * from book where LOWER(book_title) LIKE LOWER('%" + searchKey+ "%')   ORDER BY book_id
        String psql ="Select * from Employee WHERE LOWER(employee_name) LIKE lower('%" + searchKey+ "%') order by employee_id";

        List<Employee> list=new ArrayList<>();
        try{
            this.makeConnection();
            Statement statement=this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null){

                while (rs.next()){

                    Employee employee= new Employee(

                            rs.getInt("employee_id"),
                            rs.getString("employee_name"),
                            rs.getString("employee_uname"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getInt("status")
                    );
                    list.add(employee);
                }
            }
            this.disconnect();
        }
        catch (Exception e){
            System.out.println("Error while searching.. "+e);
        }
        return list;
    }
    @Override
    public Employee getIdEmployee(int idEmployee){
        String psql="Select * from Employee where employee_id='"+idEmployee+"';";
        Employee employee=new Employee();
        try{

            this.makeConnection();
            Statement statement= this.con.createStatement();
            ResultSet rs= statement.executeQuery(psql);
            if(rs!=null){
                while (rs.next()){
                    employee.setEmployee_id(rs.getInt("employee_id"));
                    employee.setEmployee_name(rs.getString("employee_name"));
                    employee.setEmployee_uname(rs.getString("employee_uname"));
                    employee.setPassword(rs.getString("password"));
                    employee.setRole(rs.getString("role"));
                    employee.setStatus(rs.getInt("status"));
                }
            }
            this.disconnect();
        }
        catch (Exception e){
            System.out.println("Error while getting id.."+e.toString());
        }
        return employee;
    }
    @Override
    public void softDeleteEmployee(int id) {
        String psql= "UPDATE Employee set status= case when status=1 then 0 when status=0 then 1 end where Employee.employee_id='"+id+"';";
        try{

            this.makeConnection();
            Statement statement= this.con.createStatement();
            statement.executeQuery(psql);
            this.disconnect();
        }
        catch (Exception e){
            System.out.println("Error while soft deleting.."+e.toString());
        }
    }

    @Override
    public void createTable() {
        String psql="create table if not exists EMPLOYEE " + "(" + "EMPLOYEE_ID serial not null," + "EMPLOYEE_NAME character varying(25)," + "EMPLOYEE_UNAME character varying(10)," + "PASSWORD text," + "ROLE character varying(10)," + "STATUS integer," + "unique(employee_uname)," + "constraint PK_EMPLOYEE primary key (EMPLOYEE_ID)" + ")";
        try{

            this.makeConnection();
            Statement statement= this.con.createStatement();
            statement.executeQuery(psql);
            this.disconnect();
        }
        catch (Exception e){
            System.out.println("Error while creating table employee.."+e.toString());
        }
    }
}