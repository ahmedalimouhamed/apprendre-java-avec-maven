package com.monapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions.*;



/**
 * Unit test for simple App.
 */ 
public class AppTest 
{
    private App app;
    
    @BeforeEach
    public void setUp() {
    	app = new App();
    }
    
    @Test
    @DisplayName("Test de l'addition avec des nombres positifs")
    public void testAdditionPositifs() {
    	assertEquals(5.0, app.add(2.0, 3.0));
    	assertEquals(10.5, app.add(5.2, 5.3));
    }
    
    @Test
    @DisplayName("Test de l'addition aves des nombres négatifs")
    public void testAdditionNegatifs() {
    	assertEquals(-5.0, app.add(-2.0, -3.0));
    	assertEquals(0.0, app.add(5.0, -5.0));
    }
    
    @Test
    @DisplayName("Test de la soustraction")
    public void testSoustraction() {
    	assertEquals(2.0, app.subtract(5.0, 3.0));
    	assertEquals(-2.0, app.subtract(3.0, 5.0));
    }
    
    @Test
    @DisplayName("Test de la multiplication")
    public void testMultiplication() {
    	assertEquals(15.0, app.multiply(3.0, 5.0));
    	assertEquals(0.0, app.multiply(5.0, 0.0));
    }
    
    @Test
    @DisplayName("Test de la division normale")
    public void testDivisionNormale() {
    	assertEquals(2.0, app.divide(10.0, 5.0));
    	assertEquals(2.5, app.divide(5.0, 2.0));
    }
    
    @Test
    @DisplayName("Test de la division par zéro")
    public void testDivisionParZero() {
    	IllegalArgumentException exception = assertThrows(
    			IllegalArgumentException.class,
    			() -> app.divide(10.0, 0.0)
    	);
    	assertEquals("Division par zéro impossible", exception.getMessage());
    }
    
    @Test
    @DisplayName("test de plusiurs opérations combinées")
    public void testOperationsCombinees() {
    	double resultat = app.add(
    			app.multiply(2.0, 3.0),
    			app.divide(10.0, 2.0)
    	);
    	assertEquals(11.0, resultat);	
    }
    
}
