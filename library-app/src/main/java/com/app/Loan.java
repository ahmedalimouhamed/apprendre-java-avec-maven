package com.app;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {
	private String isbn;
	private String memberId;
	private LocalDate loanDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private double fine;
	
	public Loan(String isbn, String memberId, LocalDate loanDate, LocalDate dueDate) {
		this.isbn = isbn;
		this.memberId = memberId;
		this.loanDate = loanDate;
		this.dueDate = dueDate;
		this.returnDate = null;
		this.fine = 0.0;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public LocalDate getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(LocalDate loanDate) {
		this.loanDate = loanDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	@Override
	public String toString() {
		return "Loan [isbn=" + isbn + ", memberId=" + memberId + ", loanDate=" + loanDate + ", dueDate=" + dueDate
				+ ", returnDate=" + returnDate + ", fine=" + fine + "]";
	}
	
	public boolean isOverdue() {
		return LocalDate.now().isAfter(dueDate) && returnDate == null;
	}
}
