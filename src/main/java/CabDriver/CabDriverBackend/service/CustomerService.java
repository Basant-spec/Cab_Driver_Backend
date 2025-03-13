package CabDriver.CabDriverBackend.service;

import CabDriver.CabDriverBackend.Repository.CustomerRepository;
import CabDriver.CabDriverBackend.exceptions.UserNotFound;
import CabDriver.CabDriverBackend.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public Customer getCustomerById(Integer CustomerId){

        return customerRepository.findById(CustomerId).orElse(null);
    }
    public void registerAccount(Customer customer){

        customerRepository.save(customer);
    }

    public String autheticateCustomer(String email,String password){
        Customer customer= customerRepository.findByEmail(email);
       if(customer==null){
           throw new UserNotFound(String.format("AppUser with email %s does not exist",email));
       }
        String originalPassword=customer.getPassword();
        if(originalPassword.equals(password)){
            return "Authentication Successfull";
        }

        return "Authentication fail";

    }


    public Customer getCustomerByEmail(String emailId){

       return customerRepository.findByEmail(emailId);

    }


}
