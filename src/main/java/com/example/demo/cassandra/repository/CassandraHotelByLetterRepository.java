package com.example.demo.cassandra.repository;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.example.demo.cassandra.domain.HotelByLetter;
import com.example.demo.cassandra.domain.HotelByLetterKey;
import com.example.demo.repository.HotelByLetterRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("test")
public class CassandraHotelByLetterRepository implements HotelByLetterRepository<HotelByLetter, HotelByLetterKey> {
    private final CassandraTemplate cassandraTemplate;

    public CassandraHotelByLetterRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public List<HotelByLetter> findByFirstLetter(String letter) {
        Select select = QueryBuilder.select().from("hotels_by_letter");
        select.where(QueryBuilder.eq("first_letter", letter));
        System.out.println("i am here!!!!");
        return this.cassandraTemplate.select(select, HotelByLetter.class);
    }

    @Override
    public HotelByLetter saveHotelByLetter(HotelByLetter hotelByLetter) {
        return this.cassandraTemplate.insert(hotelByLetter);
    }

    @Override
    public void deleteByHotelLetterKey(HotelByLetterKey hotelByLetterKey) {
        this.cassandraTemplate.deleteById(HotelByLetter.class, hotelByLetterKey);
    }

}
