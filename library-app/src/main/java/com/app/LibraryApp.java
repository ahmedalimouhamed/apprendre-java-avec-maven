package com.app;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {
	
	private LibrarySystem library;
	private Scanner scanner;
	
	public LibraryApp() {
		library = new LibrarySystem();
		scanner = new Scanner(System.in);
		initializeSampleData();
	}
	
	private void initializeSampleData() {
		library.addBook(new Book("123-456", "Le petit prince", "Antoinie de saint-exupéry", 1943));
		library.addBook(new Book("987-654", "1984", "George Orwell", 1949));
		library.addBook(new Book("874-143", "Dune", "Frank Herbert", 1965));
		library.addBook(new Book("747-782", "l\'étranger", "Albert Camus", 1942));
		library.registerMember(new Member("MEM001", "Alice Dupont", "alice@gmail.com"));
		library.registerMember(new Member("MEM002", "Charlie Brown", "charlie@gmail.com"));
	}
	
	public void start() {
		System.out.println("Système de gestion de Bibliothéque");
		while(true) {
			displayMenu();
			String choice = scanner.nextLine();
			
			switch(choice) {
				case "1": searchBooks(); break;
				case "2": loanBook(); break;
				case "3": returnBook(); break;
				case "4": addNewBook(); break;
				case "5": registerNewMember(); break;
				case "6": System.out.println("Au revoir !"); break;
				default: System.out.println("X option Invalide!");
			}
		}
	}
	
	private void displayMenu() {
		System.out.println("\n=== Menu Principal ===");
		System.out.println("1. Recherher un livre");
		System.out.println("2. Emprunter un livre");
		System.out.println("3. Reoutrner un livre");
		System.out.println("4. Ajouter un nouveau livre");
		System.out.println("5. Inscrire un nouveau membre");
		System.out.println("6. Quitter");
		System.out.println("Votre choix : ");
	}
	
	private void searchBooks() {
		System.out.println("\n=== Rechercher un livre ===");
		System.out.println("Mot-clé (titre ou auteur) : ");
		String keyword = scanner.nextLine();
		
		List<Book> results = library.searchBooks(keyword);
		
		if(results.isEmpty()) {
			System.out.println("X Aucun livre trouvé.");
		}else {
			System.out.println("Livres trouvés ("+results.size()+") : ");
			for(Book book : results) {
				String status = book.isAvailable() ? "Available" : "Emprunté";
				System.out.printf("- %s par %s (%d) - %s\n", book.getTitle(), book.getAuthor(), book.getYear(), status);
			}
		}
	}
	
	private void loanBook() {
		System.out.print("=== Emprunt de livre ===");
		System.out.print("ISBN du Livre : ");
		String isbn= scanner.nextLine();
		
		System.out.print("ID du membre : ");
		String memberId = scanner.nextLine();
		
		System.out.print("Durée de l'emprunte (Jours) : ");
		int days = Integer.parseInt(scanner.nextLine());
		
		try {
			Loan loan = library.loanBook(isbn, memberId, days);
			System.out.println("Emprunt réussi");
			System.out.println("Date de retour : "+loan.getDueDate());
		}catch(Exception e) {
			System.out.println("X Erreur : "+ e.getMessage());
		}
	}
	
	private void returnBook() {
		System.out.println("\n=== Retour de livre ===");
		System.out.print("ISBN du livre à retourner : ");
		String isbn = scanner.nextLine();
		
		try {
			library.returnBook(isbn);
			System.out.println("Livre retouré avec succès");
		}catch(Exception e) {
			System.out.println("X Erreur : " + e.getMessage());
		}
	}
	
	private void addNewBook() {
		System.out.println("\n=== Ajout d'un nouveau livre ===");
		System.out.print("ISBN : ");
		String isbn = scanner.nextLine();
		
		System.out.print("Titre : ");
		String title = scanner.nextLine();
		
		System.out.print("Auteur : ");
		String author = scanner.nextLine();
		
		System.out.println("Année : ");
		int year = Integer.parseInt(scanner.nextLine());
		
		Book newBook = new Book(isbn, title, author, year);
	}
	
	private void registerNewMember() {
		System.out.println("\n=== Inscription d'un nouveau membre ===");
		System.out.print("Id du membre : ");
		String memberId = scanner.nextLine();
		
		System.out.print("Nom : ");
		String name = scanner.nextLine();
		
		System.out.print("Email : ");
		String email = scanner.nextLine();
		
		Member newMember = new Member(memberId, name, email);
		library.registerMember(newMember);
		System.out.print("Mmbre inscrit avec succès !");
	}

	public static void main(String[] args) {
		new LibraryApp().start();
	}

}
