package CabDriver.CabDriverBackend.Controller;


import CabDriver.CabDriverBackend.exceptions.UserNotFound;
import CabDriver.CabDriverBackend.models.Customer;
import CabDriver.CabDriverBackend.requestBody.UserCredentialsRequestBody;
import CabDriver.CabDriverBackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/api/customer/register")
    public String createAccount(@RequestBody Customer customer){

     customerService.registerAccount(customer);
     return "Account created Successfully";

    }

    @GetMapping("/api/customer/authenticate")
    public String loginCustomer(@RequestBody UserCredentialsRequestBody userCredentialsRequestBody) {
        String email = userCredentialsRequestBody.getEmail();
        String password = userCredentialsRequestBody.getPassword();
        try {
            String AuthenticationDetails = customerService.autheticateCustomer(email, password);
            return AuthenticationDetails;
        } catch (UserNotFound userNotFound) {
                return userNotFound.getMessage();
        }
    }
















}
