package com.monapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {
	private TaskManager taskManager;
	private LocalDate dateTest;
	
	@BeforeEach
	public void setUp() {
		taskManager = new TaskManager();
		dateTest = LocalDate.now().plusDays(7);
	}
	
	@Test
	@DisplayName("Test d'ajout de tâche")
	void testAjoutTask() {
		taskManager.addTask("Nouvelle tâche", dateTest);
	}
	
	@Test
	@DisplayName("Test d'affichage des tâches")
	void testDisplayTasks() {
		taskManager.addTask("Tâche 1", dateTest);
		taskManager.addTask("Tâche 2", dateTest.plusDays(1));
		
		assertDoesNotThrow(() -> taskManager.displayTasks());
	}
}
