// Klasa budująca Graficzy Interfejs Użytkowanika

package flota;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OknoLogin {

    public JFrame frame;
    public JTextField fieldUser;
    public JPasswordField fieldPassword;
    

    public JLabel connectionLabel;
    String message = "Nie połączona o bazą1";


    OknoLogin() {

        //buduje główną ranke
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.GRAY);
        frame.getContentPane().setLayout(null);

        //tekst podpowiadajacy
        JLabel labelZaloguj = new JLabel("Zaloguj sie do aplikacji");
        labelZaloguj.setForeground(Color.BLACK);
        labelZaloguj.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelZaloguj.setHorizontalAlignment(SwingConstants.CENTER);
        labelZaloguj.setBounds(160, 44, 197, 39);
        frame.getContentPane().add(labelZaloguj);


        //pole wpisania nazy użytkowanika
        fieldUser = new JTextField();
        fieldUser.setHorizontalAlignment(SwingConstants.TRAILING);
        fieldUser.setForeground(Color.BLACK);
        fieldUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
        fieldUser.setToolTipText("username");
        fieldUser.setBounds(251, 111, 183, 31);
        frame.getContentPane().add(fieldUser);

        //pole wpisana hasła użytkowanika
        fieldPassword = new JPasswordField();
        fieldPassword.setHorizontalAlignment(SwingConstants.TRAILING);
        fieldPassword.setForeground(Color.BLACK);
        fieldPassword.setToolTipText("has\u0142o");
        fieldPassword.setBounds(251, 162, 183, 31);
        frame.getContentPane().add(fieldPassword);
        fieldPassword.toString();

        //tekst podpowadający co wpisać
        JLabel labelLogin = new JLabel("podaj login");
        labelLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelLogin.setForeground(Color.BLACK);
        labelLogin.setBounds(83, 111, 85, 31);
        frame.getContentPane().add(labelLogin);

        //tekst podpowiadający co wpisać
        JLabel labelhaslo = new JLabel("podaj has\u0142o");
        labelhaslo.setForeground(Color.BLACK);
        labelhaslo.setFont(new Font("Tahoma", Font.PLAIN, 15));
        labelhaslo.setBounds(83, 162, 85, 31);
        frame.getContentPane().add(labelhaslo);

        //przycisk wywołujący akcje logowania do bazy danych
        JButton buttonZaloguj = new JButton("Zaloguj");
        buttonZaloguj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Connect buttonConnect = new Connect();

                try {
                	//wywluje polaczenie z baza danych
                    buttonConnect.ConnectionToSqliteDatabase();
                    frame.dispose();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });
        buttonZaloguj.setBackground(Color.LIGHT_GRAY);
        buttonZaloguj.setForeground(Color.BLACK);
        buttonZaloguj.setBounds(345, 219, 89, 23);
        frame.getContentPane().add(buttonZaloguj);


        connectionLabel = new JLabel(message);
        connectionLabel.setForeground(Color.BLACK);
        connectionLabel.setBounds(27, 261, 197, 30);

        frame.getContentPane().add(connectionLabel);
        frame.setResizable(false);
        frame.setBackground(Color.GRAY);
        frame.setForeground(Color.GRAY);
        frame.setBounds(100, 100, 533, 341);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



}
