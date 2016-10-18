/*
 * Autor Jakub Rybakowski
 * Stworzony 13:15 18.10.2016
 */

package processManager;

public class ProcessManager {

	private Process mainProcess;
	
	public ProcessManager() {
		mainProcess = new Process("main", 0, 1); // g��wny proces 
	}

	public void newProcess(String name, int priority) { // tworzy nowy proces do poprawienia
		mainProcess.addProcess(name, priority);
	}
	
	public void shutDown() { // zabija wszystkie procesy do test�w do poprawienia
		mainProcess.kill();
	}
	
	public void ps() { // wy�wietla wszystkie procesy w systemie
		System.out.println("PID\tPPID\tNAME\tSTATE\tPRIORITY");
		mainProcess.print();
	}
}
