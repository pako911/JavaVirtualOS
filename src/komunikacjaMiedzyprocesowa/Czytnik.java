package komunikacjaMiedzyprocesowa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Czytnik {
	private int PID;
	private String nazwaPliku;
	private int numerLinii=0; //zeby powrocic do poprzedniej linii, ale nie wiem jak xd
	private FileReader fr;
	private BufferedReader brplik;
	private String s;
	PrintWriter pw = new PrintWriter(System.out, true);
	
	public Czytnik(int PID, String nazwaPliku) throws FileNotFoundException{
		this.PID = PID;
		this.nazwaPliku = nazwaPliku;
		this.fr = new FileReader(nazwaPliku);			//otworzenie pliku do odczytu
		this.brplik = new BufferedReader(fr);  //do czytania
	}
	
	public void Odczytaj() throws IOException{
		if((s = brplik.readLine()) != null) 			//pobieranie po wierszu
		{		
			numerLinii++;
			System.out.println("Wykonanie polecenia procesu "+PID+" : "+s);
		}else
			usunCzytnik();
	}
	
	public boolean Przetworz() throws IOException{
		Odczytaj();
		if(s.startsWith("XR")){  					//test komendy, proces ma czytac komunikat
			pw.println("->KOMENDA XR "); 			//rowniez informacyjnie, jak dalsze zaczynajace sie na "->"
			 //pobranie numeru procesu, z ktorego bedzie czytac ( probowal)
			try {
				if(!IPC.odbierz(PID)) //sprawdza czy wiadomosc zostala wyslana, true jest tak
					return false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}//wlasciwa metoda odpowiedzialna za czytanie, jesli odczyta wiadomosc 
				//ListaProcesow.zablokujProces(id);//blokowanie procesu gdy niue odczytal
		}							//(pierwsza w kolejce) to wyswietli sie 
	
													//"Wiadomosc zostala odebrana: tresc", jesli nie, 
													//poinformuje "Wiadomosc nie zostala odebrana (brak wiadomosci w skrzynce)"
													//!!! DODAM ZE sprawdza czy skrzynka istnieje, 
													//jesli nie to: "Wiadomosc nie zostala odebrana, skrzynka nie istnieje"
													//i tu nalezy proces zawiesic@@@@@@@@@@@@@@@@@@@@@@@@@
		if(s.startsWith("XS")){ 										//komenda do wysylania komunikatu
			pw.println("->KOMENDA XS ");
			int odb = Integer.parseInt(s.substring(3,4));
			Wiadomosc wiadomosc = new Wiadomosc(getPID(),odb,s.substring(5)); 	//skrzynka procesu wysylajacego wiadomosc ma numer procesu,
			try {
				IPC.wyslij(wiadomosc);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 					//	wiadomosc ma ten sam numer
		}						//metoda odpowiedzialna za utworzenie skrzynki 
	
		if(s.startsWith("XD")){  				//zakonczenie procesu <do testow>
			pw.println("->KOMENDA XD ");
			pw.println("*proces "+PID+" konczy dzialanie* ");
			
				try {
					if(IPC.usunSkrzynke(PID)){
						pw.println("Skrzynka procesu "+PID+" zostala usunieta"); //informuje"
						if(ListaProcesow.czyIstniejeProces(PID))				//metoda usuwajaca skrzynke <powinna zawrzec sie w miejscu gdzy proces sie konczy!!!!!
							ListaProcesow.usunProces(PID); //usuniecie procesu, proteza
						return false;
					}
					else{
					//	pw.println("cos.....cos sie popsulo");
						return false;
					}				
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
		}
		return true;
	}
	
	public void usunCzytnik() throws IOException{
		fr.close();
	}

	public int getPID(){
		return PID;
	}
	
	public boolean czyIstnieje(int pid){
		if(getPID()==pid)
			return true;
		else
			return false;
	}
}
