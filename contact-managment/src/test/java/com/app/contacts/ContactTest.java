package com.app.contacts;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
	@Test
	void tesContactCreation() {
		Contact contact = new Contact(1, "John Doe", "john@gmail.com", "0123456789");
		
		assertEquals(1, contact.getId());
		assertEquals("John Doe", contact.getName());
		assertEquals("john@gmail.com", contact.getEmail());
		assertEquals("0123456789", contact.getPhone());
	}
	
	@Test
	void testContactSetters() {
		Contact contact = new Contact(1, "Nom", "email@test.com", "000000000");
		
		contact.setName("Nouveau Nom");
		contact.setEmail("nouveau@email.com");
		contact.setPhone("1111111111");
		
		assertEquals("Nouveau Nom", contact.getName());
		assertEquals("nouveau@email.com", contact.getEmail());
		assertEquals("1111111111", contact.getPhone());
	}
	
	@Test
	void testContactToString() {
		Contact contact = new Contact(1, "Test", "test@test.com", "0123456789");
		String result = contact.toString();
		
		assertTrue(result.contains("Test"));
		assertTrue(result.contains("test@test.com"));
		assertTrue(result.contains("0123456789"));
	}
}
