package de.dlr.sc.virsat.server.servlet;

import javax.servlet.Servlet;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import de.dlr.sc.virsat.server.resources.ProjectManagementResource;
import de.dlr.virsat.external.lib.jersey.servlet.ApplicationServletContainer;

public class RepoManagementServlet extends ApplicationServletContainer implements Servlet {
	
	@Override
	protected Servlet onCreateServlet() {
		RepoManagementRestApplication resourceConfig = new RepoManagementRestApplication();
		return new ServletContainer(resourceConfig);
	}

	private class RepoManagementRestApplication extends ResourceConfig {
		private RepoManagementRestApplication() {
			register(ProjectManagementResource.class);
		}
	}

}
