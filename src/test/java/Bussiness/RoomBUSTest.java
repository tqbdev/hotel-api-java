/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.Room;
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
public class RoomBUSTest {

    private ArrayList<Room> ArrayRooms;
    private RoomBUS instance;

    public RoomBUSTest() {
        System.out.println("Install ");
        ArrayRooms = new ArrayList<>();
        ArrayRooms.add(new Room("1", "name1", 4, new Date(), true, "description"));
        ArrayRooms.add(new Room("2", "name2", 2, new Date(), false, "description"));
        instance = new RoomBUS();
    }

    @BeforeClass
    public static void setUp() throws SQLException {

        System.out.println("Setup ");

        Connecter.Switch_to_Test_Database();
        Connection connection = Connecter.Connect();
        try {
            String command = "DELETE FROM Room WHERE True";
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
     * Test of Add method, of class RoomBUS.
     */
    @Test
    public void AA_testAdd() {
        System.out.println("Add");
        Result result = instance.Add(ArrayRooms.get(0));
        Result result2 = instance.Add(ArrayRooms.get(1));

        assertTrue(Result.OK == result.getStatus());
        assertTrue(Result.OK == result2.getStatus());
    }

    @Test
    public void AB_testAdd_Duplicate() {
        System.out.println("Add2");
        Result result = instance.Add(ArrayRooms.get(0));
        System.out.println(result.getStatus());
        assertTrue(Result.ACCEPTED == result.getStatus());
    }

    /**
     * Test of Update method, of class RoomBUS.
     */
    @Test
    public void B_testUpdate() {
        Room room = ArrayRooms.get(0);
        room.setDescription("description updated");
        Result result = instance.Update(room);
        assertTrue(Result.OK == result.getStatus());

    }

    /**
     * Test of Get_by_ID method, of class RoomBUS.
     */
    @Test
    public void C_testGet_by_ID() {
        System.out.println("Get_by_ID");
        String roomID = ArrayRooms.get(1).getRoomID();
        Result result = instance.Get_by_ID(roomID);
        Room room2 = ArrayRooms.get(1);
        assertEquals(result.getObject(), room2);
    }

    /**
     * Test of Delete method, of class RoomBUS.
     */
    @Test
    public void D_testDelete() {
        System.out.println("Delete");
        String roomID = ArrayRooms.get(0).getRoomID();
        Result result = instance.Delete(roomID);
        assertTrue(Result.OK == result.getStatus());

    }
}
