package com.app.bookstore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;
import static spark.Spark.awaitStop;
import static spark.Spark.stop;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LibraryAPITest {
	@BeforeEach
	void setUp() throws InterruptedException{
		new Thread(() -> LibraryAPI.main(new String[] {})).start();
		Thread.sleep(3000);
	}
	
	@AfterEach
	void tearDown()throws InterruptedException{
		stop();
		awaitStop();
		Thread.sleep(1000);
	}
	
	private String sendGetRequest(String endpoint) throws IOException{
		URL url = new URL("http://localhost:4567" + endpoint);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuilder content = new StringBuilder();
		
		while((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		
		in.close();
		conn.disconnect();
		return content.toString();
	}
	
	@Test
	void testGetAllBooks() throws IOException{
		String response = sendGetRequest("/books");
		assertTrue(response.contains("1984") || response.contains("Le Petit Prince"));
	}
}
