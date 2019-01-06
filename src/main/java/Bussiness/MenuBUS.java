/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.Menu;
import DataAccess.MenuDAO;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class MenuBUS {

    private MenuDAO menuDAO;

    public MenuBUS() {

        menuDAO = new MenuDAO();

    }

    public Result Add(Menu menu) {
        try {
            if (menuDAO.getMenu(menu.getPriceID()) != null) {
                return new Result("Menu already exists !", Result.ACCEPTED);
            } else {
                menuDAO.addMenu(menu);
                return new Result("Add Menu sucessed !", Result.OK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request !", Result.BAD_REQUEST);
        }
    }

    public Result Delete(String priceID) {
        try {
            Menu menu = menuDAO.getMenu(priceID);
            if (menu == null) {
                return new Result("Menu not found !", Result.ACCEPTED);
            } else {
                if (menu.isActive() == false) {
                    return new Result("Menu is in use !", Result.ACCEPTED);
                } else {
                    menuDAO.deleteMenu(priceID);
                    return new Result("Deleted Menu !", Result.OK);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request! ", Result.BAD_REQUEST);

        }
    }

    public Result Update(Menu menu) {
        try {
            if (menuDAO.getMenu(menu.getPriceID()) == null) {
                return new Result("Menu not exists !", Result.ACCEPTED);

            } else {
                menuDAO.updateMenu(menu);
                return new Result("Update menu sucessed !", Result.OK);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }

    public Result Get_by_ID(String priceID) {
        Menu menu = null;
        try {
            menu = menuDAO.getMenu(priceID);
            if (menu == null) {
                return new Result("Menu not exists !", Result.ACCEPTED);

            } else {
                return new Result("Get menu sucessed !", Result.OK, menu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }

}
