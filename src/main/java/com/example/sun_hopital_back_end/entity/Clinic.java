package com.example.sun_hopital_back_end.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity(name = "clinic")
public class Clinic {
    @Id
    private int id;

    @Column(columnDefinition = "TEXT")
    private String info;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column(columnDefinition = "JSON")
    private String operateTimes;

    @Column(columnDefinition = "TEXT")
    private String linkMap;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
}

