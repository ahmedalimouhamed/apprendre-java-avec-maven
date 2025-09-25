package com.app.bookstore;
import com.google.gson.Gson;
import java.util.*;
import static spark.Spark.*;

public class LibraryAPI {
	private static Map<Integer, Book> books = new HashMap<>();
	private static int nextId = 1;
	private static Gson gson = new Gson();
	
	public static void main(String[] args) {
		port(4567);
		books.put(nextId, new Book(nextId++, "1984", "George Orwell", 1949));
		books.put(nextId, new Book(nextId++, "Le Petit Prince", "Antonie de saint-Exupéry", 1943));
		
		get("/books", (req, res) -> {
			res.type("application/json");
			return gson.toJson(books.values());
		});
		
		get("/books/:id", (req, res) -> {
			int id = Integer.parseInt(req.params(":id"));
			Book book = books.get(id);
			
			if(book != null) {
				res.type("application/json");
				return gson.toJson(book);
			}else{
				res.status(404);
				return "Livre non trouvé";
			}
		});
		
		post("/books", (req, res) -> {
			Book newBook = gson.fromJson(req.body(), Book.class);
			newBook.setId(nextId);
			books.put(nextId++, newBook);
			res.status(201);
			return gson.toJson(newBook);
		});
	}
}
