/*première ébauche du contenu de la fonction main de inscriptions.java*/
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
		
		rootMenu.start();
		
		try
		{
			inscriptions.sauvegarder();
		} 
		catch (IOException e)
		{
			System.out.println("Sauvegarde impossible." + e);
		}
