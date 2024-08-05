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

import de.dlr.sc.virsat.server.auth.ServerRoles;
import de.dlr.virsat.server.example.model.Hello;
import de.dlr.virsat.server.example.model.init.ModelInitializer;
import jakarta.annotation.security.RolesAllowed;
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

@RolesAllowed({ServerRoles.ADMIN, ServerRoles.USER})
public class HellosResource {

    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    @Path("{index}")
    public HelloResource getTodo(@PathParam("index") int index) {
        return new HelloResource(uriInfo, request, index);
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public List<Hello> getHellos() {
        return ModelInitializer.INSTANCE.getHellos();
    }

    @POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public void postHello(Hello hello) throws IOException {
        ModelInitializer.INSTANCE.getHellos().add(hello);
    }
    
    @GET
    @Path("size")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSize() {
        return "" + ModelInitializer.INSTANCE.getHellos().size();
    }

}