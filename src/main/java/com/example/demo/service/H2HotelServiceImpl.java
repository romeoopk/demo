package com.example.demo.service;

import com.example.demo.h2.domain.Hotel;
import com.example.demo.h2.domain.HotelByLetter;
import com.example.demo.h2.domain.HotelByLetterKey;
import com.example.demo.h2.repository.H2HotelByLetterRepository;
import com.example.demo.h2.repository.H2HotelRepository;
import com.example.demo.repository.HotelByLetterRepository;
import com.example.demo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Profile("dev")
public class H2HotelServiceImpl implements HotelService<Hotel,HotelByLetter> {

    public HotelRepository<Hotel> hotelRepository;
    public HotelByLetterRepository<HotelByLetter, HotelByLetterKey> hotelByLetterRepository;

    public H2HotelServiceImpl(HotelRepository hotelRepository,
                                     HotelByLetterRepository hotelByLetterRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelByLetterRepository = hotelByLetterRepository;
    }

    @Override
    public Hotel save(Hotel hotel) {
        if (hotel.getId() == null) {
            hotel.setId(UUID.randomUUID());
        }
        this.hotelRepository.saveHotel(hotel);
        this.hotelByLetterRepository.saveHotelByLetter(new HotelByLetter(hotel));
        return hotel;
    }

    @Override
    public Hotel update(Hotel hotel) {
        Hotel existingHotel = (Hotel) this.hotelRepository.findOne(hotel.getId());
        if (existingHotel != null) {
            this.hotelByLetterRepository.deleteByHotelLetterKey(new HotelByLetter(existingHotel).getHotelByLetterKey());
            this.hotelRepository.delete(hotel.getId());
            this.hotelRepository.saveHotel(hotel);
            this.hotelByLetterRepository.saveHotelByLetter(new HotelByLetter(hotel));
        }
        return hotel;
    }

    @Override
    public Hotel findOne(UUID uuid) {
        return this.hotelRepository.findOne(uuid);
    }

    @Override
    public void delete(UUID uuid) {
        Hotel hotel = this.hotelRepository.findOne(uuid);
        if (hotel != null) {
            this.hotelRepository.delete(uuid);
            this.hotelByLetterRepository.deleteByHotelLetterKey(new HotelByLetter(hotel).getHotelByLetterKey());
        }
    }

    @Override
    public List<HotelByLetter> findHotelsStartingWith(String letter) {
        return this.hotelByLetterRepository.findByFirstLetter(letter);
    }

    @Override
    public List<Hotel> findHotelsInState(String state) {
        return this.hotelRepository.findByState(state);
    }
}