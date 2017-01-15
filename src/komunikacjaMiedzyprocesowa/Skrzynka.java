package komunikacjaMiedzyprocesowa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Skrzynka {
	//			List								LinkedList
	private final Queue<Wiadomosc> messages = new ArrayDeque<>(); //kolejka wiadomosci w skrzynce procesu
	private int wlascicielSkrzynki;								//numer procesu
	private int numerSkrzynki;
	
	public Skrzynka(int wlascicielSkrzynki, int numerSkrzynki){ 					//konstruktor
		this.wlascicielSkrzynki = wlascicielSkrzynki;
		this.numerSkrzynki=numerSkrzynki;
	}
	public void dodajWiadomosc(Wiadomosc wiadomosc){ 			//dodawanie do skrzynki w kolejce
		this.messages.add(wiadomosc);
	}
	public int pobierzWlascicielaSkrzynki(){ 					//odbieranie wiadomosci i usuwanie jej ze skrzynki
		return wlascicielSkrzynki;
	}
	
	public int pobierzNumerSkrzynki(){ 					//odbieranie wiadomosci i usuwanie jej ze skrzynki
		return numerSkrzynki;
	}
	
	//								int nrOdbiorcy
	public boolean pobierzWiadomosc(){ //boolean aby w IPC wyznaczac true i false
		//int i=0;
		if(messages.isEmpty()){  								//sprawdza czy nie pusta
			System.out.println("Wiadomosc nie zostala odebrana (brak wiadomosci w skrzynce)");
			return false;  										//powrot
			}
		else{
		//	for(Wiadomosc x : messages){
		//		if(x.pobierzIDodbiorcy()==nrOdbiorcy){
				Wiadomosc wiadomosc = this.messages.poll();//this.messages.get(i); 		//pobiera pierwszy element z listy i usuwa go
			//	this.messages.remove(i);
				System.out.println("Wiadomosc zostala odebrana: "+wiadomosc.pobierzWiadomosc());
				return true;				//return wiadomosc//niewazne
		//		}
				//i++;
		//	}
			//System.out.println("W skrzynce nie ma wiadomosci dla tego procesu");
			//return false;
		}
	}
	
	public void wyswietlStanSkrzynki(){
		System.out.println("Skrzynka nr "+pobierzNumerSkrzynki()+", wlasciciel PID: "+pobierzWlascicielaSkrzynki());
		System.out.println(messages.size()+" wiad. ");
		if(!messages.isEmpty()){
			System.out.println("-----------------");
			for(Wiadomosc x : messages)
	            x.wyswietlInfo();
		System.out.println("-----------------");
		System.out.println("");
		}
	}
	
	/*public boolean sprawdzWiadomosci(int idodbiorcy){
		for(Wiadomosc x : messages){
			if(x.pobierzIDodbiorcy()==idodbiorcy)
				return true;
		}
		return false;
	}*/
	
	//w Czytnik dodane bylo przy usuwaniu procesu i zarazem skrzynki!
	public void usunWiadomosci(){
		for(Wiadomosc x : messages){
			messages.remove(x);
		}
	}
}
