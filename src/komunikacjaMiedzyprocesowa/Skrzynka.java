package komunikacjaMiedzyprocesowa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Skrzynka {
	private final List<Wiadomosc> messages = new LinkedList<>(); //kolejka wiadomosci w skrzynce procesu
	private int wlascicielSkrzynki;								//numer procesu
	
	public Skrzynka(int wlascicielSkrzynki){ 					//konstruktor
		this.wlascicielSkrzynki = wlascicielSkrzynki;
	}
	public void dodajWiadomosc(Wiadomosc wiadomosc){ 			//dodawanie do skrzynki w kolejce
		this.messages.add(wiadomosc);
	}
	public int pobierzWlascicielaSkrzynki(){ 					//odbieranie wiadomosci i usuwanie jej ze skrzynki
		return wlascicielSkrzynki;
	}
	
	//		Wiadomosc, zamienilem na String bo niepotrzebny caly obiekt do wyswietlania ;)
	public boolean pobierzWiadomosc(int nrOdbiorcy){ //boolean aby w IPC wyznaczac true i false
		int i=0;
		if(messages.isEmpty()){  								//sprawdza czy nie pusta
			System.out.println("Wiadomosc nie zostala odebrana (brak wiadomosci w skrzynce)");
			return false;  										//powrot
			}
		else{
			for(Wiadomosc x : messages){
				if(x.pobierzIDodbiorcy()==nrOdbiorcy){
				Wiadomosc wiadomosc = this.messages.get(i); 		//pobiera pierwszy element z listy i usuwa go
				this.messages.remove(i);
				System.out.println("Wiadomosc zostala odebrana: "+wiadomosc.pobierzWiadomosc());
				return true;				//return wiadomosc//niewazne
				}
				i++;
			}
			System.out.println("W skrzynce nie ma wiadomosci dla tego procesu");
			return false;
		}
	}
	
	public void wyswietlStanSkrzynki(){
		System.out.println("Skrzynka wlasciciela PID- "+pobierzWlascicielaSkrzynki());
		System.out.println(messages.size()+" wiad. ");
		if(!messages.isEmpty()){
			System.out.println("Do: \t tresc");
			System.out.println("-----------------");
			for(Wiadomosc x : messages)
	            x.wyswietlInfo();
		System.out.println("-----------------");
		System.out.println("");
		}
	}
	
	public boolean sprawdzWiadomosci(int idodbiorcy){
		for(Wiadomosc x : messages){
			if(x.pobierzIDodbiorcy()==idodbiorcy)
				return true;
		}
		return false;
	}
	
	//w Czytnik dodane bylo przy usuwaniu procesu i zarazem skrzynki!
	public void usunWiadomosci(){
		for(Wiadomosc x : messages){
			messages.remove(x);
		}
	}
}
