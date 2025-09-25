package com.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class LoanTest {
	@Test
	void testLoanCreation() {
		LocalDate today = LocalDate.now();
		LocalDate dueDate = today.plusDays(14);
		
		Loan loan = new Loan("ISBN123", "MEM001", today, dueDate);
		
		assertEquals("ISBN123", loan.getIsbn());
		assertEquals("MEM001", loan.getMemberId());
		assertEquals(today, loan.getLoanDate());
		assertEquals(dueDate, loan.getDueDate());
		assertNull(loan.getReturnDate());
		assertEquals(0.0, loan.getFine());
	}
	
	@Test
	void testReturn() {
		Loan loan = new Loan("ISBN123", "MEM001", LocalDate.now(), LocalDate.now().plusDays(14));
		assertNull(loan.getReturnDate());
		LocalDate returnDate = LocalDate.now().plusDays(10);
		loan.setReturnDate(returnDate);
		assertEquals(returnDate, loan.getReturnDate());
	}
	
	@Test
	void testLoanFineCalculation() {
		LocalDate loanDate = LocalDate.of(2024,  1, 1);
		LocalDate dueDate = LocalDate.of(2024, 1, 15);
		LocalDate returnDate = LocalDate.of(2024, 1, 20);
		
		Loan loan = new Loan("ISBN123", "MEM001", loanDate, dueDate);
		loan.setReturnDate(returnDate);
		loan.setFine(2.50);
		
		assertEquals(2.50, loan.getFine());
	}
}
