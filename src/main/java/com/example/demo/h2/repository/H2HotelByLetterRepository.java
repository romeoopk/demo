package com.example.demo.h2.repository;

import com.example.demo.h2.domain.HotelByLetter;
import com.example.demo.h2.domain.HotelByLetterKey;
import com.example.demo.repository.HotelByLetterRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("dev")
public abstract class H2HotelByLetterRepository implements CrudRepository<HotelByLetter, HotelByLetterKey>, HotelByLetterRepository<HotelByLetter, HotelByLetterKey> {

    @Override
    @Query("SELECT * FROM HOTELS_BY_LETTER WHERE first_letter = :letter")
    abstract public List<HotelByLetter> findByFirstLetter(String letter);

    public HotelByLetter saveHotelByLetter(HotelByLetter hotelByLetter) {
        return save(hotelByLetter);
    }

    @Override
    public void deleteByHotelLetterKey(HotelByLetterKey hotelByLetterKey) {
        delete(hotelByLetterKey);
    }

}
