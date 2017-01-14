
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
				//disc.wyswietlaPliki();
				//disc.www();
				
				//disc.wyswietlDanyPlik("test", "txt");
				disc.tworzeniaPliku("tsest", "txt");
				disc.wpisywanieDoPliku("tsest", "txt", "jak duzy jestem");
				
				disc.tworzeniaPliku("tse3st", "txt");
				disc.wpisywanieDoPliku("tse3st", "txt", "tak duzy ja");
				
				//disc.wpisywanieDoPliku("tsest", "txt", "dalsza czesc");
			//	disc.usuwaniePliku("tsest", "txt");
			//	disc.www();
				//disc.usuwaniePliku("test", "txt");
				disc.usuwaniePliku("test", "txt");
				disc.tworzeniaPliku("tst", "txt");
				disc.wpisywanieDoPliku("tst", "txt", "abcdefghijklmoprstuwxyz123456789qwertyuiopasdfghjklzxcvbnm987654321qazwsxedcrfvtgbyhnujmikolp");
				//disc.www();
				disc.tworzeniaPliku("tse3est", "txt");
				disc.wpisywanieDoPliku("tse3est", "txt", " farmet mial ");
				
				disc.www();
			}
		

	}


