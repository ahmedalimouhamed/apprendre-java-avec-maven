package com.app.bookstore;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
	@Test
	void testBookCreation() {
		Book book = new Book(1, "1984", "George Orwell", 1949);
		
		assertEquals(1, book.getId());
		assertEquals("1984", book.getTitle());
		assertEquals("George Orwell", book.getAuthor());
		assertEquals(1949, book.getYear());
	}
	
	@Test
	void testSetters() {
		Book book = new Book(1, "Titre", "Auteur", 2000);
		book.setTitle("Nouveau Titre");
		book.setAuthor("Nouvel Auteur");
		book.setYear(2024);
		
		assertEquals("Nouveau Titre", book.getTitle());
		assertEquals("Nouvel Auteur", book.getAuthor());
		assertEquals(2024, book.getYear());
	}
}
