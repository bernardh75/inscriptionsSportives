package inscriptions;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
<<<<<<< HEAD
=======
//import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
>>>>>>> branch 'master' of https://github.com/bernardh75/InscriptionsSportives

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.SortNatural;

import hibernateis.passerelle;

/**
 * Représente une personne physique pouvant s'inscrire à une compétition.
 */

@Entity
public class Personne extends Candidat
{
	private static final long serialVersionUID = 4434646724271327254L;
	private String prenom, mail;
<<<<<<< HEAD

	@ManyToMany(targetEntity=Equipe.class, mappedBy="membres", fetch=FetchType.EAGER)
	@Cascade(value = { CascadeType.ALL })
=======
	
	//@ManyToMany avec le target entity de personne
	@OneToMany(targetEntity=Personne.class, mappedBy = "equipes", fetch=FetchType.EAGER)
	@Cascade(value= { CascadeType.ALL })
>>>>>>> branch 'master' of https://github.com/bernardh75/InscriptionsSportives
	@SortNatural
	private Set<Equipe> equipes;
	
	
	Personne(Inscriptions inscriptions, String nom, String prenom, String mail)
	{
		super(inscriptions, nom);
		this.prenom = prenom;
		this.mail = mail;
		equipes = new TreeSet<>();
	}

	/**
	 * Retourne le prénom de la personne.
	 * @return
	 */
	
	public String getPrenom()
	{
		return prenom;
	}

	/**
	 * Modifie le prénom de la personne.
	 * @param prenom
	 */
	
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	/**
	 * Retourne l'adresse électronique de la personne.
	 * @return
	 */
	
	public String getMail()
	{
		return mail;
	}

	/**
	 * Modifie l'adresse électronique de la personne.
	 * @param mail
	 */
	
	public void setMail(String mail)
	{
		this.mail = mail;
	}

	/**
	 * Retoure les équipes dont cette personne fait partie.
	 * @return
	 */
	
	public Set<Equipe> getEquipes()
	{
		return Collections.unmodifiableSet(equipes);
	}
	
	boolean add(Equipe equipe)
	{
		equipes.add(equipe);
		passerelle.save(equipe);
		return equipes.add(equipe);
	}

	boolean remove(Equipe equipe)
	{
		equipes.remove(equipe);
		passerelle.delete(equipe);
		return equipes.remove(equipe);
	}
	
	public void delete()
	{
		for (Equipe e : equipes)
			e.remove(this);
		super.delete();
	}
	
	@Override
	public String toString()
	{
		return super.toString() + " membre de " + equipes.toString();
	}
}
