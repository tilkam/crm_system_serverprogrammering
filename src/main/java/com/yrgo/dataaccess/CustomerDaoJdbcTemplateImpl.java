package com.yrgo.dataaccess;

import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDaoJdbcTemplateImpl implements CustomerDao {
    private static final String INSERT_CUSTOMER_SQL = "insert into CUSTOMER (CUSTOMER_ID, COMPANY_NAME, NOTES) values (?, ?, ?) ";
    private static final String CREATE_TABLE_SQL = "create table CUSTOMER(CUSTOMER_ID VARCHAR(5), COMPANY_NAME VARCHAR(50), NOTES VARCHAR(50))";
    private static final String GET_ALL_CUSTOMERS_SQL = "select * from CUSTOMER";
    private JdbcTemplate jdbcTemplate;

    public CustomerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void createTables() {
        try {
            jdbcTemplate.update(CREATE_TABLE_SQL);
        } catch (Exception e) {
            System.err.println("Table already exists");
        }
    }

    @Override
    public void create(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER_SQL, customer.getCustomerId(), customer.getCompanyName(), customer.getNotes());
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
        return jdbcTemplate.query(GET_ALL_CUSTOMERS_SQL, new BookMapper());

    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
        return null;
    }

    @Override
    public void addCall(Call newCall, String customerId) throws RecordNotFoundException {

    }
}

class BookMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNumber) throws SQLException {
        String id = rs.getString(1);
        String companyName = rs.getString(2);
        String notes = rs.getString(3);

        Customer customer = new Customer(id, companyName, notes);
        return customer;
    }
}
