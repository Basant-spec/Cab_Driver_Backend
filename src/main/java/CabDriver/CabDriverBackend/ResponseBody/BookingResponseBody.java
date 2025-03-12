package CabDriver.CabDriverBackend.ResponseBody;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingResponseBody {
    private Integer bookingID;
    private Integer customerID;
    private String customerName;
    private String startingLocation;
    private String endingLocation;
    private Integer billingAmount;
    private String status;






}
