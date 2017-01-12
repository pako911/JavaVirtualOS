package memoryManagement;

import java.util.ArrayList;
import processManager.PCB;
import semaphore.InvalidSemaphoreValueException;
import semaphore.Semaphore;

public class Memory {
	public char sign[]=new char[256];
	private Semaphore FSBSEM; 
	private ListFSB FSBPTR;
	public ArrayList <PCB> processList;//lista obszarów zajętych
	//private Semaphore FSBSEM; //semafor bloków wolnej pamięci 
	private Semaphore MEMORY; //semafor pamięci
	
	public Memory(){
		for(int i=0; i<256; i++)
			sign[i]='#';
		FSBPTR = new ListFSB();
		try {
			FSBSEM=new Semaphore(1);
			MEMORY=new Semaphore(0);
		} catch (InvalidSemaphoreValueException e) {
			e.printStackTrace();
		}
		processList=new ArrayList <PCB>();
	}
	public void memoryReleasing(PCB proces){//należy jako argumenty podać proces który się usuwa
		FSBPTR.addFSB(proces.base, proces.limit);
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
		if(tmp.address>=0&&tmp.address<256){
			proces.base=tmp.address;
			proces.limit=size;
			FSBPTR.addFSB(tmp.address+size, tmp.size-size);
			processList.add(proces);
			return true;
		}else{
					if(size<FSBPTR.fullSpace()){
					defrag();
					tmp=FSBPTR.searchForSpace(size);
					proces.base=tmp.address;
					FSBPTR.addFSB(tmp.address+size, tmp.size-size);
					processList.add(proces);
					return true;
				}else{
					MEMORY.P();
					return false;
					}
		}
	}
	private void defrag(){
		int suma=0;
		processListSort();
		PCB bufor =new PCB(); 
		bufor.base=0;
		for(PCB t : processList){
			t.base=bufor.base;
			suma=suma+t.limit;
			bufor.base=bufor.base+bufor.limit;
		}
		FSBPTR.head=new FSB(bufor.base,suma );
	}
	private void processListSort(){
		PCB nier=new PCB();//PCB procesu mającego najmniejszy adres w pamięci
		ArrayList <PCB> tmp=new ArrayList<PCB>();
		while(!processList.isEmpty()){
			for(PCB temp:processList){
				if(temp.base<nier.base){
					nier=temp;
					processList.remove(nier);
					tmp.add(nier);
				}
			}
		}
		processList=tmp;
	}
	public void showMemory(){
		for(int i=0; i<256; i++){
			if(i%4==0)System.out.printf ("%1$13s","| "+i +" "+ sign[i]+" \t\n");
			else System.out.printf ("%1$13s","| "+i +" "+ sign[i]+" \t");
		}
	}
}
