package memoryManagement;

import memoryManagement.FSB;
import memoryManagement.ListFSB;

public class ListFSB {
	public FSB head;//pierwszy element listy
	public FSB tail;//ostatni element listy
	
	public ListFSB(){
		head=null;
		tail=head;
	}
	//ta funkcja nie będzie potrzebna
	private FSB findLast(){
		FSB bufor=head;
		while(bufor.next!=null)
			bufor=bufor.next;		
		return bufor;
	}
	//dodaje blok wolnej pamięci
	public void addFSB(int address, int size){
		if(head==null)
			head=new FSB(address,size);
		else{
			FSB bufor=head;
				while(bufor.next!=null)
					bufor=bufor.next;
				bufor=new FSB(address, size);
				tail=bufor;
		}
	}
	//usuwa blok wolnej pamięci
	public void removeFSB(int size){
		FSB bufor=head;
		while(bufor.next.size!=size&&bufor.next!=null)
			bufor=bufor.next;
		if(bufor.next.size==size)
			bufor.next=bufor.next.next;
	}
	public int searchForSpace(int size){
		int address;
		FSB bufor=head;
		while(bufor.size<size)
			bufor=bufor.next;
		address=bufor.address;
		return address;
	}
	//sortowanie listy
	public void sortList(){
		FSB biggest=head;
		ListFSB tmp=new ListFSB();
		while(biggest.next!=null){
			if(biggest.size<biggest.next.size&&biggest.next!=null)
				biggest=biggest.next;
		}
		tmp.addFSB(biggest.address, biggest.size);
		removeFSB(biggest.size);
	}
	public void wypisz(){
		FSB bufor=head;
		System.out.println(bufor.address + " " +bufor.size);
		while(bufor.next!=null){
			bufor=bufor.next;
			System.out.println(bufor.address + " " +bufor.size);
		}
	}
}