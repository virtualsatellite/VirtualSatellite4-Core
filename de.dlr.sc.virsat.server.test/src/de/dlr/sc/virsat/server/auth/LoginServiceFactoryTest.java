/*******************************************************************************
 * Copyright (c) 2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.server.auth;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.util.resource.Resource;
import org.junit.Test;

import de.dlr.sc.virsat.commons.file.VirSatFileUtils;
import de.dlr.sc.virsat.server.configuration.ServerConfiguration;

public class LoginServiceFactoryTest {

	@Test
	public void testInvalidClassInConfiguration() {
		ServerConfiguration.setLoginServiceClass("invalid");
		LoginServiceFactory fac = new LoginServiceFactory();
		
		assertNull("No longin service created", fac.getLoginService());
	}
	
	@Test
	public void testHashLoginServiceDefault() throws IOException {
		// Test default values
		ServerConfiguration.setLoginServiceClass("org.eclipse.jetty.security.HashLoginService");
		ServerConfiguration.setAuthPropertiesFile("resources/auth.properties");
		LoginServiceFactory fac = new LoginServiceFactory();
		LoginService service = fac.getLoginService();
		
		assertThat("HashLoginService got returned", service, instanceOf(HashLoginService.class));
		Resource configResource = Resource.newResource(((HashLoginService) service).getConfig());
		assertNotNull("Config file is valid", configResource.getFile() != null);
	}
	
	@Test
	public void testHashLoginServiceCustomFile() throws IOException {
		// Test custom file
		Path customDir = VirSatFileUtils.createAutoDeleteTempDirectory("loginServiceTest_");
		Path customFile = customDir.resolve("customFile");
		customFile.toFile().createNewFile();
		
		ServerConfiguration.setLoginServiceClass("org.eclipse.jetty.security.HashLoginService");
		ServerConfiguration.setAuthPropertiesFile(customFile.toString());
		LoginServiceFactory fac = new LoginServiceFactory();
		LoginService service = fac.getLoginService();
		
		assertThat("HashLoginService got returned", service, instanceOf(HashLoginService.class));
		Resource configResource = Resource.newResource(((HashLoginService) service).getConfig());
		assertEquals("Config file is the desired file", configResource.getFile(), customFile.toFile());
	}
}
