package com.yrgo.services.customers;

import com.yrgo.dataaccess.CustomerDao;
import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;

import java.util.List;

public class CustomerManagementServiceProductionImpl implements CustomerManagementService{

    private CustomerDao customerDao;

    public CustomerManagementServiceProductionImpl(CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    @Override
    public void newCustomer(Customer newCustomer) {

    }

    @Override
    public void updateCustomer(Customer changedCustomer) {

    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {

    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {

    }
}
