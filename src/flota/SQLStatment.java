package flota;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class SQLStatment {
		
	
	OknoGlowne okno = new OknoGlowne();
	
	String nazwanowegoportu;
	String krajnowegoportu;
	String pelnykrajnowegoporu;
	String szerokoscnowegoportu;
	String dlugoscnowegoportu;
	
String apostrof = String.valueOf('"');

	
	Connection conn = null;
	
	SQLStatment() throws SQLException {
		super();
		polecenie();
		// TODO Auto-generated constructor stub
	}

	public void polecenie() throws SQLException {
		
		nazwanowegoportu = okno.textFieldnazwaNowegoPortuNazwa.getText();
		krajnowegoportu = okno.textFieldnazwaNowegoPortuKraj.getText();
		pelnykrajnowegoporu = okno.textFieldnazwaNowegoPortuPNazwa.getText();
		
		//SQL Statement "INSERT INTO PORT(NAZWA_PORTU, KRAJ, KRAJ_PELNA_NAZWA) VALUES('"nazwanowego protu"', '"krajnowegoportu"', '"pelna nazwanowegokraju"'))"
		
		//String polecenieSql =  "INSERT INTO PORT(NAZWA_PORTU, KRAJ, KRAJ_PELNA_NAZWA) VALUES('"+nazwanowegoportu+"', '"+krajnowegoportu+"', '"+pelnykrajnowegoporu+"')";
		
		
		try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		
		try {
		String polecenieSql = "INSERT INTO PORT(NAZWA_PORTU, KRAJ, KRAJ_PELNA_NAZWA) VALUES(?,?,?)";
		//conn = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
		conn = DriverManager.getConnection("jdbc:sqlite:Flota.db");
		
		
		PreparedStatement stmt = conn.prepareStatement(polecenieSql);
		stmt.setString(1, okno.textFieldnazwaNowegoPortuNazwa.getText());
		stmt.setString(2, okno.textFieldnazwaNowegoPortuKraj.getText());
		stmt.setString(3, okno.textFieldnazwaNowegoPortuPNazwa.getText());
		
		stmt.executeUpdate();
		stmt.close();
		
		JOptionPane.showMessageDialog(null, "DOdano nowy port do bazy danych");
		
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "b³¹d, nie dodano portu do bazy");
			
		}
		
		
		
		
		/*
		stmt.setString(1, okno.textFieldnazwaNowegoPortuNazwa.getText());
		stmt.setString(2, okno.textFieldnazwaNowegoPortuKraj.getText());
		stmt.setString(3, okno.textFieldnazwaNowegoPortuPNazwa.getText());
		stmt.executeUpdate();
		stmt.close();
		
		*/
		
	
		
		
	
		
		
		
		
		
	}
	/*
public ArrayList ladunklista;

String probnytekst;
	String pobranieladunku = "select 'NAZWA_LADUNKU' from 'LADUNEK'";
	
	public void statment() throws SQLException {
		
		Statement stat = polaczenie.conn.createStatement();
		stat.execute(pobranieladunku);
		
		ladunklista.add(stat);
		probnytekst = (String) ladunklista.get(0);
		
		
		
		
		przyklady ze stackoverflow
		List<String> airPorts=new ArrayList<>();
 while (rs.next()) {      
   airPorts.add(rs.getString("airportname"));                                 
}



ArrayList <String> result = new ArrayList<String>();
ResultSet rs = stat.executeQuery( "SELECT ..." );

for (int i=0; rs.next(); i++)
{
   result.add( rs.getString(i) );
}



Connection con = ...; 
PreparedStatement ps = con.prepareStatement("SELECT name, pass FROM loginTable WHERE name = ?");
ps.setString(1, "mike");

ResultSet rs = ps.executeQuery();

List<User> users = new ArrayList<User>();

while(rs.next()) {
    String name = rs.getString(1);
    String pass = rs.getString(2);
    users.add(new User(name, pass));
}



	*/	
		
		
		
		
		
	
	
	
	

}
