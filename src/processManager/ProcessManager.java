/*
 * Autor Jakub Rybakowski
 * Stworzony 13:15 18.10.2016
 */

package processManager;

import java.util.HashMap;

import memory.Memory;

public class ProcessManager {

	private Process mainProcess;
	private Memory memory;
	
	private HashMap<Integer, Process> queue = new HashMap<Integer, Process>(); //wszyytkie
	private HashMap<Integer, Process> ready = new HashMap<Integer, Process>(); //gotowe
	private HashMap<Integer, Process> waiting = new HashMap<Integer, Process>(); //oczekujące
	
	public ProcessManager(Memory memory) {
		this.memory = memory;
		System.out.println("Tworze nowy process główny");
		mainProcess = new Process("main", 0, memory); // główny proces 
	}

	public void newProcess(String file) { // tworzy nowy proces do poprawienia
		Process process = mainProcess.createChild(file);
		queue.put(process.pcb.PID, process);
	}
	
	public void shutDown() { // zabija wszystkie procesy do testów do poprawienia
		mainProcess.exit();
	}
	
	public void ps() { // wyświetla wszystkie procesy w systemie
		System.out.println("PID\tPPID\tNAME\tSTATE\tPRIORITY");
		mainProcess.print();
	}
	
	public void setLimit(int PID, int limit) {
		
	}
	
	public void getListProces() {
		// 1 2 3 4
	}
	
	public void getProcesPCB(int PID) {
		
	}
}
