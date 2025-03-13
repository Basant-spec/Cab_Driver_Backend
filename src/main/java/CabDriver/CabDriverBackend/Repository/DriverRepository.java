package CabDriver.CabDriverBackend.Repository;

import CabDriver.CabDriverBackend.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
  public Driver findByEmailID(String emailId);





}
