/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussiness;

import DTO.Customer;
import DTO.Customer;
import DataAccess.CustomerDAO;
import java.sql.SQLException;

/**
 *
 * @author lap10467
 */
public class CustomerBUS {

    private CustomerDAO customerDAO;

    public CustomerBUS() {
        customerDAO = new CustomerDAO();
    }
    // Chúng ta sẽ test hàm add customer trong class chức năng của customer

    public Result Add(Customer customer) {
        try {
            if (customerDAO.getCustomer(customer.getCustomerID()) != null) {
                return new Result("Customer already exists !", Result.ACCEPTED);
            } else {
                customerDAO.addCustomer(customer);
                return new Result("Add Customer sucessed !", Result.OK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request !", Result.BAD_REQUEST);
        }
    }

    public Result Delete(String customerID) {
        try {
            Customer customer = customerDAO.getCustomer(customerID);
            if (customer == null) {
                return new Result("Customer not found !", Result.ACCEPTED);
            } else {
                customerDAO.deleteCustomer(customerID);
                return new Result("Deleted Customer !", Result.OK);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request! ", Result.BAD_REQUEST);

        }
    }

    public Result Update(Customer customer) {
        try {
            if (customerDAO.getCustomer(customer.getCustomerID()) == null) {
                return new Result("Customer not exists !", Result.ACCEPTED);

            } else {
                customerDAO.updateCustomer(customer);
                return new Result("Update customer sucessed !", Result.OK);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }

    public Result Get_by_ID(String customerID) {
        Customer customer = null;
        try {
            customer = customerDAO.getCustomer(customerID);
            if (customer == null) {
                return new Result("Customer not exists !", Result.ACCEPTED);

            } else {
                return new Result("Get customer sucessed !", Result.OK, customer);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return new Result("Bad request!", Result.BAD_REQUEST);

        }
    }
}
