/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.Charging;
import DataAccess.ChargingDAO;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class ChargingBUS {

    private ChargingDAO chargingDAO;

    public ChargingBUS() {
        chargingDAO = new ChargingDAO();
    }

    public Result Add(Charging charging) {
        try {
            if (chargingDAO.getCharging(charging.getChargeID()) != null) {
                return new Result("Charging already exists !", Result.ACCEPTED);
            } else {
                chargingDAO.addChargin(charging);
                return new Result("Add Charging sucessed !", Result.OK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request !", Result.BAD_REQUEST);
        }
    }

    public Result Delete(String chargingID) {
        try {
            Charging charging = chargingDAO.getCharging(chargingID);
            if (charging == null) {
                return new Result("Charging not found !", Result.ACCEPTED);
            } else {
                chargingDAO.deleteCharing(chargingID);
                return new Result("Deleted Charging !", Result.OK);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request! ", Result.BAD_REQUEST);

        }
    }

    public Result Update(Charging charging) {
        try {
            if (chargingDAO.getCharging(charging.getChargeID()) == null) {
                return new Result("Charging not exists !", Result.ACCEPTED);

            } else {
                chargingDAO.updateCharging(charging);
                return new Result("Update charging sucessed !", Result.OK);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }

    public Result Get_by_ID(String chargingID) {
        Charging charging = null;
        try {
            charging = chargingDAO.getCharging(chargingID);
            if (charging == null) {
                return new Result("Charging not exists !", Result.ACCEPTED);

            } else {
                return new Result("Get charging sucessed !", Result.OK, charging);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }

}
