package test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class TestInscriptions {

	@Test
	public void testcreateCompetition() {
		
		Competition compet = Inscriptions.getInscriptions().createCompetition("test",LocalDate.now() , true );
		assertEquals(compet.getNom(), "test");
		assertEquals(compet.estEnEquipe(), true);
	}
	
	@Test
	public void testcreatePersonne() {
		Personne tony = Inscriptions.getInscriptions().createPersonne("Tony","test","azerty" );
		assertEquals(tony.getPrenom(), "test");
	}
	
	@Test
	public void testcreateEquipe() {
		Equipe equipe = Inscriptions.getInscriptions().createEquipe("test");
		assertEquals(equipe.getNom(), "test");
	}
	
//	@Test
//	public void testgetInscriptions() {
//		
//	}
}
