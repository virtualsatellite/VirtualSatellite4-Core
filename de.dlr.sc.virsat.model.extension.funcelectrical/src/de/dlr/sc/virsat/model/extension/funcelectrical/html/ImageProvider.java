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
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;
 
import org.eclipse.core.runtime.Status;
 
import de.dlr.sc.virsat.model.concept.types.util.BeanCategoryAssignmentHelper;
import de.dlr.sc.virsat.model.dvlm.structural.StructuralElementInstance;
import de.dlr.sc.virsat.model.extension.funcelectrical.Activator;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.Interface;
import de.dlr.sc.virsat.model.extension.funcelectrical.model.InterfaceEnd;
import de.dlr.sc.virsat.model.extension.ps.model.AssemblyTree;
import de.dlr.sc.virsat.model.extension.ps.model.ConfigurationTree;
import de.dlr.sc.virsat.model.extension.ps.model.ElementDefinition;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTree;
import de.dlr.sc.virsat.model.extension.ps.model.ProductTreeDomain;
 
 
/**
 * Class to provide block diagrams to be used in html export 
 * @author bell_er
 *
 */
 
public class ImageProvider {

	
	private BeanCategoryAssignmentHelper bCaHelper;	
	private List<Interface> allInterfaces;
	private List<SEILink> seiLinks;
	// some constants to draw the block diagram
	private static final int XPOS = 0;
	private static final int YPOS = 0;
	private static final int BIGRECTWIDTH = 100;
	private static final int BIGRECTHEIGHT = 40;
	private static final int HEADERHEIGHT = 20;
	private static final int SMALLRECTWIDTH = 20;
	private static final int SMALLRECTHEIGHT = 20;
	private static final int ARROWHEADMARGIN = 5;
	private static final int INTERFACEAREAMARGIN = 10;
	private static final int INTERFACELENGTH = 150;
	private static final int FONTSIZE = 10;
	private static final int TRIMSIZE = 5;
	private static final String COLOR = "#dee3e9";
	private static final Color TEXTCOLOR = Color.BLACK;
	private static final int SATURATION = 90; 
	private static final int BRIGHTNESS = 50;
	private static final List<Color> INTERFACEENDCOLORS = new ArrayList<Color>();
	private static final int INTERFACEENDCOLORCOUNT = 10;
	private static final boolean INTERFACEENDFROM = true;
	private static final boolean INTERFACEENDTO = false;
	private static final String DEFAULTINTERFACETYPE = "TBD";
	private static final String EXPORTIMAGETYPE = "png";
	private static final String FONTTYPE = "Franklin Gothic Medium";
	private static final String[] EXPORTABLES = {
			ProductTree.class.getSimpleName(),
			ProductTreeDomain.class.getSimpleName(),
			ElementDefinition.class.getSimpleName(),
			AssemblyTree.class.getSimpleName(),
			ConfigurationTree.class.getSimpleName()
	};
			
	/**
	 * Constructor
	 */
	public ImageProvider() {
		initInterfaceEndColors();
	}
	/**
	 * 
	 * Getter for the areas
	 * @return areas
	 */
	public List<SEILink> getSeiLinks() {
		return seiLinks;
	}

	/**
	 * Export block diagrams 
	 * @param path location of the images
	 * @param sc the structural element to be Exported
	 * @return 
	 */
	public void exportImage(String path, StructuralElementInstance sc) {
		if (isExportable(sc)) {
			bCaHelper = new BeanCategoryAssignmentHelper();	
			allInterfaces = bCaHelper.getAllBeanCategoriesFromRoot(sc, Interface.class);
			seiLinks = new ArrayList<SEILink>();
			exportTree(sc, path);
			
		}
	}

	/**
	 * Checks if a given structural element instance is exportable or not
	 * @param sc the structural element instance
	 * @return true iff it is exportable
	 */
	public boolean isExportable(StructuralElementInstance sc) {
		String scName = sc.getType().getName();
		return Stream.of(EXPORTABLES).anyMatch(exportable -> exportable.equals(scName));
	}
	
