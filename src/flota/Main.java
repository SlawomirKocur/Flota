/*
Program wspierajacy działalnośc shippingową
na podstawie podanych przez uzytkownika danych (rodzaj ładuku, ilość itp.)
aplikacja sprawdza z baza danych optymalny wybór (jaki statek przyniesie najwiekszy zysk)

porownywany jest potencjalny zysk z przewozu ładunku przez dany statek oraz
szacowany koszt takiego przewozu.

Cel Biznesowy: Zwiększenie zysku firmy poprzez optymalizację zakontraktowanych przewozów

w celu uproszczenia przyjęto odległości pomięzy portami w lini prostej
tj. nie brano pod uwagę krzywizny ziemi lub też wystepowanie lądu.



 */

package flota;

import java.awt.*;


public class Main {

    public static void main (String [] args){
        //uruchamia watek glowny aplikacji
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                //inicjuje wywaolanie pierwszej ramki
                try {
                    OknoLogin window = new OknoLogin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
}

