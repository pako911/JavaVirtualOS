package memoryManagement;



public class Memory {
	private char sign[]=new char[256];
	private ListFSB FSBPTR;
	
	public Memory(){
		for(int i=0; i<256; i++)
			sign[i]='#';
		FSBPTR = new ListFSB();
	}
	
	public void memoryReleasing(){
		FSBPTR.addFSB(proces.base, proces.size);
		
	}
	public int memoryAllocation(int size){
		int address=FSBPTR.searchForSpace(size);
		return address;
	}
	public char readingFromMemory(Process proces,int address){
		if(address+proces.base<proces.size)
		return sign[address+proces.base];
		else 
	}
	public void writingToMemory(Process proces, int address, char s){
		if(address+proces.base<proces.size)
		sign[address+proces.base]=s;
		else
	}
}
