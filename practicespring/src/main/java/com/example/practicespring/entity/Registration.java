package com.example.practicespring.entity;


//import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Registration
{

     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

     @Column(name="full_name as per Aadhar")
     private String fullName;

     private String email;

     private String mobileNumber;

     private String address;

      private String status;

      @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
      private List<RegisterDocuments> registerDocuments;
}
