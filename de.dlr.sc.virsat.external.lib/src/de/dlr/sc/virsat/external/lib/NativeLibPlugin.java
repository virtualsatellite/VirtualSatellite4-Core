/*******************************************************************************
 * Copyright (c) 2008-2019 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.external.lib;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * Activator for loading a Plugin with Native Library Code
 * 
 * @author fisc_ph
 *
 */
public abstract class NativeLibPlugin extends LibPlugin {

	private static final String MANIFEST_BUNDLE_NATIVE_CODE = "Bundle-NativeCode";
	private static final String MANIFEST_BUNDLE_SHAPE = "Eclipse-BundleShape";
	private static final String MANIFEST_BUNDLE_SHAPE_DIR = "dir";
	
	protected String platformOs;
	protected Bundle bundle;
	protected String bundlePlatformPath;
	protected boolean overrideLoadAbsolutePath = true;
	
	/**
	 * This method checks if the Native libraries should be loaded by Absolute path or not.
	 * In case it is a linux plugin and the shape of the plugin is DIRECTORY, than it is recommended
	 * to load the libraries by their absolute path
	 * @return true in case the libraries should be loaded by absolute path.
	 */
	protected boolean loadFromAbsolutePath() {
		boolean isLinux = platformOs.equalsIgnoreCase(Platform.OS_LINUX);
		String bundleShape = bundle.getHeaders().get(MANIFEST_BUNDLE_SHAPE);
		boolean isShapeDir = ((bundleShape != null) && (bundleShape.equalsIgnoreCase(MANIFEST_BUNDLE_SHAPE_DIR)));
		return isLinux && isShapeDir;
	}
	
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		
		bundle = getBundle();
		platformOs = Platform.getOS();
		bundlePlatformPath = FileLocator.toFileURL(bundle.getEntry("/")).getPath();
		
		loadAllNativeLibraries();
	}

	/**
	 * Loads all the native Libraries which are necessary for VTK or other external LIB plugins.
	 * This has to be done in a special way. You have the load all the
	 * dependencies manually an in the correct order. The reason for that is
	 * the OSGI class loader which doesn't resolve dependencies of native
	 * Libraries.
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	private void loadAllNativeLibraries() throws MalformedURLException, IOException {
		// Loading Linux libraries is easier by using the absolute path.
		// In particular when loading so.1 files or similar. Make sure, the Linux Library is unpacked in this case
		boolean loadAbsolutePath = loadFromAbsolutePath();
		
		ArrayList<String> libNames = getLibraryNames(loadAbsolutePath);
		NativeLibLoader loader = new NativeLibLoader(this, libNames, loadAbsolutePath);
		boolean libsLoadedSuccessfully = loader.loadLibraries();
		if (libsLoadedSuccessfully) {
			this.getLog().log(new Status(Status.INFO, pluginId, "All Native libraries succesfully laoded from: " + pluginId, null));
		} else {
			this.getLog().log(new Status(Status.WARNING, pluginId, "Failed to load all native libs!!! \n" + loader.getLoadResult(pluginId, bundlePlatformPath), null));
		}
	}
	
	/**
	 * Gets all native library names specified in MANIFEST.MF in Bundle-NativeCode
	 * @param createAbsolutePath true the list of libraries should return the absolute path rather than just the lib name
	 * @return list of library names e.g. "vtkpng-7.0"
	 * @throws IOException 
	 */
	protected ArrayList<String> getLibraryNames(boolean createAbsolutePath) throws IOException {
		String bundleNativeCodeManifestValue = bundle.getHeaders().get(MANIFEST_BUNDLE_NATIVE_CODE);
		if (bundleNativeCodeManifestValue == null) {
			return new ArrayList<String>();
		}
		String[] nativeCodeEntries = bundleNativeCodeManifestValue.split(";");
		ArrayList<String> libNames = new ArrayList<>();
		for (int i = 0; i < nativeCodeEntries.length - 2; i++) {
			if (createAbsolutePath) {
				String bundlePlatformPath = FileLocator.toFileURL(bundle.getEntry("/")).getPath();
				String libName = bundlePlatformPath + nativeCodeEntries[i];
				libNames.add(libName);
			} else {
				String libName = nativeCodeEntries[i].substring(nativeCodeEntries[i].lastIndexOf('/') + 1, nativeCodeEntries[i].lastIndexOf('.'));
				// On Linux platforms remove the lib in front of all library names
				if (platformOs.equalsIgnoreCase(Platform.OS_LINUX)) {
					libName = libName.replaceFirst("lib", "");
				}
				libNames.add(libName);
			}
		}
		return libNames;
	}

	/**
	 * Abstract method to specify loading of the library by absolute name. Usually the code here is
	 * System.load(libNameAbsolutePath) . Still, the code has to be executed in the context of the plugin
	 * where the actual DLLs reside. Otherwise the DLL will be loaded by this class loader and will create
	 * a conflict when trying to load the libraries again.
	 * @param libNameAbsolutePath the name of the Library as absolute path
	 */
	public abstract void loadLibraryByAbsolutePath(String libNameAbsolutePath);
	
	/**
	 * Abstract method to specify loading of the library by name. Usually the code here is
	 * System.loadLibrary(libName) . Still, the code has to be executed in the context of the plugin
	 * where the actual DLLs reside. Otherwise the DLL will be loaded by this class loader and will create
	 * a conflict when trying to load the libraries again.
	 * @param libName the name of the Library as absolute path
	 */
	public abstract void loadLibraryByName(String libName);
}
