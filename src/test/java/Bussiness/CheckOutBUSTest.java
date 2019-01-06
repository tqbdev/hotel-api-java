/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.CheckOut;
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
public class CheckOutBUSTest {

    private ArrayList<CheckOut> ArrayCheckOuts;
    private CheckOutBUS instance;

    public CheckOutBUSTest() {
        System.out.println("Install ");
        ArrayCheckOuts = new ArrayList<>();
        ArrayCheckOuts.add(new CheckOut("1", "1", 1000, new Date()));
        ArrayCheckOuts.add(new CheckOut("2", "2", 2000, new Date()));
        instance = new CheckOutBUS();
    }

    @BeforeClass
    public static void setUp() throws SQLException {

        System.out.println("Setup ");

        Connecter.Switch_to_Test_Database();
        Connection connection = Connecter.Connect();
        try {
            String command = "DELETE FROM CheckOut WHERE True";
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
     * Test of Add method, of class CheckOutBUS.
     */
    @Test
    public void AA_testAdd() {
        System.out.println("Add");
        Result result = instance.Add(ArrayCheckOuts.get(0));
        Result result2 = instance.Add(ArrayCheckOuts.get(1));

        assertTrue(Result.OK == result.getStatus());
        assertTrue(Result.OK == result2.getStatus());
    }

    @Test
    public void AB_testAdd_Duplicate() {
        System.out.println("Add2");
        Result result = instance.Add(ArrayCheckOuts.get(0));
        System.out.println(result.getStatus());
        assertTrue(Result.ACCEPTED == result.getStatus());
    }

    /**
     * Test of Update method, of class CheckOutBUS.
     */
    @Test
    public void B_testUpdate() {
        CheckOut checkOut = ArrayCheckOuts.get(0);
        checkOut.setTotalAmount(20402);
        Result result = instance.Update(checkOut);
        assertTrue(Result.OK == result.getStatus());

    }

    /**
     * Test of Get_by_ID method, of class CheckOutBUS.
     */
    @Test
    public void C_testGet_by_ID() {
        System.out.println("Get_by_ID");
        String bookingID = ArrayCheckOuts.get(1).getBookingID();
        String roomID=ArrayCheckOuts.get(1).getRoomID();
        Result result = instance.Get_by_ID(bookingID,roomID);
        CheckOut checkOut2 = ArrayCheckOuts.get(1);
        assertEquals(result.getObject(), checkOut2);
    }

    /**
     * Test of Delete method, of class CheckOutBUS.
     */
    @Test
    public void D_testDelete() {
        System.out.println("Delete");
        String bookingID = ArrayCheckOuts.get(1).getBookingID();
        String roomID=ArrayCheckOuts.get(1).getRoomID();
        Result result = instance.Delete(bookingID,roomID);
        assertTrue(Result.OK == result.getStatus());

    }
}
