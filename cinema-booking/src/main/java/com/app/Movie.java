package com.app;

public class Movie {
	private String id;
	private String title;
	private int duration;
	private double price;
	public Movie(String id, String title, int duration, double price) {
		super();
		this.id = id;
		this.title = title;
		this.duration = duration;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", duration=" + duration + ", price=" + price + "]";
	}
	
}
