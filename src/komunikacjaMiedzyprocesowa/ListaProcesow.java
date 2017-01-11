package komunikacjaMiedzyprocesowa;

import java.util.LinkedList;
import java.util.List;

public class ListaProcesow {
	public static final List<Proces> Procesy = new LinkedList<>(); 
	
	public static void dodajProces(int ID){
		boolean istnieje=false;
		for(Proces x : Procesy){
			if(x.getID()==ID){
				System.out.println("BLAD! Proces o tym numerze juz istnieje!");
				istnieje=true;
				return;
			}
		}
		if(istnieje)
			return;
		else{
			Proces proces = new Proces(ID);
			Procesy.add(proces);
		}
	}
	
	public static boolean czyIstniejeProces(int id){
		for(Proces x : Procesy){
			if(x.getID()==id)
				return true;
		}
		return false;
	}
	
	public static void wyswietlWszystkieProcesy(){
		boolean yes=false;
		System.out.println();
		System.out.print("DZIALACE PROCESY: ");
		for(Proces x : Procesy){
			System.out.print(x.getID()+", ");
			yes=true;
		}
		if(!yes)
			System.out.print("brak ");
		System.out.println();
	}
	
	public static void wyswietlPCBProcesu(int ID){
		for(Proces x : Procesy){
			if(x.getID()==ID)
				x.PCB();
		}
	}
	
	public static void iterujLinie(int ID){
		for(Proces x: Procesy){
			if(x.getID() ==ID)
				x.iterujLinieKomend();
		}
	}
	
	public static void zerujLinie(int ID){
		for(Proces x: Procesy){
			if(x.getID() ==ID)
				x.wyzerujLinieKomend();
		}
	}
	
	public int pobierzLinie(int ID){
		for(Proces x: Procesy){
			if(x.getID() ==ID)
				return x.getLinieKomend();
		}
		return 0;
	}
	
	public static void usunProces(int ID){
		for(Proces x : Procesy){
			if(x.getID()==ID){
				System.out.println("Proces "+ID+" zostal usuniety");
				Procesy.remove(x);
			}
		}
	}
	
	public static void zablokujProces(int ID){
		for(Proces x : Procesy){
			if(x.getID()==ID){
				System.out.println("Proces "+ID+" zostal zablokowany");
				x.SetBlock();
			}
		}
	}
	
	public static void odblokujProces(int ID){
		for(Proces x : Procesy){
			if(x.getID()==ID){
				System.out.println("Proces "+ID+" zostal zablokowany");
				x.ResetBlock();
			}
		}
	}
	
	public static void zatrzymajProces(int ID){
		for(Proces x : Procesy){
			if(x.getID()==ID){
				System.out.println("Proces "+ID+" zostal zablokowany");
				x.SetCzekaj();
			}
		}
	}
	
	public static void uwolnijProces(int ID){
		for(Proces x : Procesy){
			if(x.getID()==ID){
				System.out.println("Proces "+ID+" zostal zablokowany");
				x.ResetCzekaj();
			}
		}
	}

}
