/*******************************************************************************
 * Copyright (c) 2008-2020 German Aerospace Center (DLR), Simulation and Software Technology, Germany.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package de.dlr.sc.virsat.swtbot.util;

import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.util.Arrays;
import java.util.TreeSet;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

import de.dlr.sc.virsat.swtbot.test.Activator;

/**
 * helper class for special SWTBot debugging. Brings functionality to 
 * e.g. dump a list of all running threads and stack traces
 */
public class SwtBotDebugHelper {

	private SwtBotDebugHelper() {
	}
	
	/**
	 * Method to print all installed builders and natures to the current workspace projects
	 * @return a sting with all information about project and workspace builders
	 */
	public static String printBuilderStates() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("\nVirtual Satellite Builder Table");
		stringBuilder.append("\n------------------------------\n\n");
	
		stringBuilder.append("\nAutobuilding          is enabled: " + ResourcesPlugin.getWorkspace().getDescription().isAutoBuilding());
		stringBuilder.append("\nAutobuilding project build order: " + ResourcesPlugin.getWorkspace().getDescription().getBuildOrder());
		
		for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects()) {
			stringBuilder.append("\n\nProject Build   Configurations : " + project.getName());
			try {
				stringBuilder.append("\nProject Natures Configurations : " + String.join(", ", project.getDescription().getNatureIds()));
				for (ICommand buildConfig : project.getDescription().getBuildSpec()) {
					stringBuilder.append("\nProjectBuilder: " + buildConfig.getBuilderName());
				}
			} catch (CoreException e) {
				stringBuilder.append("\nFailed to receive Project Description");
			}
		}
		
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}

	/**
	 * Simple method to transform an exception into a string of where it happened
	 * @param e the exception to convert
	 * @return a string such as class.method:line
	 */
	private static String printCodeLine(Exception e) {
		return e.getStackTrace()[1].toString();
	}

	/**
	 * This method prints a list of all threads their locks and stacktraces
	 * @return list as a formatted string
	 */
	public static String printListOfThreads() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\nVirtual Satellite Thread Table");
		stringBuilder.append("\n------------------------------\n\n");
	
		// First read all threads
		ThreadInfo[] infos = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
		
		// Create a tree set to sort the threads infos by the thread name
		TreeSet<ThreadInfo> threadInfos = new TreeSet<>((ti1, ti2) -> ti1.getThreadName().compareToIgnoreCase(ti2.getThreadName()));
		threadInfos.addAll(Arrays.asList(infos));
	
		// Now start printing all of them into a table
		threadInfos.forEach((threadInfo) -> {
			final int THREAD_NAME_COLUMN_WIDTH = 20;
			
			// Get all thread informations
			String threadName = threadInfo.getThreadName();
			threadName = threadName.substring(0, Math.min(THREAD_NAME_COLUMN_WIDTH, threadName.length()));
			String threadState = threadInfo.getThreadState().toString();
			StackTraceElement[] threadStes = threadInfo.getStackTrace();
			String threadCodeLine = (threadStes.length == 0) ? "unknown" : threadStes[0].toString();
			LockInfo threadLockInfo = threadInfo.getLockInfo();
			String threadLockClassName = (threadLockInfo == null) ? "None" : threadLockInfo.getClassName();
			
			String format;
			// Now print the infos depending on if there is a lock or not
			if (threadLockInfo == null) {
				format = "Thread <%1$-" + THREAD_NAME_COLUMN_WIDTH + "s> State <%2$-13s> @ %3$s%n";
				stringBuilder.append(String.format(format, threadName, threadState, threadCodeLine));
			} else {
				String lockOwnerName = threadInfo.getLockOwnerName();
				format = "Thread <%1$-" + THREAD_NAME_COLUMN_WIDTH + "s> State <%2$-13s> @ Lock <%3$s> Lock Owner<%4$s>: %n";
				stringBuilder.append(String.format(format, threadName, threadState, threadLockClassName, lockOwnerName));
			}
	
			for (StackTraceElement ste : threadStes) {
				threadCodeLine = (threadStes.length == 0) ? "unknown" : ste.toString();
				format = " -- Trace        : %1$s%n";
				stringBuilder.append(String.format(format, threadCodeLine));
			}
			
			MonitorInfo[] mis = threadInfo.getLockedMonitors();
			LockInfo[] lis = threadInfo.getLockedSynchronizers();
			if (mis.length > 0 || lis.length > 0) {
				stringBuilder.append(" -- ---- Owned Synchronizers and Monitors ----%n");
				for (MonitorInfo mi : mis) {
					format = " -- Owned Monitor      : %1$-20s @ %2$s%n";
					String monitorName = mi.getClassName();
					StackTraceElement miSte = mi.getLockedStackFrame();
					String monitorCodeLine = (miSte == null) ? "unknown" : miSte.toString();
					stringBuilder.append(String.format(format, monitorName, monitorCodeLine));
				}
			
				for (LockInfo li : lis) {
					format = " -- Owned Synchronizer : %1$-20s%n";
					String lockName = li.getClassName();
					stringBuilder.append(String.format(format, lockName));
				}
			}
			
		});
		stringBuilder.append("\n");
		
		return stringBuilder.toString();
	}

	public static void logCodeLine() {
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), SwtBotDebugHelper.printCodeLine(new Exception())));
	}
}
