/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.model.calculation.ui.editor.snippet;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import com.google.inject.Injector;

import de.dlr.sc.virsat.build.marker.ui.MarkerImageProvider;
import de.dlr.sc.virsat.model.calculation.compute.EquationHelper;
import de.dlr.sc.virsat.model.calculation.marker.VirSatEquationMarkerHelper;
import de.dlr.sc.virsat.model.calculation.ui.editor.EquationSectionUriEditorInput;
import de.dlr.sc.virsat.model.calculation.ui.handler.OpenEquationSectionHandler;
import de.dlr.sc.virsat.model.calculation.ui.internal.CalculationActivator;
import de.dlr.sc.virsat.model.calculation.ui.labeling.EquationDSLLabelProvider;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationFactory;
import de.dlr.sc.virsat.model.dvlm.calculation.CalculationPackage;
import de.dlr.sc.virsat.model.dvlm.calculation.Equation;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationIntermediateResult;
import de.dlr.sc.virsat.model.dvlm.calculation.EquationSection;
import de.dlr.sc.virsat.model.dvlm.calculation.IEquationSectionContainer;
import de.dlr.sc.virsat.model.dvlm.calculation.MathOperator;
import de.dlr.sc.virsat.model.dvlm.calculation.MultiplicationAndDivision;
import de.dlr.sc.virsat.model.dvlm.calculation.NumberLiteral;
import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.APropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.ComposedPropertyInstance;
import de.dlr.sc.virsat.model.dvlm.categories.propertyinstances.UnitValuePropertyInstance;
import de.dlr.sc.virsat.project.markers.IMarkerHelper;
import de.dlr.sc.virsat.project.ui.contentProvider.VirSatFilteredWrappedTreeContentProvider;
import de.dlr.sc.virsat.project.ui.labelProvider.VirSatTransactionalAdapterFactoryLabelProvider;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.AUiSnippetEStructuralFeatureTable;
import de.dlr.sc.virsat.uiengine.ui.editor.snippets.IUiSnippet;

/**
 * class ui snippet Equations implements the interface ui snippet for the Equation Section
 * @author scha_vo
 *
 */
public class UiSnippetEquations extends AUiSnippetEStructuralFeatureTable implements IUiSnippet {
	
	private static final String SECTION_NAME = "Equations";

	private Button buttonAdd;
	private Button buttonRemove;
	private Button buttonEdit;
	private Button buttonUpdate;

	private static final String BUTTON_ADD_TEXT = "Add Equation";
	private static final String BUTTON_REMOVE_TEXT = "Remove Equation";
	private static final String BUTTON_EDIT_TEXT = "Edit Equations";
	private static final String BUTTON_UPDATE_TEXT = "Perform Update";
	
	private static final String COLUMN_TEXT_EQUATION = "Equation";
	private static final String COLUMN_TEXT_RESULT = "Result";
	
	VirSatEquationMarkerHelper emh = new VirSatEquationMarkerHelper();
	
	/**
	 * Constructor for this class to instantiate a UI Snippet
	 */
	public UiSnippetEquations() {
		super(CalculationPackage.Literals.IEQUATION_SECTION_CONTAINER__EQUATION_SECTION);
	}

	@Override
	protected String getSectionHeading() {
		return super.getSectionHeading() + SECTION_NAME;
	}
	
	@Override
	protected IContentProvider getTableContentProvider() {
		//Get filtered content showing all Equations
		VirSatFilteredWrappedTreeContentProvider filteredContentProvider = new VirSatFilteredWrappedTreeContentProvider(adapterFactory) {
			@Override
			public Object[] getElements(Object rootObject) {
				Object[] elements = super.getElements(rootObject);
				if (elements.length > 0) {
					return ((EquationSection) elements[0]).getEquations().toArray();
				} else {
					return elements;
				}
			}
		};
	    filteredContentProvider.addClassFilterToGetElement(EquationSection.class);
	    
	    return filteredContentProvider;
	}
	
	
	@Override
	protected ITableLabelProvider getTableLabelProvider() {
		VirSatTransactionalAdapterFactoryLabelProvider labelProvider;
		
		labelProvider = new VirSatTransactionalAdapterFactoryLabelProvider(adapterFactory) {
			
			private Injector injector = CalculationActivator.getInstance().getInjector(CalculationActivator.DE_DLR_SC_VIRSAT_MODEL_CALCULATION_EQUATIONDSL);
			private ILabelProvider delegateLabelProvider = (ILabelProvider) injector.getInstance(EquationDSLLabelProvider.class);
			private MarkerImageProvider diagnosticImageProvider = new MarkerImageProvider(emh);
			
			@Override
			public String getColumnText(Object object, int columnIndex) {
				super.getColumnText(object, columnIndex);
				if (object instanceof Equation) {
					Equation eq = (Equation) object;
					
					switch (columnIndex) {
						case 0:
							return delegateLabelProvider.getText(eq);
						case 1:	
							return eq.getResultText();
						default:
							break;
					}
				}
				return super.getText(object);
			}
			
			@Override
			public Image getColumnImage(Object object, int columnIndex) {
				// Retrieve the standard image of the whatever object
				Image superImage = super.getColumnImage(object, columnIndex);
				
				if (object instanceof Equation) {
					Equation equation = (Equation) object;
					
					// Column 1 is the result column
					if (columnIndex == 1) {
						Image problemImage = diagnosticImageProvider.getProblemImageForEObject(equation.getResult());
						return (problemImage != null) ? problemImage : superImage;
					}
				}
				
				return superImage;
			}
		};
		
		return labelProvider;		
	}
	
