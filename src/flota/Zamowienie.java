package flota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

//zbiera wszelkie informacje potrzebne do zamowienia przewozy
//dystans, zysk, date zaladunku i wyladynku


public class Zamowienie {

	OknoGlowne okno = new OknoGlowne();
	
	Double wartoscZamowienia = 0.0;
	Double cenaLadunku;
	Double dystans;
	Date zaladunek;
	Date wyladunek;
	String ladunek;
	String podsumowanie;
	
	String wybranyLadunek = okno.wybranyLadunekWBTN;
	//Double ilosc = okno.wartoscZam;
	Double cenaDouble = 0.0;
	
	Double ilosc = Double.parseDouble(okno.textIlosc.getText());
	
	public Double liczWartoscZamowienia() {
		
		//pobiera wartosc danego ladunku w tonach z bazy danych
		
		Connection conn = null;
		
		
		 try {
             Class.forName("org.sqlite.JDBC");
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
		
			try {

        	//	conn = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
        		conn = DriverManager.getConnection("jdbc:sqlite:Flota.db");
        		
        		Statement stat = conn.createStatement();
        		
           		String command = ("Select CENA_ZA_TONE_USD FROM LADUNEK WHERE NAZWA_LADUNKU = '" + wybranyLadunek + "'");
           		//stat.executeQuery(command);
           		ResultSet rs = stat.executeQuery(command);
           		
           		
           		while(rs.next()) {
           			cenaDouble = rs.getDouble(1);
           			
           			
           		}
           		//PreparedStatement stat = conn.prepareStatement(command);
           		
           		//stat.setString(1, wybranyLadunek);
           		
           	
           		
           		//cenaDouble = rs.getDouble(2);
           		
           		//wartoscZamowienia = cenaDouble * ilosc;
           		
           		
           		/*
           		PreparedStatement stat = conn.prepareStatement(command);
           		stat.setString(1, wybranyLadunek);
           		ResultSet rs = stat.executeQuery();
           		
           		while(rs.next()) {
               		Double cenaDouble = rs.getDouble(1);       	
      
           		wartoscZamowienia = cenaDouble * ilosc;
           		okno.obliczonaWartoscZamowienia = wartoscZamowienia;
           		
           		*/
           		
           		//while (rs.next()) {
           			//wartoscZamowienia = cenaLadunku * ilosc;	
           		//}
           		
           		//cenaLadunku = Double.parseDouble(pobierzWartoscLadunku);
           		
           		//	wartoscZamowienia = cenaLadunku * ilosc;
        		
        		
			} catch (Exception e) {
        		JOptionPane.showMessageDialog(null, "Problem with connection of database");
        		
			}
			return wartoscZamowienia = cenaDouble * okno.ilosc;
		
		
		
	}
	
	
	
}
