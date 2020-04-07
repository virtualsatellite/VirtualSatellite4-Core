/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.branding.ui.intro;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.intro.IIntroSite;

import de.dlr.sc.virsat.branding.ui.Activator;

/**
 * Introduction / Welcome Screen page for VirSat
 */
public class IntroPart implements IIntroPart {
	
	@Override
	public IIntroSite getIntroSite() {
		return null;
	}

	@Override
	public void init(IIntroSite site, IMemento memento)
		throws PartInitException {

	}

	@Override
	public void standbyStateChanged(boolean standby) {

	}

	@Override
	public void saveState(IMemento memento) {

	}

	@Override
	public void addPropertyListener(IPropertyListener listener) {

	}

	static Image oldImage;
	
	@Override
	public void createPartControl(Composite container) {
    	final Composite outerContainer = new Composite(container, SWT.NONE);

    	GridData gridData = new GridData();
    	gridData.horizontalAlignment = GridData.FILL;
    	gridData.verticalAlignment = GridData.FILL;
    	gridData.grabExcessHorizontalSpace = true;
    	outerContainer.setLayoutData(gridData);
    	
    	outerContainer.addListener(SWT.Resize, new Listener() {
    		public void handleEvent(Event event) {
    			Rectangle rect = outerContainer.getClientArea();
    			ImageDescriptor backgroundImageDesc = Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/images/WelcomeScreen/VirtualSatellite_Modelling_4k.jpg");
    			Image backgroundImage = backgroundImageDesc.createImage();
    			GC gc = new GC(backgroundImage);
    			gc.setAntialias(SWT.ON);
    			gc.setInterpolation(SWT.HIGH);
    			double targetHeight = rect.height;
    			double targetWidth = rect.width;
    			double targetAspectRation = targetHeight / targetWidth;

    			double sourceWidth =  backgroundImage.getBounds().width;
    			double sourceHeight = backgroundImage.getBounds().height;
    			double sourceAspectRation = sourceHeight / sourceWidth;

    			double newSourceHeight;
    			double newSourceWidth;
    			
    			if (targetAspectRation < sourceAspectRation) {
    				newSourceWidth = sourceWidth;
    				newSourceHeight = sourceWidth * targetAspectRation;
    			} else {
    				newSourceHeight = sourceHeight;
    				newSourceWidth = sourceHeight / targetAspectRation;
    			}
    			
    			gc.drawImage(backgroundImage, 0, 0, (int) newSourceWidth, (int) newSourceHeight, 0, 0, rect.width, rect.height);
    			gc.dispose();
    			outerContainer.setBackgroundImage(backgroundImage);
    			if (oldImage != null) {
					oldImage.dispose();
				}
    			oldImage = backgroundImage;
    		}
    	});

    	ImageDescriptor loginImageDesc = Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/images/WelcomeScreen/arrow-48.png");
		Image loginImage = loginImageDesc.createImage();
    	
    	GridLayout gridLayout = new GridLayout();
    	gridLayout.numColumns = 2;
    	
    	outerContainer.setLayout(gridLayout);
    	
    	//adding some style text for a welcome header	
    	StyledText welcomeText = new StyledText(outerContainer, SWT.NULL);
    	welcomeText.setText("Welcome to the Virtual Satellite 4");
    	
    	final int FONT_SIZE_32 = 32;
    	final int FONT_SIZE_18 = 18;
    	
    	Font boldFont = new Font(welcomeText.getDisplay(), new FontData("Arial", FONT_SIZE_32, SWT.BOLD));
    	welcomeText.setForeground(outerContainer.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    	welcomeText.setFont(boldFont);
    	
    	gridData = new GridData();
    	gridData.grabExcessHorizontalSpace = true;
    	gridData.horizontalSpan = 2; 
    	gridData.horizontalAlignment = GridData.FILL;
    	gridData.verticalAlignment = GridData.FILL;
    	welcomeText.setLayoutData(gridData);
	    
     	
      	// adding an icon button for start modeling
    	CLabel startLabel = new CLabel(outerContainer, SWT.NULL);
    	startLabel.setImage(loginImage);
    	startLabel.setSize(loginImage.getBounds().width, loginImage.getBounds().height);
    	
    	gridData = new GridData();
     	gridData.horizontalAlignment = GridData.FILL;
    	gridData.verticalAlignment = GridData.FILL;
    	gridData.grabExcessHorizontalSpace = false;
    	startLabel.setLayoutData(gridData);	
    	
    	startLabel.addMouseListener(new MyMouseListener());
    	
    	// adding a link for start modeling
    	StyledText startLink = new StyledText(outerContainer, SWT.NULL);
    	Font notSoBoldFont = new Font(welcomeText.getDisplay(), new FontData("Arial", FONT_SIZE_18, SWT.UNDERLINE_LINK));
    	startLink.setFont(notSoBoldFont);
    	startLink.setForeground(outerContainer.getDisplay().getSystemColor(SWT.COLOR_WHITE));
    	startLink.setText("Start Modeling now!");
    	
    	gridData = new GridData(); 
    	gridData.horizontalAlignment = GridData.FILL;
    	gridData.verticalAlignment = GridData.CENTER;
    	startLink.setLayoutData(gridData);

    	startLink.addMouseListener(new MyMouseListener());
	}

	@Override
	public void dispose() {
		oldImage.dispose();

	}

	@Override
	public Image getTitleImage() {
		ImageDescriptor titleDesc = Activator.imageDescriptorFromPlugin(Activator.getPluginId(), "resources/images/Branding/VirSat16.gif");
		Image titleImage = titleDesc.createImage();
		return titleImage;
	}

	@Override
	public String getTitle() {
		return "Welcome";
	}

	@Override
	public void removePropertyListener(IPropertyListener listener) {

	}

	@Override
	public void setFocus() {

	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		return null;
	}

	/**
	 * Little embedded class to define a mouse listener on the text label in the Welcome Screen
	 * @author scha_vo
	 *
	 */
	static class MyMouseListener implements MouseListener {
		
		@Override
		public void mouseDoubleClick(MouseEvent e) {
		}

		@Override
		public void mouseDown(MouseEvent e) {
			try {
				IIntroPart welcomePart = PlatformUI.getWorkbench().getIntroManager().getIntro();
				PlatformUI.getWorkbench().getIntroManager().closeIntro(welcomePart);
			} catch (Exception ex) {
				Activator.getDefault().getLog().log(new Status(Status.ERROR, "BrandingPlugin", "Perspective not found"));
			}
		}

		@Override
		public void mouseUp(MouseEvent e) {
		}
	}
}