/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;

import java.util.ArrayList;

/**
 *
 * @author LUCYNA
 */
public class Semaphore 
{
    private ArrayList<Process> listaOczekujacych = new ArrayList<Process>();
    
    private int wartosc;
    
    public Semaphore(int wartosc) throws InvalidSemaphoreValueException
    {
        this.wartosc=wartosc;
        if(wartosc<0||wartosc>1)
        {
            throw new InvalidSemaphoreValueException();
        }
    }
    
    public void P()
    {
        System.out.println("Proces "+Process.aktualny.nazwa+" wykonuje operacje P");
        if(wartosc==1)
        {
            System.out.println("Proces działa dalej");
            wartosc=wartosc-1;
        }
        else
        {
            System.out.println("Proces dołącza do kolejki oczekuących");
            listaOczekujacych.add(Process.aktualny);
            System.out.println("Następuje przełączenie procesów");
        }
        System.out.println("koniec operacji P");
    }
    
    public void V() throws InvalidSemaphoreValueException
    {
       System.out.println("Proces "+Process.aktualny.nazwa+" wykonuje operacje V");
        if(wartosc==0)
        { 
            if(listaOczekujacych.size()>0){
                Process p = listaOczekujacych.get(0);
                listaOczekujacych.remove(0);
                System.out.println("proces "+p.nazwa +" został usunięty z listy oczekujących");
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
