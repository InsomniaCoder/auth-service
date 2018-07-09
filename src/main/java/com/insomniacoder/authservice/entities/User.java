package com.insomniacoder.authservice.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    private String password;
    private String role;

}
