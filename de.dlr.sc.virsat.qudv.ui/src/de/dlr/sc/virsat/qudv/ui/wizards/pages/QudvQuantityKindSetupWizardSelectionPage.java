/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.qudv.ui.wizards.pages;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.jface.wizard.WizardSelectionPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import de.dlr.sc.virsat.model.dvlm.units.UnitManagement;
import de.dlr.sc.virsat.project.Activator;
import de.dlr.sc.virsat.qudv.ui.wizards.DerivedQuantityKindWizard;
import de.dlr.sc.virsat.qudv.ui.wizards.SimpleQuantityKindWizard;

/**
 * class to define the first page of the quantity kind wizard
 * @author scha_vo
 *
 */
public class QudvQuantityKindSetupWizardSelectionPage extends WizardSelectionPage {

	private ComboViewer providerViewer;
	private IConfigurationElement selectedProvider;

	private UnitManagement um = null;
	
	/**
	 * public constructor
	 * @param pageName the name of the page
	 * @param um the unit mangement
	 */
	public QudvQuantityKindSetupWizardSelectionPage(String pageName, UnitManagement um) {
		super(pageName);
		this.um = um;
	}
	
	/**
	 * little embedded helper class 
	 * @author scha_vo
	 *
	 */
	private class WizardNode implements IWizardNode {
		private IWizard wizard = null;
		private IConfigurationElement configurationElement;

		/**
		 * public constructor
		 * @param c the configuration element
		 */
		WizardNode(IConfigurationElement c) {
			this.configurationElement = c;
		}

		@Override
		public void dispose() {

		}

		@Override
		public Point getExtent() {
			return new Point(-1, -1);
		}

		@Override
		public IWizard getWizard() {
			if (wizard == null) {
				try {
					wizard = (IWizard) configurationElement.createExecutableExtension("class"); //$NON-NLS-1$
				} catch (CoreException e) {
					Activator.getDefault().getLog().log(new Status(Status.WARNING, Activator.getPluginId(), "QudvQuantityKindSetupWizardSelectionPage: Failed to get Wizard"));
				}
			}
			if (wizard instanceof SimpleQuantityKindWizard) {
				((SimpleQuantityKindWizard) wizard).setUnitManagement(um);
			} else if (wizard instanceof DerivedQuantityKindWizard) {
				((DerivedQuantityKindWizard) wizard).setUnitManagement(um);
			}	
			
			return wizard;
		}

		@Override
		public boolean isContentCreated() {
			return wizard != null;
		}

	}

	@Override
	public void createControl(Composite parent) {
		setTitle("Select wizard");
		Composite main = new Composite(parent, SWT.NONE);
		GridLayout gd = new GridLayout(2, false);
		main.setLayout(gd);
		Label label = new Label(main, SWT.NONE);
		label.setText("Available wizards");
		label.setToolTipText("Choose a wizard from the list of available QUDV Wizards in the ComboBox.");
		Combo providerList = new Combo(main, SWT.NONE);
		providerViewer = new ComboViewer(providerList);
		providerViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof IConfigurationElement) {
					IConfigurationElement c = (IConfigurationElement) element;
					String result = c.getAttribute("name");
					if (result == null || result.length() == 0) {
						result = c.getAttribute("class");
					}
					return result;
				}
				return super.getText(element);
			}

		});
    	providerViewer
            .addSelectionChangedListener(new ISelectionChangedListener() {
                @Override
                public void selectionChanged(SelectionChangedEvent event) {
                    ISelection selection = event.getSelection();
                    if (!selection.isEmpty()
                            && selection instanceof IStructuredSelection) {
                        Object o = ((IStructuredSelection) selection)
                                .getFirstElement();
                        if (o instanceof IConfigurationElement) {
                            selectedProvider = (IConfigurationElement) o;
                            setMessage(selectedProvider.getAttribute("description"));
                            setSelectedNode(new WizardNode(selectedProvider));
                        }
                    }

                }
            });
    	providerViewer.setContentProvider(new ArrayContentProvider());
    	List<IConfigurationElement> providers = new ArrayList<IConfigurationElement>();
    	IExtensionRegistry registry = Platform.getExtensionRegistry();
    	IExtensionPoint extensionPoint = registry.getExtensionPoint("de.dlr.sc.virsat.qudv.ui.qudvQuantityKindWizard"); 
    	if (extensionPoint != null) {
    		IExtension[] extensions = extensionPoint.getExtensions();
    		for (IExtension extension : extensions) {
    			IConfigurationElement[] configurationElements = extension
    					.getConfigurationElements();
    			for (IConfigurationElement c : configurationElements) {
    				providers.add(c);
    			}
    		}
    	}
    	providerViewer.setInput(providers);
    	setControl(main);
	}
	
}