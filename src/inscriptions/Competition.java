package inscriptions;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.SortNatural;

/**
 * Représente une compétition, c'est-à-dire un ensemble de candidats 
 * inscrits à un événement, les inscriptions sont closes à la date dateCloture.
 *
 */
@Entity
public class Competition implements Comparable<Competition>, Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int numcompet;
	
	private static final long serialVersionUID = -2882150118573759729L;
	
	@Transient
	private Inscriptions inscriptions;
	
	private String nom;


	
	@ManyToMany
	@SortNatural
	private Set<Candidat> candidats;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCloture;
	
	@Column(columnDefinition="tinyint(1) default 0")
	private boolean enEquipe = false;
	
	

	Competition(Inscriptions inscriptions, String nom, Date dateCloture, boolean enEquipe)
	{
		this.enEquipe = enEquipe;
		this.inscriptions = inscriptions;
		this.nom = nom;
		this.dateCloture = dateCloture;
		candidats = new TreeSet<>();
	}
	
	/**
	 * Retourne le nom de la compétition.
	 * @return
	 */
	
	public String getNom()
	{
		return nom;
	}
	
	/**
	 * Modifie le nom de la compétition.
	 */
	
	public void setNom(String nom)
	{
		this.nom = nom ;
	}
	
	/**
	 * Retourne vrai si les inscriptions sont encore ouvertes, 
	 * faux si les inscriptions sont closes.
	 * @return 
	 */
	
	public boolean inscriptionsOuvertes()
	{
		Date DateSys = new Date();
		if(DateSys.after(getDateCloture()))
			return false;
		else
			return true;
	}
	
	/**
	 * Retourne la date de cloture des inscriptions.
	 * @return
	 */
	
	public Date getDateCloture()
	{
		return dateCloture;
	}
	
	/**
	 * Est vrai si et seulement si les inscriptions sont réservées aux équipes.
	 * @return
	 */
	
	public boolean estEnEquipe()
	{
		return enEquipe;
	}
	
	/**
	 * Modifie la date de cloture des inscriptions. Il est possible de la reculer 
	 * mais pas de l'avancer.
	 * @param dateCloture
	 */
	
	public void setDateCloture(Date dateCloture, Date newdateCloture)
	{
		// TODO vérifier que l'on avance pas la date.
		if (dateCloture.after(this.dateCloture))
			System.out.println("Vous ne pouvez pas avancer la date");
		else
			this.dateCloture = dateCloture;
	}
	
	/**
	 * Retourne l'ensemble des candidats inscrits.
	 * @return
	 */
	
	public Set<Candidat> getCandidats()
	{
		return Collections.unmodifiableSet(candidats);
	}
	
	/**
	 * Inscrit un candidat de type Personne à la compétition. Provoque une
	 * exception si la compétition est réservée aux équipes ou que les 
	 * inscriptions sont closes.
	 * @param personne
	 * @return
	 */
	
	public boolean add(Personne personne)
	{
		// TODO vérifier que la date de clôture n'est pas passée
		boolean inscriptions = inscriptionsOuvertes();
		if (enEquipe)
			throw new RuntimeException();
		else if(!inscriptions)
			throw new RuntimeException();
		personne.add(this);
 		return candidats.add(personne);
	}

	/**
	 * Inscrit un candidat de type Equipe à la compétition. Provoque une
	 * exception si la compétition est réservée aux personnes ou que 
	 * les inscriptions sont closes.
	 * @param personne
	 * @return
	 */

	public boolean add(Equipe equipe)
	{
		boolean inscriptions = inscriptionsOuvertes();
		if (!enEquipe)
			throw new RuntimeException();
		else if (!inscriptions)
			throw new RuntimeException();
		equipe.add(this);
		return candidats.add(equipe);
	}

	/**
	 * Désinscrit un candidat.
	 * @param candidat
	 * @return
	 */
	
	public boolean remove(Candidat candidat)
	{
		candidat.remove(this);
		return candidats.remove(candidat);
	}
	
	/**
	 * Supprime la compétition de l'application.
	 */
	
	public void delete()
	{
		for (Candidat candidat : candidats)
			remove(candidat);
		inscriptions.remove(this);
	}
	
	@Override
	public int compareTo(Competition o)
	{
		return getNom().compareTo(o.getNom());
	}
	
	@Override
	public String toString()
	{
		return getNom();
	}
}
