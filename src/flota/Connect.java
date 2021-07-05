/*
klasa nawiązująca połączenie z bazą danych

 */


package flota;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;



//klasa �aczaca z baza danych  i nastepnie inicjalizujaca wywolanie duzej ramki GUI
//polaczenie z baza jest zamykane zaraz po jego utworzeniu
//swego rodzaju test polaczenia zanim otworzy sie okno glowne GUI


public class Connect {

	   
   Connection connLogin = null;

    public void ConnectionToSqliteDatabase() throws ClassNotFoundException{

    
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        	
            	try {
            		connLogin = DriverManager.getConnection("jdbc:sqlite:Flota.db");
            		//connLogin = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
            		JOptionPane.showMessageDialog(null, "Połaczono z baza");
            		
            		connLogin.close();
            		//wywolanie nowej raki GUI
            		OknoGlowne userWindow = new OknoGlowne();
            		userWindow.initialize();
          		
            		
            	} catch (Exception e) {
            		JOptionPane.showMessageDialog(null, "Problem with connection of database");
            	} finally {
            		try {
            			if (connLogin != null) {
            				connLogin.close();
            			}
            		} catch (Exception ex) {
            			System.out.println(ex.getCause());
                }

            }

        }
    	
    



    }



