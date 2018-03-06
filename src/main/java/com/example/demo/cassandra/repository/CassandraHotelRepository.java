package com.example.demo.cassandra.repository;

import com.example.demo.cassandra.domain.Hotel;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CassandraHotelRepository extends CassandraRepository<Hotel> {}