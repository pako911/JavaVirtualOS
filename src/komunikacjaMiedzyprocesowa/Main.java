package komunikacjaMiedzyprocesowa;

import java.io.*;
//PROTEZY - Proces.java, ListaProcesow.java i Czytnik.java
public class Main {
	/*
	 PROGRAM TESTOWY POLEGA NA UTWORZENIU "PROCESU"
	 new 1 - 1 to jego numer
	 utworzenie komend: XR nrskrzynki, XS nrodb wiadomosc
	 
	 DZIALANIE MODULU:
	 PROCES 1 WYSYLAJAC WIADOMOSC: 	XS 1 blabla -otrzyma blad, ze nie moze wyslac wiadomosci do samego siebie <jak skrzynka istnieje>
	  								XS 2 blabla - dobrze
	 proces wysylajacy - nadawca - staje sie wlascicielem skrzynki - nazwa jak numer procesu
	 PROCES 1 CHCE CZYTAC :			XR 1 - blad, nie moze czytac jak jest wlascicielem
	 								XR 2 - dobrze POD WARUNKIEM ze skrzynka 2 zawiera wiadomosc przeznaczona dla procesu 1
	 trzeba usuwac skrzynki gdy proces konczy dzialanie
	 */
	static int id=0;
	static int i=0;
	static int j=-1;
	static int PID=0;
	static int numerSkrzynki;
	static int[] procesy = new int[20];
	public static void main(String[] args) throws IOException {
		PrintWriter pw = new PrintWriter(System.out, true); 						//obiekty do wyswietlania
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 	//i wpisywania w konsoli
		String str; 			//zmienne operujace na danych z konsoli
		//TEST
		pw.println(" @@@@   @@   @     @   @@      @@@   @@@   ");
		pw.println("    @  @  @   @   @   @  @    @     @@ @@  ");
		pw.println("    @  @@@@    @ @    @@@@     @@   @   @  ");
		pw.println("  @ @  @  @    @ @    @  @       @  @@ @@  ");
		pw.println("  @@@  @  @ @   @     @  @    @@@    @@@   ");
		pw.println("*******************************************");
		pw.println("******************  IPC  ******************");
		pw.println("*******************************************");
		pw.println("*POMOC: WPISZ HELP");
		pw.println("->DZIALAJ!<-");
		pw.println("PAMIETAJ !");
		pw.println("kontynuuj 'cd'");
		boolean wyjscie = false; 	//wyjscie z testowego modulu
		//CALE DZIALANIE MODULU
		do
		{			
			str = br.readLine(); 			//wpisanie komendy
			pw.println("Aktualny PID: "+PID);
				if(str.startsWith("new")){ 		//komenda do tworzenia procesu
					id++;
					j++;
					//ListaProcesow.wyswietlWszystkieProcesy(); testowne odpowiedzi
					//ListaProcesow.wyswietlPCBProcesu(id);
					//ListaProcesow.wyswietlPCBProcesu(id);
					PID = Integer.parseInt(str.substring(4,5));							//podzielenie wiersza z komenda aby wydobyc nazwe pliku wejsciowego
					ListaProcesow.dodajProces(PID);
					procesy[j] = PID; //obrazowo, w naszym systemie jako numer aktualnie wykonywanego procesu
					}
				
				if(str.startsWith("change")){
					PID = Integer.parseInt(str.substring(7,8)); //zamiana numeru procesu, do testowania
				}
				
				if(str.startsWith("delete")){
					int numera = Integer.parseInt(str.substring(7,8));
					while(IPC.usunSkrzynke(numera)); //usuwanie wszystkich skrzynek, ktorych wlascicielem jest obecny numer procesu
					
				}
				
				if(str.startsWith("XR")){  					//test komendy, proces ma czytac komunikat
					pw.println("->KOMENDA XR "); 			//rowniez informacyjnie, jak dalsze zaczynajace sie na "->"
					numerSkrzynki = Integer.parseInt(str.substring(3)); //pobranie numeru skrzynki, z ktorej chce czytac
						IPC.odbierz(numerSkrzynki,PID); //if(!IPC.odbierz(numerSkrzynki,PID)) ...
						//zablokujProces(id);//blokowanie procesu gdy nie odczytal
				}				 
			
															//"Wiadomosc zostala odebrana: tresc", jesli nie, 
															//poinformuje "Wiadomosc nie zostala odebrana (brak wiadomosci w skrzynce)"
															//!!! DODAM ZE sprawdza czy skrzynka istnieje, 
															//jesli nie to: "Wiadomosc nie zostala odebrana, skrzynka nie istnieje"
															//i tu nalezy proces zawiesic@@@@@@@@@@@@@@@@@@@@@@@@@
				if(str.startsWith("XS")){ 										//komenda do wysylania komunikatu
					pw.println("->KOMENDA XS ");
					int numerSkrzynki = Integer.parseInt(str.substring(3,4));
					Wiadomosc wiadomosc = new Wiadomosc(PID, str.substring(5)); 	//skrzynka procesu wysylajacego wiadomosc ma numer procesu,
						IPC.wyslij(wiadomosc,numerSkrzynki);//wiadomosc ma id nadawcy, id odbiorcy i tresc
				}						//metoda odpowiedzialna za utworzenie skrzynki 
				
				if(str.startsWith("sab")){
						IPC.wszystkieSkrzynki(); //metoda wyswietla kolejno utworzone skrzynki dzialajacych procesow wraz z zawartosciami		
				}
							
				//DO WYSWIETLANIA KONKRETNEJ SKRZYNKI
				if(str.startsWith("sb")){
					numerSkrzynki = Integer.parseInt(str.substring(3)); 	//pobranie numeru procesu
						IPC.informacje(numerSkrzynki);
					  							//metoda wyswietla dana skrzynke z zawartoscia,
				}								//jesli skrzynka/proces nie istnieje, wyswietli komunikat:
												//"Skrzynka procesu *ID* nie istnieje, wyswietlenie zawartosci 
												//nie powiodlo sie"	
				if(str.startsWith("sap"))
				ListaProcesow.wyswietlWszystkieProcesy();//do testow,
			//DO WYJSCIA Z SYSTEMU
			if(str.startsWith("exit"))
				wyjscie = true;
					
		}while(wyjscie != true);
		pw.println();
		pw.println();
		pw.println("@@@@  @   @  @  @@@@@");
		pw.println("@      @ @        @  ");
		pw.println("@@@     @    @    @  ");
		pw.println("@      @ @   @    @  ");
		pw.println("@@@@  @   @  @    @  ");
	}
}
