package komunikacjaMiedzyprocesowa;

import java.util.*;

public class Proces {
	private byte blok=0;
	private byte stop=0;
	private byte czekaj=0;
	private int ID;
	private int liniaKomendy=0;
	
	public Proces(int ID){
		this.ID=ID;
	}
	
	public int getID(){
		return ID;
	}
	
	//do linii komend z pliku
	public int getLinieKomend(){
		return liniaKomendy;
	}
	
	public void iterujLinieKomend(){
		liniaKomendy++;
	}
	
	public void wyzerujLinieKomend(){
		liniaKomendy=0;
	}
	
	public void SetBlock(){
		this.blok=1;
	}
	
	public void ResetBlock(){
		this.blok=0;
	}
	
	public void SetStop(){
		this.stop=1;
	}
	
	public void ResetStop(){
		this.stop=0;
	}
	
	public boolean czyBlock(){
		if(blok==1)
			return true;
		else
			return false;
	}
	
	public boolean czyStop(){
		if(stop==1)
			return true;
		else
			return false;
	}
	
	public void SetCzekaj(){
		this.czekaj=1;
	}
	
	public void ResetCzekaj(){
		this.czekaj=0;
	}
	
	public boolean czyCzeka(){
		if(czekaj==1)
			return true;
		else
			return false;
	}
	
	public void PCB(){
		System.out.println("----------------");
		System.out.println("PCB Procesu:: "+ID);
		System.out.println("Blocked bit: "+blok);
		System.out.println("Stopped Bit: "+stop);
		System.out.println("Waiting: "+czekaj);
		System.out.println("----------------");
	}
}
