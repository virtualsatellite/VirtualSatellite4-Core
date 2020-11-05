package de.dlr.sc.virsat.server.servlet;

import javax.servlet.Servlet;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import de.dlr.sc.virsat.server.resources.AccessTestResource;
import de.dlr.virsat.external.lib.jersey.servlet.ApplicationServletContainer;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

public class SwaggerDocumentationServlet extends ApplicationServletContainer implements Servlet {

	@Override
	protected Servlet onCreateServlet() {
		return new ServletContainer(new SwaggerRestApplication());
	}

	private static class SwaggerRestApplication extends ResourceConfig {
		private SwaggerRestApplication() {
			register(AccessTestResource.class);
			register(ApiListingResource.class);
			register(SwaggerSerializers.class);
			

			BeanConfig beanConfig = new BeanConfig();
			beanConfig.setVersion("1.0.2");
			beanConfig.setSchemes(new String[] { "http" });
			beanConfig.setHost("http://localhost:8000/swagger-ui");
			beanConfig.setBasePath("/rest");
			beanConfig.setResourcePackage("de.dlr.sc.virsat.server.resources");
			beanConfig.setScan(true);
		}
	}

}
