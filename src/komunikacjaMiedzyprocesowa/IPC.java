package komunikacjaMiedzyprocesowa;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class IPC {
	private static final List<Skrzynka> SkrzynkaPocztowa = new LinkedList<>(); 					//lista skrzynek
	
	//wysyla wiadomosc, jesli pierwszy raz do danego procesu to tworzy skrzynke dla odbiorcy, jesli
	//nie ma tego procesu to zwraca wiadomosc i kontynuuje wykonywanie procesu
	public static void wyslij(Wiadomosc wiadomosc,int nrSkrzynki){  //metoda do wyslania wiadomosci
		if(wiadomosc.pobierzIDnadawcy()==wiadomosc.pobierzIDodbiorcy()){
			System.out.println("Proba wyslania wiadomosci o tym samym adresacie i odbiorcy");
			System.out.println("Wysylanie nie powiodlo sie");
			return;
		}
			boolean wyslany = false;							//do sprawdzenia czy skrzynka istnieje (pierwsze wyslanie)
			for(Skrzynka x : SkrzynkaPocztowa){					//sposrod wszystkich skrzynek..
				if(x.pobierzNumerSkrzynki()==nrSkrzynki){ 	//szuka tej o podanym numerze procesu i jesli jest to wysyla do niej
					if(x.pobierzWlascicielaSkrzynki()!=wiadomosc.pobierzIDnadawcy()){
						System.out.println("Proces nie jest wlascicielem skrzynki "+nrSkrzynki);
						System.out.println("Wiadomosc nie zostala wyslana do skrzynki!");
						return;
					}
					x.dodajWiadomosc(wiadomosc);
					wyslany =true;  							//i konczy
					System.out.println("Komunikat zostal wyslany: "+wiadomosc.pobierzWiadomosc()+" do skrzynki: "+nrSkrzynki); //informuje
					
				}
			}
			
			if(wyslany == false){//jesli nie ma na liscie podanego numeru skrzynki to 
					Skrzynka skrzynka = new Skrzynka(wiadomosc.pobierzIDnadawcy(),nrSkrzynki);  							//utworz
					SkrzynkaPocztowa.add(skrzynka);
					System.out.println("Skrzynka wlasciciela: "+wiadomosc.pobierzIDnadawcy()+" o numerze: "+nrSkrzynki+" zostala utworzona"); //poinformuj o utworzeniu
					skrzynka.dodajWiadomosc(wiadomosc);  									//i dokoncz j.w.
					System.out.println("Komunikat zostal wyslany: "+wiadomosc.pobierzWiadomosc()+" do skrzynki: "+nrSkrzynki);
			}
	}
	//jesli true to wiadomosc zostaje odebrana i kontynuuje, jesli nie, proces powinien sie zawiesic, wykorzystanie
	//w klasie Czytnik metodzie Przetworz
	public static boolean odbierz(int numerSkrzynki, int obecnyProces){
		try{
			for(Skrzynka x : SkrzynkaPocztowa){  		//sprawdza skrzynki...//sprawdza czy jest skrzynka, jesli tak to...
				if(x.pobierzNumerSkrzynki()==numerSkrzynki){
					if(x.pobierzWlascicielaSkrzynki()==obecnyProces){
						System.out.println("Proces nie jest wlascicielem skrzynki "+numerSkrzynki+".");
						System.out.println("Wlasciciel skrzynki nie ma mozliwosci czytania z niej wiadomosci!");
						return false;
					}
					else
						return x.pobierzWiadomosc();
				}
			}	   //jesli znajdzie numer i bedzie tam wiadomosc...
						//		System.out.println("Komunikat zostal odebrany: "+x.pobierzWiadomosc());   WYWALILEM BO ZDUBLUJE I POBEIRZE 2 WIADOMOSCI
					throw new InterruptedException("demo");
				
		 
		}catch(InterruptedException e){
		System.out.println("Wiadomosc nie zostala odebrana, skrzynka nie istnieje"); 
		return false;
		}
	}
	
	//usuwa skrzynke, przyklad w Czytnik metodzie Przetworz przy odebraniu komendy XD
	public static boolean usunSkrzynke(int wlascicielSkrzynki){
			for(Skrzynka x : SkrzynkaPocztowa){ 												//sprawdza numer procesu ze skrzynka
				if(x.pobierzWlascicielaSkrzynki() == wlascicielSkrzynki){
					System.out.println("Skrzynka "+x.pobierzNumerSkrzynki()+" zostala usunieta");
					x.usunWiadomosci(); //usuwa wiadomosci jesli tam wystepuja, aby pozbyc sie bledu zwalniania pamieci po obiekcie
					SkrzynkaPocztowa.remove(x); 		//i usuwa ja
					return true;
					//USUNAC WIADOMOSCI Z TEJ SKRZYNKI
				}
			}
			return false;
	}
	
	public static void informacje(int wlasciciel){ 		//informuje o tym czy jest skrzynka (z zawartoscia) czy nie istnieje
		try{
		if(czyIstnieje(wlasciciel)){
			for(Skrzynka x : SkrzynkaPocztowa){
				if(x.pobierzWlascicielaSkrzynki() == wlasciciel){
					x.wyswietlStanSkrzynki();
				}
			}
		}else
			throw new InterruptedException("demo");
		}catch(InterruptedException e){
			System.out.println("Skrzynka '"+wlasciciel+"' nie istnieje, wyswietlenie zawartosci nie powiodlo sie");
		}
		
	}
	
	public static boolean czyIstnieje(int wlasciciel){  							//do sprawdzania czy istnieje skrzynka o podanym ID
		for(Skrzynka x : SkrzynkaPocztowa){
			if(x.pobierzWlascicielaSkrzynki() == wlasciciel)
				return true;
		}
		return false;
	}
	
	public static void wszystkieSkrzynki(){   	//wyswietlenie stanu wszystkich utworzonych skrzynek
		try{
			System.out.println("--SPIS SKRZYNEK WSZYSTKICH PROCESOW--");
			for(Skrzynka x : SkrzynkaPocztowa){
				x.wyswietlStanSkrzynki();
			}
		}catch(Exception e){
			System.out.println("Wyswietlanie stanu skrzynek nie powiodlo sie");
		}
	}
}
