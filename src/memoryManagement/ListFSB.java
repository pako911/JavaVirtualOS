package memoryManagement;

import memoryManagement.FSB;
import memoryManagement.ListFSB;

public class ListFSB {
	public FSB head;//first element in list
	public FSB tail;//last element in list
	
	//constructor for whole list
	public ListFSB(int sizeOfMemory){
		head=new FSB(0,sizeOfMemory);//first free-space-block's size is equal to whole memory
		tail=head;
	}
	private ListFSB(){//private constructor, for use only in this class methods
		head=null;
		tail=null;
	}
	//add free space block that starts in address field and have size of "size" argument
 	public void addFSB(int address, int size){
		FSB bufor=head;
		if(head==null){
			head=new FSB(address,size);
		tail=head;
		}else if(head!=null){
			if(tail==null)
				tail=head;
			boolean f=true;
			/*if(f){
				while(bufor.next!=null&&bufor.next.next!=null&&bufor.size+bufor.address!=address&&bufor.next.address!=(address+size))
					bufor=bufor.next;
				if(bufor.next.next!=null&&bufor.size+bufor.address!=address&&bufor.next.address!=(address+size)){
					bufor.size=bufor.size+size+bufor.next.size;
					f=false;
					removeFSB(bufor.next.size);
				}
			}*/
			if(f){
				while((bufor.size+bufor.address)!=address&&bufor.next!=null)
					bufor=bufor.next;
				if((bufor.size+bufor.address)==address){
					bufor.size=bufor.size+size;
					f=false;
				}
			}
			bufor=head;
			if(f){
				while(bufor.address!=(address+size)&&bufor.next!=null)
					bufor=bufor.next;
				if(bufor.address==(address+size)){
					bufor.address=address;
					bufor.size=bufor.size+size;
					f=false;
				}
			}
			if(f){
			tail.next=new FSB(address, size);
			tail=tail.next;
			}
			else addFSB(bufor.address,bufor.size);
		}
	}
	//removing free space block with size given in argument
	public void removeFSB(int size){
		FSB bufor=head;
		if(head!=null){
			//first element, only if there is next element
			if(head.size==size&&head.next!=null)
					head=head.next;
			//if there is only one element in list
			else if(head.size==size&&head.next==null){
				head=null;
				tail=null;
			}
			//the last one element, when there is previous
			else if(tail!=null&&tail.size==size){  
				bufor=head;
				while(bufor.next!=null&&bufor.next.size!=size)
					bufor=bufor.next;
				if(bufor.next==tail){
					bufor.next=null;
					tail=bufor;
				}			
			}
			//element in the middle
			else if(tail!=null&&tail.size!=size&&head.size!=size){
				while(bufor.next!=null&&bufor.next.size!=size)
						bufor=bufor.next;
				if(bufor.next!=null&&bufor.next.next!=null&&bufor.next.size==size)
					bufor.next=bufor.next.next;
			}else System.out.println("brak takiego elementu");
		}else System.out.println("brak elementów na liście");
	}
	//looking for space
	public FSB searchForSpace(int size){
		sortList();
		if(head!=null){
			FSB bufor=head;
			while(bufor.size<size&&bufor.next!=null)
				bufor=bufor.next;
			if(bufor.size>=size){
				FSB a=bufor;
				return a;
			}
			else return new FSB(-1, -1);
		}
		else return new FSB(-1, -1);
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
		System.out.println("whole free space: "+space);
		return space;
	}
	//sorting free space blocks in list
	public void sortList(){
		ListFSB tmp=new ListFSB();//temporary list
		while(head!=null){
			FSB smallest=head;
			while(smallest.next!=null){
				if(smallest.size>smallest.next.size)
					smallest=smallest.next;
				else break;
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
		System.out.println("Show FSB");
		FSB bufor=head;
		if(bufor!=null){
			System.out.println("FSB: starts: "+bufor.address +" |ends in: "+(bufor.address+bufor.size-1)+" |with size of: " +bufor.size);
			while(bufor.next!=null){
				bufor=bufor.next;
				System.out.println("FSB: starts: "+bufor.address +" |ends in: "+(bufor.address+bufor.size-1)+" |with size of: " +bufor.size);
				}
		}else System.out.println("Nie ma co wypisać");
	}
}