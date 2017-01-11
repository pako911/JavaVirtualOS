package komunikacjaMiedzyprocesowa;

import java.io.*;
//PROTEZY - Proces.java, ListaProcesow.java i Czytnik.java
public class Main {
	//WAZNE
	//WYMAGANE UTWORZENIE 4 PROCESOW
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
	//XC program1
	//XC program2
	//XC program3
	//XC program4
	//cd,cd(...) w miedzyczasie jak chce sprawdzic stan skrzynek to SAB lub SB nr_procesu
	//gdy dostane informacje o zakonczonym procesie : EXIT
	//ale po zakonczeniu wszystkich procesow tablica tych czytnikow - interpreterow do kazdego z plikow nie sa usuwane
	//zrobione tylko do sprawdzenia i ogolnego pogladu
	//tyle
	
	/*
	 co do wykorzystania:
	 sytuacja taka: sa utworzone dwa procesy, 1,2,3,4
	 Przyklad 1:
	 jesli proces 1 chce wyslac do 2 rozkaz XS 2 wiadomosc(IPC.wyslij(wiadomosc,PIDodbiorcy), 
	 jak odebrac to XR(IPC.odbierz(proces_obecny)
	 jesli bedzie chcial wyslac do innego, np 4, to przed wykonaniem XS czy proces istnieje <czy jego numer istnieje> forem czy cos
	 dodatkowo, przy wyslaniu do procesu ktorego nie ma po tym ma sie zawiesic i zmienic numer procesu
	 gdy proces chce czytac ze skrzynki a w niej nic nie bedzie lub skrzynka nie istnieje if(!IPC.odbierz(id))
	 usuniecie skrzynki nastepuje razem z usunieciem procesu
	 //procesy dzialaja po kolei: 1,2,3,4,1,2,3,4 do konca
	 */
	static int id=0;
	static int i=0;
	static int j=-1;
	static Czytnik[] cz = new Czytnik[20];
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
			String nazwaPliku;			
			String sciezka;	
			str = br.readLine(); 			//wpisanie komendy
			
				if(str.startsWith("XC")){ 		//komenda do tworzenia procesu
					pw.println("->KOMENDA XC");  	//tak o zebym wiedzial ze zadzialalo to akurat xd zbedne
					id++;
					j++;
					//ListaProcesow.wyswietlWszystkieProcesy(); testowne odpowiedzi
					//ListaProcesow.wyswietlPCBProcesu(id);
					ListaProcesow.dodajProces(id);
					//ListaProcesow.wyswietlPCBProcesu(id);
					nazwaPliku = str.substring(3); 							//podzielenie wiersza z komenda aby wydobyc nazwe pliku wejsciowego
					sciezka = "c:\\JavaSO\\"+nazwaPliku+".txt"; 	//utworzenie sciezki do pliku aby go otworzyc
					cz[j] = new Czytnik(id,sciezka);
					}
				if(str.startsWith("cd")){
					if(!cz[i].Przetworz()) //ZMIANA PROCESU
						i++; 
					if(i>j)
						i=0;
					}
				
				if(str.startsWith("SAB")){
					try {
						IPC.wszystkieSkrzynki(); //metoda wyswietla kolejno utworzone skrzynki dzialajacych procesow wraz z zawartosciami
					} catch (InterruptedException e) {
						e.printStackTrace();
					}  			
				}
							
				//DO WYSWIETLANIA KONKRETNEJ SKRZYNKI
				if(str.startsWith("SB")){
					int numer = Integer.parseInt(str.substring(3,4)); 	//pobranie numeru procesu
					try {
						IPC.informacje(numer);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}  							//metoda wyswietla dana skrzynke z zawartoscia,
				}								//jesli skrzynka/proces nie istnieje, wyswietli komunikat:
												//"Skrzynka procesu *ID* nie istnieje, wyswietlenie zawartosci 
												//nie powiodlo sie"	
				if(str.startsWith("SAP"))
				ListaProcesow.wyswietlWszystkieProcesy();
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
