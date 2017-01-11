package memoryManagement;

import java.util.ArrayList;

public class Memory {
	public char sign[]=new char[256];
	private ListFSB FSBPTR;
	private ArrayList <Process> processList;//lista obszarów zajętych
	public Memory(){
		for(int i=0; i<256; i++)
			sign[i]='#';
		FSBPTR = new ListFSB();
	}
	
	public void memoryReleasing(int base, int size){//należy jako argumenty podać base i size procesu który się usuwa
		FSBPTR.addFSB(base,size);
		
	}
	public int memoryAllocation(int size, Process proces){
		int address=FSBPTR.searchForSpace(size);
		processList.add(proces);
		return address;
	}
	public void showMemory(){
		for(int i=0; i<256; i++){
			if(i%4==0)System.out.printf ("%1$13s","| "+i +" "+ sign[i]+" \t\n");
			else System.out.printf ("%1$13s","| "+i +" "+ sign[i]+" \t");
		}
	}
}
