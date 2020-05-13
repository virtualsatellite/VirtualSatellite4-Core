/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/

package de.dlr.sc.virsat.swtbot.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBotControl;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.ui.forms.widgets.Section;
import org.hamcrest.SelfDescribing;

/**
 * This class is used to deal with the Editor sections of the
 * Virtual Satellite Generic Editor.
 */
public class SwtBotSection extends AbstractSWTBotControl<Section> {

	/**
	 * @param section section to create 
	 * @throws WidgetNotFoundException if the widget is not found
	 */
	public SwtBotSection(Section section) throws WidgetNotFoundException {
		super(section);
	}

	/**
	 * @param section section to create 
	 * @param description the description
	 * @throws WidgetNotFoundException if the widget is not found
	 */
	public SwtBotSection(Section section, SelfDescribing description) throws WidgetNotFoundException {
		super(section, description);
	}

	/**
	 * @return true if expanded false if not
	 */
	public boolean isExpanded() {
		return syncExec(new BoolResult() {
			public Boolean run() {
				return widget.isExpanded();
			}
		});
	}

	/**
	 * We define a click method on SWTBotSection. When the collapse button is clicked, it reaches out to the UI Thread.
	 * Then if the actual widget is expanded, it collapses the widget. If the widget is collapsed, it expands it.
	 * @return the current 
	 */
	public SwtBotSection click() {
		syncExec(new BoolResult() {
			public Boolean run() {
				widget.setExpanded(!widget.isExpanded());
				return true;
			}
		});
		return this;
	}
	
	/**
	 * We have Section->Composite->Table
	 * First the method finds the composite under the section, then it finds the table under the composite.
	 * The general UI of the project requires that each section contains only one table,
	 * so we can simply return table.get(0).
	 * @return first table
	 */
	public SWTBotTable getSWTBotTable() {
		List<Table> table = new ArrayList<>();
		syncExec(new BoolResult() {
			public Boolean run() {
				for (Control c : widget.getChildren()) {
					if (c instanceof Composite) {
						for (Control cc : ((Composite) c).getChildren()) {
							if (cc instanceof Table) {
								table.add((Table) cc); 
							}
						}
					}
				}
				return true;
			}
		});
		return new SWTBotTable(table.get(0));
	}

}