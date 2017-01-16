/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semaphore;
import processManager.Process;
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
            Process p1= new Process("p1", 0, null);
            Process p2= new Process("p2",0, null);
            Process p3= new Process("p3",0, null);

            Semaphore s = new Semaphore(1, null);
            
            PCB proces1=p1.pcb;
            s.P(proces1);
            PCB proces2=p2.pcb;
            s.P(proces2);
            PCB proces3=p3.pcb;
            s.P(proces3);
            PCB proces11=p1.pcb;
            s.V();
            PCB proces22=p2.pcb;
            s.V();
            PCB proces33=p3.pcb;
            s.V();
        // TODO code application logic here
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    
    }
}
