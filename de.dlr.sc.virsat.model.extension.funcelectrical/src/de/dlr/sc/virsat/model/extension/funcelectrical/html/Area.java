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

/**
 * Class to provide areas with top topLefty and bottomRightx bottomRighty corners with a link name. When clicked on that area that link opens
 * @author bell_er
 *
 */
public class Area {
	private int toplLeftx;
	private int topLefty;
	private int bottomRightx;
	private int bottomRighty;
	private String linkSEIname;
	
	/**
	 * Constructor
	 * 
	 * @author bell_er
	 * @param linkSEIname name of the sei which will be reachable by a link
	 * @param topLeftx coordinate of the top topLefty corner for the area
	 * @param topLefty coordinate of the top topLefty corner for the area
	 * @param bottomRightx coordinate of the bottomRightx bottomRighty corner for the area
	 * @param bottomRighty coordinate of the bottomRightx bottomRighty corner for the area
	 */
	public Area(int topLeftx, int topLefty, int bottomRightx, int bottomRighty, String linkSEIname) {
		super();
		this.toplLeftx = topLeftx;
		this.topLefty = topLefty;
		this.bottomRightx = bottomRightx;
		this.bottomRighty = bottomRighty;
		this.linkSEIname = linkSEIname;
	}
	/**
	 * 
	 * Getter for the toplLeftx
	 * @return toplLeftx
	 */
	public int getTopLeftx() {
		return toplLeftx;
	}
	/**
	 * @param topLeftx topLeftx
	 */
	public void setTop(int topLeftx) {
		this.toplLeftx = topLeftx;
	}
	/**
	 * 
	 * Getter for the topLefty
	 * @return topLefty
	 */
	public int getTopLefty() {
		return topLefty;
	}
	/**
	 * @param topLefty topLefty
	 */
	public void setTopLefty(int topLefty) {
		this.topLefty = topLefty;
	}
	/**
	 * @return bottomRightx
	 */
	public int getBottomRightx() {
		return bottomRightx;
	}
	/**
	 * @param bottomRightx bottomRightx
	 */
	public void setBottomRightx(int bottomRightx) {
		this.bottomRightx = bottomRightx;
	}
	/**
	 * 
	 * Getter for the bottomRighty
	 * @return bottomRighty
	 */
	public int getBottomRighty() {
		return bottomRighty;
	}
	/**
	 * 
	 * Setter for the bottomRighty
	 * @param bottomRighty bottomRighty
	 */
	public void setBottomRighty(int bottomRighty) {
		this.bottomRighty = bottomRighty;
	}
	/**
	 * 
	 * Getter for the link sei name
	 * @return linkSEIname
	 */
	public String getLinkSEIname() {
		return linkSEIname;
	}
	/**
	 * @param linkSEIname linkSEIname
	 */
	public void setLinkSEIname(String linkSEIname) {
		this.linkSEIname = linkSEIname;
	}
	
	
}
