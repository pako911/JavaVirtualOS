package memory;

import java.util.HashMap;

class Proces{
	int base;
	int limit;
	public Proces(int b, int l){
		base=b;
		limit=l;
	}
}

public class Memory {
	private char sign[]=new char[256];
	private ListFSB FSBPTR;
	HashMap<Integer, Proces> procescontrol=new HashMap<Integer, Proces>();
	
	public Memory(){
		FSBPTR = new ListFSB();
	}
	
	public void memoryReleasing(){
		
	}
	public void memoryAllocation(int size){
		
	}
	public void readingFromMemory(){
		
	}
	public void writingInMemory(){
		
	}
}