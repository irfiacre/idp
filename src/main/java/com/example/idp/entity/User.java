package com.example.idp.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column()
    private String role = "user";

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column()
    private Boolean verified = false;

    @Column(nullable = false)
    private Integer verificationCode;

}



