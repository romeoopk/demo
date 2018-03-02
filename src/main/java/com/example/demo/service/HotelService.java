package com.example.demo.service;

import com.example.demo.cassandra.domain.Hotel;
import com.example.demo.cassandra.domain.HotelByLetter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface HotelService<T, K> {
    T save(T hotel);
    T update(T hotel);
    T findOne(UUID uuid);
    void delete(UUID uuid);

    List<K> findHotelsStartingWith(String letter);
    List<T> findHotelsInState(String state);
}
