package de.dlr.sc.virsat.model.extension.thermal.ui.wizards;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import de.dlr.sc.virsat.model.extension.thermal.Activator;

public class CadThermalExportWizard extends Wizard implements INewWizard {
	
	public static final String ID = "de.dlr.sc.virsat.model.extension.thermal.ui.wizards.cadThermalExport";

	private CadThermalExportPage page;
	private IContainer model;
	
	/**
	 * Default constructor
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

		
		return true;
	}
	
	
	
    @Override
    public void addPages() {
        page = new CadThermalExportPage(model);
        addPage(page);
    }
}
