
		package dysk;

import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

public class Test {

			public static void main(String[] args) {
				Disc disc = new Disc();
				
				//disc.tworzeniaPliku("TEST", "TXT");
				//disc.wpisywanieDoPliku("TEST", "TXT", "12");
				//disc.tworzeniaPliku("zd", "TXT");
				//disc.wpisywanieDoPliku("zd", "TXT", "jajajjajaj");
				//disc.dopiszDoPliku("zd", "TXT", " jajo");
				//disc.dopiszDoPliku("TEST", "TXT", " ugotuje jajko");
				//disc.wyswietlaPliki();
				//disc.wys();
			//	disc.wyswietlDanyPlik("zd", "TXT");
				//disc.wyswietlDanyPlik("TEST", "TXT");
				//disc.drukujDysk("zd", "TXT");
				//disc.www();
				//disc.tworzeniaPliku("jem", "ee");
				//disc.wpisywanieDoPliku("jem", "ee", "data");
				//disc.www();
				//disc.usuwaniePliku("jem", "ee");
				//disc.wyswietlaPliki();
				//disc.wys();
				 
				disc.tworzeniaPliku("test", "txt");
				disc.wpisywanieDoPliku("test", "txt", "hestm");
				disc.wyswietlaPliki();
				disc.usuwaniePliku("test", "txt");
				disc.wyswietlaPliki();
				
			}
		

	}


