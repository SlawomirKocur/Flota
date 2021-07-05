package flota;

import java.sql.Date;

//zbiera informacje o zamowieniu i dodaja jako tekst
public class PoodsumowanieZamowienia extends Zamowienie {

	
	
	String pods;
	Date dataZaladunek= (Date) okno.dataZaladunku;
	Date dataWyladunek = (Date) okno.dataWyladunku;
	
	
	public void podsumowanie() {
		
		pods = "ZAMOWIENIE\n ladunek " +okno.wybranyLadunekWBTN;
				
				
			//	+ "\nwartosc zamowienia " +wartoscZamowienia+ "\ndata zaladunku " + dataZaladunek+ "\ndata wyladunku" + dataWyladunek;
		
		
	}
}
