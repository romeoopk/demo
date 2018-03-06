package com.example.demo.service;

import java.util.UUID;

public interface HotelService<T> {
    T save(T hotel);
    Object update(T hotel);
    T findOne(UUID uuid);
    void delete(UUID uuid);
}
