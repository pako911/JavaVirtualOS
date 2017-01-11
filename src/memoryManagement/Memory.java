package memoryManagement;

import java.util.ArrayList;
import java.util.Iterator;

import semaphore.InvalidSemaphoreValueException;
import semaphore.Semaphore;

public class Memory {
	public char sign[]=new char[256];
	private ListFSB FSBPTR;
	private ArrayList <Process> processList;//lista obszarów zajętych
	//private Semaphore FSBSEM; //semafor bloków wolnej pamięci 
	private Semaphore MEMORY; //semafor pamięci
	
	public Memory(){
		for(int i=0; i<256; i++)
			sign[i]='#';
		FSBPTR = new ListFSB();
		try {
			FSBSEM=new Semaphore(1);
		} catch (InvalidSemaphoreValueException e) {
			e.printStackTrace();
		}
		try {
			MEMORY=new Semaphore(0);
		} catch (InvalidSemaphoreValueException e) {
			e.printStackTrace();
		}
	}
	
	public void memoryReleasing(Process proces){//należy jako argumenty podać proces który się usuwa
		FSBPTR.addFSB(proces.base, proces.limit);
		processList.remove(proces);
		for(int i=0; i<MEMORY.countList; i++)//!!!Potrzebna jest metoda w klasie semoafora
		MEMORY.V();
	}
	public void memoryAllocation(int size, Process proces){
		FSB tmp=FSBPTR.searchForSpace(size);
		processList.add(proces);
		if(tmp.address>=0&&tmp.address<256){
			proces.base=tmp.address;
			FSBPTR.addFSB(tmp.address+size, tmp.size-size);
		}else if(size<FSBPTR.fullSpace()){
		defrag();
		memoryAllocation(size, proces);
		}else MEMORY.P();
	}
	private void defrag(){
		
	}
	private void processListSort(){
		Process nier;//proces mający najmniejszy adres w pamięci
		ArrayList <Process> tmp;
		
		for(Process temp:processList){
			if(temp.base<nier.base){
				nier=temp;
				processList.remove(nier);
				tmp.add(nier);
			}
		}
		
	}
	public void showMemory(){
		for(int i=0; i<256; i++){
			if(i%4==0)System.out.printf ("%1$13s","| "+i +" "+ sign[i]+" \t\n");
			else System.out.printf ("%1$13s","| "+i +" "+ sign[i]+" \t");
		}
	}
}
