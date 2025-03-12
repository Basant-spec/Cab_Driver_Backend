package CabDriver.CabDriverBackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
   private Integer id;
    @Column(unique=true,nullable=false)
    private  String licenseID;
    private  String vehicleType;
    private   String firstName;
    private   String lastName;
    @Column(unique=true,length=10,nullable=false)
    private   Long phoneNumber;
    @Column(unique=true,nullable=false)
    private  String emailID;
    private Double rating;
    private Integer totalRideServed;
    @OneToMany(mappedBy="driver")
    private List<Booking> booking;




}
