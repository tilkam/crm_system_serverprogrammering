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
        CustomerManagementService service = container.getBean(CustomerManagementService.class);
        List<Customer> customers = service.getAllCustomers();
        for(Customer customer:customers) {
            System.out.println(customer);
        }
        service.newCustomer(new Customer("TEST", "COMPANYTEST", "TESTNOTES"));

        System.out.println("------");
        customers = service.getAllCustomers();
        for(Customer customer:customers) {
            System.out.println(customer);
        }

        System.out.println("------");
        try{
            System.out.println(service.findCustomerById("TEST"));
        }catch(CustomerNotFoundException e){
            e.getMessage();
        }

        System.out.println("------");
        service.deleteCustomer(customers.get(0));
        customers = service.getAllCustomers();
        for(Customer customer:customers) {
            System.out.println(customer);
        }
        System.out.println("------");
        customers = service.findCustomersByName("COMPANYTEST");
        for(Customer customer:customers) {
            System.out.println(customer);
        }
        System.out.println("------");

        try{
            System.out.println(service.getFullCustomerDetail("TEST"));
        }
        catch(CustomerNotFoundException e){
            e.getMessage();
        }
    }
}
