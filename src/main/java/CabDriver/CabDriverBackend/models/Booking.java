package CabDriver.CabDriverBackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
private Integer id;
@ManyToOne
private  Customer customer;
private  Integer billAmount;
 private  String status;
 private String feedback;
 @ManyToOne
 Driver driver;





}
