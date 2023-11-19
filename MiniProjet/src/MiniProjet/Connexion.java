package MiniProjet;

import java.sql.*;
public class Connexion {
Connection myCnx ;
String url,pilote;
public Connexion() {
		try {
		pilote=new String("com.mysql.cj.jdbc.Driver");
		Class.forName(pilote);
		// Definition de l'URL de connexion
		url = new String("jdbc:mysql://127.0.0.1/miniprojet?serverTimezone=UTC");//ADD BASE DONNE NAME
		// Etablissement de la Connexion
		myCnx = DriverManager.getConnection(url,"root","");
		System.out.println("CONNEXION ETABLIE");
		
		// Fermeture de la connexion
		}
		catch( ClassNotFoundException e) {System.err.println(" Erreur lors du chargement du pilote " + e); }
		catch (SQLException e) {System.err.println("Erreur de syntaxe SQL :" + e);}
		}
public ResultSet getData(String query)throws SQLException {
		Statement statement= myCnx.createStatement();
	ResultSet rs = statement.executeQuery(query);
	
	return rs;
}
public void insert(String query) throws SQLException {
	
	Statement statement= myCnx.createStatement();
	System.out.println(query);
	statement.executeUpdate(query);
	}
}

