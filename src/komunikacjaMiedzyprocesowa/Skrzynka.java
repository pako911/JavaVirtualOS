package komunikacjaMiedzyprocesowa;

public class Wiadomosc {
	private int IDnadawcy;
	//private int IDodbiorcy;
	private String trescWiadomosci;
	private int rozmiarWiadomosci;
	//								int IDodbiorcy
	public Wiadomosc(int IDnadawcy, String trescWiadomosci){ //wiadomosc zawiera od kogo wiadomosc (IDnadawcy), tresc (trescWiadomosci) 							
		this.IDnadawcy = IDnadawcy;		
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
	
	/*public int pobierzIDodbiorcy(){
		return IDodbiorcy;
	}*/
	
	public int pobierzRozmiarWiadomosci(){
		return rozmiarWiadomosci;
	}
	public void wyswietlInfo(){	//tj wyswietla wiadomosc konkretna
		System.out.println(this.pobierzWiadomosc());
	} 	//					this.pobierzIDodbiorcy()+
}
