package memoryManagement;

import java.util.ArrayList;
import processManager.PCB;
import semaphore.InvalidSemaphoreValueException;
import semaphore.Semaphore;

public class Memory {
	public int s=128;//ustawienie wielkości tablicy
	public char sign[]=new char[s];
	private Semaphore FSBSEM; 
	public ListFSB FSBPTR;
	public ArrayList <PCB> processList;//lista obszarów zajętych
	//private Semaphore FSBSEM; //semafor bloków wolnej pamięci 
	private Semaphore MEMORY; //semafor pamięci
	public Memory(){
		for(int i=0; i<s; i++)
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
	public void wypiszPBC()
	{
		for (PCB now: processList)
			System.out.println("PCB start in: "+now.base+" | end in: "+now.limit+" | with sieze of "+ now.limit );
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
		if(tmp.address>=0&&tmp.address<s){
			proces.base=tmp.address;
			proces.limit=size;
			FSBPTR.addFSB(tmp.address+size, tmp.size-size);
			FSBPTR.removeFSB(tmp.size);
			processList.add(proces);
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
				sign[i+bufor.base]=sign[i+t.base];
			}
			bufor.base=t.base;
			suma=suma+t.limit;
			bufor.base=bufor.base+bufor.limit;
		}
		FSBPTR.head=new FSB(bufor.base,s-suma );
		System.out.println("Writing out free space in memory");
		FSBPTR.wypisz();
	}
	//sorting process control blocks in list
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
	public void showMemory(){//wypisz 
		for(int i=0; i<s; i++){
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
		ho.memoryAllocation(50, i);
		ho.memoryAllocation(50, new PCB() );
		ho.memoryReleasing(i);
		ho.memoryAllocation(40, i);
		ho.memoryAllocation(20, new PCB());
		ho.memoryAllocation(5, new PCB());
		ho.memoryAllocation(3, new PCB());
		ho.memoryAllocation(2, new PCB());
		
		ho.defrag();
		ho.FSBPTR.wypisz();	
		ho.wypiszPBC();
	}
}