	/**
	 * Method to Initialize interfaceEndColor List
	 * 
	 */
	private void initInterfaceEndColors() {
		for (int i = 0; i < INTERFACEENDCOLORCOUNT; i++) {
		    INTERFACEENDCOLORS.add(Color.getHSBColor(i, SATURATION + i * INTERFACEENDCOLORCOUNT, BRIGHTNESS + i * INTERFACEENDCOLORCOUNT));
		}
	}
	/**
	 * Export block diagrams for ConfigurationTree
	 * @param path location of the images
	 * @param sc Structural element to be exported
	 */
	private void exportTree(StructuralElementInstance sc, String path) {
		for (StructuralElementInstance child : sc.getChildren()) {
			exportTree(child,  path);
		}
		
		// if there are no interface ends dont do anything 
		List<InterfaceEnd> seiInterfaceEnds = bCaHelper.getAllBeanCategories(sc, InterfaceEnd.class);
		if (seiInterfaceEnds.size() <= 0) {
			return;
		}
		
		List<StructuralElementInstance> fromList = getStructuralElements(allInterfaces, sc, INTERFACEENDFROM);
		List<StructuralElementInstance> toList = getStructuralElements(allInterfaces, sc, INTERFACEENDTO);
		SEILink seiLink = new SEILink(sc.getName(), new ArrayList<Area>());
		BufferedImage offImage = createImage(seiInterfaceEnds, fromList, toList, sc);
		Graphics2D g = offImage.createGraphics();
		g.setFont(new Font(FONTTYPE, Font.PLAIN, FONTSIZE));
		
		int xpos = XPOS;
		// we will print the structural element instances which have a connection to our sc here
		List<InterfaceEnd> seiLeftInterfaceEnds = drawBlockDiagramLeft(g, fromList, xpos, YPOS, seiLink, sc);
		// decide the position of our sei if there are no from interfaces it will be left of the image, otherwise right
		if (fromList.size() >  0) {
			xpos = BIGRECTWIDTH + INTERFACELENGTH;
			drawHeader(g, sc, BIGRECTWIDTH + INTERFACELENGTH, YPOS, BIGRECTWIDTH + BIGRECTWIDTH);
		} else {
			xpos = (SMALLRECTWIDTH / 2);
			drawHeader(g, sc, (SMALLRECTWIDTH / 2), YPOS, BIGRECTWIDTH + BIGRECTWIDTH);
		}
		// we will print the structural element instances which have a connection from our sc here
		List<InterfaceEnd> seiRightInterfaceEnds = drawBlockDiagramRight(g, toList, xpos + BIGRECTWIDTH, YPOS, seiLink, sc);		

		// draw the interface ends who have no interface
		int leftHeight = HEADERHEIGHT * Math.max(1, fromList.size()) + BIGRECTHEIGHT * (Math.max(0, seiLeftInterfaceEnds.size() + fromList.size() - 1));
		int rightHeight = HEADERHEIGHT * Math.max(1, toList.size()) + BIGRECTHEIGHT * (Math.max(0, seiRightInterfaceEnds.size() + toList.size() - 1));
		for (InterfaceEnd ie : seiInterfaceEnds) {
			if (!seiLeftInterfaceEnds.contains(ie) && !seiRightInterfaceEnds.contains(ie)) {
				if (leftHeight > rightHeight) {
					drawLeftInterfaceEnd(g, ie, xpos + BIGRECTWIDTH, rightHeight);
					rightHeight = rightHeight + BIGRECTHEIGHT;
				} else {
					drawRightInterfaceEnd(g, ie, xpos, leftHeight);
					leftHeight = leftHeight + BIGRECTHEIGHT;
				}
			}
		}
		g.setColor(Color.decode(COLOR));
		
		//fill the empty areas of our structural element
		if (leftHeight > rightHeight) {
			g.fillRect(xpos + BIGRECTWIDTH, rightHeight, BIGRECTWIDTH, (leftHeight - rightHeight));
		} else if (rightHeight > leftHeight) {
			g.fillRect(xpos, leftHeight, BIGRECTWIDTH, (rightHeight - leftHeight));
		}
		
		// add the areas
		seiLinks.add(seiLink);
		try {
		    // retrieve image
		    File outputfile = new File(path + "\\"  + sc.getName() + "." + EXPORTIMAGETYPE);
		    ImageIO.write(offImage, EXPORTIMAGETYPE, outputfile);
		} catch (IOException e) {
			Activator.getDefault().getLog().log(new Status(Status.ERROR, Activator.getPluginId(), Status.OK, "Problem with exporting html", e));
					
		}
	}
	/**
	 * This method will take an interface list and a structural element instance, then it will return a structural element list. Those list will include structural elements which have an incoming interface
	 * from the given structural element if the given location is INTERFACE_END_FROM.  Those list will include structural elements which have an outgoing interface
	 * to the given structural element if the given location is INTERFACE_END_TO.
	 * @param interfaces the interface list
	 * @param sc the given Structural element
	 * @param givenLocation Returned list will include structural elements which have an incoming interface from the given structural element if the given location is INTERFACE_END_FROM.
	 * Returned list will include structural elements which have an outgoing interface to the given structural element if the given location is INTERFACE_END_TO.
	 * @return the structural element List which have an incoming interface
	 * from the given structural element
	 */
	private List<StructuralElementInstance> getStructuralElements(List<Interface> interfaces, StructuralElementInstance sc, boolean givenLocation) {
		List<StructuralElementInstance> returnList = new ArrayList<StructuralElementInstance>(); 
		for (Interface i : interfaces) {
			if (getInterfaceEnd(i, !givenLocation) != null && getInterfaceEnd(i, givenLocation) != null) {
				StructuralElementInstance container2 = (StructuralElementInstance) getInterfaceEnd(i, !givenLocation).getTypeInstance().eContainer();
				if (sc.getUuid().equals(container2.getUuid())) {
					StructuralElementInstance container1 = (StructuralElementInstance) getInterfaceEnd(i, givenLocation).getTypeInstance().eContainer();
					if (!returnList.contains(container1)) {
						returnList.add(container1);
					}
				}
			}
		}
		return returnList;
	}
	/**
	 * This method will take an interface list and a structural element instance, then it will return a structural element list. Those list will include structural elements which have an incoming interface
	 * from the given structural element if the given location is INTERFACE_END_FROM.  Those list will include structural elements which have an outgoing interface
	 * to the given structural element if the given location is INTERFACE_END_TO.
	 * @param i the interface 
	 * @param givenLocation if it is interfaceEndFrom return interfaceEndFrom. if it is interfaceEndTo return interfaceEndTo
	 * @return the interfaceEnd on one side of an interface
	 */
	private InterfaceEnd getInterfaceEnd(Interface i, boolean givenLocation) {
		if (givenLocation) {
			return i.getInterfaceEndFrom();
		} else {
			return i.getInterfaceEndTo();
		}
	}
	
