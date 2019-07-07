package com.slate.android;

import java.net.URISyntaxException;

import org.testng.annotations.Test;

//import org.junit.Test;
import com.slate.api.TodoistAPI;

public class Todoist extends BaseTestcase{

	@Test(priority = 0)
	public void createTask() throws URISyntaxException {
		TODOIST_PAGE.loginTodoist(USER_NAME, PASSWORD);
		TODOIST_PAGE.movetoProject("TEST_PROJ");
		TODOIST_PAGE.addTask(TASK_NAME);	
		
		// API call to verify the task
		TodoistAPI.verifyTask(TASK_NAME);
	}

	@Test(priority = 0)
	public void reopenTask() throws Exception{
		
		TODOIST_PAGE.loginTodoist(USER_NAME, PASSWORD);
		TODOIST_PAGE.movetoProject("TEST_PROJ");
		TODOIST_PAGE.addTask(TASK_NAME);
		TASK_ID = TodoistAPI.verifyTask(TASK_NAME); 
		TODOIST_PAGE.completeTask(TASK_NAME);
		
		// API call to reopen the task
		TodoistAPI.reopenTask(TASK_ID);
		
		TODOIST_PAGE.verifyTaskDisplayed(TASK_NAME);
	}	
	
	@Test(priority = 1)
	public void createProject() throws Exception {		
		// API call to create the project
		TodoistAPI.createProject("PROJ_"+TIMESTAMP.getTimestamp());
		
		TODOIST_PAGE.loginTodoist(USER_NAME, PASSWORD);
		TODOIST_PAGE.movetoProjectFolder();
		TODOIST_PAGE.verifyProjectDisplayed("PROJ_"+TIMESTAMP.getTimestamp());
	}


}
