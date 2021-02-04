/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.server.Activator;

/**
 * This resource serves the swagger documentation
 */
@Path("/")
public class DocumentationResource {

	public static final String FILE_NOT_FOUND = "File not found";
	public static final String SWAGGER_JSON = "swagger.json";
	public static final String SWAGGER_YAML = "swagger.yaml";
	
	private String directory;
	
	public DocumentationResource(String directory) {
		this.directory = directory;
	}
	
    @GET
    @Path(SWAGGER_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public String swaggerJson() {
        return getFileContent(SWAGGER_JSON);
    }

    @GET
    @Path(SWAGGER_YAML)
    @Produces("application/yaml")
    @PermitAll
    public String swaggerYaml() {
        return getFileContent(SWAGGER_YAML);
    }

    /**
     * Reads the JSON/yaml file
     * @param fileName
     * @return content of the file with the fileName
     */
    private String getFileContent(String fileName) {
		
    	String content = FILE_NOT_FOUND;
    	String realmResourceName = "doc-gen" + File.separator + directory + File.separator + fileName;
    	
		try {
			InputStream inputStream  = FileLocator.openStream(Activator.getDefault().getBundle(), 
					new org.eclipse.core.runtime.Path(realmResourceName), false);
			content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(
					new Status(Status.ERROR, Activator.getPluginId(), "Error reading documentation file: " + realmResourceName, e));
		}
		
		return content;
    }
}
