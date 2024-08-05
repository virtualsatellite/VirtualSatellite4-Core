/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.virsat.server.example.resources;

import java.io.IOException;
import java.util.List;

import de.dlr.virsat.server.example.model.Person;
import de.dlr.virsat.server.example.model.init.ModelInitializer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.UriInfo;


@Path("/persons")
@SecurityScheme(
		  type = SecuritySchemeType.HTTP,
		  name = "basicAuth",
		  scheme = "basic")
@OpenAPIDefinition(
	info = @Info(
		version = "0.1",
		title = "The Example Model API",
		description = "API to access some People saying Hello. It helps to understand the architecture and functionality of Jetty, jersey, Jakarta, etc."
	),
	servers = {@Server(url = "/rest/persons")}
)
public class PersonsResource {

    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Person> getPersons() {
        return ModelInitializer.INSTANCE.getPersons();
    }

    @Path("{index}")
    public PersonResource getPerson(@PathParam("index") int index) {
        return new PersonResource(uriInfo, request, index);
    }

    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public void postPerson(Person person) throws IOException {
        ModelInitializer.INSTANCE.getPersons().add(person);
    }

    @GET
    @Path("size")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSize() {
        return "" + ModelInitializer.INSTANCE.getPersons().size();
    }

    @GET
    @Path("hellotojersey")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
    	return "Hello Jersey!";
    } 
}