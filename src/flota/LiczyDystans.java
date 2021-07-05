package flota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

/*Klasa oblicza dystans pomiedzy dwowa punktami
 * punkty sa okreslona na podstawie prawdziwej szerokosci i dlugosci geograficznej
 * Jednak dla uproszczeni przyjeto pozycja bez uwzglednienia polkoli wschodniej i zachodniej
 * przyjeto tak jakby porty znajdowaly sie na jednej polkoli(sic!)
 * 
 * 
 * dla uproszczenia odleglosci sa podane w lini prostej
 * nie uwzglednia krzywizny ziemi oraz wystepowania przeszkod (np. wystepowanie l¹du)
 * odleglos jest podawana w milachmorskich
 * 
 * algorytm liczacy odleglosc nie byl weryfikowany
 * poniewaz nie to jest przedmiotem tej aplikacji
 * 
 * odleglosc nalezy traktowac "synbolicznie"
 *
 *
 *
 * 
 * 
 * 
 */
public class LiczyDystans extends Zamowienie {

	//OknoGlowne okno = new OknoGlowne();
	
	double lat1;
	double lon1;
	double lat2;
	double lon2;
	
	
	//przypisuje wartosc wybrane przez uzytkownika
	
	
	String portZaladunkowy = okno.wybranyPort;
	String portWyladunkowy = okno.wybranyPortWyladunkowy;
	
	
	
	public void pobranieDanych() {
		
		Connection conn = null;
		
		   try {
               Class.forName("org.sqlite.JDBC");
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
		
		   
		   try {

			//pobiera szerokosc geograficzna z bazydanych
			//nastepnie przypisuje do zmiennej 
			//conn = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
			conn = DriverManager.getConnection("jdbc:sqlite:Flota.db");
			
			Statement stat = conn.createStatement();
       		String pobierzSzerokoscPortZaladunkowy = ("Select SZEROKOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = " + portZaladunkowy);
       		stat.executeUpdate(pobierzSzerokoscPortZaladunkowy);
       		lat1 =  Double.parseDouble(pobierzSzerokoscPortZaladunkowy);
       
       		
       		//pobiera dlugosc geograficzna z bazy danycg
       		//nastepnie przypisuje do zmiennej
       		Statement stat2 = conn.createStatement();
       		String pobierzDlugoscPortZaladunkowy = ("Select DLUGOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = " + portZaladunkowy);
       		stat2.executeUpdate(pobierzDlugoscPortZaladunkowy);
       		lon1 = Double.parseDouble(pobierzDlugoscPortZaladunkowy);
       		
       		
    	
       		
       	//pobiera szerokosc geograficzna z bazydanych
			//nastepnie przypisuje do zmiennej 
       		Statement stat3 = conn.createStatement();
       		String pobierzSzerokoscPortWyladunkowy = ("Select SZEROKOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = " + portWyladunkowy);
       		stat3.executeUpdate(pobierzSzerokoscPortWyladunkowy);
       		lon2 = Double.parseDouble(pobierzSzerokoscPortWyladunkowy);
       		
       	//pobiera dlugosc geograficzna z bazy danycg
       		//nastepnie przypisuje do zmiennej
       		Statement stat4 = conn.createStatement();
       		String pobierzDlugoscPortWyladunkowy = ("Select DLUGOSC_GEOGRAFICZNA FROM PORT WHERE NAZWA_PORTU = " + portWyladunkowy);
       		stat4.executeUpdate(pobierzDlugoscPortWyladunkowy);
       		lon2 = Double.parseDouble(pobierzDlugoscPortWyladunkowy);
       		
       		//zamyka polaczenie z baza danych
       		conn.close();
       		
       		
		   
		   } catch (Exception e) {
       		JOptionPane.showMessageDialog(null, "Problem with connection of database");
		   }
	
		
	}
	
	
	
	
	
	//oblicza dystans pomiedzy wybranymi "portami"
	//na podstawie pobrany z bazy danych polozen  geograficznych
	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			
				dist = dist * 0.8684;
			
			return (dist);
		}
	}
}
