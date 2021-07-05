package flota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;

import javax.swing.JOptionPane;

//zbiera wszelkie informacje potrzebne do zamowienia przewozy
//dystans, zysk, date zaladunku i wyladynku


public class Zamowienie {

	OknoGlowne okno = new OknoGlowne();
	
	Double wartoscZamowienia;
	Double cenaLadunku;
	Double dystans;
	Date zaladunek;
	Date wyladunek;
	String ladunek;
	String podsumowanie;
	
	String wybranyLadunek = okno.wybranyLadunekWBTN;
	Double ilosc = okno.ilosc;
	
	
	
	public double liczWartoscZamowienia() {
		
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
           		String pobierzWartoscLadunku = ("Select CENA_ZA_TONE_USD FROM LADUNEK WHERE NAZWA_LADUNKU = " + wybranyLadunek);
           		stat.executeUpdate(pobierzWartoscLadunku);
           		cenaLadunku = Double.parseDouble(pobierzWartoscLadunku);
           		
           		wartoscZamowienia = cenaLadunku * ilosc;
        		
        		
			} catch (Exception e) {
        		JOptionPane.showMessageDialog(null, "Problem with connection of database");
        		
			}
		return wartoscZamowienia;
		
		
	}
	
	
	
}
