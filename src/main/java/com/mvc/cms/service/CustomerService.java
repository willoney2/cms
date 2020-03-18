package com.mvc.cms.service;

import com.mvc.cms.dao.CustomerDAO;
import com.mvc.cms.exception.CustomerNotFoundException;
import com.mvc.cms.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
//import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    /*private int customerIdCount = 1;
    private List<Customer> customerList = new CopyOnWriteArrayList<>();*/

    public Customer addCustomer(Customer customer){
        /*customer.setCustomerID(customerIdCount);
        customerList.add(customer);
        customerIdCount++;
        return customer;*/
        return customerDAO.save(customer);
    }

    public List<Customer> getCustomers(){
        //return customerList;
        return customerDAO.findAll();
    }

    public Customer getCustomer(int customerId){
        /*return customerList
                .stream()
                .filter(c -> c.getCustomerID() == customerId)
                .findFirst()
                .get();*/
        Optional<Customer> optionalCustomer = customerDAO.findById(customerId);

        if(!optionalCustomer.isPresent())
            throw new CustomerNotFoundException("Customer record is not available. . .");
        return optionalCustomer.get();
    }

    public Customer updateCustomer(int customerId, Customer customer){

        customer.setCustomerID(customerId);
        /*customerList
                .stream()
                .forEach(c -> {
                    if (c.getCustomerID() == customerId){
                        c.setCustomerFirstName(customer.getCustomerFirstName());
                        c.setCustomerLastName(customer.getCustomerLastName());
                        c.setCustomerEmail(customer.getCustomerEmail());

                    }
                });

        return customerList
                .stream()
                .filter(c -> c.getCustomerID() == customerId)
                .findFirst()
                .get();*/
        return customerDAO.save(customer);
    }

    public void deleteCustomer(int customerId){

       /* customerList
                .stream()
                .forEach(c -> {
                    if (c.getCustomerID() == customerId){
                                customerList.remove(c);
                                 }
                             }
                );*/
       customerDAO.deleteById(customerId);
    }


}
