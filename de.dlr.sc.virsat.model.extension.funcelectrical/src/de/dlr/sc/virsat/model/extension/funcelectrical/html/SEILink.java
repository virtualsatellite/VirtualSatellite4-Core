/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.extension.funcelectrical.html;

import java.util.ArrayList;

/**
 * Class to provide areas on block diagrams to be used in html export , to give links to sei`s when clicking on them
 * @author bell_er
 *
 */
public class SEILink {
	private String containerSEIname;
	private ArrayList<Area> areas;

	/**
	 * Constructor
	 * 
	 * @author bell_er
	 * @param containerSEIname name of the container sei
	 * @param areas the areas contained in the image
	 */
	public SEILink(String containerSEIname, ArrayList<Area> areas) {
		super();
		this.containerSEIname = containerSEIname;
		this.areas = areas;
	}
	/**
	 * 
	 * Getter for the areas
	 * @return areas
	 */
	public ArrayList<Area> getAreas() {
		return areas;
	}
	/**
	 * @param areas areas
	 */
	public void setAreas(ArrayList<Area> areas) {
		this.areas = areas;
	}
	/**
	 * Constructor
	 */
	public SEILink() {
	}
	/**
	 * 
	 * Getter for the container sei name
	 * @return containerSEIname
	 */
	public String getContainerSEIname() {
		return containerSEIname;
	}
	/**
	 * @param containerSEIname linkSEIname
	 */
	public void setContainerSEIname(String containerSEIname) {
		this.containerSEIname = containerSEIname;
	}
	
	
	
	
	
	
	

	
}
