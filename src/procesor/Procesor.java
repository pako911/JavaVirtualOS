package procesor;

import java.util.ArrayList;
import java.util.Random;

import processManager.ProcessManager;

public class Procesor {

    private ArrayList<Proces> lista_procesow_gotowych=new ArrayList<Proces> ();
    private ArrayList<Proces> lista_procesow_oczekujacych=new ArrayList<Proces> ();
    
    public Proces Running;
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
        if(czy_wywlaszczajacy)
        {
        	if(Running!=null && Running.ilosc_instrukcji_do_konca_fazy>file.thau)
        	{
				int PID = processManager.newProcess(file);
        		System.out.println("Dodano nowy proces o PID: "+Running.get_PID());
        		Running.stan=Proces.Stan.GOTOWY;
        		Running.thau=(int)alpha*theta+(1-alpha)*t;
                t=(int)Running.thau;
        		Running=file;
        		Running.stan=Proces.Stan.AKTYWNY;
        		
        	        		
        	}
        	else
        	{
				processManager.newProcess(file);
        	}
        		
        }
        else
        {
			processManager.newProcess(file);
        }
    	      
    }

    public void przelacz_proces()
    {
        System.out.println("Przelaczenie procesu");
        System.out.println("====================");
        System.out.println();
        if(Running!=null)
        {
        	System.out.println("Proces o PID : "+Running.get_PID()+ " przechodzi w stan oczekiwania.");
            Running.stan=Proces.Stan.OCZEKUJACY;
            lista_procesow_oczekujacych.add(Running);
            
            Running.thau=(int)alpha*theta+(1-alpha)*t;
            t=(int)Running.thau;
        }
        if(lista_procesow_gotowych.size()>0)
        {
	        Proces nastepny=lista_procesow_gotowych.get(0);
	        int index_nastepnego=0;
	   	    for(int i=1;i<lista_procesow_gotowych.size();i++)
	        {
	   	    	Proces p=lista_procesow_gotowych.get(i);
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
	        System.out.println("Proces o PID : "+nastepny.get_PID()+" przechodzi w stan aktywnosci");
            nastepny.stan=Proces.Stan.AKTYWNY;
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
        		System.out.println("Proces o PID: "+Running.get_PID()+ " zostal wykonany, time : "+theta);
        	}
        	
        	while(probability>15)
        	{
        		probability=r.nextInt(100)+1;
        		
	        	if(probability<=15)
	        	{
	        		przelacz_proces();
	        		System.out.println(probability);
	        		System.out.println("Proces o PID: "+Running.get_PID()+ " zostal wykonany, time: "+theta);
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
    	for (Proces proces : lista_procesow_gotowych) {
    		System.out.println("-------------------------");
    		System.out.println("PID "+proces.get_PID());
    		System.out.println("Theta "+theta);
    		System.out.println("-------------------------");
    		}
    }
    
    public void wyswietl_liste_procesow_oczekujacych()
    {
    	System.out.println("Lista procesow oczekujacych");
    	for (Proces proces : lista_procesow_gotowych) {
    		System.out.println("-------------------------");
    		System.out.println("PID "+proces.get_PID());
    		System.out.println("Theta "+theta);
    		System.out.println("-------------------------");
    		}
    }
    

}