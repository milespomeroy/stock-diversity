package com.milespomeroy.resources;

import com.milespomeroy.jdbi.UserDAO;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    private final UserDAO dao;

    public UserResource(UserDAO dao) {
       this.dao = dao;
    }

    @GET
    @Timed
    public List<String> getAllUsers() {
        return dao.findAll();
    }
}
