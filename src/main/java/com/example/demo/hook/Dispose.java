package com.example.demo.hook;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class Dispose implements DisposableBean {

    @Override
    public void destroy() throws Exception {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }
}
