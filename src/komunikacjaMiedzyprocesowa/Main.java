package komunikacjaMiedzyprocesowa;

import java.io.*;
//PROTEZY - Proces.java i ListaProcesow.java
public class Main {
	//WAZNE
	//tworzy skrzynke gdy proces wysle wiadomosc - i staje sie jej wlascicielem
	//jak pobierze komende zeby wyslac XS to IPC.wyslij(wiadomosc,PID);
	//jak pobierze komende zeby odebrac XR to IPC.odbierz(PID);
	//program testowy znajdowal sie w lokalizacji : C:\JavaSO\program1.txt
	//komenda do stanu skrzynki danego procesu IPC.informacje(PID);
	//komenda do wyswietlenia stanow wszystkich istniejacych skrzynek IPC.wszystkieSkrzynki();
	//komenda do usuwania skrzynki IPC.usunSkrzynke(PID);
	//gdy proces chce czytac a skrzynki nie ma to dostaje informacje ze nie istnieje
	//gdy nie ma komunikatu do odebrania, rowniez dostaje stosowna informacje
	//SAB - wyswietla zawartosci wszystkich skrzynek procesow
	//SB nr_procesu - wyswietla skrzynke dla danego procesu - jesli istnieje
	//DODAC ZE BLOKUJE PROCES CZY COS
	
	//przykladowe wpisywanie:
	//czy praca krokowa? (t/n) : t
	//XC program1
	//cd,cd(...) w miedzyczasie jak chce sprawdzic stan skrzynek to SAB lub SB nr_procesu
	//gdy dostane informacje o zakonczonym procesie : EXIT
	//tyle
	
	static int id=0;
	public static void main(String[] args) throws InterruptedException, IOException {
		PrintWriter pw = new PrintWriter(System.out, true); 						//obiekty do wyswietlania
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 	//i wpisywania w konsoli
		String str; 			//zmienne operujace na danych z konsoli
		String s; 				//zmienna do czytania z pliku wejsciowego
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
		
		boolean wyjscie = false; 	//wyjscie z testowego modulu
		//CALE DZIALANIE MODULU
		do
		{			
			String nazwaPliku;			
			String sciezka;	
			boolean dzialanie = false; 	//zmienna czy proces zostal zakonczony - komenda XD
					pw.println("PAMIETAJ !");
					pw.println("kontynuuj 'cd'");
					str = br.readLine(); 			//wpisanie komendy
					
					if(str.startsWith("XC")){ 		//komenda do tworzenia procesu
						pw.println("->KOMENDA XC");  	//tak o zebym wiedzial ze zadzialalo to akurat xd zbedne
						id++;
						//ListaProcesow.wyswietlWszystkieProcesy(); testowne odpowiedzi
						//ListaProcesow.wyswietlPCBProcesu(id);
						ListaProcesow.dodajProces(id);
						dzialanie=true;
						//ListaProcesow.wyswietlPCBProcesu(id);
						//ListaProcesow.wyswietlWszystkieProcesy();
						nazwaPliku = str.substring(3); 							//podzielenie wiersza z komenda aby wydobyc nazwe pliku wejsciowego
						sciezka = "c:\\JavaSO\\"+nazwaPliku+".txt"; 	//utworzenie sciezki do pliku aby go otworzyc
						FileReader fr = new FileReader(sciezka);			//otworzenie pliku do odczytu
						BufferedReader brplik = new BufferedReader(fr);  //do czytania
					do{
						str = br.readLine(); //pobranie komendy
					if(str.startsWith("cd")){
						if((s = brplik.readLine()) != null) 			//pobieranie po wierszu
						{		
							ListaProcesow.iterujLinie(id);
							pw.println("Wykonanie polecenia: "+s); 		//informacyjnie
							if(s.startsWith("XR")){  					//test komendy, proces ma czytac komunikat
								pw.println("->KOMENDA XR "); 			//rowniez informacyjnie, jak dalsze zaczynajace sie na "->"
								int y = Integer.parseInt(s.substring(3,4)); //pobranie numeru procesu, z ktorego bedzie czytac ( probowal)
								if(!IPC.odbierz(y))
									System.out.println("proces "+id+" ma byc zablokowany");//wlasciwa metoda odpowiedzialna za czytanie, jesli odczyta wiadomosc 
									//ListaProcesow.zablokujProces(id);//blokowanie procesu gdy niue odczytal
							}							//(pierwsza w kolejce) to wyswietli sie 
						
																		//"Wiadomosc zostala odebrana: tresc", jesli nie, 
																		//poinformuje "Wiadomosc nie zostala odebrana (brak wiadomosci w skrzynce)"
																		//!!! DODAM ZE sprawdza czy skrzynka istnieje, 
																		//jesli nie to: "Wiadomosc nie zostala odebrana, skrzynka nie istnieje"
																		//i tu nalezy proces zawiesic@@@@@@@@@@@@@@@@@@@@@@@@@
							if(s.startsWith("XS")){ 										//komenda do wysylania komunikatu
								pw.println("->KOMENDA XS ");
								Wiadomosc wiadomosc = new Wiadomosc(id,s.substring(3)); 	//skrzynka procesu wysylajacego wiadomosc ma numer procesu,
								IPC.wyslij(wiadomosc, id); 					//	wiadomosc ma ten sam numer
							}						//metoda odpowiedzialna za utworzenie skrzynki 
						
							if(s.startsWith("XD")){  				//zakonczenie procesu <do testow>
								pw.println("->KOMENDA XD ");
								pw.println("*proces "+id+"konczy dzialanie* ");
								IPC.usunSkrzynke(id);  				//metoda usuwajaca skrzynke <powinna zawrzec sie w miejscu gdzy proces sie konczy!!!!!
								ListaProcesow.usunProces(id); //usuniecie procesu, proteza
								dzialanie=false;
							}
							if(s.startsWith("SAB")){
								IPC.wszystkieSkrzynki();  			//metoda wyswietla kolejno utworzone skrzynki dzialajacych procesow wraz z zawartosciami
							}
									
							//DO WYSWIETLANIA KONKRETNEJ SKRZYNKI
							if(s.startsWith("SB")){
								int numer = Integer.parseInt(str.substring(3,4)); 	//pobranie numeru procesu
								IPC.informacje(numer);  							//metoda wyswietla dana skrzynke z zawartoscia,
							}							
						}
					//<jesli nie istnieje> i wyslanie do niej komunikatu		
							
							
					if(str.startsWith("SAB")){
						IPC.wszystkieSkrzynki();  			//metoda wyswietla kolejno utworzone skrzynki dzialajacych procesow wraz z zawartosciami
					}
							
					//DO WYSWIETLANIA KONKRETNEJ SKRZYNKI
					if(str.startsWith("SB")){
						int numer = Integer.parseInt(str.substring(3,4)); 	//pobranie numeru procesu
						IPC.informacje(numer);  							//metoda wyswietla dana skrzynke z zawartoscia,
					}															//jesli skrzynka/proces nie istnieje, wyswietli komunikat:
				}																//"Skrzynka procesu *ID* nie istnieje, wyswietlenie zawartosci 
																					//nie powiodlo sie"
			 					//zamkniecie pliku
					}while(dzialanie!=false);
					fr.close();
					}
			//DO WYJSCIA Z SYSTEMU
			if(str.startsWith("EXIT"))
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
