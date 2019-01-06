/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.Customer; // import class customer từ bên hàm chính ở thư mục main

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
// chúng ta sẽ tạo ra một class customerbustest tương ứng với customerbus mà chúng ta cần test
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerBUSTest {

    private ArrayList<Customer> ArrayCustomers;
    private CustomerBUS instance;

    public CustomerBUSTest() {
        System.out.println("Install ");
        // tạo một mảng customer, sau đó add thông tin của 2 customer vào bao gồm ID, tên, ngày tháng ,....
        ArrayCustomers = new ArrayList<>();
        ArrayCustomers.add(new Customer("1", "Nguyen A", "Le Dai Hanh", new Date(),"01234567", new Date()));
        ArrayCustomers.add(new Customer("2", "Nguyen B", "Vo Thi Sau", new Date(),"0123467", new Date()));
        ////////
        instance = new CustomerBUS();
    }

    @BeforeClass
    public static void setUp() throws SQLException {

        System.out.println("Setup ");

        Connecter.Switch_to_Test_Database();
        Connection connection = Connecter.Connect();
        try {
            String command = "DELETE FROM Customer WHERE True";
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
     * Kiểm tra hàm add customer
     */
    // ta sẽ bắt đầu viết hàm test cho chức năng add customer
    @Test
    public void addCustomer() {
    	System.out.print("start add");
        Result result = instance.Add(ArrayCustomers.get(0));
        Result result2 = instance.Add(ArrayCustomers.get(1));
      // dùng assert để so sánh kết quả trả về và kết quả mong muốn xem có giống nhau không
      // nếu giống nhau thì test case của mình đã pass
        assertTrue(Result.OK == result.getStatus());
        assertTrue(Result.OK == result2.getStatus());
    }
    // Sau khi viết xong, ta bắt đầu chạy hàm test
    
    /**
     * Kiểm tra hàm delete customer
     */
    @Test
    public void deleteCustomer() {
    	System.out.print("start delete");
    	// Ta co customerId: 1, 2 la da ton tai trong db
        Result result = instance.Delete("1"); // OK
        Result result2 = instance.Delete("3"); // ACCEPTED

        assertTrue(Result.OK == result.getStatus());
        assertTrue(Result.ACCEPTED == result2.getStatus());
    }
}
