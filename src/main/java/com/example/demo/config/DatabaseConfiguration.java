package com.example.demo.config;

import com.example.demo.h2.repository.H2HotelRepository;
import com.example.demo.repository.HotelByLetterRepository;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.CassandraHotelServiceImpl;
import com.example.demo.service.H2HotelServiceImpl;
import com.example.demo.service.HotelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DatabaseConfiguration {

}
