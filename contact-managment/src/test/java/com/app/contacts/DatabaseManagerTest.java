package com.app.contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseManagerTest {
	private DatabaseManager dbManager;
	
	@BeforeEach
	void setUp() {
		dbManager = new DatabaseManager();
	}
	
	@AfterEach
	void tearDown() {
		dbManager.cleanup();
	}
	
	@Test
	void testAddContact() {
		Contact contact = new Contact(0, "Test Contact", "test@gmail.com", "0123456789");
		dbManager.addContact(contact);
		
		List<Contact> contacts = dbManager.getAllContacts();
		assertEquals(1, contacts.size());
		assertEquals("Test Contact", contacts.get(0).getName());
	}
	
	@Test
	void testGetAllContacts() {
		dbManager.addContact(new Contact(0, "contact 1", "contact1@gmail.com", "1111111111"));
		dbManager.addContact(new Contact(0, "contact 2", "contact2@gmail.com", "2222222222"));
		
		List<Contact> contacts = dbManager.getAllContacts();
		assertEquals(2, contacts.size());
		assertEquals("contact 1", contacts.get(0).getName());
		assertEquals("contact 2", contacts.get(1).getName());
	}
}
