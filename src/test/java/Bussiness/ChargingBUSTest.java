/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.Charging;
import DataAccess.Connecter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author lap10467
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChargingBUSTest {

    private ArrayList<Charging> ArrayChargings;
    private ChargingBUS instance;

    public ChargingBUSTest() {
        System.out.println("Install ");
        ArrayChargings = new ArrayList<>();
        ArrayChargings.add(new Charging("1","1", 1000, new Date(), 0, 0, 1));
        ArrayChargings.add(new Charging("2","2", 2000, new Date(), 2, 2, -2));

        instance = new ChargingBUS();
    }

    @BeforeClass
    public static void setUp() throws SQLException {

        System.out.println("Setup ");

        Connecter.Switch_to_Test_Database();
        Connection connection = Connecter.Connect();
        try {
            String command = "DELETE FROM Charging WHERE True";
            Statement statement = connection.createStatement();
            statement.execute(command);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @AfterClass
    public static void tearDown() throws SQLException {
        Connecter.CloseConnection();
        Connecter.Switch_to_Main_Database();
    }

    /**
     * Test of Add method, of class ChargingBUS.
     */
    @Test
    public void AA_testAdd() {
        System.out.println("Add");
        Result result = instance.Add(ArrayChargings.get(0));
        Result result2 = instance.Add(ArrayChargings.get(1));

        assertTrue(Result.OK == result.getStatus());
        assertTrue(Result.OK == result2.getStatus());
    }

    @Test
    public void AB_testAdd_Duplicate() {
        System.out.println("Add2");
        Result result = instance.Add(ArrayChargings.get(0));
        System.out.println(result.getStatus());
        assertTrue(Result.ACCEPTED == result.getStatus());
    }

    /**
     * Test of Update method, of class ChargingBUS.
     */
    @Test
    public void B_testUpdate() {
        Charging charging = ArrayChargings.get(0);
        charging.setTotalAmount(203123);
        Result result = instance.Update(charging);
        assertTrue(Result.OK == result.getStatus());

    }

    /**
     * Test of Get_by_ID method, of class ChargingBUS.
     */
    @Test
    public void C_testGet_by_ID() {
        System.out.println("Get_by_ID");
        String chargingID = ArrayChargings.get(1).getChargeID();
        Result result = instance.Get_by_ID(chargingID);
        Charging charging2 = ArrayChargings.get(1);
        assertEquals(result.getObject(), charging2);
    }

    /**
     * Test of Delete method, of class ChargingBUS.
     */
    @Test
    public void D_testDelete() {
        System.out.println("Delete");
        String chargingID = ArrayChargings.get(0).getChargeID();
        Result result = instance.Delete(chargingID);
        assertTrue(Result.OK == result.getStatus());

    }
}
