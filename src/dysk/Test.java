
		package dysk;

import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

public class Test {

			public static void main(String[] args) {
				Disc disc = new Disc();
				
				disc.tworzeniaPliku("TEST", "TXT");
				disc.wpisywanieDoPliku("TEST", "TXT", "Jarkoasdoiaskdhjfkl zaa");
				disc.tworzeniaPliku("zd", "TXT");
				disc.wpisywanieDoPliku("zd", "TXT", "jajajjajaj");
				
				
			}
		

	}


