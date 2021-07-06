/*
Klasa buduje gÅ‚Ã³wny GUI

 */

package flota;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.border.TitledBorder;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;


public class OknoGlowne {

    
	
	
    JTextField textIlosc;

    public Double deklarujIloscLadunku = 0.0;

    
    
    public JTextField textFieldnazwaNowegoPortuNazwa;
    public JTextField textFieldnazwaNowegoPortuKraj;
    public JTextField textFieldnazwaNowegoPortuPNazwa;
    public JTextField textFieldnazwaNowegoPortuSzerokosc;
    public JTextField textFieldnazwaNowegoPortuDlugosc;
    public JButton btnDodajNowyPort, btnDodajNowyStatek;
    private JTextField textNazwaLadunku;
    private JTextField textCenaLadunku;
    private JTextField textObjetoscLadunku;

    private JTextField textUwagi;
    public JTextField textNazwaNowegoStatku;
    public JTextField textLadownoscStatku;
    public JTextField textObjetoscStatku;
    public JTextField textKoszt;
    
    public JLabel lblZamowienieLadunek, lblZamowieniePort, lblZamowieniePWyladunkowy, lblZamowienieIlosc, lblZamowienieDataZal, lblZamowienieDataWy;
    
    public JComboBox comboBoxPortWyladunkowy, comboBoxPort, comboBoxLadunek;
    
    private ArrayList<String> listaLadunek = new ArrayList<String>();
	private ArrayList<String> listaPort = new ArrayList<String>();
	
	public String[] tabelaListaLadunek, tabelaListaPort;
    
    
    JDateChooser dateChooser, dateChooserZaladunek, dateChooserWyladunek;
   
    String wybranyLadunekWBTN, wybranyPort, wybranyPortWyladunkowy;
    String wartosZamowieniString;
    Double wartoscZamowienia;
    Double ilosc;
    Double iloscDouble;
    Double obliczonaWartoscZamowienia;
    Double wartoscZam;
    Date dataZaladunku, dataWyladunku;
    JLabel lblWartoscZamowienia;
    
