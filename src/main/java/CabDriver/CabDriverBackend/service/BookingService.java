package CabDriver.CabDriverBackend.service;


import CabDriver.CabDriverBackend.Repository.BookingRepository;
import CabDriver.CabDriverBackend.ResponseBody.BookingResponseBody;
import CabDriver.CabDriverBackend.exceptions.UserNotFound;
import CabDriver.CabDriverBackend.models.Booking;
import CabDriver.CabDriverBackend.models.Customer;
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

   }





