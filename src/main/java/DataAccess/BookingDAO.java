/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DTO.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class BookingDAO {

    private static final String TABLE_NAME = "Booking";
    private static final String BOOKING_ID_COL = "bookingID";
    private static final String CUSTOMER_ID_COL = "customerID";
    private static final String ROOM_ID_COL = "roomID";
    private static final String BOOKING_DATE_COL = "bookingDate";
    private static final String FROM_DATE_COL = "fromDate";
    private static final String TO_DATE_COL = "toDate";
    private static final String BOOKING_STATUS_COL = "bookingStatus";

    Connection connection = null;

    public BookingDAO() {
    }

    //add Booking
    public int addBooking(Booking booking) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "INSERT INTO " + TABLE_NAME + " values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, booking.getBookingID());
            preparedStatement.setString(2, booking.getCustomerID());
            preparedStatement.setString(3, booking.getRoomID());
            preparedStatement.setDate(4, new java.sql.Date(booking.getBookingDate().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(booking.getFromDate().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(booking.getToDate().getTime()));
            preparedStatement.setInt(7, booking.getBookingStatus());
            

            int ret = preparedStatement.executeUpdate();
            connection.commit();
            return ret;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }
    
    //delete Booking
    public int deleteBooking(String bookingID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "DELETE FROM " + TABLE_NAME + " WHERE " + BOOKING_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, bookingID);
            int ret = preparedStatement.executeUpdate();
            connection.commit();
            return ret;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }

    
    //update    Booking
    public int updateBooking(Booking booking) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "UPDATE " + TABLE_NAME
                    + " SET " + CUSTOMER_ID_COL + " = ? , "
                    + ROOM_ID_COL + " = ? , "
                    + BOOKING_DATE_COL + " = ? , "
                    + FROM_DATE_COL + " = ? , "
                    + TO_DATE_COL + " = ?  ,"
                    + BOOKING_STATUS_COL +" = ? "
                    + " WHERE " + BOOKING_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);

            preparedStatement.setString(7, booking.getBookingID());
            preparedStatement.setString(1, booking.getCustomerID());
            preparedStatement.setString(2, booking.getRoomID());
            preparedStatement.setDate(3, new java.sql.Date(booking.getBookingDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(booking.getFromDate().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(booking.getToDate().getTime()));
            preparedStatement.setInt(6, booking.getBookingStatus());
              int ret = preparedStatement.executeUpdate();
            connection.commit();
            return ret;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }


    
    //get Booking
    public Booking getBooking(String bookingID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "SELECT * FROM " + TABLE_NAME + " WHERE " + BOOKING_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, bookingID);
            ResultSet rs = preparedStatement.executeQuery();
            connection.commit();

            Booking booking = null;
            if (rs.next()) {
                booking = new Booking(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDate(6),rs.getInt(7));
            }
            return booking;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }

}
