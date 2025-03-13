package CabDriver.CabDriverBackend.service;


import CabDriver.CabDriverBackend.Repository.BookingRepository;
import CabDriver.CabDriverBackend.ResponseBody.BookingResponseBody;
import CabDriver.CabDriverBackend.exceptions.InvalidOperationException;
import CabDriver.CabDriverBackend.exceptions.ResourceDoesNotExistException;
import CabDriver.CabDriverBackend.exceptions.UserNotFound;
import CabDriver.CabDriverBackend.models.AppUser;
import CabDriver.CabDriverBackend.models.Booking;
import CabDriver.CabDriverBackend.models.Customer;
import CabDriver.CabDriverBackend.models.Driver;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingService {
    @Autowired
    CustomerService customerService;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    DriverService driverService;
public void handleCustomerRequest(String startingLocation, String endingLocation, Integer customerId){

    Customer customer=customerService.getCustomerById(customerId);

    if(customer==null){
        throw new UserNotFound(String.format("user with id  %s does not exist",customerId));
    }
    Booking booking = new Booking();
    booking.setCustomer(customer);
    booking.setStatus("Draft");
    booking.setBillAmount(0);
    booking.setStartingLocation(startingLocation);
    booking.setEndingLocation(endingLocation);
    bookingRepository.save(booking);
}

   public List<BookingResponseBody> getBookingByStatus(String state) {

       List<Booking> bookingList = bookingRepository.getBookingByStatus(state);
       List<BookingResponseBody> bookingResponseBodyList = new ArrayList<>();
       for (Booking booking : bookingList) {
           BookingResponseBody bookingResponseBody = new BookingResponseBody();

           bookingResponseBody.setBookingID(booking.getId());
           bookingResponseBody.setCustomerID(booking.getCustomer().getId());
           bookingResponseBody.setStartingLocation(booking.getStartingLocation());
           bookingResponseBody.setEndingLocation(booking.getEndingLocation());
           bookingResponseBody.setCustomerName(booking.getCustomer().getFirstName());
           bookingResponseBody.setBillingAmount(booking.getBillAmount());
           bookingResponseBody.setStatus(booking.getStatus());

           bookingResponseBodyList.add(bookingResponseBody);


       }
          return bookingResponseBodyList;

       }

       public String updateBooking(String operation, String email,Integer bookingId){

            Customer customer=customerService.getCustomerByEmail(email);
            String userType="";
            Driver driver=driverService.getDriverByEmail(email);
           Integer userId=-1;
            AppUser user=null;
          if(customer!=null){
              userId= customer.getId();
              userType="CUSTOMER";
              user=customer;
          }else if(driver!=null){
              userId=driver.getId();
              userType="DRIVER";
              user=driver;
          }else{

              throw new UserNotFound(String.format("User with id %s does not exist",userId));
          }
           Booking booking =bookingRepository.findById(bookingId).orElse(null);
           if(booking==null){
               throw new ResourceDoesNotExistException(String.format("Booking with id %s does not exist in system",bookingId));
           }
          if(operation.equals("ACCEPT")){

              if(userType.equals("CUSTOMER")){
                  throw new InvalidOperationException(String.format("Customer can not accept rides"));
              }



           booking.setDriver(driver);
           booking.setStatus("ACCEPTED");
           booking.setBillAmount(100);
           bookingRepository.save(booking);
           return String.format("Driver with id %d Accepted the booking ",userId);
          }else if(operation.equals("CANCEL")) {
              if (userType.equals("CUSTOMER")) {
                  if (booking.getCustomer().getId() == userId) {
                      booking.setStatus("CANCELED");
                      bookingRepository.save(booking);
                      return String.format("Customer with id %d cancelled ride with booking id %s", userId, bookingId);
                  } else {
                      throw new InvalidOperationException(String.format("Customer with %d is not allowed to cancel booking with id %d",
                              userId, bookingId));
                  }

              } else if (userType.equals("DRIVER")) {
                  if (booking.getDriver().getId() == userId) {
                      booking.setStatus("CANCELED");
                      bookingRepository.save(booking);
                      return String.format("Driver with id %d canceled the booking ", userId);

                  } else {
                      throw new InvalidOperationException(String.format("Driver with %d is not allowed to cancel booking with id %d",
                              userId, bookingId));
                  }


              }
          }

           return "";


       }












   }





