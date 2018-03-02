package com.example.demo.repository;

import com.example.demo.cassandra.domain.Hotel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface HotelRepository<T>  {
    T saveHotel(T hotel);
    T update(T hotel);
    T findOne(UUID hotelId);
    void delete(UUID hotelId);
    List<T> findByState(String state);
}