    /**
     * @wbp.parser.entryPoint
     */
    public JFrame userFrame;
    private JTable table;
    /**
     * @wbp.parser.entryPoint
     */
    public void initialize() throws SQLException, ClassNotFoundException {	
    	userFrame = new JFrame();
    	/*
    	 * Tworzy polaczenie z baza danych
    	 * w celu pobrania listy wierzy ktore uzupelniaja polas JComboBox
    	 * 
    	 * 
    	 */
    	Connection conn = null;
    	
    	
    	   try {
               Class.forName("org.sqlite.JDBC");
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
   		   
   		   try {

          		//conn = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
          		conn = DriverManager.getConnection("jdbc:sqlite:Flota.db");
          		
          		
          		Statement stmtLadunek = conn.createStatement();
          		ResultSet rsLadunek = stmtLadunek.executeQuery("SELECT NAZWA_LADUNKU FROM LADUNEK");
          		while(rsLadunek.next()) {
          			String sLadunek = rsLadunek.getString(1);
          			
          			//wyniki zapytania sa dodawane do Arraylisty
          			listaLadunek.add(sLadunek);
          			//nastepnie array lista jest przerabiana na tablice
          			//ktora nastepnie tworzy poszczegolne wiersze w JComboBox
          			tabelaListaLadunek = listaLadunek.toArray(new String[listaLadunek.size()]);
       
          		}
          			
          		Statement stmtPort = conn.createStatement();
          		ResultSet rsPort = stmtPort.executeQuery("SELECT NAZWA_PORTU FROM PORT");
          		
               	while(rsPort.next()) {
               		String sPort = rsPort.getString(1);
               			
               		listaPort.add(sPort);
               		tabelaListaPort = listaPort.toArray(new String[listaPort.size()]);
               		
               	
               	}
               	conn.close();
          			
          
      
          	} catch (Exception e) {
          		JOptionPane.showMessageDialog(null, "Problem with connection of database");
   		
          	}
    	
    	
    
        //buduje gÅ‚Ã³wna ramke
        userFrame.getContentPane().setBackground(Color.GRAY);
        userFrame.getContentPane().setLayout(null);
        userFrame.setResizable(false);
        userFrame.setBackground(Color.GRAY);
        userFrame.setForeground(Color.GRAY);
        userFrame.setBounds(100, 100, 1600, 800);
        userFrame.setVisible(true);
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel dbLog = new JLabel("Po\u0142\u0105czono z baz\u0105 danych:");
        dbLog.setFont(new Font("Tahoma", Font.PLAIN, 10));
        dbLog.setBounds(50, 11, 312, 23);
        dbLog.setForeground(Color.BLACK);
        userFrame.getContentPane().add(dbLog);
        
        JPanel panelNowyPort = new JPanel();
        panelNowyPort.setBackground(Color.GRAY);
        
        panelNowyPort.setBorder(new TitledBorder(null, "wprowadz nowy port do bazy danych", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelNowyPort.setBounds(50, 461, 324, 231);
        userFrame.getContentPane().add(panelNowyPort);
        panelNowyPort.setLayout(null);
        
        textFieldnazwaNowegoPortuNazwa = new JTextField();
        textFieldnazwaNowegoPortuNazwa.setToolTipText("Pole wymagane");
        textFieldnazwaNowegoPortuNazwa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldnazwaNowegoPortuNazwa.setBounds(228, 26, 86, 20);
        panelNowyPort.add(textFieldnazwaNowegoPortuNazwa);
        textFieldnazwaNowegoPortuNazwa.setColumns(10);
        
        JLabel nazwaNowegoPortuNazwa = new JLabel("Nazwa");
        nazwaNowegoPortuNazwa.setBounds(6, 25, 43, 19);
        panelNowyPort.add(nazwaNowegoPortuNazwa);
        nazwaNowegoPortuNazwa.setForeground(Color.BLACK);
        nazwaNowegoPortuNazwa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JLabel nazwaNowegoPortuKraj = new JLabel("Kraj");
        nazwaNowegoPortuKraj.setBounds(6, 59, 26, 19);
        panelNowyPort.add(nazwaNowegoPortuKraj);
        nazwaNowegoPortuKraj.setForeground(Color.BLACK);
        nazwaNowegoPortuKraj.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JLabel nazwaNowegoPortuPNazwa = new JLabel("Pe\u0142na nazwa kraju");
        nazwaNowegoPortuPNazwa.setBounds(6, 89, 119, 19);
        panelNowyPort.add(nazwaNowegoPortuPNazwa);
        nazwaNowegoPortuPNazwa.setForeground(Color.BLACK);
        nazwaNowegoPortuPNazwa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JLabel nazwaNowegoPortuSzerokosc = new JLabel("Szeroko\u015B\u0107 geograficzna");
        nazwaNowegoPortuSzerokosc.setBounds(6, 119, 152, 19);
        panelNowyPort.add(nazwaNowegoPortuSzerokosc);
        nazwaNowegoPortuSzerokosc.setForeground(Color.BLACK);
        nazwaNowegoPortuSzerokosc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JLabel nazwaNowegoPortuDlugosc = new JLabel("Dlugo\u015B\u0107 geograficzna");
        nazwaNowegoPortuDlugosc.setBounds(6, 149, 137, 19);
        panelNowyPort.add(nazwaNowegoPortuDlugosc);
        nazwaNowegoPortuDlugosc.setForeground(Color.BLACK);
        nazwaNowegoPortuDlugosc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        textFieldnazwaNowegoPortuKraj = new JTextField();
        textFieldnazwaNowegoPortuKraj.setToolTipText("Pole wymagane");
        textFieldnazwaNowegoPortuKraj.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldnazwaNowegoPortuKraj.setColumns(10);
        textFieldnazwaNowegoPortuKraj.setBounds(228, 57, 86, 20);
        panelNowyPort.add(textFieldnazwaNowegoPortuKraj);
        
        textFieldnazwaNowegoPortuPNazwa = new JTextField();
        textFieldnazwaNowegoPortuPNazwa.setToolTipText("Pole wymagane");
        textFieldnazwaNowegoPortuPNazwa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldnazwaNowegoPortuPNazwa.setColumns(10);
        textFieldnazwaNowegoPortuPNazwa.setBounds(228, 90, 86, 20);
        panelNowyPort.add(textFieldnazwaNowegoPortuPNazwa);
        
        textFieldnazwaNowegoPortuSzerokosc = new JTextField();
        textFieldnazwaNowegoPortuSzerokosc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldnazwaNowegoPortuSzerokosc.setColumns(10);
        textFieldnazwaNowegoPortuSzerokosc.setBounds(228, 120, 86, 20);
        panelNowyPort.add(textFieldnazwaNowegoPortuSzerokosc);
        
        textFieldnazwaNowegoPortuDlugosc = new JTextField();
        textFieldnazwaNowegoPortuDlugosc.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textFieldnazwaNowegoPortuDlugosc.setColumns(10);
        textFieldnazwaNowegoPortuDlugosc.setBounds(228, 150, 86, 20);
        panelNowyPort.add(textFieldnazwaNowegoPortuDlugosc);
        

        
        
        
        btnDodajNowyPort = new JButton("Dodaj");
        btnDodajNowyPort.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		//Naleza³oby to przeniesc jako osobna klasa lub metoda
        		//ODDZIELONA od czesci budujacej GUI
        		
        		Connection conn2 = null;
        		
        		
        		try {
                    Class.forName("org.sqlite.JDBC");
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
        		
        		
        		try {
        			
        		//Dodaje nowy port do bazy danych
        		String polecenieSql = "INSERT INTO PORT(NAZWA_PORTU, KRAJ, KRAJ_PELNA_NAZWA, SZEROKOSC_GEOGRAFICZNA, D£UGOSC_GEOGRAFICZNA) VALUES(?,?,?,?,?)";
        		//conn2 = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
        		conn2 = DriverManager.getConnection("jdbc:sqlite:Flota.db");
        		
        		PreparedStatement stmt = conn2.prepareStatement(polecenieSql);
        		stmt.setString(1, textFieldnazwaNowegoPortuNazwa.getText());
        		stmt.setString(2, textFieldnazwaNowegoPortuKraj.getText());
        		stmt.setString(3, textFieldnazwaNowegoPortuPNazwa.getText());
        		stmt.setString(4, textFieldnazwaNowegoPortuSzerokosc.getText());
        		stmt.setString(5, textFieldnazwaNowegoPortuDlugosc.getText());
        		
        		stmt.executeUpdate();
        		stmt.close();
        		conn2.close();
        		
        		JOptionPane.showMessageDialog(null, "Dodano nowy port do bazy danych");
        		
        		}catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, "B³¹d, nie dodano portu do bazy");
        			
        		}
        		
        		
        	}
        	
        	
        	
        	
        });
        btnDodajNowyPort.setForeground(Color.BLACK);
        btnDodajNowyPort.setBackground(Color.LIGHT_GRAY);
        btnDodajNowyPort.setBounds(117, 197, 89, 23);
        panelNowyPort.add(btnDodajNowyPort);
        
