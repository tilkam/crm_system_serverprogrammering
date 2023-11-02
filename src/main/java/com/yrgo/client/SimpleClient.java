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
        container.close();
    }
}
