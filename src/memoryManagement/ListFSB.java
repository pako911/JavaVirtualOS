package memoryManagement;

import memoryManagement.FSB;
import memoryManagement.ListFSB;
import processManager.PCB;

public class ListFSB {
	public FSB head;//first element in list
	public FSB tail;//last element in list
	
	//constructor for whole list
	public ListFSB(){
		head=new FSB(0,128);//first free-space-block's size is equal to whole memory
		tail=head;
	}
	//add free space block that starts in address field and have size of "size" argument
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
	//removing free space block with size given in argument
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
	//looking for space
	public FSB searchForSpace(int size){
		if(head==null){
			return new FSB(-1, -1);
		}else{
			FSB bufor=head;
			while(bufor.size<size&&bufor.next!=null)
				bufor=bufor.next;
			if(bufor.size>=size){
				FSB a=new FSB(bufor.address, bufor.size);
				return a;
			}
			else return new FSB(-1, -1);
		}
	}
	//checks if after defragmentation there will be space for process
	public int fullSpace(){
		FSB bufor=head;
		int space=0;
		while(bufor.next!=null){
			space=space+bufor.size;
			bufor=bufor.next;
		}
		space=space+bufor.size;
		System.out.println("calosc "+space);
		return space;
	}
	//sorting free space blocks in list
	public void sortList(){
		ListFSB tmp=new ListFSB();//tymczasowa lista
		while(head!=null){
			FSB smallest=head;
			while(smallest.next!=null){
				if(smallest.size>smallest.next.size)
					smallest=smallest.next;
			}
			tmp.addFSB(smallest.address, smallest.size);
			removeFSB(smallest.size);
			smallest=head;
		}
		if(tmp.head!=null)
		head=tmp.head;
		else System.out.println("nie ma co sortowac");
	}
	//write out free space blocks
	public void wypisz(){
		FSB bufor=head;
		if(bufor!=null){
			System.out.println("FSB: "+bufor.address + " " +bufor.size);
			while(bufor.next!=null){
				bufor=bufor.next;
				System.out.println("FSB: "+bufor.address + " " +bufor.size);
				}
		}else System.out.println("Nie ma co wypisać");
	}
}