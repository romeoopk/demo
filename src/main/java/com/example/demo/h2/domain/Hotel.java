package com.example.demo.h2.domain;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import javax.persistence.*;


import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "hotels")
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @Column(name="state")
    private String state;

    @Column(name="zip")
    private String zip;

    public Hotel() {
    }

    public Hotel(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getZip() {
        return this.zip;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}