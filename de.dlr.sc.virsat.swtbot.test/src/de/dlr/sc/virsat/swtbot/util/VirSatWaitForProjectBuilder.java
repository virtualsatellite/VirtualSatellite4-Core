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
 *
 */
public class VirSatWaitForProjectBuilder extends DefaultCondition implements IJobChangeListener {

	private boolean error = false;
	private String message = "";
	private int minExpectedWorkspaceBuilder;

	// The Name of the WorkspaceBuilder as they get called by Eclipse
	@SuppressWarnings("restriction")
	private static final String WORKSPACE_BUILDER_NAME = org.eclipse.core.internal.utils.Messages.events_building_0;
	
	/**
	 * Constructor for the condition
	 * @param minExpectedWorkspaceBuilder the minimum amount of builders which are expected to be executed
	 */
	public VirSatWaitForProjectBuilder(int minExpectedWorkspaceBuilder) {
		this.minExpectedWorkspaceBuilder = minExpectedWorkspaceBuilder;
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

	private Map<String, Integer> mapJobScheduleCounters = new HashMap<>();
	
	@Override
	public void init(SWTBot bot) {
		synchronized (this) {
			super.init(bot);
			mapJobScheduleCounters.clear();
			
			// Wait until all jobs that may interfere with this condition are done
			while (Job.getJobManager().find(ResourcesPlugin.FAMILY_AUTO_BUILD).length > 0) {
				try {
					wait(ASwtBotTestCase.GENERAL_SWTBOT_WAIT_TIME);
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
		Job[] currentlyQueuedJobs = Job.getJobManager().find(ResourcesPlugin.FAMILY_AUTO_BUILD);

		// Wait that there is no other job still running or queued
		message = "";
		if (currentlyQueuedJobs.length > 0) {
			for (Job queuedJob : currentlyQueuedJobs) {
				message += "Job <" + queuedJob + "> is still in the queue with state <" + queuedJob.getState() + "\n"; 
			}
			return false;
		}
		
		// Check if the minimum expected amount of builds has been caught
		if (mapJobScheduleCounters.size() < minExpectedWorkspaceBuilder) {
			message = "Counted " + mapJobScheduleCounters.size() + " jobs, but expected a minimum of " + minExpectedWorkspaceBuilder + " ...";
			return false;
		}
		
		// Check if all scheduled jobs are also considered done
		message = "";
		for (String jobNameWithIndex : mapJobScheduleCounters.keySet()) {
			Integer scheduleCounter = mapJobScheduleCounters.get(jobNameWithIndex);
			if (scheduleCounter != 0) {
				message += "Job < " + jobNameWithIndex + "> is not done as often it got scheduled ...\n";
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
	public synchronized void done(IJobChangeEvent event) {
		String jobNameWithIndex = event.getJob().toString();
		if (jobNameWithIndex.contains(WORKSPACE_BUILDER_NAME)) {
			Integer jobScheduleCount = mapJobScheduleCounters.get(jobNameWithIndex);
			if (jobScheduleCount == null) {
				setErrorState("Job <" + jobNameWithIndex + "> being done but not scheduled ...");
			} else {
				jobScheduleCount--;
			}

			mapJobScheduleCounters.put(jobNameWithIndex, jobScheduleCount);

			if (jobScheduleCount < 0) {
				setErrorState("Job <" + jobNameWithIndex + "> finished more often than being scheduled ...");
			}
		}
	}

	@Override
	public synchronized void scheduled(IJobChangeEvent event) {
		String jobNameWithIndex = event.getJob().toString();
		if (jobNameWithIndex.contains(WORKSPACE_BUILDER_NAME)) {
			Integer jobScheduleCount = mapJobScheduleCounters.get(jobNameWithIndex);
			if (jobScheduleCount == null) {
				jobScheduleCount = 1;
			} else {
				jobScheduleCount++;
			}
			
			mapJobScheduleCounters.put(jobNameWithIndex, jobScheduleCount);
			
			System.out.println("SWTBot: Job <" + jobNameWithIndex + "> got scheduled " + jobScheduleCount + " times ...");
			
			if (jobScheduleCount > 1) {
				setErrorState("Job <" + jobNameWithIndex + "> got scheduled " + jobScheduleCount + " times ...");
			}
		}
	}

	@Override
	public void running(IJobChangeEvent event) {
	}

	@Override
	public void sleeping(IJobChangeEvent event) {
	}

	@Override
	public void aboutToRun(IJobChangeEvent event) {
	}

	@Override
	public void awake(IJobChangeEvent event) {
	}
}
