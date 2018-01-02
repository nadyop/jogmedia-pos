package com.blibli.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class My_Connection {
    protected Connection con;
    public My_Connection(){}
    public My_Connection(Connection con){
        this.con=con;
    }
    public void makeConnection(){
        System.out.println("Openning database...");
        try{
            con = DriverManager.getConnection(
                    "jdbc:postgresql://ec2-54-235-148-19.compute-1.amazonaws.com:5432/dc30q5j3noe3mj",
                    "asmerpqvzuwdmg",
                    "018e4696ce272a12ec7dcf4422a44b9b772204f58afaa1ce9d2c2e21781689fb"
            );
            System.out.println("Success openning db!");
        }
        catch (Exception e){
            System.out.println("Error while openning db");
            System.out.println(e);
        }
    }
    public void disconnect(){
        System.out.println("Open disconnect");
        try{
            this.con.close();
            System.out.println("Success disconnect");
        }
        catch (Exception e){
            System.out.println("Error while disconnect db");
            System.out.println(e);
        }
    }
}