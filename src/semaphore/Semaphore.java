/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;

import java.util.ArrayList;

import processManager.PCB;
import processManager.PCB.Stany;
import processManager.Process;
/**
 *
 * @author LUCYNA
 */
public class Semaphore /*siedziałam na skype od 23, chyba mam coś z mikrofonem, nie wstawiałam wcześniej nic bo i tak mi gówno działało
serio ciągle siedzę i staram się to jakoś ogarnąć, chciałam załatwić korki, ale nikt nie miał wolnych terminów
tak więc nie mam tego w dupie po prostu jestem głupia
i nie chciałabym żebyście przeze mnie zawalili projekt, staram się jak mogę ;/
*/
{
   
    public ArrayList<Process> listaOczekujacych = new ArrayList<Process>();
    public ArrayList<Process> listaGotowych = new ArrayList<Process>();
    
    private int wartosc;
	private PCB proces;
    
    public Semaphore(int wartosc, ArrayList<Process> listaOczekujacych) throws InvalidSemaphoreValueException
    {
    	this.listaOczekujacych=listaOczekujacych;
        this.wartosc=wartosc;
        if(wartosc<0||wartosc>1)
        {
            throw new InvalidSemaphoreValueException();
        }
    }
    public int countList()//przepraszam że bez pytania pozwoliłem sobie dopisać tą funkcję :(
    {
    	return listaOczekujacych.size();
    }
    public void P(Process proces)
    {
    	 
        System.out.println("Proces "+proces.pcb.name+" wykonuje operacje P");
        if(wartosc==1)
        {
            System.out.println("Proces działa dalej");
            wartosc=wartosc-1;
        }
        else
        {
            System.out.println("Proces dołącza do kolejki oczekujących");
            proces.pcb.state=Stany.OCZEKUJACY;
            listaOczekujacych.add(proces);
            System.out.println("Następuje przełączenie procesów");
        }
        System.out.println("koniec operacji P");
    }
    
    public void V() throws InvalidSemaphoreValueException
    {
       System.out.println("Proces wykonuje operacje V");
        if(wartosc==0)
        { 
            if(listaOczekujacych.size()>0){
                Process p = listaOczekujacych.get(0);
                p.pcb.state=Stany.GOTOWY;
                p.allocMem(p, p.nazwaPliku);
                listaOczekujacych.remove(0);
                System.out.println("proces "+p.pcb.PID +" został usunięty z listy oczekujących");
            }
            else
            {
                System.out.println("brak procesów oczekujących");
                wartosc=1;
             
            }
        }
        else
            throw new InvalidSemaphoreValueException();
        System.out.println("koniec operacji V");
    }
	public ArrayList<Process> getListaGotowych() {
		return listaGotowych;
	}
	public void setListaGotowych(ArrayList<Process> listaGotowych) {
		this.listaGotowych = listaGotowych;
	}
	public PCB getProces() {
		return proces;
	}
	public void setProces(PCB proces) {
		this.proces = proces;
	}
    
}
