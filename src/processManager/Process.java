/*
 * Autor Jakub Rybakowski
 * Stworzony 13:26 18.10.2016
 */

package processManager;

import java.util.HashMap;
import java.util.Map.Entry;

public class Process {

	private static int CPID = 0;
	private int PID; // identyfikator procesy
	private String name; // nazwa
	private int PPID; // proces nadrzędny
	private String state; // stan procesu NEW READY RUNNING WAITING TERMINATED
	private byte[] program; // kod programu 
	private short A, B, C, counter;
	
	private HashMap<Integer, Process> children = new HashMap<Integer, Process>();
	
	public Process(String name, int PPID) {
		this.name = name;
		this.PPID = PPID;
		this.PID = CPID++;
		this.state = "NEW";
	}
	
	public void createChild(String name) {
		Process process = new Process(name, PID);
		children.put(CPID, process);
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
 