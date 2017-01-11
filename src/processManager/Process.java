/*
 * Autor Jakub Rybakowski
 * Stworzony 13:26 18.10.2016
 */

package processManager;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;

import memory.Memory;

public class Process {

	private static int CPID = 0;
	private int PID; // identyfikator procesy
	private String name; // nazwa
	private int PPID; // proces nadrzędny
	private String state; // stan procesu NEW READY RUNNING WAITING TERMINATED
	private short A, B, C, counter;
	public int base; // początke w paomieci
	public int limit; // długość zajmowanej pamięci
	public PCB pcb;
	
	private Memory memory;
	
	private HashMap<Integer, Process> children = new HashMap<Integer, Process>();
	
	public Process(String name, int PPID, Memory memory) {
		this.memory = memory;
		this.name = name;
		this.PPID = PPID;
		this.PID = CPID++;
		this.state = "READY";
	}
	
	public Process createChild(String sfile) {
		Process process = new Process(name, PID, memory);
		process.state = "NEW";
		File file = new File(sfile);
		System.out.println(file.length());
		limit = base + (int) file.length();
		base = memory.memoryAllocation((int) file.length(), pcb);
		
		process.state = "READY";
		
		children.put(CPID, process);
		return process;
	}
	
	public void print() {
		System.out.println(PID+"\t"+PPID+"\t"+name+"\t"+state+"\t");
		for (Entry<Integer, Process> entry : children.entrySet()) {
			entry.getValue().print();
		}
	}
	
	public void exit() {
		state = "TERMINATED";
		for (Entry<Integer, Process> entry : children.entrySet()) {
			entry.getValue().exit();
		}
	}
	
	public void waitpid() {
		
	}
	
	public int getPID() {
		return PID;
	}
	
	public int getPPID() {
		return PPID;
	}
		
	public String getState() {
		return state;
	}
}
 