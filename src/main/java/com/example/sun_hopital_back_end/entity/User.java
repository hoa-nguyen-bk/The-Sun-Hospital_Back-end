package com.example.sun_hopital_back_end.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String medicalHistory;

    private String avatar;

    @Column(columnDefinition = "JSON")
    private String favourites;


    private String bloodGroup;
    private String password;
    private Integer yearsExperience;
    private String level;
    private String status;

    @Column(columnDefinition = "TEXT")
    private String languages;

    @Column(columnDefinition = "TEXT")
    private String awards;

    @Column(columnDefinition = "TEXT")
    private String description;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
