/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess;

import DTO.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class MenuDAO {

    private static final String TABLE_NAME = "Menu";
    private static final String PRICE_ID_COL = "priceID";
    private static final String ROOM_ID_COL = "roomID";
    private static final String AMOUNT_COL = "amount";
    private static final String IS_ACTIVE_COL = "isActive";
    private static final String CREATE_DATE_COL = "createDate";
    private static final String NOTE_COL = "note";

    Connection connection = null;

    public MenuDAO() {
    }

    //add Menu
    public int addMenu(Menu menu) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "INSERT INTO " + TABLE_NAME + " values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, menu.getPriceID());
            preparedStatement.setString(2, menu.getRoomID());
            preparedStatement.setLong(3, menu.getAmount());
            preparedStatement.setBoolean(4, menu.isActive());
            preparedStatement.setDate(5, new java.sql.Date(menu.getCreateDate().getTime()));
            preparedStatement.setString(6, menu.getNote());
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

    //delete Menu
    public int deleteMenu(String priceID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "DELETE FROM " + TABLE_NAME + " WHERE " + PRICE_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, priceID);
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

    //update Room
    public int updateMenu(Menu menu) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);

            String command = "UPDATE " + TABLE_NAME
                    + " SET " + ROOM_ID_COL + " = ? , "
                    + AMOUNT_COL + " = ? , "
                    + IS_ACTIVE_COL + " = ? , "
                    + CREATE_DATE_COL + " = ? , "
                    + NOTE_COL + " = ?  "
                    + " WHERE " + PRICE_ID_COL + " = ?";
            System.out.println(command);
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(6, menu.getPriceID());
            preparedStatement.setString(1, menu.getRoomID());
            preparedStatement.setLong(2, menu.getAmount());
            preparedStatement.setBoolean(3, menu.isActive());
            preparedStatement.setDate(4, new java.sql.Date(menu.getCreateDate().getTime()));
            preparedStatement.setString(5, menu.getNote());
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
    
    
     //get Menu
    public Menu getMenu(String priceID) throws SQLException {
        try {
            connection = Connecter.Connect();
            connection.setAutoCommit(false);
            String command = "SELECT * FROM " + TABLE_NAME + " WHERE " + PRICE_ID_COL + " = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(command);
            preparedStatement.setString(1, priceID);
            ResultSet rs = preparedStatement.executeQuery();
            connection.commit();

            Menu menu = null;
            if (rs.next()) {
                menu = new Menu(rs.getString(1), rs.getString(2), rs.getLong(3), 
                        rs.getBoolean(4), rs.getDate(5), rs.getString(6));
            }
            return menu;
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.close();
        }

    }

}
