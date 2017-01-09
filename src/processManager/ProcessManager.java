/*
 * Autor Jakub Rybakowski
 * Stworzony 13:15 18.10.2016
 */

package processManager;

public class ProcessManager {

	private Process mainProcess;
	
	public ProcessManager() {
		mainProcess = new Process("main", 0); // główny proces 
	}

	public void newProcess(String name) { // tworzy nowy proces do poprawienia
		mainProcess.createChild(name);
	}
	
	public void shutDown() { // zabija wszystkie procesy do testów do poprawienia
		mainProcess.exit();
	}
	
	public void ps() { // wyświetla wszystkie procesy w systemie
		System.out.println("PID\tPPID\tNAME\tSTATE\tPRIORITY");
		mainProcess.print();
	}
}
