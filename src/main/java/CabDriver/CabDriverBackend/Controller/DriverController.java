package CabDriver.CabDriverBackend.Controller;


import CabDriver.CabDriverBackend.models.Driver;
import CabDriver.CabDriverBackend.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {
    @Autowired
    DriverService driverService;
   @PostMapping("/api/driver/register")
     public String createAccount(@RequestBody Driver driver){

       driverService.registerDriver(driver);;

       return "Driver Account Created Successfully";





    }




}
