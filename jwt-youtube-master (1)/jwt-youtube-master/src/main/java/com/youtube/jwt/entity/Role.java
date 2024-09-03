package com.youtube.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role
{

    @Id
    private String roleName;
    private String roleDescription;


}

