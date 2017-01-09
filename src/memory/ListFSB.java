package memory;

public class ListFSB {
	public FSB head;//pierwszy element listy
	public FSB tail;//ostatni element listy
	
	public ListFSB(){
		head=null;
		tail=head;
	}
	//ta funkcja nie b�dzie potrzebna
	private FSB findLast(){
		FSB bufor=head;
		while(bufor.next!=null)
			bufor=bufor.next;		
		return bufor;
	}
	//dodaje blok wolnej pami�ci
	public void addFSB(int address, int size){
		if(head==null)
			head=new FSB(address,size);
		else{
			FSB bufor=head;
				while(bufor.next!=null)
					bufor=bufor.next;
				bufor=new FSB(address, size);
		}
	}
	//usuwa blok wolnej pami�ci
	public void removeFSB(int size){
		FSB bufor=head;
		while(bufor.next.size!=size&&bufor.next!=null)
			bufor=bufor.next;
		if(bufor.next.size==size)
			bufor.next=bufor.next.next;
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
	public static void main(String [ ] args)
	{
		ListFSB lista=new ListFSB();
		lista.addFSB(23,4);
		lista.addFSB(34, 63);
		lista.addFSB(12, 34);
		lista.wypisz();
	}
}
