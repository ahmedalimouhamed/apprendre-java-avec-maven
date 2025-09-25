package com.app;
import java.time.LocalDateTime;
import java.util.*;

public class CinemaBooking {
	private Map<String, Movie> movies;
	private Map<String, List<Booking>> bookings;
	
	public CinemaBooking() {
		movies = new HashMap<>();
		bookings = new HashMap<>();
		initializeMovies();
	}
	
	private void initializeMovies() {
		movies.put("AVG001", new Movie("AVG001", "Avengers", 120, 10.0));
		movies.put("LOTR002", new Movie("LOTR002", "Lord of the rings", 180, 12.0));
		movies.put("INC003", new Movie("INC003", "Inception", 148, 11.0));
	}
	
	public Booking bookSeat(String movieId, String customerName, int seatNumber) {
		if(!movies.containsKey(movieId)) {
			throw new IllegalArgumentException("Film non trouvé");
		}
		
		Movie movie = movies.get(movieId);
		
		if(seatNumber <1 || seatNumber > 50) {
			throw new IllegalArgumentException("Siège introuvable");
		}
		
		if(isSeatBooked(movieId, seatNumber)) {
			throw new IllegalStateException("Siège déja réservé");
		}
		
		Booking booking = new Booking(
				UUID.randomUUID().toString(),
				movieId,
				customerName,
				seatNumber,
				LocalDateTime.now(),
				movie.getPrice()
		);
		
		bookings.computeIfAbsent(movieId,  k -> new ArrayList<>()).add(booking);
		return booking;
	}
	
	public boolean isSeatBooked(String movieId, int seatNumber) {
		return bookings.getOrDefault(movieId, Collections.emptyList())
				.stream()
				.anyMatch(b -> b.getSeatNumber() == seatNumber);
	}
	
	public double calculateTotalRevenue(String movieId) {
		return bookings.getOrDefault(movieId, Collections.emptyList())
				.stream()
				.mapToDouble(Booking::getPrice)
				.sum();
	}
	
	
	
	@Override
	public String toString() {
		return "CinemaBooking [movies=" + movies + ", bookings=" + bookings + "]";
	}
	
}
