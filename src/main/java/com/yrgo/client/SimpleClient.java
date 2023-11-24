package com.yrgo.client;

import com.yrgo.dataaccess.CustomerDaoJpaImpl;
import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;
import com.yrgo.services.calls.CallHandlingService;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerManagementServiceProductionImpl;
import com.yrgo.services.customers.CustomerNotFoundException;
import com.yrgo.services.diary.DiaryManagementService;
import org.hibernate.internal.build.AllowSysOut;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

public class SimpleClient {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext container = new
                ClassPathXmlApplicationContext("application-annotation.xml");
        CustomerManagementService service = container.getBean(CustomerManagementService.class);
        Customer newCustomer = new Customer("1", "SEB", "notes for SEB");
        Customer newCustomer2 = new Customer("2", "H&M", "notes for H&M");
        service.newCustomer(newCustomer);
        service.newCustomer(newCustomer2);
        System.out.println("FIND CUSTOMER BY ID:");
        try {
            Customer findById = service.findCustomerById("1");
            System.out.println(findById);
        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }
        newCustomer2.setCompanyName("Hennes & Mauritz");
        service.updateCustomer(newCustomer2);

        System.out.println("UPDATED CUSTOMER:");
        System.out.println(newCustomer2.getCompanyName());

        System.out.println("FIND CUSTOMER BY NAME:");
        List<Customer> c = service.findCustomersByName("Hennes & Mauritz");
        c.forEach(System.out::println);

        System.out.println("FULL CUSTOMER DETAILS: ");
        Call call = new Call(newCustomer.getNotes(), LocalDateTime.now());

        try {
            service.recordCall(newCustomer2.getCustomerId(), call);
            Customer details =  service.getFullCustomerDetail("2");
            System.out.println(details.getCalls());
            details.getCalls().forEach(System.out::println);

        } catch (CustomerNotFoundException e) {
            throw new RuntimeException(e);
        }

        //PRINT ALL CUSTOMERS
        List<Customer> customers = service.getAllCustomers();
        System.out.println("ALL CUSTOMERS:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        container.close();
    }
}
