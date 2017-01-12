
		package dysk;

import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

public class Test {

			public static void main(String[] args) {
				Disc disc = new Disc();
				
				disc.tworzeniaPliku("TEST", "TXT");
				disc.wpisywanieDoPliku("TEST", "TXT", "Jarkoasdoiaskdhjfkl sdfsadfsoidfhasdgfjksahfjkhsajkdhfjkasdhdjkfhdsjakhfjkashjkfhajskhfjksahjkfhasjkhfjkasdhdfjkhasjkfhjksadhdfjkdshzaa");
				disc.tworzeniaPliku("zd", "TXT");
				disc.wpisywanieDoPliku("zd", "TXT", "jajajjajaj");
				disc.dopiszDoPliku("zd", "TXT", "jajo");
				disc.wyswietlaPliki();
				disc.wys();
				disc.drukujDysk("zd", "TXT");
			}
		

	}


