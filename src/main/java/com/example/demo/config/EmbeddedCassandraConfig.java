package com.example.demo.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import static java.lang.Integer.parseInt;

@Configuration
@EnableCassandraRepositories
@Profile(value = {"dev"})
public class EmbeddedCassandraConfig {

    @Autowired
    Environment environment;

    @Bean
    public Cluster cluster() throws Exception {

        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        Cluster cluster = Cluster.builder()
                .addContactPoints(environment.getProperty("spring.data.cassandra.contact-points")).withPort(parseInt(environment.getProperty("spring.data.cassandra.port"))).build();
        return cluster;

    }

    @Bean
    public Session session() {

        Session session = null;
        try {
            session =  cluster().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }

    @Bean
    protected CQLDataLoader getKeyspaceName() {
        final CQLDataLoader cqlDataLoader = new CQLDataLoader(session());
        cqlDataLoader.load(new ClassPathCQLDataSet("cql/schema.cql", false, true, environment.getProperty("spring.data.cassandra.keyspace-name")));
        return cqlDataLoader;
    }

    @Bean
    public CassandraMappingContext mappingContext() {
        return new BasicCassandraMappingContext();
    }


    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session());
    }

}
