/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;

/**
 *
 * @author LUCYNA
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        try{
            Process p1= new Process("p1");
            Process p2= new Process("p2");
            Process p3= new Process("p3");

            Semaphore s = new Semaphore(1);
            
            Process.aktualny=p1;
            s.P();
            Process.aktualny=p2;
            s.P();
            Process.aktualny=p3;
            s.P();
            Process.aktualny=p1;
            s.V();
            Process.aktualny=p2;
            s.V();
            Process.aktualny=p3;
            s.V();
        // TODO code application logic here
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
    }
}
