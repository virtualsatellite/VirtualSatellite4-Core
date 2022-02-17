package de.dlr.sc.virsat.model.extension.thermal.ui.wizards;

import java.io.IOException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.statushandlers.StatusManager;

import de.dlr.sc.virsat.model.dvlm.categories.CategoryAssignment;
import de.dlr.sc.virsat.model.extension.thermal.Activator;
import de.dlr.sc.virsat.model.extension.thermal.cad.CadExporterThermal;
import de.dlr.sc.virsat.model.extension.thermal.model.ThermalAnalysis;

/**
 * Export wizard for thermal CAD data
 */
public class CadThermalExportWizard extends Wizard implements INewWizard {
	
	public static final String ID = "de.dlr.sc.virsat.model.extension.thermal.ui.wizards.cadThermalExport";

	private CadThermalExportPage page;
	private IContainer model;
	
	/**
	 * Create the wizard object
	 */
	public CadThermalExportWizard() {
		super();
		setWindowTitle("CAD Thermal Export (Beta)");
		
		// Setup persistency
		IDialogSettings pluginSettings = Activator.getDefault().getDialogSettings();
		IDialogSettings wizardSettings = pluginSettings.getSection(ID);
		if (wizardSettings == null) {
			wizardSettings = new DialogSettings(ID);
			pluginSettings.addSection(wizardSettings);
		}
		setDialogSettings(wizardSettings);
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.model = ResourcesPlugin.getWorkspace().getRoot();
	}

	@Override
	public boolean performFinish() {
		CategoryAssignment selectedCA = (CategoryAssignment) page.getSelection();
		CadExporterThermal exporter = new CadExporterThermal(new ThermalAnalysis(selectedCA));
		
		String outputFilePath = page.getDestination();
		
		Job exportJob = new Job("Performing Thermal CAD Export") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					exporter.writeCadInput(outputFilePath);
					return Status.OK_STATUS;
				} catch (IOException e) {
					Status status = new Status(Status.ERROR, Activator.getPluginId(), 
							"CadThermalExportWizard: Failed to perform export!", e);
					StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
					return Status.CANCEL_STATUS;
				}
			}
		};
		exportJob.schedule();

		return true;
	}
	
	
	
    @Override
    public void addPages() {
        page = new CadThermalExportPage(model);
        addPage(page);
    }
}
