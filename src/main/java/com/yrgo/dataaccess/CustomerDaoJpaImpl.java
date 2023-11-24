package com.yrgo.dataaccess;

import com.yrgo.domain.Call;
import com.yrgo.domain.Customer;
import com.yrgo.services.customers.CustomerNotFoundException;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerDaoJpaImpl implements CustomerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Override
    public Customer getById(String customerId) throws RecordNotFoundException, CustomerNotFoundException {
        try {
            return (Customer) em.createQuery("select customer from Customer as customer where customer.customerId=:customerId").setParameter("customerId", customerId).getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public List<Customer> getByName(String name) throws CustomerNotFoundException {
        try {
            return em.createQuery("select customer from Customer as customer where customer.companyName=:name", Customer.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (NoResultException e) {
            throw new CustomerNotFoundException();
        }
    }

    @Override
    public void update(Customer customerToUpdate)  {
            em.merge(customerToUpdate);
    }


    @Override
    public void delete(Customer oldCustomer) throws RecordNotFoundException {
        Customer customer = em.find(Customer.class, oldCustomer.getCustomerId());
        em.remove(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return em.createQuery("select customer from Customer as customer", Customer.class).getResultList();

    }

    @Override
    public Customer getFullCustomerDetail(String customerId) throws RecordNotFoundException {
        TypedQuery<Customer> customer = em.createQuery("select customer from Customer as customer " +
                "left join fetch customer.calls where customer.customerId=:customerId", Customer.class).setParameter("customerId", customerId);
        if (customer == null) {
            throw new RecordNotFoundException();
        }

        return customer.getSingleResult();
    }

    @Override
    public void addCall(Call newCall, String customerId) throws RecordNotFoundException {
        Customer customer = em.find(Customer.class, customerId);

        if (customer == null) {
            throw new RecordNotFoundException();
        }
        customer.addCall(newCall);
        newCall.setCustomerId(customerId);
        customer.getCalls().add(newCall);

        em.persist(newCall);
        em.merge(customer);

    }
}
