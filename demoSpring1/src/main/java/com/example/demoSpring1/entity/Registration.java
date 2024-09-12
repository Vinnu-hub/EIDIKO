package com.example.demoSpring1.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_registration")
public class Registration
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="As_per_aadhar")
    private String fullname;

    private String email;

    private String password;

    private String contact;

    private String aadress;

    private String status="pending";


    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<Document> documents;
}



























