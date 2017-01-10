package memoryManagement;

import memoryManagement.FSB;

public class FSB {
	public FSB next;
	public int address;
	public int size;
	
	public FSB(int a, int s){
		address=a;
		size=s;
		next=null;
	}
}
