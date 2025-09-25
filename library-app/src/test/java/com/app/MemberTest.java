package com.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
	@Test
	void testMemberCreation() {
		Member member = new Member("MEM001", "John doe", "john@gmail.com");
		
		assertEquals("MEM001", member.getMemberId());
		assertEquals("John doe", member.getName());
		assertEquals("john@gmail.com", member.getEmail());
	}
	
	@Test
	void testMemberSetters() {
		Member member = new Member("MEM001", "Test", "test@test.com");
		
		member.setName("Nouveau Nom");
		member.setEmail("nouveau@email.com");
		
		assertEquals("Nouveau Nom", member.getName());
		assertEquals("nouveau@email.com", member.getEmail());
	}
}
