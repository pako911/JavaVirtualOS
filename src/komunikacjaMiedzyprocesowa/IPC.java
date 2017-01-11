package komunikacjaMiedzyprocesowa;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class IPC {
	private static final List<Skrzynka> SkrzynkaPocztowa = new LinkedList<>(); 					//lista skrzynek
	public static void wyslij(Wiadomosc wiadomosc, int IDodbiorcy) throws InterruptedException{  //metoda do wyslania wiadomosci
		boolean wyslany = false;							//do sprawdzenia czy skrzynka istnieje (pierwsze wyslanie)
		for(Skrzynka x : SkrzynkaPocztowa){					//sposrod wszystkich skrzynek...
			if(x.pobierzWlascicielaSkrzynki()==IDodbiorcy){ 	//szuka tej o podanym numerze procesu i jesli jest to wysyla do niej
				x.dodajWiadomosc(wiadomosc);
				wyslany =true;  							//i konczy
				System.out.println("Komunikat zostal wyslany: "+wiadomosc.pobierzWiadomosc()); //informuje
				TimeUnit.MILLISECONDS.sleep(50); 			 //uspienie ,a zeby szybko nie przechodzilo ;)
			}
		}
		
		if(wyslany == false){  														//jesli nie ma na liscie podanego numeru skrzynki to 
			Skrzynka skrzynka = new Skrzynka(IDodbiorcy);  							//utworz
			System.out.println("Skrzynka procesu "+IDodbiorcy+" zostala utworzona"); //poinformuj o utworzeniu
			skrzynka.dodajWiadomosc(wiadomosc);  									//i dokoncz j.w.
			SkrzynkaPocztowa.add(skrzynka);
			System.out.println("Komunikat do procesu "+IDodbiorcy+" zostal wyslany: "+wiadomosc.pobierzWiadomosc());
			TimeUnit.MILLISECONDS.sleep(50);
		}
	}
	//			  Wiadomosc String
	public static boolean odbierz(int IDodbiorcy) throws InterruptedException{
				//sprawdza czy jest skrzynka, jesli tak to...
			for(Skrzynka x : SkrzynkaPocztowa){  		//sprawdza skrzynki...
				if(x.pobierzWlascicielaSkrzynki()==IDodbiorcy){
				   //jesli znajdzie numer i bedzie tam wiadomosc...
						//		System.out.println("Komunikat zostal odebrany: "+x.pobierzWiadomosc());   WYWALILEM BO ZDUBLUJE I POBEIRZE 2 WIADOMOSCI
					TimeUnit.MILLISECONDS.sleep(50);
					return x.pobierzWiadomosc(); 		//pobierz
				}
			}
						//jesli nie to... !!!! TU MA NIE BYC KLAMRY
		TimeUnit.MILLISECONDS.sleep(50);
		System.out.println("Wiadomosc nie zostala odebrana, skrzynka nie istnieje"); 
		return false;
	}
	
	public static boolean usunSkrzynke(int wlascicielSkrzynki) throws InterruptedException{
		for(Skrzynka x : SkrzynkaPocztowa){ 												//sprawdza numer procesu ze skrzynka
			if(x.pobierzWlascicielaSkrzynki() == wlascicielSkrzynki){
			x.usunWiadomosci(); //usuwa wiadomosci jesli tam wystepuja, aby pozbyc sie bledu zwalniania pamieci po obiekcie
			SkrzynkaPocztowa.remove(x); 		//i usuwa ja
			//USUNAC WIADOMOSCI Z TEJ SKRZYNKI
			TimeUnit.MILLISECONDS.sleep(50);
			return true;
			}
		}return false;
	}
	public static void informacje(int ID) throws InterruptedException{ 		//informuje o tym czy jest skrzynka (z zawartoscia) czy nie istnieje
		if(czyIstnieje(ID)){
			for(Skrzynka x : SkrzynkaPocztowa){
				if(x.pobierzWlascicielaSkrzynki() == ID){
					x.wyswietlStanSkrzynki();
					TimeUnit.MILLISECONDS.sleep(50);
				}
			}
		}else{
			TimeUnit.MILLISECONDS.sleep(50);
			System.out.println("Skrzynka procesu "+ID+" nie istnieje, wyswietlenie zawartosci nie powiodlo sie");
		}
	}
	
	public static boolean czyIstnieje(int ID){  							//do sprawdzania czy istnieje skrzynka o podanym ID
		for(Skrzynka x : SkrzynkaPocztowa){
			if(x.pobierzWlascicielaSkrzynki() == ID)
				return true;
		}
		return false;
	}
	
	public static void wszystkieSkrzynki() throws InterruptedException{   	//wyswietlenie stanu wszystkich utworzonych skrzynek
		System.out.println("--SPIS SKRZYNEK WSZYSTKICH PROCESOW--");
		for(Skrzynka x : SkrzynkaPocztowa){
			TimeUnit.MILLISECONDS.sleep(50);
			x.wyswietlStanSkrzynki();
		}
	}
}
