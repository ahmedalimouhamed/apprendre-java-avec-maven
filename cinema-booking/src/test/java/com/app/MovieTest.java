package com.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
	@Test
	void testMovieCreation() {
		Movie movie = new Movie("AVG001", "Avengers", 120, 10.0);
		
		assertEquals("AVG001", movie.getId());
		assertEquals("Avengers", movie.getTitle());
		assertEquals(120, movie.getDuration());
		assertEquals(10.0, movie.getPrice());
	}
	
	@Test
	void testMovieSetters() {
		Movie movie = new Movie("TEST", "Test Movie", 100, 5.0);
		
		movie.setTitle("Nouveau Titre");
		movie.setDuration(150);
		movie.setPrice(12.5);
		
		assertEquals("Nouveau Titre", movie.getTitle());
		assertEquals(150, movie.getDuration());
		assertEquals(12.5, movie.getPrice());
	}
	
	@Test
	void testMovieToString() {
		Movie movie = new Movie("TEST", "Test Movie", 120, 10.0);
		String result = movie.toString();
		
		assertTrue(result.contains("Test Movie"));
		assertTrue(result.contains("120"));
		assertTrue(result.contains("10.0"));
	}
}



















