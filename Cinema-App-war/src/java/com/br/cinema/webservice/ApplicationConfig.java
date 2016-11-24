package com.br.cinema.webservice;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Alexandre Lopes
 */
@ApplicationPath("cinema")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(com.br.cinema.webservice.UserService.class);
        return resources;
    }

}
