package CabDriver.CabDriverBackend.requestBody;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerBookingRequestBody {
private String startingLocation;
private String endingLocation;

}
