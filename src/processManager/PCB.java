package processManager;

public class PCB {
	
    public enum Stany{
        AKTYWNY,
        OCZEKUJACY,
    	GOTOWY,
    	NOWY,
    	ZAKONCZONY
    }
    
    public Stany state = Stany.NOWY;
	public String name; // nazwa
	public int PID; // identyfikator procesy
	public int base; // początke w paomieci
	public int limit; // długość zajmowanej pamięci
	public int A, B, C, counter;
	public int PPID; // proces nadrzędny
    public double thau;
    public double ilosc_instrukcji_do_konca_fazy;
    public int timer;
	//stopped bit
	//blocked bit
	//stop_waiting_bit
	//semaphore_common
	//semaphore_reciver
	
	public PCB() {

	}
}
