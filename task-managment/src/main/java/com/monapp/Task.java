package com.monapp;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class Task 
{
    private String description;
    private boolean completed;
    private LocalDate dueDate;
    
    public Task(String description, LocalDate dueDate) {
    	this.description = description;
    	this.dueDate = dueDate;
    	this.completed = false;
    }
    
    public void markCompleted() {
    	this.completed = true;
    }
    
    public boolean isCompleted() {
    	return this.completed;
    }
    
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
    public String toString() {
    	return String.format("[%s] %s - Echeance: %s", completed? "X": " ", description, dueDate);
    }
}
