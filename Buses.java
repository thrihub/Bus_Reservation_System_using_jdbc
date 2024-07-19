package com.java.git;

import java.sql.*;

import java.util.Scanner;

public class Buses {

	public static void main(String[] args) throws ClassNotFoundException,SQLException{
    
    final String URL = "jdbc:mysql://localhost:3306/bus";
    final String USER = "root";
   final String PASSWORD = "admin"; 

    
        String sql = "INSERT INTO Bus (bus_number, capacity) VALUES (?, ?)";
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        
             PreparedStatement pst = con.prepareStatement(sql);
            
        
    

    
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter bus number: ");
        int busNumber = scanner.nextInt();
        pst.setInt(1,busNumber);
        
        System.out.print("Enter capacity: ");
        int capacity = scanner.nextInt();
        pst.setInt(2, capacity);
        pst.executeUpdate();
        System.out.println("Bus Added successfully");
        
        

        scanner.close();
    }
}