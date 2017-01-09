/*
 * Autor Jakub Rybakowski
 * Stworzony 13:15 18.10.2016
 */

package processManager;

import memory.Memory;

public class ProcessManager {

	private Process mainProcess;
	private Memory memory;
	
	public ProcessManager(Memory memory) {
		this.memory = memory;
		System.out.println("Tworze nowy process główny");
		mainProcess = new Process("main", 0, memory); // główny proces 
	}

	public void newProcess(String file) { // tworzy nowy proces do poprawienia
		mainProcess.createChild(file);
	}
	
	public void shutDown() { // zabija wszystkie procesy do testów do poprawienia
		mainProcess.exit();
	}
	
	public void ps() { // wyświetla wszystkie procesy w systemie
		System.out.println("PID\tPPID\tNAME\tSTATE\tPRIORITY");
		mainProcess.print();
	}
}
