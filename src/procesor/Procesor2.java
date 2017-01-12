package procesor;

import java.util.ArrayList;
import java.util.Random;

import processManager.PCB;
import processManager.ProcessManager;
import processManager.PCB.Stany;

public class Procesor {

    private ArrayList<PCB> lista_procesow_gotowych=new ArrayList<PCB> ();
    private ArrayList<PCB> lista_procesow_oczekujacych=new ArrayList<PCB> ();
    
    public PCB Running;
    public boolean czy_wywlaszczajacy;//wybor metody przydzialu procesora sjf/srt(wywlaszczaj�ca), ustawiane po dodaniu nowego procesu
    public double alpha;
    public double theta;
    public int t;
	private ProcessManager processManager;
    
	public Procesor(ProcessManager processManager)
	{
		this.processManager = processManager;
		alpha=0.5;
		czy_wywlaszczajacy=false;
		Running=null;
		theta=10;
		t=0;
	}
    
    public void dodaj_proces(String file)
    {
    	int PID = processManager.newProcess(file);
    	
        if(czy_wywlaszczajacy)
        {
        	if(Running!=null && Running.ilosc_instrukcji_do_konca_fazy>processManager.getProces(PID).pcb.thau)
        	{
				
        		System.out.println("Dodano nowy proces o PID: "+Running.PID);
        		Running.state=Stany.GOTOWY;
        		Running.thau=(int)alpha*theta+(1-alpha)*t;
                t=(int)Running.thau;
        		Running=processManager.getProces(PID).pcb;
        		Running.state=Stany.AKTYWNY;	// do poprawy
        	}
        }
    }

    public void przelacz_proces()
    {
        System.out.println("Przelaczenie procesu");
        System.out.println("====================");
        System.out.println();
        if(Running!=null)
        {
        	System.out.println("Proces o PID : "+Running.PID+ " przechodzi w stan oczekiwania.");
            Running.state = Stany.OCZEKUJACY;
            lista_procesow_oczekujacych.add(Running);
            
            Running.thau=(int)alpha*theta+(1-alpha)*t;
            t=(int)Running.thau;
        }
        if(lista_procesow_gotowych.size()>0)
        {
        	PCB nastepny=lista_procesow_gotowych.get(0);
	        int index_nastepnego=0;
	   	    for(int i=1;i<lista_procesow_gotowych.size();i++)
	        {
	   	    	PCB p=lista_procesow_gotowych.get(i);
	        	if(i==1)
	        	{
	        		p.thau=Running.thau;
	        		
	        		
	        	}
	        	p.thau=(int)alpha*theta+(1-alpha)*t;
	            t=(int)Running.thau;

	        	if(p.thau<nastepny.thau)
	            {
	            	nastepny=p;
	                index_nastepnego=i;
	            }
	            
	            
	             
	        }
	        System.out.println("Proces o PID : "+nastepny.PID+" przechodzi w stan aktywnosci");
            nastepny.state=Stany.AKTYWNY;
            lista_procesow_gotowych.remove(index_nastepnego);
            Running=nastepny;
            
        }

        else
        {
            System.out.println("Nie ma zadnego procesu na liscie.");
            Running=null;
        }	
    }

    public void wykonaj()
    {
        if(Running!=null)
        {
        	Random r=new Random();
        	int probability;
        	probability=r.nextInt(100)+1;
        	
        	if(probability<=15)
        	{
        		przelacz_proces();
        		System.out.println("Proces o PID: "+Running.PID+ " zostal wykonany, time : "+theta);
        	}
        	
        	while(probability>15)
        	{
        		probability=r.nextInt(100)+1;
        		
	        	if(probability<=15)
	        	{
	        		przelacz_proces();
	        		System.out.println(probability);
	        		System.out.println("Proces o PID: "+Running.PID+ " zostal wykonany, time: "+theta);
	        	}
	        	else
	        	{
	        		theta++;
	        	}
        	}
        }
    }
    public void wyswietl_liste_procesow_gotowych()
    {
    	System.out.println("Lista procesow gotowych");
    	for (PCB proces : lista_procesow_gotowych) {
    		System.out.println("-------------------------");
    		System.out.println("PID "+proces.PID);
    		System.out.println("Theta "+theta);
    		System.out.println("-------------------------");
    		}
    }
    
    public void wyswietl_liste_procesow_oczekujacych()
    {
    	System.out.println("Lista procesow oczekujacych");
    	for (PCB proces : lista_procesow_gotowych) {
    		System.out.println("-------------------------");
    		System.out.println("PID "+proces.PID);
    		System.out.println("Theta "+theta);
    		System.out.println("-------------------------");
    		}
    }
    

}