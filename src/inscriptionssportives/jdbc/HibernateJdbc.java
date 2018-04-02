package inscriptionssportives.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class HibernateJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/inscriptionssportives?useSSL=false";
		String utilisateur = "root";
		String mdp = "";
		try {
			System.out.println("Connexion à la base de données : " + jdbcUrl);
			Connection maConnexion = 
					DriverManager.getConnection(jdbcUrl, utilisateur, mdp);
			System.out.println("Connexion réussie !");
		}
		catch (Exception eee) {
			eee.printStackTrace();
		}
	}

}
