package com.app;

import java.util.Objects;

public class Book {
	private String isbn;
	private String title;
	private String author;
	private int year;
	private boolean available;
	
	public Book(String isbn, String title, String author, int year) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.year = year;
		this.available = true;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", year=" + year + ", available="
				+ available + "]";
	}

	@Override
	public int hashCode() {
		return isbn.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(isbn, other.isbn);
	}
}
