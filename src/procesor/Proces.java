package procesor;

//import java.util.Random;

public class Proces {
    
    private static int free_pid=0;
    private int PID;
    public double thau;
    public double ilosc_instrukcji_do_konca_fazy=0;
    public int time;
    
    public enum Stan{
        AKTYWNY,
        OCZEKUJACY,
    	GOTOWY,
    	NOWY
        
    }
    
    public Stan stan=Stan.NOWY;
    
    public int get_PID()
    {
        return PID;
    }
    

    
    public Proces()
    {
        thau=10;
        free_pid++;
        PID=free_pid;
        
       
    }
    
   
   }