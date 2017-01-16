/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;

import java.util.ArrayList;

import processManager.PCB;
import processManager.PCB.Stany;
import processManager.ProcessManager;

/**
 *
 * @author LUCYNA
 */
public class Semaphore 
{
    private ArrayList<PCB> listaOczekujacych;
    
    private int wartosc;
    
    public Semaphore(int wartosc, ArrayList<PCB> listaOczekujacych) throws InvalidSemaphoreValueException
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
    public void P(PCB proces)
    {
         PCB p = listaOczekujacych.get(0);
        System.out.println("Proces "+proces.name+" wykonuje operacje P");
        if(wartosc==1)
        {
            System.out.println("Proces działa dalej");
            wartosc=wartosc-1;
        }
        else
        {
            System.out.println("Proces dołącza do kolejki oczekuących");
            proces.state=Stany.OCZEKUJACY;
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
                PCB p = listaOczekujacych.get(0);
                p.state=Stany.GOTOWY;
                listaOczekujacych.remove(0);
                System.out.println("proces "+p.PID +" został usunięty z listy oczekujących");
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
    
}
