package com.yrgo.dataaccess;

import java.util.List;

import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;

public interface CustomerDao {

	public void create(Customer customer);

	public Customer getById(String customerId) throws RecordNotFoundException;

	public List<Customer> getByName(String name);

	public void update(Customer customerToUpdate) throws RecordNotFoundException;

	public void delete(Customer oldCustomer) throws RecordNotFoundException;

	public List<Customer> getAllCustomers();


	public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException;


	public void addCall (Call newCall, String customerId) throws RecordNotFoundException;
}
