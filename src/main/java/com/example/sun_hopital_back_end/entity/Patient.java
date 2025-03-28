package com.example.sun_hopital_back_end.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Date;


@Data
@Entity(name = "patient")

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String first_name;
    private Date date_of_birth;
    private String address;
    private String contact;
    private String medical_history;
    private String avatar;
    private String favourites;
    private String last_name;
    private String phone;
    private String email;
    private String blood_group;
    private String note;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id")

    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMedical_history() {
        return medical_history;
    }

    public void setMedical_history(String medical_history) {
        this.medical_history = medical_history;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFavourites() {
        return favourites;
    }

    public void setFavourites(String favourites) {
        this.favourites = favourites;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", medical_history='" + medical_history + '\'' +
                ", avatar='" + avatar + '\'' +
                ", favourites='" + favourites + '\'' +
                ", last_name='" + last_name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", blood_group='" + blood_group + '\'' +
                ", note='" + note + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
