package com.example.sun_hopital_back_end.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role_title;
    private String role_description;

    @OneToMany(mappedBy = "role")
    private List<Patient> patients;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_title() {
        return role_title;
    }

    public void setRole_title(String role_title) {
        this.role_title = role_title;
    }

    public String getRole_desription() {
        return role_description;
    }

    public void setRole_desription(String role_desription) {
        this.role_description = role_desription;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_title='" + role_title + '\'' +
                ", role_desription='" + role_description + '\'' +
                ", patients=" + patients +
                '}';
    }
}
