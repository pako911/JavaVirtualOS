/*
 * Autor Jakub Rybakowski
 * Stworzony 13:26 18.10.2016
 */

package processManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

import com.sun.org.apache.regexp.internal.recompile;

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
		if(CPID==0)
			pcb.name="main";
		else
			pcb.name = "proces "+CPID ;
		pcb.PPID = PPID;
		pcb.PID = CPID++;
		pcb.state = Stany.NOWY;
		System.out.println("STWORZONO NOWY PROCES O PID "+pcb.PID);
	}
	
	public Process createChild(String sfile) {
		try {
			FileInputStream fileInputStream = new FileInputStream(sfile);
			Process process = new Process(pcb.name, pcb.PID, memory);
			File file = new File(sfile);
			String kod = "";
			for(int i = 0; i <file.length(); i++) {
				char znak = (char)fileInputStream.read();
				kod = kod + znak;
			}
			kod = kod.replaceAll("\r", "").replaceAll("\n", ";");
			boolean memoryGood = memory.memoryAllocation((int) kod.length(), process.pcb);
			if(memoryGood) { 			
				System.out.println("ZAALOKOWANO PAMIĘĆ OD "+process.pcb.base+" O ROZMIARZE "+ process.pcb.limit+ " PID "+process.pcb.PID);
				for(int i = 0; i<process.pcb.limit; i++) {
					memory.sign[i+process.pcb.base] = kod.charAt(i);
				}
				process.pcb.state = Stany.GOTOWY;
			} else {
				process.pcb.state = Stany.OCZEKUJACY;
			}
			getChildren().put(process.pcb.PID, process);
			fileInputStream.close();
			return process;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
	
	public String[] getNextRozkaz() {
		String kod = "";
		for(int i = 0; i<pcb.limit; i++) {
			kod = kod + memory.sign[i+pcb.base];
		}
		int a = kod.split(";").length;
		if(a-1 == pcb.counter) { pcb.state = Stany.ZAKONCZONY; }
		kod = kod.split(";")[pcb.counter];
		String rozkaz[] = new String[3];
		rozkaz = kod.split(" ");
		return rozkaz;
	}
}
 