package memoryManagement;

import java.util.ArrayList;
import java.util.Collections;
import processManager.Process;
import processManager.ProcessManager;
import semaphore.InvalidSemaphoreValueException;
import semaphore.Semaphore;

public class Memory {
	private int sizeOfMemory=256;//setting size of memory's table of characters
	public char sign[]=new char[sizeOfMemory];
	private ListFSB FSBPTR;
	private ArrayList <Process> processList;//list of occupied areas
	private Semaphore MEMORY; //memory semaphore
	//private Semaphore FSBSEM; //semaphore of free space blocks
	
	public Memory(ArrayList <Process> listaOczekujacych){
		for(int i=0; i<sizeOfMemory; i++)
			sign[i]= '_';
		FSBPTR = new ListFSB(sizeOfMemory);
		try {
			//FSBSEM=new Semaphore(1);//not needed for now
			MEMORY=new Semaphore(0, listaOczekujacych);
		} catch (InvalidSemaphoreValueException e) {
			System.out.println("Semaphore error");
		}
		processList=new ArrayList <Process>();
	}
	public void wypiszPCB()
	{
		System.out.println("Show PCB");
		for (Process now: processList)
			System.out.println("PCB start in: "+now.pcb.base+""
					+ " | end in: "+(now.pcb.limit+now.pcb.base-1)+""
					+ " | with size of "+ now.pcb.limit );
	}
	private void eraseMemory(int address, int size){
		for(int i=address; i<address+size; i++)
			sign[i]='_';
	}
	private void fillMemory(int address, int size){
		for(int i=0; i<size; i++)
			sign[i+address]='$';
	}
	public void memoryReleasing(Process proces){//as argument give process that you want to remove
		FSBPTR.addFSB(proces.pcb.base, proces.pcb.limit);
		eraseMemory(proces.pcb.base, proces.pcb.limit);
		processList.remove(proces);
		for(int i=0; i<MEMORY.countList(); i++)
			try {
				MEMORY.V();
				
			} catch (InvalidSemaphoreValueException e) {
				e.printStackTrace();
			}
	}
	public boolean memoryAllocation(int size, Process proces){
		FSB tmp=FSBPTR.searchForSpace(size);
		if(tmp.address>=0&&tmp.address<sizeOfMemory){
			proces.pcb.base=tmp.address;
			proces.pcb.limit=size;
			FSBPTR.addFSB(tmp.address+size, tmp.size-size);
			FSBPTR.removeFSB(tmp.size);
			processList.add(proces);
			fillMemory(proces.pcb.base,proces.pcb.limit);
			return true;
		}
		else if(size<FSBPTR.fullSpace()){
				System.out.println("After defragmentation there will be enough"
						+ " space for proces");
			defrag();//use of defragmentation method
			tmp=FSBPTR.searchForSpace(size);
			proces.pcb.base=tmp.address;
			proces.pcb.limit=size;
			tmp.address+=size;
			tmp.size-=size;
			processList.add(proces);
			fillMemory(proces.pcb.base,proces.pcb.limit);
			return true;
		}else{
			System.out.println("Nie znaleziono pamięci. Wchodzę pod semafor");
			MEMORY.P(proces);// only use of P semaphore function
			return false;
		}
	}
	private void defrag(){//memory defragmentation
		int suma=0;
		processListSort();
		Process bufor =new Process(); 
		bufor.pcb.base=0;
		for(Process t : processList){
			for(int i=0; i<t.pcb.limit;i++){
				sign[suma+i]=sign[t.pcb.base+i];
			}
			t.pcb.base=suma;
			suma=suma+t.pcb.limit;
		}
		FSBPTR.head=new FSB(suma,sizeOfMemory-suma );
		eraseMemory(suma,sizeOfMemory-suma);
	}
	//sorting process control blocks in list
	private void processListSort(){
		for(int i=0; i<processList.size(); i++){
			for(int j=0; j<processList.size()-1-i; j++){
				if(processList.get(j).pcb.base>processList.get(j+1).pcb.base)
					Collections.swap(processList, j, j+1);
			}
		}
	}
	public void showMemory(){//write out memory's frames
		for(int i=0; i<sizeOfMemory; i++){
			if((i+1)%4==0)System.out.printf ("%1$7s",i +"|"+ sign[i]+"\t\n");
			else System.out.printf ("%1$7s",i +"|"+ sign[i]+"\t");
			/*System.out.println("\t"+i+"| \t"+sign[i]);
			i++;
			System.out.print("\t"+i+"| \t"+sign[i]);*/
		}
	}
	public static void main(String[] args){
		Memory ho=new Memory(new ArrayList());
		Process r=new Process();
		Process i= new Process();
		ho.memoryAllocation(30, r);
		ho.memoryAllocation(70, i);
		ho.wypiszPCB();
		
		ho.memoryReleasing(r);
		ho.memoryReleasing(i);

		ho.FSBPTR.wypisz();
		ho.wypiszPCB();
		System.out.println("PROCESS TERMINATED");
	}
}
