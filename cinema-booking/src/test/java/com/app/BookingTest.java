package com.app;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {
	@Test
	void testBookingCreation() {
		LocalDateTime now = LocalDateTime.now();
		Booking booking = new Booking("BOOK001", "MOV001", "John Doe", 5, now, 10.0);
		
		assertEquals("BOOK001", booking.getId());
		assertEquals("MOV001", booking.getMovieId());
		assertEquals("John Doe", booking.getCustomerName());
		assertEquals(5, booking.getSeatNumber());
		assertEquals(now, booking.getBookingTime());
		assertEquals(10.0, booking.getPrice());
	}
	
	@Test
	void testBookingSetters() {
		Booking booking = new Booking("BOOK001", "MOV001", "Test", 1, LocalDateTime.now(), 5.0);
		booking.setCustomerName("Nouveau Nom");
		booking.setSeatNumber(10);
		booking.setPrice(15.0);
		
		assertEquals("Nouveau Nom", booking.getCustomerName());
		assertEquals(10, booking.getSeatNumber());
		assertEquals(15.0, booking.getPrice());
	}
}
