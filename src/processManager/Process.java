/*
 * Autor Jakub Rybakowski
 * Stworzony 13:26 18.10.2016
 */

package processManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import memoryManagement.Memory;
import processManager.PCB.Stany;

public class Process {

	private static int CPID = 0;
	public PCB pcb;
	
	private Memory memory;
	
	private HashMap<Integer, Process> children = new HashMap<Integer, Process>();
	
	public Process(String name, int PPID, Memory memory) {
		pcb = new PCB();
		this.memory = memory;
		pcb.name = name;
		pcb.PPID = PPID;
		pcb.PID = CPID++;
		pcb.state = Stany.NOWY;
		System.out.println("NOWY o ID "+pcb.PID);
	}
	
	public Process createChild(String sfile) {
		Process process = new Process(pcb.name, pcb.PID, memory);

		File file = new File(sfile);
		boolean memoryGood = memory.memoryAllocation((int) file.length(), process.pcb);
		if(memoryGood) { 
			process.pcb.state = Stany.GOTOWY;
		} else {
			process.pcb.state = Stany.OCZEKUJACY;
		}
		getChildren().put(process.pcb.PID, process);
		return process;
	}
	
	public void print() {
		System.out.println(pcb.PID+"\t"+pcb.PPID+"\t"+pcb.name+"\t"+pcb.state+"\t");
		for (Entry<Integer, Process> entry : getChildren().entrySet()) {
			entry.getValue().print();
		}
	}
	
	public void exit() {
		pcb.state = Stany.ZAKONCZONY;
		memory.memoryReleasing(pcb);
	}
		
	public int getPID() {
		return pcb.PID;
	}
	
	public int getPPID() {
		return pcb.PPID;
	}
		
	public Stany getState() {
		return pcb.state;
	}

	public HashMap<Integer, Process> getMap() {
		return getChildren();
	}

	public PCB getProcesPCB(int sPID) {
		for(Entry<Integer, Process> entry : getChildren().entrySet()) {
			if(entry.getValue().pcb.PID == sPID) {
				return entry.getValue().pcb;
			}
		}
		return null;
	}

	public Process getProces(int sPID) {
		for(Entry<Integer, Process> entry : getChildren().entrySet()) {
			if(entry.getValue().pcb.PID == sPID) {
				return entry.getValue();
			}
		}
		return null;
	}

	public HashMap<Integer, Process> getChildren() {
		return children;
	}

	public void setChildren(HashMap<Integer, Process> children) {
		this.children = children;
	}
}
 