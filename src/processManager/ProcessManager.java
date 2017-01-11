/*
 * Autor Jakub Rybakowski
 * Stworzony 13:15 18.10.2016
 */

package processManager;

import java.util.HashMap;

import memory.Memory;
import processManager.PCB.Stany;

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

	public int newProcess(String file) { // tworzy nowy proces do poprawienia
		Process process = mainProcess.createChild(file);
		queue.put(process.pcb.PID, process);
		return process.pcb.PID;
	}

	public void ps() { // wyświetla wszystkie procesy w systemie
		System.out.println("PID\tPPID\tNAME\tSTATE");
		mainProcess.print();
	}
	
	public void setLimit(int PID, int limit) {
		mainProcess.getProces(PID).pcb.limit = limit;
	}
	
	public void setState(int PID, Stany aktywny) {
		if(aktywny.equals(Stany.AKTYWNY)) {
			getProces(PID).pcb.state = Stany.AKTYWNY;
			ready.remove(PID);
		} else if(aktywny.equals(Stany.OCZEKUJACY)) {			
			getProces(PID).pcb.state = Stany.OCZEKUJACY;
			waiting.put(PID, getProces(PID));
		} else if(aktywny.equals(Stany.GOTOWY)) {
			if(getProces(PID).pcb.state.equals(Stany.OCZEKUJACY)) {
				waiting.remove(PID);
			}
			getProces(PID).pcb.state = Stany.GOTOWY;
		} else if(aktywny.equals(Stany.NOWY)) {
			getProces(PID).pcb.state = Stany.NOWY;
		} else if(aktywny.equals(Stany.ZAKONCZONY)) {
			getProces(PID).pcb.state = Stany.ZAKONCZONY;
			queue.remove(PID);
		}
	}
	
	public void kill(int PID) {
		getProces(PID).exit();
		queue.remove(PID);
	}
	
	public HashMap<Integer, Process> getListProces() {
		return queue;
	}
	
	public PCB getProcesPCB(int PID) {
		return mainProcess.getProcesPCB(PID);
	}
	
	public Process getProces(int PID) {
		return mainProcess.getProces(PID);
	}
}
