package CabDriver.CabDriverBackend.models;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
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
public class Customer {
    @Id
  //  @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer age;
    private String password;
    private String firstName;
    private String LastName;
    @Column(unique=true,nullable = false)
    private String email;
    @Column(unique=true,nullable = false)
    private Long phoneNumber;
    private String address;
    @OneToMany(mappedBy="customer")
    private List<Booking> bookings;









}
