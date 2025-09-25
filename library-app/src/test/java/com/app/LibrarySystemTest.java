package com.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class LibrarySystemTest {
	private LibrarySystem library;
	private Book book1, book2;
	private Member member1, member2;
	
	@BeforeEach
	void setUp() {
		library = new LibrarySystem();
		
		book1 = new Book("ISBN001", "Livre 1", "Auteur 1", 2020);
		book2 = new Book("ISBN002", "Livre 2", "Auteur 2", 2021);
		
		member1 = new Member("MEM001", "John Doe", "john@email.com");
		member2 = new Member("MEM002", "Jane Smith", "jane@gmail.com");
		
		library.addBook(book1);
		library.addBook(book2);
		library.registerMember(member1);
		library.registerMember(member2);		
	}
	
	@Test
	void testAddBook() {
		Book newBook = new Book("ISBN003", "Nouveau Livre", "Auteur", 2023);
		library.addBook(newBook);
		
		List<Book> results = library.searchBooks("Nouveau");
		assertEquals(1, results.size());
		assertEquals("Nouveau Livre", results.get(0).getTitle());
	}
	
	@Test
	void testRegisterMember() {
		Member newMember = new Member("MEM003", "TestMember", "test@gmail.com");
		library.registerMember(newMember);
		
		assertDoesNotThrow(() -> library.loanBook("ISBN001", "MEM003", 14));
	}
	
	@Test
	void testLoanBookSuccess() {
		Loan loan = library.loanBook("ISBN001", "MEM001", 14);
		
		assertNotNull(loan);
		assertEquals("ISBN001", loan.getIsbn());
		assertEquals("MEM001", loan.getMemberId());
		assertFalse(book1.isAvailable());
	}
	
	@Test
	void testLoanBookBookNotFound() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			library.loanBook("ISBN_INEXISTANT", "MEM001", 14);
		});
		
		assertEquals("Livre non trouvé avec ISBN : ISBN_INEXISTANT", exception.getMessage());
	}
	
	@Test
	void testLoanBookMemberNotFound() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			library.loanBook("ISBN001", "MEM_INEXISTANT", 14);
		});
		
		assertEquals("Membre non trouvé avec id : MEM_INEXISTANT", exception.getMessage());
	}
	
	@Test
	void testLoanBookAlreadyBorrowed() {
		library.loanBook("ISBN001", "MEM001", 14);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			library.loanBook("ISBN001", "MEM002", 14);
		});
		
		assertEquals("Livre déja emprunté : Livre 1", exception.getMessage());
	}
	
	@Test
	void testReturnBook() {
		library.loanBook("ISBN001", "MEM001", 14);
		assertFalse(book1.isAvailable());
		
		library.returnBook("ISBN001");
		assertTrue(book1.isAvailable());
	}
	
	@Test
	void testReturnBookNotFound() {
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			library.returnBook("ISBN_INEXISTANT");
		});
		
		assertEquals("Aucun emprunt actif trouvé pour ISBN : ISBN_INEXISTANT", exception.getMessage());
	}
	
	@Test 
	void testSearchBooks() {
		List<Book> results = library.searchBooks("Livre");
		assertEquals(2, results.size());
		
		results = library.searchBooks("1");
		assertEquals(1, results.size());
		assertEquals("Livre 1", results.get(0).getTitle());
		
		results = library.searchBooks("Auteur 2");
		assertEquals(1, results.size());
		assertEquals("Livre 2", results.get(0).getTitle());
		
		results = library.searchBooks("Inexistant");
		assertEquals(0, results.size());
	}
	
	@Test
	void testLateReturnFine(){
		Loan loan = library.loanBook("ISBN001", "MEM001", 7);
		loan.setReturnDate(loan.getLoanDate().plusDays(10));
		
		if(loan.getReturnDate().isAfter(loan.getDueDate())) {
			long daysLate = loan.getReturnDate().toEpochDay() - loan.getDueDate().toEpochDay();
			double expectedFine = daysLate * 0.5;
			loan.setFine(expectedFine);
		}
		
		assertTrue(loan.getFine() > 0);
	}
}
