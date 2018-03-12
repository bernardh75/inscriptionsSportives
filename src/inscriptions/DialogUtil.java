package inscriptions;

import java.io.IOException;
import java.util.ArrayList;

import commandLineMenus.*;
import commandLineMenus.examples.employees.core.*;
import commandLineMenus.examples.employees.userDialog.PersonnelConsole;

import static commandLineMenus.rendering.examples.util.InOut.*;

public class DialogUtil {
	
	private Inscriptions inscriptions;
	
	public DialogUtil(Inscriptions inscriptions)
	{
		this.inscriptions = inscriptions;
	}
	
	public void start()
	{
		menuPrincipal().start();
	}
	
	private Menu menuPrincipal()
	{
		Menu menu = new Menu("Gestion des inscriptions sportives");
		menu.add(menuCompetitions());
		menu.add(menuEquipes());
		menu.add(menuPersonnes());
		menu.add(menuQuitter());
		return menu;
	}

	private Menu menuQuitter()
	{
		Menu menu = new Menu("Quitter", "q");
		menu.add(quitterEtEnregistrer());
		menu.add(quitterSansEnregistrer());
		menu.addBack("r");
		return menu;
	}
	
	// Menus
	
	
	private Menu menuCompetitions()
	{
		Menu menu = new Menu("Gérer les compétitions", "l");
		menu.add(afficherCompetitions());
		menu.add(ajouterCompetition());
		menu.add(selectionnerCompetition());
		menu.addBack("q");
		return menu;
	}
	
	private Menu menuEquipes()
	{
		Menu menu = new Menu("Gérer les équipes", "l");
		menu.add(afficherEquipes());
		menu.add(ajouterEquipe());
		menu.add(selectionnerEquipe());
		menu.addBack("q");
		return menu;
	}
	
	private Menu menuPersonnes()
	{
		Menu menu = new Menu("Gérer les personnes", "l");
		menu.add(afficherPersonnes());
		menu.add(ajouterPersonne());
		menu.add(selectionnerPersonne());
		menu.addBack("q");
		return menu;
	}

	
	// Affichages
	
	
	private Option afficherCompetitions()
	{
		return new Option("Afficher les compétitions", "l", () -> {System.out.println(inscriptions.getCompetitions());});
	}
	
	private Option afficherEquipes()
	{
		return new Option("Afficher les équipes", "l", () -> {System.out.println(inscriptions.getEquipes());});
	}
	
	private Option afficherPersonnes()
	{
		return new Option("Afficher les personnes", "l", () -> {System.out.println(inscriptions.getPersonnes());});
	}
	
	
	// Ajouts
	
	
	private Option ajouterCompetition()
	{
		return new Option("Ajouter une compétition", "a", () -> {inscriptions.createCompetition(getString("Entrez le nom de votre compétition : "), null, false);});
	}
	
	private Option ajouterEquipe()
	{
		return new Option("Ajouter une équipe", "a", () -> {inscriptions.createEquipe(getString("Entrez le nom de votre équipe : "));});
	}
	
	private Option ajouterPersonne()
	{
		return new Option("Ajouter une personne", "a", () -> {inscriptions.createPersonne(getString("Entrez le nom de votre personne : \n"), null, null);});
	}

	
	
	// Selections
	
	
	private List<Competition> selectionnerCompetition()
	{
		return new List<Competition>("Sélectionner une compétition", "e", 
				() -> new ArrayList<>(inscriptions.getCompetitions()),
				(element) -> editerCompetition(element)
				);
	}
	
	private List<Equipe> selectionnerEquipe()
	{
		return new List<Equipe>("Sélectionner une équipe", "e", 
				() -> new ArrayList<>(inscriptions.getEquipes()),
				(element) -> editerEquipe(element)
				);
	}
	
	private List<Personne> selectionnerPersonne()
	{
		return new List<Personne>("Sélectionner une personne", "e", 
				() -> new ArrayList<>(inscriptions.getPersonnes()),
				(element) -> editerPersonne(element)
				);
	}
	
	
	
	// Selectionner : Competition
	
	
	private Menu editerCompetition(Competition competition)
    {
        Menu menu = new Menu("Editer " + competition.getNom());
//        menu.add(afficherCandidats(competition));
//        menu.add(ajouterPersonneCompetition(competition));
//        menu.add(ajouterEquipeCompetition(competition));
//        menu.add(supprimerCandidat(competition));
//        menu.add(modifierCompetition(competition));
//        menu.add(supprimer(competition));
        menu.addBack("q");
        return menu;
    }
	
	
	
	
	// Selectionner : Equipe
	
	
	
	private Menu editerEquipe(Equipe equipe)
    {
        Menu menu = new Menu("Editer " + equipe.getNom());
        menu.add(afficherMembres(equipe));
//        menu.add(ajouterMembre(equipe));
//        menu.add(supprimerMembre(equipe));
//        menu.add(modifierEquipe(equipe));
        menu.add(supprimerEquipe(equipe));
        menu.addBack("q");
        return menu;
    }
	
	private Option afficherMembres(final Equipe equipe)
	{
		return new Option("Afficher l'équipe", "l", 
				() -> 
				{
					System.out.println(equipe.getMembres());
				}
		);
	}
	
//	private Option ajouterMembre()
//	{
//		return new Option("Ajouter un membre", "a", () -> {Equipe.add(null);});
//	}
	
//	private Option supprimerMembre()
//	{
//		return new Option("Supprimer un membre", "a", () -> {Equipe.remove(null);});
//	}
	
//	private Option modifierEquipe()
//	{
//		return new Option("Ajouter un membre", "a", () -> {Equipe.add(null);});
//	}
	
	private List<Equipe> supprimerEquipe(final Equipe equipe)
	{
		return new List<>("Supprimer une équipe", "s", 
				() -> new ArrayList<>(inscriptions.getEquipes()),
				(index, element) -> {element.delete();}
				);
	}
	
	
	
	// Selectionner : Personne
	
	
	
	private Menu editerPersonne(Personne personne)
	{
	    Menu menu = new Menu("Editer " + personne.getNom());
//	    menu.add(modifierPersonne(personne));
//	    menu.add(supprimerPersonne(personne));
	    menu.addBack("q");
	    return menu;
	}
	
//	private Option modifierPersonne(Personne personne)
//	{
//		return new Option("Modifier une personne", "a", () -> {Equipe.add(null);});
//	}
//	
//	private Option supprimerPersonne(Personne personne)
//	{
//		return new Option("Supprimer une personne", "a", () -> {Personne.delete();});
//	}
	

	
	
	
	
	private Option quitterEtEnregistrer()
    {
        return new Option("Quitter et enregistrer", "q", 
                () -> 
                {
                    try
                    {
                        inscriptions.sauvegarder();
                        Action.QUIT.optionSelected();
                    } 
                    catch (IOException e)
                    {
                        System.out.println("Impossible d'effectuer la sauvegarde");
                    }
                }
            );
    }
	
	private Option quitterSansEnregistrer()
	{
		return new Option("Quitter sans enregistrer", "a", Action.QUIT);
	}
	
	
	
	public static void main(String[] args)
    {
        Inscriptions inscriptions = Inscriptions.getInscriptions();
        DialogUtil personnelc = new DialogUtil(inscriptions);
        personnelc.start();
    }
}
