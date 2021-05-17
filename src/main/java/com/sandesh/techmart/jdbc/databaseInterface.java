package com.sandesh.techmart.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class databaseInterface {
    public static Connection getDBCconnection() throws Exception{
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Stock","root",null);
            return conn;
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    public static int readQuantity (String productname){
        Connection conn=null;
        try {
            conn=getDBCconnection();
            String readQuantityQueryString = "select quantity from stock where product = ?";
            PreparedStatement readQuantityQuery = conn.prepareStatement(readQuantityQueryString);
            readQuantityQuery.setString(1,productname);
            ResultSet result = readQuantityQuery.executeQuery();

            while(result.next()){
                return result.getInt(1);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;

    }

    public static int updateQuantity (String productname, int updateCount) throws outOfStockException, orderQuantityHigh {
        int currentQuantity=readQuantity(productname);
        if (currentQuantity==0){
            throw new outOfStockException();
        }
        System.out.println("currentQuantity: "+currentQuantity);
        System.out.println("orderedQuantity: "+updateCount);
        System.out.println("remainQuantity: "+(currentQuantity-updateCount));
        if (currentQuantity<updateCount){
            throw new orderQuantityHigh();
        }
        updateCount=currentQuantity-updateCount;
        try {
            Connection conn = getDBCconnection();
            String updateQuantityQueryString = "UPDATE stock SET quantity=? WHERE product=?;";
            PreparedStatement updateQuantityQuery = conn.prepareStatement(updateQuantityQueryString);
            updateQuantityQuery.setString(2,productname);
            updateQuantityQuery.setInt(1,updateCount);


            int result = updateQuantityQuery.executeUpdate();

            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }

//    public static void main(String[] args) {
//        System.out.println(readQuantity("iphone"));
//        updateQuantity("iphone",12);
//        System.out.println(readQuantity("iphone"));
//    }

}
