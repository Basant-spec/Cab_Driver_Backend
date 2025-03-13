package CabDriver.CabDriverBackend.Controller;

import CabDriver.CabDriverBackend.ResponseBody.BookingResponseBody;
import CabDriver.CabDriverBackend.exceptions.InvalidOperationException;
import CabDriver.CabDriverBackend.exceptions.ResourceDoesNotExistException;
import CabDriver.CabDriverBackend.exceptions.UserNotFound;
import CabDriver.CabDriverBackend.models.Booking;
import CabDriver.CabDriverBackend.requestBody.CustomerBookingRequestBody;
import CabDriver.CabDriverBackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

 @PutMapping("/update")

    public ResponseEntity updateBookingStatus(@RequestParam String opr,
                                              @RequestParam String email,
                                              @RequestParam Integer bookingID){
  try{
      String response=bookingService.updateBooking(opr,email,bookingID);
      return new ResponseEntity(response, HttpStatus.CREATED);
  }catch(UserNotFound userNotFound){
      return new ResponseEntity(userNotFound.getMessage(),HttpStatus.NOT_FOUND);
  }catch(InvalidOperationException invalidOperationException){
      return new ResponseEntity(invalidOperationException.getMessage(),HttpStatus.NOT_ACCEPTABLE);
  }catch(ResourceDoesNotExistException resourceDoesNotExistException){
      return new ResponseEntity(resourceDoesNotExistException.getMessage(),HttpStatus.NOT_FOUND);
  }


 }












}
