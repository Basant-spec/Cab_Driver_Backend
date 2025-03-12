package CabDriver.CabDriverBackend.requestBody;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCredentialsRequestBody {
     private  String email;
   private String password;

}