        JPanel panelNowyLadunek = new JPanel();
        panelNowyLadunek.setLayout(null);
        panelNowyLadunek.setBorder(new TitledBorder(null, "wprowadz nowy ³adunek do bazy danych", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelNowyLadunek.setBackground(Color.GRAY);
        panelNowyLadunek.setBounds(718, 461, 807, 231);
        userFrame.getContentPane().add(panelNowyLadunek);
        
        textNazwaLadunku = new JTextField();
        textNazwaLadunku.setToolTipText("Pole wymagane");
        textNazwaLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textNazwaLadunku.setColumns(10);
        textNazwaLadunku.setBounds(228, 26, 86, 20);
        panelNowyLadunek.add(textNazwaLadunku);
        
        JLabel nazwaNowegoLadunku = new JLabel("Nazwa");
        nazwaNowegoLadunku.setForeground(Color.BLACK);
        nazwaNowegoLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nazwaNowegoLadunku.setBounds(6, 25, 43, 19);
        panelNowyLadunek.add(nazwaNowegoLadunku);
        
        JLabel nazwaCenaNowegoLaduku = new JLabel("Cena \u0142adunku");
        nazwaCenaNowegoLaduku.setForeground(Color.BLACK);
        nazwaCenaNowegoLaduku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nazwaCenaNowegoLaduku.setBounds(6, 59, 152, 19);
        panelNowyLadunek.add(nazwaCenaNowegoLaduku);
        
        JLabel nazwaObjetoscLadunku = new JLabel("Obj\u0119to\u015B\u0107 \u0142adunku");
        nazwaObjetoscLadunku.setForeground(Color.BLACK);
        nazwaObjetoscLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nazwaObjetoscLadunku.setBounds(6, 89, 152, 19);
        panelNowyLadunek.add(nazwaObjetoscLadunku);
        
        JLabel nazwaGrainStaandard = new JLabel("Grain Standard");
        nazwaGrainStaandard.setForeground(Color.BLACK);
        nazwaGrainStaandard.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nazwaGrainStaandard.setBounds(6, 119, 152, 19);
        panelNowyLadunek.add(nazwaGrainStaandard);
        
        JLabel nazwaUwqagiDotLadunku = new JLabel("Uwagi");
        nazwaUwqagiDotLadunku.setHorizontalAlignment(SwingConstants.TRAILING);
        nazwaUwqagiDotLadunku.setForeground(Color.BLACK);
        nazwaUwqagiDotLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nazwaUwqagiDotLadunku.setBounds(660, 27, 137, 19);
        panelNowyLadunek.add(nazwaUwqagiDotLadunku);
        
        textCenaLadunku = new JTextField();
        textCenaLadunku.setToolTipText("Cena za jedn\u0105 tone (USD)");
        textCenaLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textCenaLadunku.setColumns(10);
        textCenaLadunku.setBounds(228, 57, 86, 20);
        panelNowyLadunek.add(textCenaLadunku);
        
        textObjetoscLadunku = new JTextField();
        textObjetoscLadunku.setToolTipText("Obj\u0119to\u015B\u0107 1 tony \u0142adunku");
        textObjetoscLadunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textObjetoscLadunku.setColumns(10);
        textObjetoscLadunku.setBounds(228, 90, 86, 20);
        panelNowyLadunek.add(textObjetoscLadunku);
        
        Choice grainStandard = new Choice();
        grainStandard.setFont(new Font("Tahoma", Font.PLAIN, 15));
        grainStandard.setBounds(228, 120, 86, 20);
        panelNowyLadunek.add(grainStandard);
        grainStandard.add("-----");
        grainStandard.add("Yes");
        grainStandard.add("No");
        
        
        
        textUwagi = new JTextField();
        textUwagi.setFont(new Font("Tahoma", Font.PLAIN, 10));
        textUwagi.setColumns(10);
        textUwagi.setBounds(332, 58, 465, 162);
        panelNowyLadunek.add(textUwagi);
        
        JButton btnDodajNowyLadunek = new JButton("Dodaj");
        btnDodajNowyLadunek.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		/*
        		 * czesc kodu obslugujaca dodawanie nowego ladunku do bazy danych
        		 * 
        		 * 
        		 * 
        		 */
        		
        
        		//dostosowuje dane wpisane przez uzytkowenika na odpowiedni format
        		//odpowiedni dla bazy danych
        		String CenaLadunku = textCenaLadunku.getText();
        		Double CenaLadunkuDouble = Double.parseDouble(CenaLadunku);
        	
        		String ObjetoscLadunku= textObjetoscLadunku.getText();
        		Double ObjetoscLadunkuDouble = Double.parseDouble(ObjetoscLadunku);
        		
        	
        		String grainStandardtext = grainStandard.getSelectedItem();
        		String Uwagi = textUwagi.getText();
        		
        		
        		Connection conn3 = null;
        	     
        		
        		
        		try {
        		
        			//nawiazanie polaczenia z bazadanych
        			//conn3 = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
        			conn3 = DriverManager.getConnection("jdbc:sqlite:Flota.db");
        			
        			//polecenie dla bazy danych
        			String polecenieSql = "INSERT INTO LADUNEK(NAZWA_LADUNKU,   CENA_ZA_TONE_USD, OBJETOSC_TONY, GRAIN_STANDARD, UWAGI_DOT_LADUNKU) VALUES(?,?,?,?,?)";
        			PreparedStatement stmt = conn3.prepareStatement(polecenieSql);

        			stmt.setString(1, textNazwaLadunku.getText());       		
        			stmt.setDouble(2, CenaLadunkuDouble);
        			stmt.setDouble(3, ObjetoscLadunkuDouble);
        			stmt.setString(4, grainStandardtext);
        			stmt.setString(5, Uwagi);
        			
        		        		
        			stmt.executeUpdate();
        			stmt.close();
        			conn3.close();
        		
        			JOptionPane.showMessageDialog(null, "Dodano nowy typ ³adunku do bazy danych");
        		
        		}catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, "B³¹d, nie dodano nowego typu ³adunku do bazy");
        			
        		}
        		
        	}
        });
        btnDodajNowyLadunek.setForeground(Color.BLACK);
        btnDodajNowyLadunek.setBackground(Color.LIGHT_GRAY);
        btnDodajNowyLadunek.setBounds(117, 197, 89, 23);
        panelNowyLadunek.add(btnDodajNowyLadunek);
        
       
		
        JPanel panelNowyStatek = new JPanel();
        panelNowyStatek.setLayout(null);
        //datePanel.add(jPanel, BorderLayout.WEST);
        panelNowyStatek.setBorder(new TitledBorder(null, "wprowadz nowy statek do bazy danych", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelNowyStatek.setBackground(Color.GRAY);
        panelNowyStatek.setBounds(384, 461, 324, 231);
        panelNowyStatek.setVisible(true);
        userFrame.getContentPane().add(panelNowyStatek);
        
        
        
        
        textNazwaNowegoStatku = new JTextField();
        textNazwaNowegoStatku.setToolTipText("Pole wymagane");
        textNazwaNowegoStatku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textNazwaNowegoStatku.setColumns(10);
        textNazwaNowegoStatku.setBounds(228, 26, 86, 20);
        panelNowyStatek.add(textNazwaNowegoStatku);
        
        JLabel nazwaNowegoStatku = new JLabel("Nazwa");
        nazwaNowegoStatku.setForeground(Color.BLACK);
        nazwaNowegoStatku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nazwaNowegoStatku.setBounds(6, 25, 43, 19);
        panelNowyStatek.add(nazwaNowegoStatku);
        
        JLabel nazwaLadownosci = new JLabel("\u0141adowno\u015B\u0107 statku DWT");
        nazwaLadownosci.setForeground(Color.BLACK);
        nazwaLadownosci.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nazwaLadownosci.setBounds(6, 59, 174, 19);
        panelNowyStatek.add(nazwaLadownosci);
        
        JLabel nazwaObjetosci = new JLabel("Obj\u0119to\u015B\u0107 \u0141adowni");
        nazwaObjetosci.setForeground(Color.BLACK);
        nazwaObjetosci.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nazwaObjetosci.setBounds(6, 89, 119, 19);
        panelNowyStatek.add(nazwaObjetosci);
        
        JLabel nazwaDostepnosci = new JLabel("Data dost\u0119pno\u015Bci");
        nazwaDostepnosci.setForeground(Color.BLACK);
        nazwaDostepnosci.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nazwaDostepnosci.setBounds(6, 119, 152, 19);
        panelNowyStatek.add(nazwaDostepnosci);
        
        JLabel nazwaSpalanie = new JLabel("Dobowy koszt paliwa");
        nazwaSpalanie.setForeground(Color.BLACK);
        nazwaSpalanie.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nazwaSpalanie.setBounds(6, 149, 137, 19);
        panelNowyStatek.add(nazwaSpalanie);
        
        textLadownoscStatku = new JTextField();
        textLadownoscStatku.setToolTipText("Wprowadz wartosc calkowita lub u\u017Cyj kropki \"  .  \"\r\nPoje nie rozpoznaje przecinka");
        textLadownoscStatku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textLadownoscStatku.setColumns(10);
        textLadownoscStatku.setBounds(228, 57, 86, 20);
        panelNowyStatek.add(textLadownoscStatku);
        
        textObjetoscStatku = new JTextField();
        textObjetoscStatku.setToolTipText("Wprowadz wartosc calkowita lub u\u017Cyj kropki \"  .  \"\r\nPoje nie rozpoznaje przecinka");
        textObjetoscStatku.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textObjetoscStatku.setColumns(10);
        textObjetoscStatku.setBounds(228, 90, 86, 20);
        panelNowyStatek.add(textObjetoscStatku);
        
        textKoszt = new JTextField();
        textKoszt.setToolTipText("Wprowadz wartosc calkowita lub u\u017Cyj kropki \"  .  \"\r\nPoje nie rozpoznaje przecinka");
        textKoszt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textKoszt.setColumns(10);
        textKoszt.setBounds(228, 150, 86, 20);
        panelNowyStatek.add(textKoszt);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(228, 119, 86, 20);
        panelNowyStatek.add(dateChooser);
        
    //  String dataDostepnosci = (new java.text.SimpleDateFormat("MM/dd/yyyy")).format(dateChooser.getDate());
        
        
        
        btnDodajNowyStatek = new JButton("Dodaj");
        btnDodajNowyStatek.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	
         		
        		/*
        		 * ¯utowanie wartosc Date wybranej w JDateChooser na format String
        		 * oraz pozostalych wartosci wybranych w panelu "nowy statek
        		 * ze String na Double aby w nbazie danych wpisane wartosci reprezentowaly wartosci liczbowe"
        		     
        		 */     		

        	
        		String ladownoscString = textLadownoscStatku.getText();
        		Double ladownoscDouble = Double.parseDouble(ladownoscString);
        		
        		
        		String objetoscString= textObjetoscStatku.getText();
        		Double objetoscDouble = Double.parseDouble(objetoscString);
        		
        		String dataDostepnosci = (new java.text.SimpleDateFormat("dd/MM/yyyy")).format(dateChooser.getDate());
        		
        		String koszt = textKoszt.getText();
        		Double kosztDouble = Double.parseDouble(koszt);
        		
        	     
        		Connection conn4 = null;
        		
        		try {
        		

        			//conn4 = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
        			conn4 = DriverManager.getConnection("jdbc:sqlite:Flota.db");
        			
        			
        			
        			String polecenieSql = "INSERT INTO STATEK(NAZWA_STATKU,   LADOWNOSC_STATKU_DWT, LADOWNOSC_STATKU_OBJETOSC_M3, SZCOWANA_DATA_DOSTEPNOSCI, DOBOWY_KOSZY_PALIWA_USD) VALUES(?,?,?,?,?)";
        			PreparedStatement stmt = conn4.prepareStatement(polecenieSql);

        			stmt.setString(1, textNazwaNowegoStatku.getText());       		
        			stmt.setDouble(2, ladownoscDouble);
        			stmt.setDouble(3, objetoscDouble);
        			stmt.setString(4, dataDostepnosci);
        			stmt.setDouble(5, kosztDouble);
        		        		
        			stmt.executeUpdate();
        			stmt.close();
        			conn4.close();
        		
        			JOptionPane.showMessageDialog(null, "Dodano nowy statek do bazy danych");
        		
        		}catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, "B³¹d, nie dodano statku do bazy");
        			
        		}
        		
  
        		
        		
        	}
        });
        btnDodajNowyStatek.setForeground(Color.BLACK);
        btnDodajNowyStatek.setBackground(Color.LIGHT_GRAY);
        btnDodajNowyStatek.setBounds(117, 197, 89, 23);
        panelNowyStatek.add(btnDodajNowyStatek);
        
        JPanel panelZamownienie = new JPanel();
        panelZamownienie.setBorder(new TitledBorder(null, "wprowadz nowe zamówienie", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelZamownienie.setBackground(Color.GRAY);
        panelZamownienie.setBounds(50, 45, 476, 405);
        userFrame.getContentPane().add(panelZamownienie);
                panelZamownienie.setLayout(null);
        
        
                //tekst podpowaiadjÄ…cy
                JLabel lbLadunek = new JLabel("Rodzaj \u0142adunku");
                lbLadunek.setBounds(40, 34, 132, 19);
                panelZamownienie.add(lbLadunek);
                lbLadunek.setFont(new Font("Tahoma", Font.PLAIN, 15));
                lbLadunek.setForeground(Color.BLACK);
                
                        //tekst podpowaiadjÄ…cy
                        JLabel lbZaladunek = new JLabel("Port zaladunkowy");
                        lbZaladunek.setBounds(40, 64, 132, 29);
                        panelZamownienie.add(lbZaladunek);
                        lbZaladunek.setForeground(Color.BLACK);
                        lbZaladunek.setFont(new Font("Tahoma", Font.PLAIN, 15));
                        
                                //tekst podpowaiadjÄ…cy
                                JLabel lbWyladunek = new JLabel("Port wyladukowy");
                                lbWyladunek.setBounds(40, 104, 132, 29);
                                panelZamownienie.add(lbWyladunek);
                                lbWyladunek.setForeground(Color.BLACK);
                                lbWyladunek.setFont(new Font("Tahoma", Font.PLAIN, 15));
                                
                                        //tekst podpowaiadjÄ…cy
                                        JLabel lbIlosc = new JLabel("Podaj ilo\u015B\u0107 laduku");
                                        lbIlosc.setBounds(40, 144, 132, 29);
                                        panelZamownienie.add(lbIlosc);
                                        lbIlosc.setForeground(Color.BLACK);
                                        lbIlosc.setFont(new Font("Tahoma", Font.PLAIN, 15));
                                        
                                                //tekst podpowaiadjÄ…cy
                                                JLabel lbData = new JLabel("data zaladunku");
                                                lbData.setBounds(40, 184, 132, 29);
                                                panelZamownienie.add(lbData);
                                                lbData.setForeground(Color.BLACK);
                                                lbData.setFont(new Font("Tahoma", Font.PLAIN, 15));
                                                
                                                        textIlosc = new JTextField(String.valueOf(deklarujIloscLadunku));
                                                        textIlosc.setBounds(182, 146, 119, 29);
                                                        panelZamownienie.add(textIlosc);
                                                        textIlosc.setBackground(Color.WHITE);
                                                        textIlosc.setForeground(Color.BLACK);
                                                        textIlosc.setColumns(10);
                                                        textIlosc.setToolTipText("\u0142adunek w tonach");
                                                                
                                                                JLabel lblPodajDatWyladunku = new JLabel("data dostarczenia");
                                                                lblPodajDatWyladunku.setForeground(Color.BLACK);
                                                                lblPodajDatWyladunku.setFont(new Font("Tahoma", Font.PLAIN, 15));
                                                                lblPodajDatWyladunku.setBounds(40, 225, 132, 29);
                                                                panelZamownienie.add(lblPodajDatWyladunku);
                                                                
                                                                dateChooserZaladunek = new JDateChooser();
                                                                dateChooserZaladunek.setBounds(182, 184, 119, 29);
                                                                panelZamownienie.add(dateChooserZaladunek);
                                                                
                                                                dateChooserWyladunek = new JDateChooser();
                                                                dateChooserWyladunek.setBounds(182, 225, 119, 29);
                                                                panelZamownienie.add(dateChooserWyladunek);
                                                                
                                                                lblZamowienieLadunek = new JLabel("-----");
                                                                lblZamowienieLadunek.setFont(new Font("Tahoma", Font.PLAIN, 10));
                                                                lblZamowienieLadunek.setVerticalAlignment(SwingConstants.TOP);
                                                                lblZamowienieLadunek.setBounds(325, 39, 141, 19);
                                                                panelZamownienie.add(lblZamowienieLadunek);
                                                                
                                                                lblZamowieniePort = new JLabel("-----");
                                                                lblZamowieniePort.setVerticalAlignment(SwingConstants.TOP);
                                                                lblZamowieniePort.setFont(new Font("Tahoma", Font.PLAIN, 10));
                                                                lblZamowieniePort.setBounds(325, 74, 141, 19);
                                                                panelZamownienie.add(lblZamowieniePort);
                                                                
                                                                lblZamowieniePWyladunkowy = new JLabel("-----");
                                                                lblZamowieniePWyladunkowy.setVerticalAlignment(SwingConstants.TOP);
                                                                lblZamowieniePWyladunkowy.setFont(new Font("Tahoma", Font.PLAIN, 10));
                                                                lblZamowieniePWyladunkowy.setBounds(325, 114, 141, 19);
                                                                panelZamownienie.add(lblZamowieniePWyladunkowy);
                                                                
                                                                lblZamowienieIlosc = new JLabel("-----");
                                                                lblZamowienieIlosc.setVerticalAlignment(SwingConstants.TOP);
                                                                lblZamowienieIlosc.setFont(new Font("Tahoma", Font.PLAIN, 10));
                                                                lblZamowienieIlosc.setBounds(325, 154, 141, 19);
                                                                panelZamownienie.add(lblZamowienieIlosc);
                                                                
                                                                lblZamowienieDataZal = new JLabel("-----");
                                                                lblZamowienieDataZal.setVerticalAlignment(SwingConstants.TOP);
                                                                lblZamowienieDataZal.setFont(new Font("Tahoma", Font.PLAIN, 10));
                                                                lblZamowienieDataZal.setBounds(325, 194, 141, 19);
                                                                panelZamownienie.add(lblZamowienieDataZal);
                                                                
                                                                lblZamowienieDataWy = new JLabel("-----");
                                                                lblZamowienieDataWy.setVerticalAlignment(SwingConstants.TOP);
                                                                lblZamowienieDataWy.setFont(new Font("Tahoma", Font.PLAIN, 10));
                                                                lblZamowienieDataWy.setBounds(325, 235, 141, 19);
                                                                panelZamownienie.add(lblZamowienieDataWy);                                                         
                                                                
                                                                lblWartoscZamowienia = new JLabel("Wartos zamowienia");
                                                                lblWartoscZamowienia.setBounds(10, 265, 456, 71);
                                                                panelZamownienie.add(lblWartoscZamowienia);
                                                                
                                                                JButton btnPodsumowanie = new JButton("Podsumowanie");
                                                                btnPodsumowanie.addActionListener(new ActionListener() {
                                                                	public void actionPerformed(ActionEvent e) {
                                                                		
                                                                		wybranyLadunekWBTN = (String) comboBoxLadunek.getSelectedItem();
                                                                		wybranyPort = (String) comboBoxPort.getSelectedItem();
                                                                		wybranyPortWyladunkowy = (String) comboBoxPortWyladunkowy.getSelectedItem();
                                                                		String iloscString = textIlosc.getText();
                                                                		iloscDouble = Double.parseDouble(iloscString);
                                                                		ilosc = Double.parseDouble(textIlosc.getText());
                                                                		dataZaladunku = dateChooserZaladunek.getDate();
                                                                		dataWyladunku = dateChooserWyladunek.getDate();
                                                                		                                                            		
                                                                		
                                                                		String podsumowanieLadunek = wybranyLadunekWBTN;
                                                                		String podsumowaniePort = wybranyPort;
                                                                		String podsumowanieWyladunkowy = wybranyPortWyladunkowy;
                                                                		String podsumowanieIlosc = textIlosc.getText();
                                                                		String podsumowanieDataZa = dataZaladunku.toString();
                                                                		String podsumowanieDataWy = dataWyladunku.toString();
                                                                	
                                                                		lblZamowienieLadunek.setText(podsumowanieLadunek);
                                                                		lblZamowieniePort.setText(podsumowaniePort);
                                                                		lblZamowieniePWyladunkowy.setText(podsumowanieWyladunkowy);
                                                                		lblZamowienieIlosc.setText(podsumowanieIlosc);
                                                                		lblZamowienieDataZal.setText(podsumowanieDataZa);
                                                                		lblZamowienieDataWy.setText(podsumowanieDataWy);
                                                                		
                                                                		wartoscZam = Double.parseDouble(podsumowanieIlosc);
                                                                	
                                                                		Double cenaDouble;
                                                                		Connection conn = null;
                                                                		
                                                                		
                                                               		 try {
                                                                            Class.forName("org.sqlite.JDBC");
                                                                        } catch (ClassNotFoundException e2) {
                                                                            e2.printStackTrace();
                                                                        }
                                                               		
                                                               			try {

                                                                  
                                                                       		conn = DriverManager.getConnection("jdbc:sqlite:Flota.db");
                                                                       		
                                                                       		Statement stat = conn.createStatement();
                                                                       		
                                                                          		String command = ("Select CENA_ZA_TONE_USD FROM LADUNEK WHERE NAZWA_LADUNKU = '" + wybranyLadunekWBTN + "'");
                                                                          		//stat.executeQuery(command);
                                                                          		ResultSet rs = stat.executeQuery(command);
                                                                          		
                                                                          		
                                                                          		while(rs.next()) {
                                                                          			cenaDouble = rs.getDouble(1);
                                                                          			wartoscZamowienia = cenaDouble * ilosc;
                                                                          			wartosZamowieniString = String.valueOf(wartoscZamowienia);
                                                                          			
                                                                          		}
                                                                     		
                                                               			} catch (Exception e3) {
                                                                       		JOptionPane.showMessageDialog(null, "Problem with connection of database");
                                                                       		
                                                               			}
                                                               			
                                                               			lblWartoscZamowienia.setText("Wartoœæ zamówienia wynosi: " +wartosZamowieniString+ " USD");
                                                                		
                                                                		
                                                                		
                                                                		
                                                                	}
                                                                });
                                                                btnPodsumowanie.setBounds(10, 341, 132, 53);
                                                                panelZamownienie.add(btnPodsumowanie);
                                                                
                                                                
                                                                    comboBoxLadunek = new JComboBox(tabelaListaLadunek);
                                                                    comboBoxLadunek.setBounds(182, 31, 119, 29);
                                                                    panelZamownienie.add(comboBoxLadunek);
                                                                    comboBoxLadunek.setForeground(Color.BLACK);
                                                                    comboBoxLadunek.setBackground(Color.WHITE);
                                                                    
                                                                    
                                                                    
                                                                    comboBoxPort = new JComboBox(tabelaListaPort);
                                                                    comboBoxPort.setBounds(182, 66, 119, 29);
                                                                    panelZamownienie.add(comboBoxPort);
                                                                    comboBoxPort.setForeground(Color.BLACK);
                                                                    comboBoxPort.setBackground(Color.WHITE);
                                                                    
       
      
                                                                    comboBoxPortWyladunkowy = new JComboBox(tabelaListaPort);
                                                                    comboBoxPortWyladunkowy.setBounds(182, 106, 119, 29);
                                                                    panelZamownienie.add(comboBoxPortWyladunkowy);
                                                                    comboBoxPortWyladunkowy.setForeground(Color.BLACK);
                                                                    comboBoxPortWyladunkowy.setBackground(Color.WHITE);
                                                                    
                                                                    JButton btnAnalizuj = new JButton("Analizuj zamowienie");
                                                                    btnAnalizuj.setBounds(334, 341, 132, 53);
                                                                    panelZamownienie.add(btnAnalizuj);
                                                                    
                                                                    JPanel panelPodsumowanie = new JPanel();
                                                                    panelPodsumowanie.setBorder(new TitledBorder(null, "podsumowanie zamównienia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
                                                                    
                                                                    
                                                                    
                                                                    
                                                                    panelPodsumowanie.setBackground(Color.GRAY);
                                                                    panelPodsumowanie.setBounds(311, 21, 155, 237);
                                                                    panelZamownienie.add(panelPodsumowanie);
                                                                    
                                                                  
                                                                    
                                                                    
                                                                    
                                                                   
     
        JPanel panelWynikiBazy = new JPanel();
        panelWynikiBazy.setBorder(new TitledBorder(null, "wyniki zapytania bazy danych", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelWynikiBazy.setBackground(Color.GRAY);
        panelWynikiBazy.setBounds(718, 108, 807, 342);
        userFrame.getContentPane().add(panelWynikiBazy);
        panelWynikiBazy.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 24, 787, 307);
        scrollPane.setBackground(Color.GRAY);
        scrollPane.setForeground(Color.GRAY);
        panelWynikiBazy.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        table.setForeground(Color.BLACK);
        table.setBackground(Color.GRAY);
        
        JButton btnPokarzStatki = new JButton("Statki");
        btnPokarzStatki.setForeground(Color.BLACK);
        btnPokarzStatki.setBackground(Color.LIGHT_GRAY);
        btnPokarzStatki.setBounds(726, 82, 89, 23);
        userFrame.getContentPane().add(btnPokarzStatki);
        
        JButton btnPokarzPorty = new JButton("Porty");
        btnPokarzPorty.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Connection conn6 = null;
        		
        		try {
        		

        			//conn6 = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
        			conn6 = DriverManager.getConnection("jdbc:sqlite:Flota.db");
        			
        			
        			
        			String query = "Select* From PORT";
        			PreparedStatement pst = conn6.prepareStatement(query);
        			ResultSet rs = pst.executeQuery();
        			table.setModel(DbUtils.resultSetToTableModel(rs));
        			
        			
        			
        		
        		}catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, "B³¹d po³¹czenia z baz¹");
        			
        		}
        		
        		
        	}
        });
        btnPokarzPorty.setForeground(Color.BLACK);
        btnPokarzPorty.setBackground(Color.LIGHT_GRAY);
        btnPokarzPorty.setBounds(825, 82, 89, 23);
        userFrame.getContentPane().add(btnPokarzPorty);
        
        JButton btnPokarzLadunki = new JButton("Ladunki");
        btnPokarzLadunki.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Connection conn7 = null;
        		try {
            		

        		//	conn7 = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
        			conn7 = DriverManager.getConnection("jdbc:sqlite:Flota.db");
        			
        			String query = "Select* From LADUNEK";
        			PreparedStatement pst = conn7.prepareStatement(query);
        			ResultSet rs = pst.executeQuery();
        			table.setModel(DbUtils.resultSetToTableModel(rs));
        			
        			
        			
        		
        		}catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, "B³¹d po³¹czenia z baz¹");
        			
        		}
        		
        		
        	}
        });
        btnPokarzLadunki.setForeground(Color.BLACK);
        btnPokarzLadunki.setBackground(Color.LIGHT_GRAY);
        btnPokarzLadunki.setBounds(924, 82, 89, 23);
        userFrame.getContentPane().add(btnPokarzLadunki);
        btnPokarzStatki.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		Connection conn5 = null;
        		
        		try {
        		

        			//conn5 = DriverManager.getConnection("jdbc:sqlite:C:\\JAVA\\Moje bazy danych\\Flota.db");
        			conn5 = DriverManager.getConnection("jdbc:sqlite:Flota.db");
        			
        			String query = "Select* From STATEK";
        			PreparedStatement pst = conn5.prepareStatement(query);
        			ResultSet rs = pst.executeQuery();
        			table.setModel(DbUtils.resultSetToTableModel(rs));
        			
        			
        			
        		
        		}catch (Exception ex) {
        			JOptionPane.showMessageDialog(null, "B³¹d po³¹czenia z baz¹");
        			
        		}
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        		
        	}
        });
        
        
       
       
        }
}
