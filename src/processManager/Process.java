/*
 * Autor Jakub Rybakowski
 * Stworzony 13:26 18.10.2016
 */

package processManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import memory.Memory;
import processManager.PCB.Stany;

public class Process {

	private static int CPID = 0;
	public PCB pcb;
	
	private Memory memory;
	
	private HashMap<Integer, Process> children = new HashMap<Integer, Process>();
	
	public Process(String name, int PPID, Memory memory) {
		pcb = new PCB();
		this.memory = memory;
		this.pcb.name = name;
		this.pcb.PPID = PPID;
		this.pcb.PID = CPID++;
		this.pcb.state = Stany.NOWY;
	}
	
	public Process createChild(String sfile) {
		Process process = new Process(pcb.name, pcb.PID, memory);

		File file = new File(sfile);
		pcb.limit = pcb.base + (int) file.length();
		pcb.base = memory.memoryAllocation((int) file.length(), pcb);
		if(pcb.base == -1) { 
			process.pcb.state = Stany.OCZEKUJACY;
		} else {
			process.pcb.state = Stany.GOTOWY;
		}
		getChildren().put(CPID, process);
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
 