	/**
	 * This method will take an interface list and two structural element instance, then it will return the interfaces between them. 
	 * @param interfaces the interface list
	 * @param toSc the given Structural element which the interfaces go to
	 * @param fromSc the Structural element which the interfaces go from
	 * @return the Interfaces from fromSc to toSc
	 */
	private List<Interface> getRelevantInterfaces(List<Interface> interfaces, StructuralElementInstance toSc, StructuralElementInstance fromSc) {
		List<Interface> returnList = new ArrayList <Interface>();
		for (Interface i : interfaces) {
			if (i.getInterfaceEndTo() != null && i.getInterfaceEndFrom() != null) {
				StructuralElementInstance temp = (StructuralElementInstance) i.getInterfaceEndTo().getTypeInstance().eContainer();
				StructuralElementInstance temp2 = (StructuralElementInstance) i.getInterfaceEndFrom().getTypeInstance().eContainer();
				if (temp.equals(toSc) && temp2.equals(fromSc)) {
					returnList.add(i);
				}
			}
		}
		return returnList;
	}	
	/**
	 * This method will take an interface and will find the element configuration who contains that interface
	 * @param i the interface 
	 * @return name of the ElementConfiguration
	 */
	private String getSeiOfAnInterface(Interface i) {
		StructuralElementInstance sei = (StructuralElementInstance) i.getTypeInstance().eContainer();
		return sei.getName();	
	}
	/**
	 * This method will draw an interface end and write the name of the interface left of it
	 * @param g the graphics object
	 * @param ie the interfaceEnd
	 * @param xpos top left x position to draw
	 * @param ypos top left y position to draw
	 */
	private void drawLeftInterfaceEnd(Graphics2D g, InterfaceEnd ie, int xpos, int ypos) {
		int width = g.getFontMetrics().stringWidth(getInterfaceNameandType(g, ie));
		int xposRect = xpos + BIGRECTWIDTH - (SMALLRECTWIDTH / 2);
		int yposRect = ypos + (BIGRECTHEIGHT - SMALLRECTHEIGHT) / 2;
		int xposLabel = xpos + BIGRECTWIDTH - width - (SMALLRECTWIDTH / 2);
		int yposLabel = ypos + (BIGRECTHEIGHT / 2);
		drawInterfaceEnd(g, ie, xpos, ypos, xposRect, yposRect, xposLabel, yposLabel);
	}
	/**
	 * This method will draw an interface end and write the name of the interface right of it
	 * @param g the graphics object
	 * @param ie the interfaceEnd
	 * @param xpos top left x position to draw
	 * @param ypos top left y position to draw
	 */
	private void drawRightInterfaceEnd(Graphics2D g, InterfaceEnd ie, int xpos, int ypos) {
		int xposRect = xpos - (SMALLRECTWIDTH / 2);
		int yposRect = ypos + (BIGRECTHEIGHT - SMALLRECTHEIGHT) / 2;
		int xposLabel = xpos + (SMALLRECTWIDTH / 2);
		int yposLabel = ypos + (BIGRECTHEIGHT / 2);
		drawInterfaceEnd(g, ie, xpos, ypos, xposRect, yposRect, xposLabel, yposLabel);
	}
	/**
	 * This method will draw an interface end and write the name of the interface accordingly
	 * @param g the graphics object
	 * @param ie the interfaceEnd
	 * @param xpos top left x position to draw the sei parts
	 * @param ypos top left y position to draw the sei parts
	 * @param xposRect top left x position to draw the interface rectangle
	 * @param yposRect top left y position to draw the interface rectangle
	 * @param xposLabel top left x position to draw the interface name
	 * @param yposLabel top left y position to draw the interface name
	 */
	private void drawInterfaceEnd(Graphics2D g, InterfaceEnd ie, int xpos, int ypos, int xposRect, int yposRect, int xposLabel, int yposLabel) {
		g.setColor(Color.decode(COLOR));
		g.fillRect(xpos, ypos, BIGRECTWIDTH, BIGRECTHEIGHT);
		int code;
		if (ie.getType() == null) {
			code = DEFAULTINTERFACETYPE.hashCode();
		} else {
			code = ie.getType().getName().toUpperCase().hashCode();
		}
		g.setColor(INTERFACEENDCOLORS.get(Math.abs(code % INTERFACEENDCOLORCOUNT)));
		g.fillRect(xposRect, yposRect, SMALLRECTWIDTH, SMALLRECTHEIGHT);
		g.setColor(TEXTCOLOR);
		String interfaceEnd = getInterfaceNameandType(g, ie);
		g.drawString(interfaceEnd, xposLabel, yposLabel);
	}

