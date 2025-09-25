package com.app;

import java.util.Scanner;

public class CinemaApp {
	private CinemaBooking cinema;
	private Scanner scanner;
	
	public CinemaApp() {
		cinema = new CinemaBooking();
		scanner = new Scanner(System.in);
	}
	
	public void start() {
		System.out.println("Système de réservation de cinéma");
		
		while(true){
			displayMenu();
			String choice = scanner.nextLine();
			
			switch(choice) {
				case "1": 
					listMovies();
					break;
				case "2":
					bookSeat();
					break;
				case "3":
					checkSeatAvailability();
					break;
				case "4":
					showRevenue();
					break;
				case "5":
					System.out.println("Au revoire");
					break;
				default:
					System.out.println("X Option invalide");
					
			}
		}
	}
	
	private void displayMenu() {
		System.out.println("\n=== Menu Principal ===");
		System.out.println("1. Lister les films");
		System.out.println("2. Réserver un siège");
		System.out.println("3. Vérifier disponibilité siège");
		System.out.println("4. Afficher le revenu");
		System.out.println("5. Quitter");
		System.out.println("Votre choix : ");
	}
	
	private void listMovies() {
		System.out.println("\n=== Filmes Disponibles ===");
		System.out.println("AVG001 - Avengers (120min) - 10.0€");
		System.out.println("LOTR002 - Lord of the rings (180min) - 12.0€");
		System.out.println("INC003 - Inception (140min) - 11.0€");
	}
	
	public void bookSeat() {
		System.out.println("\n=== Réserver un siège ===");
		System.out.print("ID du film : ");
		String movieId = scanner.nextLine();
		
		System.out.print("Votre nom : ");
		String customerName = scanner.nextLine();
		
		System.out.print("Numéro de siège (1-50) : ");
		int seatNumber = Integer.parseInt(scanner.nextLine());
		
		try {
			Booking booking = cinema.bookSeat(movieId, customerName, seatNumber);
			System.out.println("Réservation réussie ! ");
			System.out.println("Détails : "+ booking);
		}catch(Exception e) {
			System.out.println("X Erreur : "+ e.getMessage());
		}
	}
	
	private void checkSeatAvailability() {
		System.out.println("\n=== Vérifier Disponibilité ===");
		System.out.print("ID du film : ");
		String movieId = scanner.nextLine();
		
		System.out.print("Numéro de siège : ");
		int seatNumber = Integer.parseInt(scanner.nextLine());
		
		if(cinema.isSeatBooked(movieId, seatNumber)) {
			System.out.println("X Siège "+seatNumber+" déja réservé");
		}else {
			System.out.println("Siège "+seatNumber+" disponible");
		}
	}
	
	private void showRevenue() {
		System.out.println("\n=== Revenus ===");
		double revenueAvg = cinema.calculateTotalRevenue("AVG001");
		double revenueLotr = cinema.calculateTotalRevenue("LOTR002");
		double revenueInc = cinema.calculateTotalRevenue("INC003");
		double total = revenueAvg + revenueLotr + revenueInc;
		
		System.out.printf("Avengers : %.2f€\n", revenueAvg);
		System.out.printf("Lord of the rings : %.2f€\n", revenueLotr);
		System.out.printf("Inception : %.2f€\n", revenueInc);
		System.out.printf("Total : %.2f€\n", total);
	}
	
	public static void main(String[] args) {
		new CinemaApp().start();
	}
}
