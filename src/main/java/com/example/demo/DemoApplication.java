package com.example.demo;

import com.example.demo.cassandra.domain.Hotel;
import com.example.demo.cassandra.domain.HotelByLetter;
import com.example.demo.cassandra.repository.CassandraHotelByLetterRepository;
import com.example.demo.cassandra.repository.CassandraHotelRepository;
import com.example.demo.service.HotelService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

@SpringBootApplication

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}

@RestController
class WelcomeController {

	@RequestMapping("/")
	public String hello() throws UnknownHostException {
		return "Hello World! from "+ InetAddress.getLocalHost().getHostName();
	}
}