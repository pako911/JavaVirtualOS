package komunikacjaMiedzyprocesowa;

public class Wiadomosc {
	private int IDnadawcy;
	private int IDodbiorcy;
	private String trescWiadomosci;
	private int rozmiarWiadomosci;
	
	public Wiadomosc(int IDnadawcy, int IDodbiorcy, String trescWiadomosci){ //wiadomosc zawiera od kogo wiadomosc (IDnadawcy), tresc (trescWiadomosci) 							
		this.IDnadawcy = IDnadawcy;		
		this.IDodbiorcy = IDodbiorcy;//i rozmiarWiadomosci - nie uzyty pozniej, chyba ze ograniczaamy dlugosc wiadomosci	
		this.trescWiadomosci = trescWiadomosci;
		this.rozmiarWiadomosci = trescWiadomosci.length();
	
	}	//boolean
	public String pobierzWiadomosc(){  //boolean - po dluzszej analizie doszedlem do wniosku ze latwiej pozniej w programie glownym operowac na true/false
		return trescWiadomosci;
	}
	
	public boolean czyOdebrana(){  //boolean - po dluzszej analizie doszedlem do wniosku ze latwiej pozniej w programie glownym operowac na true/false
		return true;
	}
	
	public int pobierzIDnadawcy(){
		return IDnadawcy;
	}
	
	public int pobierzIDodbiorcy(){
		return IDodbiorcy;
	}
	
	public int pobierzRozmiarWiadomosci(){
		return rozmiarWiadomosci;
	}
	public void wyswietlInfo(){	//tj wyswietla wiadomosc konkretna
		System.out.println(this.pobierzIDodbiorcy()+": \t "+this.pobierzWiadomosc());
	}
}
