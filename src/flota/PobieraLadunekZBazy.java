package flota;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import javax.swing.JOptionPane;

public class PobieraLadunekZBazy  {

	
	

	private ArrayList<String> listaLadunek = new ArrayList<String>();
	private ArrayList<String> listaPort = new ArrayList<String>();
	
	public String[] tabelaListaLadunek, tabelaListaPort;
		
	Connection conn = null;
	

	public void  pobranieLadunek() throws ClassNotFoundException{
				
		
		
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		   
		   try {

       		//conn = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
       		conn = DriverManager.getConnection("jdbc:sqlite:Flota.db");
       		
       		       		 
       		String polecenieSql = "SELECT NAZWA_LADUNKU FROM LADUNEK;";
       		PreparedStatement  pst = conn.prepareStatement(polecenieSql);
       		ResultSet rs = pst.executeQuery();
       		
       		while(rs.next()) {
       			String s = rs.getString(2);
       			
       			
       			listaLadunek.add(s);
       			tabelaListaLadunek = listaLadunek.toArray(new String[listaLadunek.size()]);
    
       		}
   
       	} catch (Exception e) {
       		JOptionPane.showMessageDialog(null, "Problem with connection of database");
		
       	}
	finally {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception ex) {
			System.out.println(ex.getCause());
		   
		   
		}
		   
	}
		   

		   
	}
	
	
	
	
	
	public void pobraniePort() throws ClassNotFoundException{
		

		
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
        try {

       		//conn = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
       		conn = DriverManager.getConnection("jdbc:sqlite:Flota.db");
       		
       		
       		 
       		String polecenieSql = "SELECT NAZWA_PORTU FROM PORT;";
       		PreparedStatement  pst = conn.prepareStatement(polecenieSql);
       		ResultSet rs = pst.executeQuery();
       		
       		while(rs.next()) {
       			String s = rs.getString(2);
       			listaPort.add(s);
       			tabelaListaPort = listaPort.toArray(new String[listaPort.size()]);
       		
       		}
       
				
       		
       	} catch (Exception e) {
       		JOptionPane.showMessageDialog(null, "Problem with connection of database");
		
       	}
        finally {
    		try {
    			if (conn != null) {
    				conn.close();
    			}
    		} catch (Exception ex) {
    			System.out.println(ex.getCause());
    		   
    		   
    		}
    		   
    	}
        
        
        
       
    
	}
	
		
	
	
}

