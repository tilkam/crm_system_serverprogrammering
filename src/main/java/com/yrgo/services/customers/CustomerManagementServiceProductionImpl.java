package com.yrgo.services.customers;

import com.yrgo.dataaccess.CustomerDao;
import com.yrgo.dataaccess.RecordNotFoundException;
import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("customerService")
@Transactional(propagation= Propagation.REQUIRES_NEW)
public class CustomerManagementServiceProductionImpl implements CustomerManagementService {
    @Autowired
    private CustomerDao customerDao;

    @Override
    public void newCustomer(Customer newCustomer) {
        try {
            customerDao.create(newCustomer);
        } catch (Exception e) {
            System.err.println("CUSTOMER with id " + newCustomer.getCustomerId() + " already exists");
        }
    }

    @Override
    public void updateCustomer(Customer changedCustomer) {
        try {
            customerDao.update(changedCustomer);
        } catch (RecordNotFoundException e) {
            System.err.println("Something went wrong when trying to UPDATE customer");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCustomer(Customer oldCustomer) {
        try {
            customerDao.delete(oldCustomer);
        } catch (RecordNotFoundException e) {
            System.err.println("Something went wrong when trying to DELETE customer");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findCustomerById(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getById(customerId);
        } catch (RecordNotFoundException e) {
            System.err.println("Error when trying to find CUSTOMER by ID");
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public List<Customer> findCustomersByName(String name) {
        try {
            return customerDao.getByName(name);
        } catch (Exception e) {
            System.err.println("Error when trying to find CUSTOMER by name");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }
    @Override
    @Transactional
    public Customer getFullCustomerDetail(String customerId) throws CustomerNotFoundException {
        try {
            return customerDao.getFullCustomerDetail(customerId);
        } catch (RecordNotFoundException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    @Transactional
    public void recordCall(String customerId, Call callDetails) throws CustomerNotFoundException {
        try {
            customerDao.addCall(callDetails, customerId);
        } catch (RecordNotFoundException e) {
            System.err.println("Error when adding call");
            throw new CustomerNotFoundException();

        }
    }
}
