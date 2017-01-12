package memoryManagement;

import memoryManagement.FSB;
import memoryManagement.ListFSB;
import processManager.PCB;

public class ListFSB {
	public FSB head;//pierwszy element listy
	public FSB tail;//ostatni element listy
	
	//konstruktor
	public ListFSB(){
		head=new FSB(0,64);
		head=null;
		tail=head;
	}
	//dodaje blok wolnej pamięci
	public void addFSB(int address, int size){
		FSB bufor=head;
		if(head==null){
			head=new FSB(address,size);
		tail=head;
		}else if(head!=null){
			boolean f=true;
			while(bufor.address!=(address+size+1)&&bufor.next!=null)
				bufor=bufor.next;
			if(bufor.address==(address+size+1)){
				bufor.address=address;
				bufor.size=bufor.size+size;
				f=false;
			}
			bufor=head;
			while((bufor.size+bufor.address+1)!=address&&bufor.next!=null)
				bufor=bufor.next;
			if((bufor.size+bufor.address+1)==address){
				bufor.size=bufor.size+size;
				//bufor.address=address;
				f=false;
			}
			if(f){
			tail.next=new FSB(address, size);
			tail=tail.next;
			}
		}
	}
	//usuwa blok wolnej pamięci
	public void removeFSB(int size){
		FSB bufor=head;
		
		if(head.size==size&&head.next!=null)//pierwszy elelment gdy jest kolejny
			head=head.next;
		else if(head.size==size&&head.next==null){//tylko jeden element
			head=null;
			tail=null;
		}
		else if(tail!=null&&tail.size==size&&head!=null){//ostatni element gdy były poprzednie
			bufor=head;
			while(bufor.next.size!=size&&bufor.next!=null)
				bufor=bufor.next;
			if(bufor.next==tail){
				bufor.next=null;
				tail=bufor;
			}			
		}
		else if(tail!=null&&tail.size!=size&&head.size!=size){//element w środku
			while(bufor.next.size!=size&&bufor.next!=null)
				bufor=bufor.next;
			if(bufor.next.size==size)
				bufor.next=bufor.next.next;
		}else System.out.println("brak takiego elementu");
	}
	//szuka miejsca
	public FSB searchForSpace(int size){
		FSB bufor=head;
		while(bufor.size<size&&bufor.next!=null)
			bufor=bufor.next;
		if(bufor.size>=size){
			FSB a=new FSB(bufor.address, bufor.size);
			return a;
		}
		else return new FSB(-1, -1);
	}
	public int fullSpace(){//sprawdza czy po defragmentacji będzie miejsce na proces
		FSB bufor=head;
		int space=0;
		while(bufor.next!=null){
			space=space+bufor.size;
			System.out.println(space);
			bufor=bufor.next;
		}
		System.out.println("calosc "+space);
		return space;
	}
	//sortowanie listy
	public void sortList(){
		FSB smallest=head;
		ListFSB tmp=new ListFSB();//tymczasowa lista
		
		while(head!=null){
			while(smallest.next!=null){
				if(smallest.size>smallest.next.size&&smallest.next!=null)
					smallest=smallest.next;
			}
			tmp.addFSB(smallest.address, smallest.size);
			removeFSB(smallest.size);
			smallest=head;
		}
		head=tmp.head;
	}
	//wypisz wolne bloki pamięci
	public void wypisz(){
		FSB bufor=head;
		System.out.println(bufor.address + " " +bufor.size);
		while(bufor.next!=null){
			bufor=bufor.next;
			System.out.println(bufor.address + " " +bufor.size);
		}
	}
	public static void main(String[] args){
		/*ListFSB nowa=new ListFSB();
		nowa.addFSB(23, 2);
		nowa.addFSB(12, 4);
		nowa.addFSB(7, 4);
		nowa.wypisz();*/
		
		Memory ho=new Memory();
		PCB i=new PCB();
		PCB o= new PCB();
		ho.memoryAllocation(20, i);
		ho.memoryAllocation(40, new PCB() );
		ho.memoryReleasing(i);
		ho.memoryAllocation(22, new PCB());
		ho.FSBPTR.wypisz();
	}
}