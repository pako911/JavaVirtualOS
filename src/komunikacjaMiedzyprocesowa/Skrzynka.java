package komunikacjaMiedzyprocesowa;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Skrzynka {
	private final Queue<Wiadomosc> messages = new ArrayDeque<>(); //kolejka wiadomosci w skrzynce procesu
	public int wlascicielSkrzynki;								//numer procesu
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
	public boolean pobierzWiadomosc(){ //boolean aby w IPC wyznaczac true i false
		if(messages.isEmpty()){  								//sprawdza czy nie pusta
			System.out.println("Wiadomosc nie zostala odebrana (brak wiadomosci w skrzynce)");
			return false;  										//powrot
			}
		else{
			Wiadomosc wiadomosc = this.messages.poll(); 		//pobiera pierwszy element z listy i usuwa go
			System.out.println("Wiadomosc zostala odebrana: "+wiadomosc.pobierzWiadomosc());
			return true;				//return wiadomosc//niewazne
		}
	}
	
	public void wyswietlStanSkrzynki(){
		System.out.println("Skrzynka PID "+pobierzWlascicielaSkrzynki()+": "+messages.size()+" wiad. ");
		if(!messages.isEmpty()){
			System.out.println("PID: \ttresc");
			System.out.println("-----------------");
			for(Wiadomosc x : messages)
	            x.wyswietlInfo();
		System.out.println("-----------------");
		System.out.println("");
		}
	}
	
	//w Czytnik dodane bylo przy usuwaniu procesu i zarazem skrzynki!
	public void usunWiadomosci(){
		for(Wiadomosc x : messages){
			messages.remove(x);
		}
	}
}
