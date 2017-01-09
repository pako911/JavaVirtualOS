package komunikacjaMiedzyprocesowa;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		Wiadomosc wiad = new Wiadomosc(1,"blabla");
		wiad.wyswietlInfo();
		Skrzynka skrzynka = new Skrzynka(1);
		skrzynka.dodajWiadomosc(new Wiadomosc(1,"blablaaaa"));
		skrzynka.dodajWiadomosc(new Wiadomosc(1,"blablaaaaaa"));
		skrzynka.wyswietlStanSkrzynki();
		skrzynka.pobierzWiadomosc();
		skrzynka.wyswietlStanSkrzynki();
		skrzynka.pobierzWiadomosc();
		skrzynka.wyswietlStanSkrzynki();

		Wiadomosc wiado = new Wiadomosc(2,"wiadomosc1dla2");
		Wiadomosc wiadom = new Wiadomosc(2,"wiadomosc2dla2");
		Wiadomosc wiadomo = new Wiadomosc(1,"wiadomosc1dla1");
		Wiadomosc wiadomos = new Wiadomosc(1,"wiadomosc2dla1");
		IPC.wyslij(wiado,2);
		IPC.informacje(2);
		IPC.odbierz(2);
		IPC.informacje(2);
		System.out.println("**************************");
		System.out.println("**************************");
		System.out.println("**************************");
		IPC.wyslij(wiadom,2);
		IPC.wyslij(wiado,2);
		IPC.informacje(2);
		IPC.odbierz(2);
		IPC.informacje(2);
		IPC.wyslij(wiadomo,1);
		IPC.wyslij(wiadomos,1);
		IPC.informacje(1);
		IPC.odbierz(1);
		IPC.informacje(1);
		IPC.odbierz(1);
		IPC.informacje(1);
		IPC.odbierz(1);
		IPC.informacje(1);
		IPC.usunSkrzynke(1);
		IPC.informacje(1);
		IPC.informacje(3);
		IPC.wyslij(wiadom,12);
		IPC.wyslij(wiadom,4);
		IPC.wyslij(wiadom,7);
		IPC.wyslij(wiadom,9);
		IPC.wszystkieSkrzynki();
		IPC.odbierz(9);
		IPC.informacje(9);
		IPC.odbierz(9);
		IPC.informacje(9);
		IPC.usunSkrzynke(9);
		IPC.informacje(9);
		IPC.odbierz(9);
		IPC.informacje(9);
	}
}
