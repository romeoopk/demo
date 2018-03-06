package com.example.demo.controller;


import com.example.demo.cassandra.domain.Hotel;
import com.example.demo.service.HotelService;
import com.example.demo.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.example.demo.util.Utility.*;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@RestController
@RequestMapping("/hotels")

class HotelController extends BaseController {

    @Autowired
    private HotelService hotelService;

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity get(@PathVariable("id") UUID uuid) {
        Object obj = this.hotelService.findOne(uuid);
        return new ResponseEntity(obj != null ? obj : "No record found!", obj != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    public @ResponseBody ResponseEntity save(@RequestBody com.example.demo.cassandra.domain.Hotel hotel) throws IOException {
        return new ResponseEntity(this.hotelService.save(hotel), HttpStatus.CREATED);

    }

    @PutMapping(consumes = "application/json")
    public @ResponseBody ResponseEntity update(@RequestBody Hotel hotel) {
        Object obj = this.hotelService.update(hotel);
        return new ResponseEntity<>(obj, isFalse(obj instanceof HttpStatus) ? HttpStatus.OK : (HttpStatus) obj);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID uuid) {
        this.hotelService.delete(uuid);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }

}
