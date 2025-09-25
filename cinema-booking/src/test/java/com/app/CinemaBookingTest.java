package com.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CinemaBookingTest {
	private CinemaBooking cinema;
	
	@BeforeEach
	void setUp() {
		cinema = new CinemaBooking();
	}
	
	@Test
	void testInitialization() {
		assertDoesNotThrow(() -> cinema.bookSeat("AVG001", "Test Client", 1));
	}
	
	@Test
	void testBookSeatSuccess() {
		Booking booking = cinema.bookSeat("AVG001", "John Doe", 5);
		
		assertNotNull(booking);
		assertEquals("John Doe", booking.getCustomerName());
		assertEquals(10.0, booking.getPrice());
	}
	
	@Test
	void testBookSeatMovieNotFound() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			cinema.bookSeat("INEXISTANT", "Client", 1);
		});
		assertEquals("Film non trouvé", exception.getMessage());
	}
	
	@Test
	void testBookSeatInvalidNumber() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			cinema.bookSeat("AVG001", "Clint", 0);
		});
		
		assertEquals("Siège introuvable", exception.getMessage());
	}
	
	@Test
	void testBookSeatAlreadyBooked() {
		cinema.bookSeat("AVG001", "Client1", 5);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			cinema.bookSeat("AVG001", "Client2", 5);
		});
		
		assertEquals("Siège déja réservé", exception.getMessage());
	}
	
	@Test
	void testIsSeatBooked() {
		assertFalse(cinema.isSeatBooked("AVG001", 5));
		
		cinema.bookSeat("AVG001", "Client", 5);
		
		assertTrue(cinema.isSeatBooked("AVG001", 5));
		assertFalse(cinema.isSeatBooked("AVG001", 6));
	}
	
	@Test
	void testCalculateTotalRevenue() {
		assertEquals(0.0, cinema.calculateTotalRevenue("AVG001"));
		
		cinema.bookSeat("AVG001", "Client1", 1);
		cinema.bookSeat("AVG001", "Client2", 2);
		cinema.bookSeat("LOTR002", "Client3", 1);
		
		assertEquals(20.0, cinema.calculateTotalRevenue("AVG001"));
		assertEquals(12.0, cinema.calculateTotalRevenue("LOTR002"));
	}
	
	@Test
	void testMultipleMoviesBooking() {
		Booking booking1 = cinema.bookSeat("AVG001", "Client1", 1);
		Booking booking2 = cinema.bookSeat("LOTR002", "Client2", 1);
		Booking booking3 = cinema.bookSeat("INC003", "Client3" , 1);
		
		assertNotNull(booking1);
		assertNotNull(booking2);
		assertNotNull(booking3);
		
		assertEquals(10.0, cinema.calculateTotalRevenue("AVG001"));
		assertEquals(12.0, cinema.calculateTotalRevenue("LOTR002"));
		assertEquals(11.0, cinema.calculateTotalRevenue("INC003"));
	}
}



















