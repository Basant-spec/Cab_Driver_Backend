package CabDriver.CabDriverBackend.service;


import CabDriver.CabDriverBackend.Repository.DriverRepository;
import CabDriver.CabDriverBackend.models.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    DriverRepository driverRepository;
    public void registerDriver(Driver driver){

    driverRepository.save(driver);


    }
    public Driver getDriverByEmail(String emailId){

        return driverRepository.findByEmailID(emailId);
    }



}
