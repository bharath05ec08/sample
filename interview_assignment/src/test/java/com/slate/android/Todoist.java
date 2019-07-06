package com.slate.android;

import java.net.URISyntaxException;

import org.junit.Test;
import com.slate.api.TodoistAPI;

public class Todoist extends BaseTestcase{
	
	@Test
	public void createProject() throws Exception {		
		// API call to create the project
		TodoistAPI.createProject("PROJ_"+TIMESTAMP.getTimestamp());
		
		TODOIST_PAGE.loginTodoist("bharath05ec08@gmail.com", "sudhahar");
		TODOIST_PAGE.movetoProjectFolder();
		TODOIST_PAGE.verifyProjectDisplayed("PROJ_"+TIMESTAMP.getTimestamp());
	}

	@Test
	public void createTask() throws URISyntaxException {
		TODOIST_PAGE.loginTodoist("bharath05ec08@gmail.com", "sudhahar");
		TODOIST_PAGE.movetoProject("TEST_PROJ");
		TODOIST_PAGE.addTask(TIMESTAMP.getTimestamp());	
		
		// API call to verify the task
		TodoistAPI.verifyTask(TIMESTAMP.getTimestamp());
	}

	@Test
	public void reopenTask() throws Exception{
		
		TODOIST_PAGE.loginTodoist("bharath05ec08@gmail.com", "sudhahar");
		TODOIST_PAGE.movetoProject("TEST_PROJ");
		TODOIST_PAGE.addTask(TIMESTAMP.getTimestamp());
		TODOIST_PAGE.completeTask(TIMESTAMP.getTimestamp());
		
		// API call to reopen the task
		TodoistAPI.reopenTask(TASK_ID);
		
		TODOIST_PAGE.verifyTaskDisplayed(TIMESTAMP.getTimestamp());
	}

}
