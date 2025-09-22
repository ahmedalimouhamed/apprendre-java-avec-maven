package com.monapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class TaskTest {
	private Task task;
	private LocalDate dateTest;
	
	@BeforeEach
	public void setUp() {
		dateTest = LocalDate.of(2024, 12, 31);
		task = new Task("Test tâche", dateTest);
	}
	
	@Test
	@DisplayName("Test de la créattion d'une tache")
	void testCreationTask() {
		assertFalse(task.isCompleted());
		assertEquals("Test tâche", task.getDescription());
		assertEquals(dateTest, task.getDueDate());
	}
	
	@Test
	@DisplayName("Test du marquage comme complétée")
	void testMarkCompleted() {
		task.markCompleted();
		assertTrue(task.isCompleted());
	}
	
	@Test
	@DisplayName("Test de la méthode toString")
	void testToString() {
		String result = task.toString();
		assertTrue(result.contains("Test tâche"));
		assertTrue(result.contains("2024-12-31"));
		assertTrue(result.contains("[ ]"));
		
		task.markCompleted();
		result = task.toString();
		assertTrue(result.contains("X"));
	}
}
