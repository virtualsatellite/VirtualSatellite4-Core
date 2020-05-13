/*******************************************************************************
 * Copyright (c) 2008-2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.uiengine.ui.swt.forms;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.ui.forms.widgets.ScrolledForm;

/**
 * This class implements an SWT mouse wheel listener.
 * It redirects scroll actions of a scrollable widget to the
 * parent ScrolledForm in case the table or tree has been scrolled 
 * to one its ends. 
 *
 */
public class VirSatMouseWheelScrollRedirectListener implements MouseWheelListener {

	private Scrollable scrollable;
	private ScrollBar redirectScrollBar;
	private ScrolledForm redirectScrolledForm;
	
	/**
	 * Constructor that takes the scrollable for which to find the ScrolledForm
	 * @param scrollable A scrolable such as a tree or table.
	 */
	public VirSatMouseWheelScrollRedirectListener(Scrollable scrollable) {
		this.scrollable = scrollable;
	}
	
	/**
	 * Call this to initialize the Listener.
	 * Calling this method tries to identify the ScrolledForm as well as the ScrollBar
	 */
	public void init() {
		this.redirectScrolledForm = findNextParentScrolledForm(scrollable);
		if (redirectScrolledForm != null) {
			this.redirectScrollBar = redirectScrolledForm.getVerticalBar();
		}
	}
	
	@Override
	public void mouseScrolled(MouseEvent e) {
		if (redirectScrollBar != null && redirectScrolledForm != null) {
			
			// the count on the event tells us how many ticks should be scrolled
			// This value comes from the operating system settings a positive value means
			// That things are scrolled upwards
			boolean scrollUp = e.count > 0;
			
			// Get the positions of the current scrollable. Counts are in elements of the table.
			// This means selection is the current element index which is visible at the top.
			// Maximum is the last element. The page count represents the amount of elements that are
			// displayed in one table or basically the amount of elements to forward to show the next
			// page in the table.
			int scrollablePositionCurrent = scrollable.getVerticalBar().getSelection();
			int scrollablePositionMaximum =  scrollable.getVerticalBar().getMaximum();
			int scrollablePageIncrement = scrollable.getVerticalBar().getPageIncrement();

			// Now calculate if when scrollingUp or down the scroll has to be redirected
			// Redirection is needed when the table is either at the top or bottom of its scrollable elements			
			boolean delegateUp = (scrollablePositionCurrent == 0) && scrollUp;
			boolean delegateDown = (scrollablePositionCurrent >= scrollablePositionMaximum - scrollablePageIncrement) && !scrollUp;
			
			// Now start delegating the scroll if needed
			if (delegateUp || delegateDown) {
				// Now get the scroll properties from the redirected ScrollBar of the parent ScrolledForm
				int redirectPositionCurrent = redirectScrollBar.getSelection();
				int redirectPositionIncrement = redirectScrollBar.getIncrement();
				int redirectPositionNew = redirectPositionCurrent - redirectPositionIncrement * e.count;

				// Now set the new location and get it again. 
				// The get makes sure that the new value is casted to the correct boundaries.
				redirectScrollBar.setSelection(redirectPositionNew);
				redirectPositionNew = redirectScrollBar.getSelection();
				
				// And finally update the content area of the scrolled form with the new location
				Control content = redirectScrolledForm.getContent();
				Point location = content.getLocation();
				content.setLocation(location.x, -redirectPositionNew);
			}
		}
	}
	
	/**
	 * This method detects the next parent ScrolledForm in which the given control is nested
	 * @param control the control such as a table which is nested into a ScrolledForm
	 * @return null in case it could not be found
	 */
	protected ScrolledForm findNextParentScrolledForm(Control control) {
		Control parentControl = control.getParent();
		
		// Try to find a next parent vertical ScrollBar as long as there are parents
		while (parentControl != null) {
			if (parentControl instanceof ScrolledForm) {
				ScrolledForm parentScrolledForm = (ScrolledForm) parentControl;
				ScrollBar parentScrollBar = parentScrolledForm.getVerticalBar();
				if (parentScrollBar != null) {
					return (ScrolledForm) parentControl;
				}
			}
			
			// Get the next parent control and try it again
			parentControl = parentControl.getParent();
		}
		
		// Apparently there was no other parent control with a Vertical Scrollbar
		// Return null for it
		return null;
	}
}