	@Override
	protected void createTableColumns(EditingDomain editingDomain) {
	    // Column Equation
		createDefaultColumn(tableViewer, COLUMN_TEXT_EQUATION);
	
		//Column Result
		createDefaultColumn(tableViewer, COLUMN_TEXT_RESULT);
	}
	
	@Override
	protected void setTableViewerInput(EditingDomain editingDomain) {
		tableViewer.setInput(model);
	}
	
	@Override
	protected Composite createButtons(FormToolkit toolkit, Composite sectionBody) {
		Composite compositeButtons = super.createButtons(toolkit, sectionBody);
		
		buttonAdd = toolkit.createButton(compositeButtons, BUTTON_ADD_TEXT, SWT.PUSH);
		buttonRemove = toolkit.createButton(compositeButtons, BUTTON_REMOVE_TEXT, SWT.PUSH);
		buttonEdit = toolkit.createButton(compositeButtons, BUTTON_EDIT_TEXT, SWT.PUSH);
		buttonUpdate = toolkit.createButton(compositeButtons, BUTTON_UPDATE_TEXT, SWT.PUSH);

		checkWriteAccess(buttonAdd);
		checkWriteAccess(buttonRemove);
		checkWriteAccess(buttonEdit);
		
		return compositeButtons;
	}

	@Override
	protected void addButtonSelectionListeners(Composite composite, EditingDomain editingDomain) {
		
		buttonAdd.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// some code to create a unit via our QudvWizard 
				
				// create a new Equation with the name of the super user as default user name 
				Equation newEquation = CalculationFactory.eINSTANCE.createEquation();
				MultiplicationAndDivision multi = CalculationFactory.eINSTANCE.createMultiplicationAndDivision();
				multi.setOperator(MathOperator.MULTIPLY);
				NumberLiteral left = CalculationFactory.eINSTANCE.createNumberLiteral();
				left.setValue("22");
				NumberLiteral right = CalculationFactory.eINSTANCE.createNumberLiteral();
				right.setValue("23");
				multi.setRight(right);
				multi.setLeft(left);
				newEquation.setExpression(multi);
				EquationIntermediateResult eir = CalculationFactory.eINSTANCE.createEquationIntermediateResult();
				eir.setName("Result");
				newEquation.setResult(eir);
				
				IEquationSectionContainer equationSectionContainer = ((IEquationSectionContainer) model).getEquationSection();
				if (equationSectionContainer == null) {
					EquationSectionUriEditorInput input = OpenEquationSectionHandler.createXtextEquationEditorInput(model);
					equationSectionContainer = input.getEquationSection();
				}
				
				Command addCommand = AddCommand.create(editingDomain, equationSectionContainer, CalculationPackage.eINSTANCE.getEquationSection_Equations(), newEquation);
				editingDomain.getCommandStack().execute(addCommand);
				setTableViewerInput(editingDomain);
				//new WizardDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell(), new QudvUnitSetupWizard((UnitManagement) model)).open();
				// the wizard guides the user through the possible steps to add a unit
				// at the end, on the performFinish() method it executes a cmd over the commandStack which ends the new unit in the proper way.
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});

		buttonRemove.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<?> selection = tableViewer.getStructuredSelection().toList();
				if (!selection.isEmpty()) {
					Command cmd = DeleteCommand.create(editingDomain, selection);
					editingDomain.getCommandStack().execute(cmd);
					setTableViewerInput(editingDomain);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		buttonEdit.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				performEquationsUpdate(editingDomain);
				OpenEquationSectionHandler.openXtextEquationEditor(model);
			} 
				
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		
		buttonUpdate.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				performEquationsUpdate(editingDomain);
			} 
				
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
	}
	
	/**
	 * Updates the local equations
	 * @param editingDomain the editing domain
	 */
	private void performEquationsUpdate(EditingDomain editingDomain) {
		if (model instanceof IEquationSectionContainer) {
			IEquationSectionContainer container = (IEquationSectionContainer) model;
			
			if (container.getEquationSection() != null) {
				// If our model has indeed equations, update them now but only consider here
				// the local equations, equations that may set the values variables mentioned
				// in the local equations should be updated by the builder
				
				Command updateCommand = new RecordingCommand((TransactionalEditingDomain) editingDomain, "Update Equations") {
					@Override
					protected void doExecute() {
						EquationHelper eqHelper = new EquationHelper();
						eqHelper.evaluate(container.getEquationSection().getEquations());
					}
				};
				editingDomain.getCommandStack().execute(updateCommand);
			}
		}
	}
	
	@Override
	public boolean isActive(EObject model) {
		
		//equation snippet will be added to editor only in case there is at least one unit value property
		if (model instanceof CategoryAssignment) {
			CategoryAssignment ca = (CategoryAssignment) model;
			for (APropertyInstance propertyInstance: ca.getPropertyInstances()) {
				if (propertyInstance instanceof UnitValuePropertyInstance || propertyInstance instanceof ComposedPropertyInstance) {
					return true;
				}	
			}	
		}
		return false;
	}
	
	@Override
	protected Set<EObject> getPossiblyMarkedObjects() {
		Set<EObject> possiblyMarkedObjects = new HashSet<>();
		IEquationSectionContainer container = (IEquationSectionContainer) model;
		if (container.getEquationSection() != null) {
			possiblyMarkedObjects.addAll(container.getEquationSection().getEquations());
		}
		return possiblyMarkedObjects;
	}
	
	@Override
	protected Set<IMarkerHelper> getMarkerHelpers() {
		return Collections.singleton(emh);
	}
}
