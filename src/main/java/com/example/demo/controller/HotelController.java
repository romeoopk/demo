package com.example.demo.controller;

import com.example.demo.h2.domain.Hotel;
import com.example.demo.h2.domain.HotelByLetter;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")

class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping(path = "/{id}")
    public <T> T get(@PathVariable("id") UUID uuid) {
        return (T)this.hotelService.findOne(uuid);
    }

    @PostMapping
    public <T extends com.example.demo.h2.domain.Hotel> ResponseEntity save(@RequestBody T hotel) {
        return new ResponseEntity(this.hotelService.save(hotel), HttpStatus.CREATED);
    }

    @PutMapping
    public <T> ResponseEntity<T> update(@RequestBody Hotel hotel) {
        T savedHotel = (T)this.hotelService.update(hotel);
        return new ResponseEntity<>(savedHotel, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID uuid) {
        this.hotelService.delete(uuid);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/startingwith/{letter}")
    public <T> List<T> findHotelsWithLetter(@PathVariable("letter") String letter) {
        return this.hotelService.findHotelsStartingWith(letter);
    }

    @GetMapping(path = "/fromstate/{state}")
    public <T> List<T> findHotelsInState(@PathVariable("state") String state) {
        return this.hotelService.findHotelsInState(state);
    }
}
