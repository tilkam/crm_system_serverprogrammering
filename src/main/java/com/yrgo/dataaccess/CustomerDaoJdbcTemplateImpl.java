package com.yrgo.dataaccess;

import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;

import java.util.List;

public class CustomerDaoJdbcTemplateImpl implements CustomerDao{
    @Override
    public void create(Customer customer) {

    }

    @Override
    public Customer getById(String customerId) throws RecordNotFoundException {
        return null;
    }

    @Override
    public List<Customer> getByName(String name) {
        return null;
    }

    @Override
    public void update(Customer customerToUpdate) throws RecordNotFoundException {

    }

    @Override
    public void delete(Customer oldCustomer) throws RecordNotFoundException {

    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
        return null;
    }

    @Override
    public void addCall(Call newCall, String customerId) throws RecordNotFoundException {

    }
}
