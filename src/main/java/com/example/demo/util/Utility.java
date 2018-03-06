package com.example.demo.util;

import com.example.demo.h2.domain.Hotel;
import com.example.demo.marker.TableMarker;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class Utility {

    //TODO: remove this helper method as the choice of repo doesnt exist on the passed in domain model object anymore!
    public TableMarker loadEntity(final String hotel, final String activeProfile) {

        ObjectMapper mapper = new ObjectMapper();
        TableMarker tableMarker = null;

        try {
            if (activeProfile.equals("dev") || activeProfile.isEmpty())
                tableMarker = mapper.readValue(hotel, com.example.demo.cassandra.domain.Hotel.class);
            else
                tableMarker = mapper.readValue(hotel, com.example.demo.cassandra.domain.Hotel.class);

        } catch (IOException ioEx) {
            System.out.println("Exception converting String to object :: " + ioEx.getMessage());
            // TODO: appropriate error handling!
        }

        return tableMarker;
    }
}
