package com.example.demo.cassandra.domain;

import com.example.demo.marker.TableMarker;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import java.util.UUID;

@Table("hotels")
public class Hotel implements Serializable, TableMarker {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    @Column(value = "id")
    private UUID id;
    @Column(value = "name")
    private String name;
    @Column(value = "address")
    private String address;
    @Column(value = "state")
    private String state;
    @Column(value = "zip")
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