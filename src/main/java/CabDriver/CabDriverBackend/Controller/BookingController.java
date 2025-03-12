package CabDriver.CabDriverBackend.Controller;

import CabDriver.CabDriverBackend.ResponseBody.BookingResponseBody;
import CabDriver.CabDriverBackend.exceptions.UserNotFound;
import CabDriver.CabDriverBackend.models.Booking;
import CabDriver.CabDriverBackend.requestBody.CustomerBookingRequestBody;
import CabDriver.CabDriverBackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;
 @PostMapping("/request")

 public String CreateCustomerBooking(@RequestBody CustomerBookingRequestBody customerBookingRequestBody,
                                      @RequestParam Integer CustomerId){
   String startingLocation= customerBookingRequestBody.getStartingLocation();
   String endingLocation = customerBookingRequestBody.getEndingLocation();

    try{
         bookingService.handleCustomerRequest(startingLocation,endingLocation,CustomerId);
         return "Wating for driver to accept";
    }catch(UserNotFound userNotFound){
     return userNotFound.getMessage();
    }

 }

 @GetMapping("/all")
    public List<BookingResponseBody> getBookingByStatus(@RequestParam String state){

     return bookingService.getBookingByStatus(state);

 }












}
