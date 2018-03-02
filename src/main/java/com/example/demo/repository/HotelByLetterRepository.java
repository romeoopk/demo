package com.example.demo.repository;

import java.util.List;

public interface HotelByLetterRepository<T, K> {
    List<T> findByFirstLetter(String letter);
    T saveHotelByLetter(T hotelByLetterKey);
    void deleteByHotelLetterKey(K hotelByLetterKey);
}