	/**
	 * This method will return name and type of an interface end, if it is too long it trims it
	 * @param g the graphics object
	 * @param ie the interfaceEnd
	 * @return  name and type of an interface end combined
	 */
	private String getInterfaceNameandType(Graphics2D g, InterfaceEnd ie) {
		String typeName;
		if (ie.getType() == null) {
			typeName = DEFAULTINTERFACETYPE;
		} else {
			typeName = ie.getType().getName().toUpperCase();
		}
		
		String interfaceEnd = ie.getName() + ":" + typeName;
		int width = g.getFontMetrics().stringWidth(interfaceEnd);
		if (width > BIGRECTWIDTH - SMALLRECTHEIGHT / 2) {
			String name = ie.getName();
			if (name.length() > TRIMSIZE) {
				name = name.substring(0, TRIMSIZE);	
				name = name + "..";
			}
			if (typeName.length() > TRIMSIZE) {
				typeName = typeName.substring(0, TRIMSIZE);	
				typeName = ".." + typeName;
			}
			interfaceEnd = name + ":" + typeName;
		}
		return interfaceEnd;
	}
	/**
	 * This method will draw an the header and write the Sei name and Type
	 * @param g the graphics object
	 * @param sei the Structural Element Instance
	 * @param xpos top left x position to draw
	 * @param ypos top left y position to draw
	 * @param rectWidth the width of the header
	 */
	private void drawHeader(Graphics2D g, StructuralElementInstance sei, int xpos, int ypos, int rectWidth) {
		g.setColor(Color.decode(COLOR));
		g.fillRect(xpos, ypos, rectWidth, HEADERHEIGHT);
		g.setColor(TEXTCOLOR);
		String type = "«" + sei.getType().getName() + "»";
		int width = g.getFontMetrics().stringWidth(type);
		g.drawString(type, xpos + ((rectWidth - width) / 2), ypos + FONTSIZE);
		width = g.getFontMetrics().stringWidth(sei.getName());
		g.drawString(sei.getName(), xpos + ((rectWidth - width) / 2), ypos + FONTSIZE  + FONTSIZE); 
	}
	/**
	 * This method will draw an interface with an arrow on the right, will write the interface name and then will generate a link area
	 * @param g the graphics object
	 * @param i  interface  written 
	 * @param xpos top left x position to draw
	 * @param ypos top left y position to draw
	 * @param seiLink area List to add interface area
	 */
	private void drawInterface(Graphics2D g, Interface i, int xpos, int ypos, SEILink seiLink) {
		Polygon arrowHead = null;
		//draw the interface line here
		Line2D line = new Line2D.Double(xpos + BIGRECTWIDTH + (SMALLRECTWIDTH / 2), ypos + (BIGRECTHEIGHT / 2), xpos + BIGRECTWIDTH + INTERFACELENGTH, ypos + (BIGRECTHEIGHT / 2));
		g.draw(line);
		// add the interface area to areas
		seiLink.getAreas().add(new Area((int) line.getX1(), (int) line.getY1() - INTERFACEAREAMARGIN, (int) line.getX2(), (int) line.getY2() + INTERFACEAREAMARGIN, getSeiOfAnInterface(i)));
		// Write interface name 
		int width = g.getFontMetrics().stringWidth(i.getName());
		g.drawString(i.getName(), (int) ((int) (((line.getX2() - line.getX1()) - width) / 2) + line.getX1()), ypos + (BIGRECTHEIGHT - SMALLRECTHEIGHT));
		//create the arrow here
		arrowHead = new Polygon();  
		arrowHead.addPoint(xpos + BIGRECTWIDTH + INTERFACELENGTH - (2 * ARROWHEADMARGIN) - (SMALLRECTWIDTH / 2), (int) line.getY2() - ARROWHEADMARGIN);
		arrowHead.addPoint(xpos + BIGRECTWIDTH + INTERFACELENGTH - (2 * ARROWHEADMARGIN) - (SMALLRECTWIDTH / 2), (int) line.getY2() + ARROWHEADMARGIN);
		arrowHead.addPoint(xpos + BIGRECTWIDTH + INTERFACELENGTH - (SMALLRECTWIDTH / 2), (int) line.getY2());
		g.fill(arrowHead);
	
	}
	/**
	 * This method will calculate the image height and image width and then create the empty image
	 * @param seiInterfaceEnds interface ends of our sei
	 * @param fromList structural element List which have an outgoing interface
	 * to the given structural element
	 * @param toList the structural element List which have an incoming interface
	 * from the given structural element
	 * @param sc the structural Element instance
	 * @return offImage the empty image
	 */
	private BufferedImage createImage(List<InterfaceEnd> seiInterfaceEnds, List<StructuralElementInstance> fromList, List<StructuralElementInstance> toList, StructuralElementInstance sc) {
		int heightfrom = 0;	
		for (StructuralElementInstance sei : fromList) {		
			List<Interface> interfaceList =  getRelevantInterfaces(allInterfaces, sc, sei);		
			heightfrom = heightfrom + HEADERHEIGHT;
			for (int i = 0; i < interfaceList.size(); i++) {
				heightfrom = heightfrom + BIGRECTHEIGHT;
			}
			heightfrom = heightfrom + BIGRECTHEIGHT;
		}
		
		int heightto = 0;
		for (StructuralElementInstance sei : toList) {		
			List<Interface> interfaceList = getRelevantInterfaces(allInterfaces, sei, sc);
			heightto = heightto + HEADERHEIGHT;
			for (int i = 0; i < interfaceList.size(); i++) {
				heightto = heightto + BIGRECTHEIGHT;
			}
			heightto = heightto + BIGRECTHEIGHT;
		}
		
		int imgwidth = 0;
		if (heightto == 0 && heightfrom == 0) {
			imgwidth = BIGRECTWIDTH + BIGRECTWIDTH + SMALLRECTWIDTH;
		} else if (heightto == 0 || heightfrom == 0) {
			imgwidth = BIGRECTWIDTH + INTERFACELENGTH + BIGRECTWIDTH + BIGRECTWIDTH + SMALLRECTWIDTH;   
		} else {
			imgwidth = BIGRECTWIDTH + INTERFACELENGTH + BIGRECTWIDTH + BIGRECTWIDTH + INTERFACELENGTH + BIGRECTWIDTH;
		}
		
		double size = seiInterfaceEnds.size();
		int seiHeight = (int) (Math.ceil(size / 2) * BIGRECTHEIGHT + HEADERHEIGHT);
		int height = Math.max(seiHeight, Math.max(heightfrom, heightto));
		BufferedImage offImage = new BufferedImage(imgwidth, height, BufferedImage.TYPE_INT_ARGB);
		return offImage;
	}
	/**
	 * This method will draw the first half of the image (some other sei ---> our sei)
	 * @param g the graphics object
	 * @param xpos top left x position to draw
	 * @param ypos top left y position to draw
	 * @param seiLink the object to hold link areas to make them clickable
	 * @param list the structural element List which have an either incoming interface to our sc or which have an outgoing interface from our sc
	 * @param sc the structural Element instance
	 * @return seiInterfaceEnds the drawn interface ends
	 */
	private List<InterfaceEnd> drawBlockDiagramLeft(Graphics2D g, List<StructuralElementInstance> list, int xpos, int ypos, SEILink seiLink, StructuralElementInstance sc) {
		List<InterfaceEnd> seiInterfaceEnds = new ArrayList<InterfaceEnd>();	
		for (StructuralElementInstance sei : list) {
			if (ypos != YPOS) {
				// draw the missing part of our structural element
				g.setColor(Color.decode(COLOR));
				g.fillRect(xpos  + BIGRECTWIDTH + INTERFACELENGTH, ypos, BIGRECTWIDTH, BIGRECTHEIGHT + HEADERHEIGHT);
				ypos = ypos + BIGRECTHEIGHT;
			}
			List<Interface> interfaceList =  getRelevantInterfaces(allInterfaces, sc, sei);
			Area area = new Area(xpos, ypos, xpos + BIGRECTWIDTH + SMALLRECTWIDTH, ypos + interfaceList.size() * BIGRECTHEIGHT + (BIGRECTHEIGHT - SMALLRECTHEIGHT), sei.getName());
			seiLink.getAreas().add(area);
			drawHeader(g, sei, xpos, ypos, BIGRECTWIDTH);
			// update y coordinate
			ypos = ypos + HEADERHEIGHT;
			for (Interface i: interfaceList) {
				drawLeftInterfaceEnd(g, i.getInterfaceEndFrom(), xpos, ypos);			
				drawInterface(g, i, xpos, ypos, seiLink);
				drawRightInterfaceEnd(g, i.getInterfaceEndTo(), xpos + BIGRECTWIDTH + INTERFACELENGTH, ypos);
				seiInterfaceEnds.add(i.getInterfaceEndTo());
				// update y coordinate
				ypos = ypos + BIGRECTHEIGHT;	
			}	
		}
		return seiInterfaceEnds;
	}
	/**
	 * This method will draw the second half of the image (our sei ---> some other sei)
	 * @param g the graphics object
	 * @param xpos top left x position to draw
	 * @param ypos top left y position to draw
	 * @param seiLink the object to hold link areas to make them clickable
	 * @param list the structural element List which have an either incoming interface to our sc or which have an outgoing interface from our sc
	 * @param sc the structural Element instance
	 * @return seiInterfaceEnds the drawn interface ends
	 */
	private List<InterfaceEnd> drawBlockDiagramRight(Graphics2D g, List<StructuralElementInstance> list, int xpos, int ypos, SEILink seiLink, StructuralElementInstance sc) {
		List<InterfaceEnd> seiInterfaceEnds = new ArrayList<InterfaceEnd>();
		int mainSeiXpos = xpos;
		int otherSeiXpos = xpos + BIGRECTWIDTH + INTERFACELENGTH;
		for (StructuralElementInstance sei : list) {
			if (ypos != YPOS) {
				// draw the missing part of our structural element
				g.setColor(Color.decode(COLOR));
				g.fillRect(mainSeiXpos, ypos, BIGRECTWIDTH, BIGRECTHEIGHT + HEADERHEIGHT);
				ypos = ypos + BIGRECTHEIGHT;
			}
			List<Interface> interfaceList = getRelevantInterfaces(allInterfaces, sei, sc);	
			Area area = new Area(otherSeiXpos, ypos, otherSeiXpos + BIGRECTWIDTH, ypos + interfaceList.size() * BIGRECTHEIGHT + (BIGRECTHEIGHT - SMALLRECTHEIGHT), sei.getName());
			seiLink.getAreas().add(area);
			drawHeader(g, sei, otherSeiXpos, ypos, BIGRECTWIDTH);
			// update y coordinate
			ypos = ypos + HEADERHEIGHT;
			for (Interface i:interfaceList) {
				drawLeftInterfaceEnd(g, i.getInterfaceEndFrom(), xpos, ypos);
				drawInterface(g, i, xpos, ypos, seiLink);
				drawRightInterfaceEnd(g, i.getInterfaceEndTo(), otherSeiXpos, ypos);
				seiInterfaceEnds.add(i.getInterfaceEndFrom());
				// update y coordinate
				ypos = ypos + BIGRECTHEIGHT;
			}	
		}
		return seiInterfaceEnds;
	}
	
}
