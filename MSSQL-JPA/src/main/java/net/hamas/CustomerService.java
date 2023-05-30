package net.hamas;
 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public List<Customer> viewCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return customers;
    }

    public void deleteCustomer(int id) {
        customerRepo.deleteById(id);
    }

    public void addCustomer(Customer customer) {
        Long id = customerRepo.count() + 1;
        customer.setId(id);
        customerRepo.save(customer);
    }
    
    public void editCustomer(Customer customer) {
       customerRepo.save(customer);
  
    }

    public Customer viewCustomer(int id) {
        Optional<Customer> customer = customerRepo.findById(id);
        return customer.get();
    }

}
