/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DTO.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class CustomerDAO {

    private static final String TABLE_NAME = "Customer";
    private static final String CUSTOMER_ID_COL = "customerID";
    private static final String FULL_NAME_COL = "fullName";
    private static final String ADDRESS_COL = "address";
    private static final String DAY_OF_BIRTH_COL = "dayOfBirth";
    private static final String PHONE_NUMBER_COL = "phoneNumber";
    private static final String CREATE_DATE_COL = "createDate";
    Connection connection = null;

    public CustomerDAO() {
    }

    //add Menu
    public int addCustomer(Customer customer) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "INSERT INTO " + TABLE_NAME + " values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getFullName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setDate(4, new java.sql.Date(customer.getDayOfBirth().getTime()));
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setDate(6, new java.sql.Date(customer.getCreateDate().getTime()));
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

    //delete Customer
    public int deleteCustomer(String customerID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "DELETE FROM " + TABLE_NAME + " WHERE " + CUSTOMER_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, customerID);
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

    //update Customer
    public int updateCustomer(Customer customer) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "UPDATE " + TABLE_NAME
                    + " SET " + FULL_NAME_COL + " = ? , "
                    + ADDRESS_COL + " = ? , "
                    + DAY_OF_BIRTH_COL + " = ? , "
                    + PHONE_NUMBER_COL + " = ? , "
                    + CREATE_DATE_COL + " = ?  "
                    + " WHERE " + CUSTOMER_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);

            preparedStatement.setString(6, customer.getCustomerID());
            preparedStatement.setString(1, customer.getFullName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setDate(3, new java.sql.Date(customer.getDayOfBirth().getTime()));
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setDate(5, new java.sql.Date(customer.getCreateDate().getTime()));
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
    
     //get room
    public Customer getCustomer(String customerID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "SELECT * FROM " + TABLE_NAME + " WHERE " + CUSTOMER_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, customerID);
            ResultSet rs = preparedStatement.executeQuery();
            connection.commit();

            Customer customer = null;
            if (rs.next()) {
                customer = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getDate(6));
            }
            return customer;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }
}
