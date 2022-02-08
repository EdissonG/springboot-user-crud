package com.springboot.users.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column ( nullable = false )
    private String name;
    @Column ( nullable = false )
    private String lastname;
    @Column ( nullable = false, unique = true )
    private String email;
    @Column ( nullable = false )
    private String password;

}
