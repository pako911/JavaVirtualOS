package komunikacjaMiedzyprocesowa;

public class Wiadomosc {
	private int IDnadawcy;
	private String trescWiadomosci;
	private int rozmiarWiadomosci;
	
	public Wiadomosc(int IDnadawcy,String trescWiadomosci){ //wiadomosc zawiera od kogo wiadomosc (IDnadawcy), tresc (trescWiadomosci) 							
		this.IDnadawcy = IDnadawcy;							//i rozmiarWiadomosci - nie uzyty pozniej, chyba ze ograniczaamy dlugosc wiadomosci	
		this.trescWiadomosci = trescWiadomosci;
		this.rozmiarWiadomosci = trescWiadomosci.length();
	}	//boolean
	public String pobierzWiadomosc(){  //boolean - po dluzszej analizie doszedlem do wniosku ze latwiej pozniej w programie glownym operowac na true/false
		return trescWiadomosci;
	}
	
	public boolean czyOdebrana(){  //boolean - po dluzszej analizie doszedlem do wniosku ze latwiej pozniej w programie glownym operowac na true/false
		return true;
	}
	
	public int pobierzIDprocesu(){
		return IDnadawcy;
	}
	public int pobierzRozmiarWiadomosci(){
		return rozmiarWiadomosci;
	}
	public void wyswietlInfo(){	//tj wyswietla wiadomosc konkretna
		System.out.println(this.pobierzWiadomosc()+"("+this.rozmiarWiadomosci+")");
	}
}
