package CabDriver.CabDriverBackend.Repository;

import CabDriver.CabDriverBackend.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

     public Customer findByEmail(String email);



}
