package com.example.demo.cassandra.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.demo.cassandra.domain.Hotel;
import com.example.demo.repository.HotelRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Profile("test")
public class CassandraHotelRepository implements HotelRepository<Hotel> {

    private final CassandraOperations cassandraTemplate;

    public CassandraHotelRepository(CassandraOperations cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return this.cassandraTemplate.insert(hotel);
    }

    @Override
    public Hotel update(Hotel hotel) {
        return this.cassandraTemplate.update(hotel);
    }

    @Override
    public Hotel findOne(UUID hotelId) {
        return this.cassandraTemplate.selectOneById(Hotel.class, hotelId);
    }

    @Override
    public void delete(UUID hotelId) {
        this.cassandraTemplate.deleteById(Hotel.class, hotelId);
    }

    @Override
    public List<Hotel> findByState(String state) {
        Select select = QueryBuilder.select().from("hotels_by_state");
        select.where(QueryBuilder.eq("state", state));
        return this.cassandraTemplate.select(select, Hotel.class);
    }
}