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

import de.dlr.virsat.server.example.model.Person;
import de.dlr.virsat.server.example.model.init.ModelInitializer;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

public class PersonResource {
    @Context
    UriInfo uriInfo;
    
    @Context
    Request request;
    
    int index;
    
    public PersonResource(UriInfo uriInfo, Request request, int index) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.index = index;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Person getPerson() {
        return ModelInitializer.INSTANCE.getPersons().get(index);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putPerson(Person person) {
   		ModelInitializer.INSTANCE.getPersons().set(index, person);
        return Response.created(uriInfo.getAbsolutePath()).build();
    }

    @DELETE
    public void deletePerson() {
        ModelInitializer.INSTANCE.getPersons().remove(index);
    }
    
    @Path("hellos")
    public HellosResource sayPlainTextHello() {
    	return new HellosResource();
    } 
}