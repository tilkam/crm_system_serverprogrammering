package com.yrgo.client;

import com.yrgo.domain.Customer;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SimpleClient {
    public static void main(String[] args)  {
        ClassPathXmlApplicationContext container = new
                ClassPathXmlApplicationContext("application.xml");
        CustomerManagementService service = container.getBean(CustomerManagementService.class);

        //PRINT ALL CUSTOMERS
        List<Customer> customers = service.getAllCustomers();
        System.out.println("ALL CUSTOMERS:");
        for(Customer customer:customers) {
            System.out.println(customer);
        }
        //ADD NEW CUSTOMER
        Customer testCustomer = new Customer("TEST", "COMPANYTEST", "TESTNOTES");
        service.newCustomer(testCustomer);
        //PRINT ALL CUSTOMERS
        System.out.println("------");
        System.out.println("UPDATED ALL CUSTOMERS:");
        customers = service.getAllCustomers();
        for(Customer customer:customers) {
            System.out.println(customer);
        }
        //FIND SPECIFIC CUSTOMER
        System.out.println("------");
        System.out.println("FIND CUSTOMER BY ID:");
        try{
            System.out.println(service.findCustomerById("TEST"));
        }catch(CustomerNotFoundException e){
            e.getMessage();
        }
        //DELETE CUSTOMER
        System.out.println("------");
        System.out.println("DELETE CUSTOMER:");
        service.deleteCustomer(customers.get(0));
        customers = service.getAllCustomers();
        for(Customer customer:customers) {
            System.out.println(customer);
        }
        System.out.println("------");
        //GET ALL DETAILS FROM SPECIFIC CUSTOMER
        System.out.println("DETAILS FROM SPECIFIC CUSTOMER:");
        try{
            System.out.println(service.getFullCustomerDetail("TEST"));
        }
        catch(CustomerNotFoundException e){
            e.getMessage();
        }
        //UPDATE CUSTOMER THAT ALREADY EXISTS AND PRINT ALL
        testCustomer.setCompanyName("UPDATEDTEST");
        service.updateCustomer(testCustomer);
        System.out.println("------");
        System.out.println("UPDATED CUSTOMER:");
        for(Customer customer:customers) {
            System.out.println(customer);
        }
        //FIND CUSTOMERS WITH A SPECIFIC NAME
        System.out.println("------");
        System.out.println("FIND CUSTOMER BY NAME:");
        List<Customer> customersByName = service.findCustomersByName("UPDATEDTEST");
        for(Customer customer:customersByName) {
            System.out.println(customer);
        }
    }
}
