/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.virsat.external.lib.jersey.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * This is a wrapping Container which allows to implement a customized
 * Servlet that e.g. provides an application and configuration. This is needed
 * for equinox to register the classes, rather than just the package name.
 * @author fisc_ph
 *
 */
public abstract class ApplicationServletContainer implements Servlet {

	protected Servlet servlet;
	
	/**
	 * The constructor with the context path to be used
	 */
	public ApplicationServletContainer() {
		this.servlet = onCreateServlet();
	}

	/**
	 * Implement this class to create the servlet to be wrapped.
	 * @return The servlet that will be wrapped by this container.
	 */
	protected abstract Servlet onCreateServlet();

	@Override
	public void destroy() {
		this.servlet.destroy();
	}

	@Override
	public ServletConfig getServletConfig() {
		return this.servlet.getServletConfig();
	}

	@Override
	public String getServletInfo() {
		return servlet.getServletInfo();
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		this.servlet.init(arg0);
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		this.servlet.service(arg0, arg1);
	}
}
