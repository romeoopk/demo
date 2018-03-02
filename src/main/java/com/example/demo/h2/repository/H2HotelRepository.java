package com.example.demo.h2.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.demo.h2.domain.Hotel;
import com.example.demo.h2.domain.HotelByLetter;
import com.example.demo.h2.domain.HotelByLetterKey;
import com.example.demo.repository.HotelRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Profile("dev")
public abstract class H2HotelRepository implements CrudRepository<Hotel, String>, HotelRepository<Hotel> {

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return save(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return save(hotel);
    }

    @Override
    @Query("SELECT * FROM HOTELS WHERE id = :hotelId")
    abstract public Hotel findOne(UUID hotelId);

    @Override
    abstract public void delete(UUID hotelId);

    @Override
    abstract public List<Hotel> findByState(String state);
}