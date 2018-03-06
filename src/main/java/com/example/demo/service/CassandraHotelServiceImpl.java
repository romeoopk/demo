package com.example.demo.service;

import com.example.demo.cassandra.domain.Hotel;
import com.example.demo.cassandra.repository.CassandraHotelRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.data.cassandra.repository.support.BasicMapId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class CassandraHotelServiceImpl implements HotelService<Hotel> {

    @Autowired
    private CassandraHotelRepository hotelRepository;

    @Override
    public Hotel save(Hotel hotel) {
        if (hotel.getId() == null) {
            hotel.setId(UUID.randomUUID());
        }
        this.hotelRepository.save(hotel);
        return hotel;
    }

    @Override
    public Object update(Hotel hotel) {
        if(hotel.getId() == null)
            return HttpStatus.BAD_REQUEST;
        Hotel existingHotel = this.hotelRepository.findOne(BasicMapId.id("id", hotel.getId()));
        if (existingHotel != null) {
            this.hotelRepository.delete(BasicMapId.id("id", hotel.getId()));
            hotel = this.hotelRepository.save(hotel);
        }
        else
            return HttpStatus.NOT_FOUND;

        return hotel;
    }

    @Override
    public Hotel findOne(UUID uuid) {
        return this.hotelRepository.findOne(BasicMapId.id("id", uuid));
    }

    @Override
    public void delete(UUID uuid) {

        Hotel hotel = this.hotelRepository.findOne(BasicMapId.id("id", uuid));
        if (hotel != null) {
            this.hotelRepository.delete(BasicMapId.id("id", uuid));
            //this.hotelByLetterRepository.deleteByHotelLetterKey(new HotelByLetter(hotel).getHotelByLetterKey());
        }
    }

}