package com.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
	@Test
	void testBookCreation() {
		Book book = new Book("123-456", "Le petit prince", "Antonie de saint-exupéry", 1943);
		
		assertEquals("123-456", book.getIsbn());
		assertEquals("Le petit prince", book.getTitle());
		assertEquals("Antonie de saint-exupéry", book.getAuthor());
		assertEquals(1943, book.getYear());
		assertTrue(book.isAvailable());
	}
	
	@Test
	void testBookAvailability() {
		Book book = new Book("123", "Test", "Auteur", 2000);
		assertTrue(book.isAvailable());
		
		book.setAvailable(false);
		assertFalse(book.isAvailable());
		
		book.setAvailable(true);
		assertTrue(book.isAvailable());
	}
	
	@Test
	void testBookToString() {
		Book book = new Book("123", "Test", "Auteur", 2000);
		String result = book.toString();
		
		assertTrue(result.contains("Test"));
		assertTrue(result.contains("Auteur"));
		assertTrue(result.contains("2000"));
	}
}
