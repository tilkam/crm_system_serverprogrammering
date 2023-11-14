package com.yrgo.client;

import com.yrgo.domain.Customer;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SimpleClient {
    public static void main(String[] args)  {
        ClassPathXmlApplicationContext container = new
                ClassPathXmlApplicationContext("application.xml");
        CustomerManagementService service = container.getBean("customerService", CustomerManagementService.class);
        Customer newCustomer = new Customer("1", "compName", "someNotes");
        Customer newCustomer2 = new Customer("2", "secondCompany", "someNotes");
        service.newCustomer(newCustomer);
        service.newCustomer(newCustomer2);
        System.out.println("FIND CUSTOMER BY ID:");
        try{
            Customer findById = service.findCustomerById("1");
            System.out.println(findById);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
        newCustomer2.setCompanyName("UPDATEDNAME");
        service.updateCustomer(newCustomer2);
        System.out.println("UPDATED CUSTOMER:");
        System.out.println(newCustomer2);
        System.out.println("FIND CUSTOMER BY NAME:");
        List<Customer> c = service.findCustomersByName(newCustomer2.getCompanyName());
        c.forEach(System.out::println);

        //PRINT ALL CUSTOMERS
        List<Customer> customers = service.getAllCustomers();
        System.out.println("ALL CUSTOMERS:");
        for(Customer customer:customers) {
            System.out.println(customer);
        }
        container.close();
    }
}
