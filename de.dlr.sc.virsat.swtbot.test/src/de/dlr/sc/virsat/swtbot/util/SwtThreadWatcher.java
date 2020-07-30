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

import org.eclipse.core.runtime.Status;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import de.dlr.sc.virsat.project.Activator;

/**
 * A Junit Rule that creates a log entry when the test starts, ends and fails.
 * The entry contains a complete print of the current Threads running. This helps
 * to detect dead lock situations.
 */
public class SwtThreadWatcher extends TestWatcher { 
	
	@Override
	protected void starting(Description description) {
		String testClass = description.getClassName();
		String testMethod = description.getMethodName();
		
		StringBuilder failedTestMessage = new StringBuilder();
		failedTestMessage.append("----------------------------------------------------\n");
		failedTestMessage.append(String.format("Starting: %s.%s%n", testClass, testMethod));
		failedTestMessage.append("----------------------------------------------------\n");
		failedTestMessage.append("\n");
		failedTestMessage.append(SwtBotDebugHelper.printListOfThreads());
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "SwtThreadWatcher: Starting Test Case\n" + failedTestMessage.toString()));
	}
	
	@Override
	protected void finished(Description description) {
		String testClass = description.getClassName();
		String testMethod = description.getMethodName();
		
		StringBuilder failedTestMessage = new StringBuilder();
		failedTestMessage.append("----------------------------------------------------\n");
		failedTestMessage.append(String.format("Finished: %s.%s%n", testClass, testMethod));
		failedTestMessage.append("----------------------------------------------------\n");
		failedTestMessage.append("\n");
		failedTestMessage.append(SwtBotDebugHelper.printListOfThreads());
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "SwtThreadWatcher: Finished Test Case\n" + failedTestMessage.toString()));
	};
	
	@Override
	protected void failed(Throwable e, Description description) {
		String testClass = description.getClassName();
		String testMethod = description.getMethodName();
		
		StringBuilder failedTestMessage = new StringBuilder();
		failedTestMessage.append("----------------------------------------------------\n");
		failedTestMessage.append("           Thread Table for failed test\n");
		failedTestMessage.append("----------------------------------------------------\n");
		failedTestMessage.append("\n");
		failedTestMessage.append(String.format("Failed Test Case: %s.%s%n", testClass, testMethod));
		failedTestMessage.append(String.format("Reported Reason : %s%n", e.getMessage()));
		for (StackTraceElement ste : e.getStackTrace()) {
			failedTestMessage.append(String.format(" -- Exception Trace: %s%n", ste.toString()));
		}
		failedTestMessage.append("\n");
		failedTestMessage.append(SwtBotDebugHelper.printListOfThreads());
		
		Activator.getDefault().getLog().log(new Status(Status.INFO, Activator.getPluginId(), "SwtThreadWatcher: Failed Test Case\n" + failedTestMessage.toString()));
	
		throw new RuntimeException(failedTestMessage.toString());
	}
}
