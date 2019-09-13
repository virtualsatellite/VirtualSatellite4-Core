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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

import de.dlr.sc.virsat.swtbot.test.ASwtBotTestCase;

/**
 * This Condition waits for the Workspace Builder to execute
 * @author fisc_ph
 *
 */
public class VirSatWaitForProjectBuilder extends DefaultCondition implements IJobChangeListener {

	private boolean error = false;
	private String message = "";
	private int expectedBuilder;
	
	/**
	 * Constructor for the condition
	 * @param expectedBuilder the minimum amount of builders which are expected to be executed
	 */
	public VirSatWaitForProjectBuilder(int expectedBuilder) {
		this.expectedBuilder = expectedBuilder;
	}
	
	/**
	 * Use this method to set the error state and a message to be reported by
	 * the SWTBot Framework
	 * @param message The message to be reported
	 */
	private void setErrorState(String message) {
		this.message = message;
		this.error = true;
	}

	private Map<String, Integer> mapScheduleCounter = new HashMap<>();
	
	@Override
	public void init(SWTBot bot) {
		synchronized (this) {
			super.init(bot);
			
			mapScheduleCounter.clear();
			
			while (Job.getJobManager().find(ResourcesPlugin.FAMILY_AUTO_BUILD).length > 0) {
				try {
					Thread.sleep(ASwtBotTestCase.GENERAL_SWTBOT_WAIT_TIME);
				} catch (InterruptedException e) {
					setErrorState("Could not wait for cleaned JobManager due to: " + e.getMessage());
				}
			}
			
			// Start listen to the job manager
			Job.getJobManager().addJobChangeListener(this);
		}
		
		// Now try to save all editors which will trigger the
		// Workspace  builder to execute
		if (bot instanceof SWTWorkbenchBot) {
			SWTWorkbenchBot swtBot = (SWTWorkbenchBot) bot;
			swtBot.saveAllEditors();
		}
	}
	
	@Override
	public synchronized boolean test() throws Exception {
		if (error) {
			return false;
		}
		
		// Try to find all jobs
		Job[] queuedJobs = Job.getJobManager().find(ResourcesPlugin.FAMILY_AUTO_BUILD);

		// Wait that there is no other job still running
		message = "";
		if (queuedJobs.length > 0) {
			for (Job queuedJob : queuedJobs) {
				message += "Job <" + queuedJob + "> is still in the queue with state <" + queuedJob.getState() + "\n"; 
			}
			return false;
		}
		
		// Check if the minimum expected amount of builds has been caught
		if (mapScheduleCounter.size() < expectedBuilder) {
			message = "Counted " + mapScheduleCounter.size() + " jobs, but expected a minimum of " + expectedBuilder + " ...";
			return false;
		}
		
		// Check if all scheduled jobs are also considered done
		message = "";
		for (String numberedJobName : mapScheduleCounter.keySet()) {
			Integer scheduleCounter = mapScheduleCounter.get(numberedJobName);
			if (scheduleCounter != 0) {
				message += "Job < " + numberedJobName + "> is not done as often it got scheduled ...\n";
			}
		}
		
		// If there is no error message left, things are fine.
		if (!message.isEmpty()) {
			return false;
		} else {
			Job.getJobManager().removeJobChangeListener(this);
			return true;
		}
	}

	@Override
	public String getFailureMessage() {
		Job.getJobManager().removeJobChangeListener(this);
		return message;
	}

	@Override
	public void aboutToRun(IJobChangeEvent event) {
	}

	@Override
	public void awake(IJobChangeEvent event) {
	}

	@Override
	public synchronized void done(IJobChangeEvent event) {
		if (event.getJob().toString().contains(WORKSPACE_BUILDER_NAME)) {
			String numberedJobName = event.getJob().toString();
			Integer scheduleCount = mapScheduleCounter.get(numberedJobName);
			if (scheduleCount == null) {
				setErrorState("Job <" + numberedJobName + "> being done but not scheduled ...");
			} else {
				scheduleCount--;
			}

			mapScheduleCounter.put(numberedJobName, scheduleCount);

			if (scheduleCount < 0) {
				setErrorState("Job <" + numberedJobName + "> finished more often than being scheduled ...");
			}
		}
	}

	@Override
	public void running(IJobChangeEvent event) {
	}

	private static final String WORKSPACE_BUILDER_NAME = "Building workspace";
	
	@Override
	public synchronized void scheduled(IJobChangeEvent event) {
		String numberedJobName = event.getJob().toString();
		System.out.println("SWTBot: Job <" + numberedJobName + "> got scheduled...");
		if (numberedJobName.contains(WORKSPACE_BUILDER_NAME)) {
			Integer scheduleCount = mapScheduleCounter.get(numberedJobName);
			if (scheduleCount == null) {
				scheduleCount = 1;
			} else {
				scheduleCount++;
			}
			
			mapScheduleCounter.put(numberedJobName, scheduleCount);
			
			System.out.println("SWTBot: Job <" + numberedJobName + "> got scheduled " + scheduleCount + " times ...");
			
			if (scheduleCount > 1) {
				setErrorState("Job <" + numberedJobName + "> got scheduled " + scheduleCount + " times ...");
			}
		}
	}

	@Override
	public void sleeping(IJobChangeEvent event) {
	}
}
