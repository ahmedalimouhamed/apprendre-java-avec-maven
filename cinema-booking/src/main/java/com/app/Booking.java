package com.app;

import java.time.LocalDateTime;

public class Booking {
	private String id;
	private String movieId;
	private String customerName;
	private int seatNumber;
	private LocalDateTime bookingTime;
	private double price;
	public Booking(String id, String movieId, String customerName, int seatNumber, LocalDateTime bookingTime,
			double price) {
		super();
		this.id = id;
		this.movieId = movieId;
		this.customerName = customerName;
		this.seatNumber = seatNumber;
		this.bookingTime = bookingTime;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}
	public LocalDateTime getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", movieId=" + movieId + ", customerName=" + customerName + ", seatNumber="
				+ seatNumber + ", bookingTime=" + bookingTime + ", price=" + price + "]";
	}
	
	
}
