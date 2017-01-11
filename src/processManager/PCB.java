package processManager;

public class PCB {
    public enum Stan{
        AKTYWNY,
        OCZEKUJACY,
    	GOTOWY,
    	NOWY,
    	ZAKONCZONY
    }
	public String name; // nazwa
	public int PID; // identyfikator procesy
	public int base; // początke w paomieci
	public int limit; // długość zajmowanej pamięci
	public short A, B, C, counter;
	//stopped bit
	//blocked bit
	//stop_waiting_bit
	//semaphore_common
	//semaphore_reciver
	
	public PCB() {

	}
}
