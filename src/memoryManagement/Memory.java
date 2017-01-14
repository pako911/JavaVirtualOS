package memoryManagement;

import java.util.ArrayList;
import java.util.Collections;
import processManager.PCB;
import semaphore.InvalidSemaphoreValueException;
import semaphore.Semaphore;

public class Memory {
	private int sizeOfMemory=128;//setting size of memory's table of characters
	public char sign[]=new char[sizeOfMemory];
	private ListFSB FSBPTR;
	private ArrayList <PCB> processList;//list of occupied areas
	private Semaphore MEMORY; //memory semaphore
	//private Semaphore FSBSEM; //semaphore of free space blocks
	
	public Memory(){
		for(int i=0; i<sizeOfMemory; i++)
			sign[i]= '_';
		FSBPTR = new ListFSB(sizeOfMemory);
		try {
			//FSBSEM=new Semaphore(1);//not needed for now
			MEMORY=new Semaphore(0);
		} catch (InvalidSemaphoreValueException e) {
			System.out.println("Semaphore error");
		}
		processList=new ArrayList <PCB>();
	}
	public void wypiszPCB()
	{
		for (PCB now: processList)
			System.out.println("PCB start in: "+now.base+""
					+ " | end in: "+(now.limit+now.base-1)+""
					+ " | with size of "+ now.limit );
	}
	private void eraseMemory(int address, int size){
		for(int i=address; i<address+size; i++)
			sign[i]='_';
	}
	private void fillMemory(int address, int size){
		for(int i=0; i<size; i++)
			sign[i+address]='$';
	}
	public void memoryReleasing(PCB proces){//as argument give process that you want to remove
		FSBPTR.addFSB(proces.base, proces.limit);
		eraseMemory(proces.base, proces.limit);
		processList.remove(proces);
		for(int i=0; i<MEMORY.countList(); i++)
			try {
				MEMORY.V();
			} catch (InvalidSemaphoreValueException e) {
				e.printStackTrace();
			}
	}
	public boolean memoryAllocation(int size, PCB proces){
		FSB tmp=FSBPTR.searchForSpace(size);
		if(tmp.address>=0&&tmp.address<sizeOfMemory){
			proces.base=tmp.address;
			proces.limit=size;
			FSBPTR.addFSB(tmp.address+size, tmp.size-size);
			FSBPTR.removeFSB(tmp.size);
			processList.add(proces);
			fillMemory(proces.base,proces.limit);
			return true;
		}
		else if(size<FSBPTR.fullSpace()){
				System.out.println("After defragmentation there will be enough"
						+ " space for proces");
			defrag();//use of defragmentation method
			tmp=FSBPTR.searchForSpace(size);
			proces.base=tmp.address;
			FSBPTR.addFSB(tmp.address+size, tmp.size-size);
			processList.add(proces);
			fillMemory(proces.base,proces.limit);
			return true;
		}else{
			System.out.println("Nie znaleziono pamięci. Wchodzę pod semafor");
			MEMORY.P();// only use of P semaphore function
			return false;
		}
	}
	private void defrag(){//memory defragmentation
		int suma=0;
		processListSort();
		PCB bufor =new PCB(); 
		bufor.base=0;
		for(PCB t : processList){
			for(int i=0; i<t.limit;i++){
				sign[suma+i]=sign[t.base+i];
			}
			t.base=suma;
			suma=suma+t.limit;
		}
		FSBPTR.head=new FSB(suma,sizeOfMemory-suma );
		eraseMemory(suma,sizeOfMemory-suma);
	}
	//sorting process control blocks in list
	private void processListSort(){
		for(int i=0; i<processList.size(); i++){
			for(int j=0; j<processList.size()-1-i; j++){
				if(processList.get(j).base>processList.get(j+1).base)
					Collections.swap(processList, j, j+1);
			}
		}
	}
	public void showMemory(){//write out memory's frames
		for(int i=0; i<sizeOfMemory; i++){
			//if(i%4==0)System.out.printf ("%1$13s","| "+i +" "+ sign[i]+" \t\n");
			//else System.out.printf ("%1$13s","| "+i +" "+ sign[i]+" \t");
			System.out.println("\t"+i+"| \t"+sign[i]);
		}
	}
	public static void main(String[] args){
		/*ListFSB nowa=new ListFSB();
		nowa.addFSB(23, 2);
		nowa.addFSB(12, 4);
		nowa.addFSB(7, 4);
		nowa.wypisz();
		System.out.println(nowa.fullSpace());*/
		Memory ho=new Memory();
		PCB i=new PCB();
		PCB r=new PCB();
		ho.memoryAllocation(30, r);
		ho.memoryAllocation(70, new PCB());
		ho.memoryReleasing(r);
		
		ho.memoryAllocation(50, new PCB() );
		ho.defrag();
		ho.FSBPTR.sortList();
		ho.showMemory();
		ho.wypiszPCB();
		
		ho.FSBPTR.wypisz();
		System.out.println("PROCESS TERMINATED");
	}
}
