package com.java.git;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Reservation {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        final String URL = "jdbc:mysql://localhost:3306/bus";
        final String USER = "root";
        final String PASSWORD = "admin";

        String checkBusSql = "SELECT COUNT(*) FROM Bus WHERE bus_id = ?";
        String checkPassengerSql = "SELECT COUNT(*) FROM Passenger WHERE passenger_id = ?";
        String checkRouteSql = "SELECT COUNT(*) FROM Route WHERE route_id = ?";
        String insertReservationSql = "INSERT INTO Reservation (bus_id, passenger_id, reservation_date, seat_number, route_id) VALUES (?, ?, ?, ?, ?)";

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);  
        PreparedStatement checkBusPst = con.prepareStatement(checkBusSql);
        PreparedStatement checkPassengerPst = con.prepareStatement(checkPassengerSql);
        PreparedStatement checkRoutePst = con.prepareStatement(checkRouteSql);
        PreparedStatement insertReservationPst = con.prepareStatement(insertReservationSql);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter bus ID: ");
        int busId = scanner.nextInt();
        checkBusPst.setInt(1, busId);
        ResultSet busRs = checkBusPst.executeQuery();
        busRs.next();
        if (busRs.getInt(1) == 0) {
            System.out.println("Bus ID does not exist.");
            return;
        }

        System.out.print("Enter passenger ID: ");
        int passengerId = scanner.nextInt();
        checkPassengerPst.setInt(1, passengerId);
        ResultSet passengerRs = checkPassengerPst.executeQuery();
        passengerRs.next();
        if (passengerRs.getInt(1) == 0) {
            System.out.println("Passenger ID does not exist.");
            return;
        }

        System.out.print("Enter route ID: ");
        int routeId = scanner.nextInt();
        checkRoutePst.setInt(1, routeId);
        ResultSet routeRs = checkRoutePst.executeQuery();
        routeRs.next();
        if (routeRs.getInt(1) == 0) {
            System.out.println("Route ID does not exist.");
            return;
        }

        scanner.nextLine(); // consume the newline

        System.out.print("Enter reservation date (YYYY-MM-DD): ");
        String reservationDate = scanner.nextLine();

        System.out.print("Enter seat number: ");
        int seatNumber = scanner.nextInt();

        insertReservationPst.setInt(1, busId);
        insertReservationPst.setInt(2, passengerId);
        insertReservationPst.setString(3, reservationDate);
        insertReservationPst.setInt(4, seatNumber);
        insertReservationPst.setInt(5, routeId);
        insertReservationPst.executeUpdate();
        System.out.println("Reservation added successfully");

        scanner.close();
        checkBusPst.close();
        checkPassengerPst.close();
        checkRoutePst.close();
        insertReservationPst.close();
        con.close();
    }
}