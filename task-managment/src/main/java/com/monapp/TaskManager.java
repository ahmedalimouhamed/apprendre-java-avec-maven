package com.monapp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
	private List<Task> tasks;
	private Scanner scanner;
	
	public TaskManager() {
		tasks = new ArrayList<>();
		scanner = new Scanner(System.in);
	}
	
	public void addTask(String description, LocalDate dueDate) {
		tasks.add(new Task(description, dueDate));
	}
	
	public void displayTasks() {
		for(int i = 0; i < tasks.size(); i++) {
			System.out.println((i + 1) + ". "+tasks.get(i));
		}
	}
	
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
