package com.app;

import java.time.LocalDate;
import java.util.*;
import java.util.Scanner;

public class LibrarySystem {
	private Map<String, Book> books;
	private Map<String, Member> members;
	private List<Loan> loans;
	
	public LibrarySystem() {
		this.books = new HashMap<>();
		this.members = new HashMap<>();
		this.loans = new ArrayList<>();
	}
	
	public void addBook(Book book) {
		books.put(book.getIsbn(), book);
	}
	
	public Book getBook(String isbn) {
		return books.get(isbn);
	}
	
	public List<Book>getAllBooks(){
		return new ArrayList<>(books.values());
	}
	
	public boolean removeBook(String isbn) {
		if(books.containsKey(isbn)) {
			Book book = books.get(isbn);
			if(!book.isAvailable()) {
				throw new IllegalStateException("Impossible de supprimer un livre emprunté");
			}
			
			books.remove(isbn);
			return true;
		}
		return false;
	}
	
	public void registerMember(Member member) {
		members.put(member.getMemberId(), member);
	}
	
	public Member getMember(String memberId) {
		return members.get(memberId);
	}
	
	public List<Member> getAllMembers(){
		return new ArrayList<>(members.values());
	}
	
	public Loan loanBook(String isbn, String memberId, int days) {
		if(!books.containsKey(isbn)) {
			throw new IllegalArgumentException("Livre non trouvé avec ISBN : "+isbn);
		}
		
		if(!members.containsKey(memberId)) {
			throw new IllegalArgumentException("Membre non trouvé avec id : " + memberId);
		}
		
		Book book = books.get(isbn);
		if(!book.isAvailable()) {
			throw new IllegalStateException("Livre déja emprunté : " + book.getTitle());
		}
		
		LocalDate loanDate = LocalDate.now();
		LocalDate dueDate = loanDate.plusDays(days);
		
		Loan loan = new Loan(isbn, memberId, loanDate, dueDate);
		book.setAvailable(false);
		loans.add(loan);
		
		return loan;
	}
	
	public void returnBook(String isbn) {
		Loan activeLoan = loans.stream()
				.filter(loan -> loan.getIsbn().equals(isbn) && loan.getReturnDate() == null)
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("Aucun emprunt actif trouvé pour ISBN : "+isbn));
		
		LocalDate returnDate = LocalDate.now();
		activeLoan.setReturnDate(returnDate);
		
		Book book = books.get(isbn);
		book.setAvailable(true);
		
		if(returnDate.isAfter(activeLoan.getDueDate())) {
			long daysLate = returnDate.toEpochDay() - activeLoan.getDueDate().toEpochDay();
			double fine = daysLate * 0.50;
			activeLoan.setFine(fine);
			System.out.printf("Livre retourné avec %.2f€ de frais de retard (%d jours)\n", fine, daysLate);
		}
	}
	
	public List<Book> searchBooks(String keyword){
		if(keyword == null || keyword.trim().isEmpty()) {
			return getAllBooks();
		}
		
		String searchTerm = keyword.toLowerCase();
		return books.values().stream()
				.filter(book -> 
					book.getTitle().toLowerCase().contains(searchTerm) ||
					book.getAuthor().toLowerCase().contains(searchTerm) ||
					book.getIsbn().toLowerCase().contains(searchTerm)
				)
				.toList();
				
	}
	
	public List<Loan> getActiveLoans(){
		return loans.stream()
				.filter(loan -> loan.getReturnDate() == null)
				.toList();
	}
	
	public List<Loan> getMemberLoans(String memberId){
		return loans.stream()
				.filter(loan -> loan.getMemberId().equals(memberId))
				.toList();
	}
	
	public List<Loan> getOverdueLoans(){
		return loans.stream()
				.filter(Loan::isOverdue)
				.toList();
	}
	
	public double calculateTotalFines() {
		return loans.stream()
				.mapToDouble(Loan::getFine)
				.sum();
	}
	
	public int getAvailablrBooksCount() {
		return (int) books.values().stream()
				.filter(Book::isAvailable)
				.count();
	}
	
	public int getBorrowedBooksCount() {
		return (int) books.values().stream()
				.filter(book -> !book.isAvailable())
				.count();
	}

}
