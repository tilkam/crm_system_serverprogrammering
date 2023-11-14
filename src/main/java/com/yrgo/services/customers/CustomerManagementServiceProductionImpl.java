package com.yrgo.services.customers;

import com.yrgo.dataaccess.CustomerDao;
import com.yrgo.dataaccess.RecordNotFoundException;
import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;

import java.util.List;

public class CustomerManagementServiceProductionImpl implements CustomerManagementService {

    private CustomerDao customerDao;

    public CustomerManagementServiceProductionImpl(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void newCustomer(Customer newCustomer) {
        try{
            customerDao.create(newCustomer);
        }catch(Exception e){
            System.out.println("CUSTOMER with id " + newCustomer.getCustomerId() + " already exists");
        }

    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        try {
            customerDao.update(changedCustomer);
        } catch (RecordNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {
        try {
            customerDao.delete(oldCustomer);
        } catch (RecordNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findCustomerById(String customerId) {
        try {
            return customerDao.getById(customerId);
        } catch (RecordNotFoundException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        return customerDao.getByName(name);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getFullCustomerDetail(customerId);
        } catch (RecordNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
    }
}
