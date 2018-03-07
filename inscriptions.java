package inscriptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;
import commandLineMenus.*;
import commandLineMenus.rendering.examples.util.InOut;
/**
 * Point d'entrÃ©e dans l'application, un seul objet de type Inscription
 * permet de gÃ©rer les compÃ©titions, candidats (de type equipe ou personne)
 * ainsi que d'inscrire des candidats Ã  des compÃ©tition.
 */

public class Inscriptions implements Serializable
{
	private static final long serialVersionUID = -3095339436048473524L;
	private static final String FILE_NAME = "Inscriptions.srz";
	private static Inscriptions inscriptions;
	
	private SortedSet<Competition> competitions = new TreeSet<>();
	private SortedSet<Candidat> candidats = new TreeSet<>();

	private Inscriptions()
	{
	}
	
	/**
	 * Retourne les compÃ©titions.
	 * @return
	 */
	
	public SortedSet<Competition> getCompetitions()
	{
		return Collections.unmodifiableSortedSet(competitions);
	}
	
	/**
	 * Retourne tous les candidats (personnes et Ã©quipes confondues).
	 * @return
	 */
	
	public SortedSet<Candidat> getCandidats()
	{
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Retourne toutes les personnes.
	 * @return
	 */
	
	public SortedSet<Personne> getPersonnes()
	{
		SortedSet<Personne> personnes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Personne)
				personnes.add((Personne)c);
		return Collections.unmodifiableSortedSet(personnes);
	}

	/**
	 * Retourne toutes les Ã©quipes.
	 * @return
	 */
	
	public SortedSet<Equipe> getEquipes()
	{
		SortedSet<Equipe> equipes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Equipe)
				equipes.add((Equipe)c);
		return Collections.unmodifiableSortedSet(equipes);
	}

	/**
	 * CrÃ©Ã©e une compÃ©tition. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Competition}.
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */
	
	public Competition createCompetition(String nom, 
			LocalDate dateCloture, boolean enEquipe)
	{
		Competition competition = new Competition(this, nom, dateCloture, enEquipe);
		competitions.add(competition);
		return competition;
	}

	/**
	 * CrÃ©Ã©e une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.

	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	
	public Personne createPersonne(String nom, String prenom, String mail)
	{
		Personne personne = new Personne(this, nom, prenom, mail);
		candidats.add(personne);
		return personne;
	}
	
	/**
	 * CrÃ©Ã©e une Candidat de type Ã©quipe. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Equipe}.
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */
	
	public Equipe createEquipe(String nom)
	{
		Equipe equipe = new Equipe(this, nom);
		candidats.add(equipe);
		return equipe;
	}
	
	void delete(Competition competition)
	{
		competitions.remove(competition);
	}
	
	void delete(Candidat candidat)
	{
		candidats.remove(candidat);
	}
	
	/**
	 * Retourne l'unique instance de cette classe.
	 * CrÃ©e cet objet s'il n'existe dÃ©jÃ .
	 * @return l'unique objet de type {@link Inscriptions}.
	 */
	
	public static Inscriptions getInscriptions()
	{
		
		if (inscriptions == null)
		{
			inscriptions = readObject();
			if (inscriptions == null)
				inscriptions = new Inscriptions();
		}
		return inscriptions;
	}

	/**
	 * Retourne un object inscriptions vide. Ne modifie pas les compÃ©titions
	 * et candidats dÃ©jÃ  existants.
	 */
	
	public Inscriptions reinitialiser()
	{
		inscriptions = new Inscriptions();
		return getInscriptions();
	}

	/**
	 * Efface toutes les modifications sur Inscriptions depuis la derniÃ¨re sauvegarde.
	 * Ne modifie pas les compÃ©titions et candidats dÃ©jÃ  existants.
	 */
	
	public Inscriptions recharger()
	{
		inscriptions = null;
		return getInscriptions();
	}
	
	private static Inscriptions readObject()
	{
		ObjectInputStream ois = null;
		try
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions)(ois.readObject());
		}
		catch (IOException | ClassNotFoundException e)
		{
			return null;
		}
		finally
		{
				try
				{
					if (ois != null)
						ois.close();
				} 
				catch (IOException e){}
		}	
	}
	
	/**
	 * Sauvegarde le gestionnaire pour qu'il soit ouvert automatiquement 
	 * lors d'une exÃ©cution ultÃ©rieure du programme.
	 * @throws IOException 
	 */
	
	public void sauvegarder() throws IOException
	{
		ObjectOutputStream oos = null;
		try
		{
			FileOutputStream fis = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(this);
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (oos != null)
					oos.close();
			} 
			catch (IOException e){}
		}
	}
	
	@Override
	public String toString()
	{
		return "Candidats : " + getCandidats().toString()
			+ "\nCompetitions  " + getCompetitions().toString();
	}
	
	public static void main(String[] args)
	{
//		Inscriptions inscriptions = Inscriptions.getInscriptions();
//		Competition flechettes = inscriptions.createCompetition("Mondial de flÃ©chettes", null, false);
//		Personne tony = inscriptions.createPersonne("Tony", "Dent de plomb", "azerty"), 
//				boris = inscriptions.createPersonne("Boris", "le Hachoir", "ytreza");
//		flechettes.add(tony);
//		Equipe lesManouches = inscriptions.createEquipe("Les Manouches");
//		lesManouches.add(boris);
//		lesManouches.add(tony);
//		System.out.println(inscriptions);
//		lesManouches.delete();
//		System.out.println(inscriptions);
		Menu rootMenu = new Menu ("Inscription Sportive");
		Menu addCompetition = new Menu("Gérer les compétions", "1");
		Menu addEquipe = new Menu("Gérer les personnes", "2");
		Menu addPersonne = new Menu("Gérer les équipes", "3");
		
		rootMenu.add(addCompetition);
		rootMenu.add(addEquipe);
		rootMenu.add(addPersonne);
		rootMenu.addQuit("q");
		
		addCompetition.add(
				new Option ("Ajouter une compétition", "1", new Action()
				{
					@Override
					public void optionSelected() {
						String nom = InOut.getString("Entrer le nom de la compétition : \n"); 
						System.out.println("\n");
					}
				}));

		addEquipe.add(
				new Option ("Ajouter une équipe", "1", new Action()
				{
					@Override
					public void optionSelected() {
						String nom = InOut.getString("Entrer le nom de l'équipe : \n"); 
						System.out.println("\n");
					}
				}));
		addPersonne.add(
				new Option ("Ajouter une personne", "1", new Action()
				{
					@Override
					public void optionSelected() {
						String nom = InOut.getString("Entrer votre nom : \n"); 
						System.out.println("\n");
					}
				}));	
		
		rootMenu.start();		try
		{
			inscriptions.sauvegarder();
		} 
		catch (IOException e)
		{
			System.out.println("Sauvegarde impossible." + e);
		}
	}
}
