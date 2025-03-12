package CabDriver.CabDriverBackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
@ManyToOne
private  Customer customer;
private  Integer billAmount;
 private  String status;
 private String feedback;
 @ManyToOne
 private Driver driver;
private String startingLocation;
private String endingLocation;





}
