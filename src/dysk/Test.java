
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
				disc.wys();
				disc.dopiszDoPliku("zd", "TXT", " dskj");
				disc.tworzeniaPliku("TEST", "TXT");
				disc.tworzeniaPliku("TEST2", "TXT");
				disc.usuwaniePliku("TEST", "TXT");
				disc.wys();
				disc.tworzeniaPliku("TEST222", "TXT");
				disc.WyswietlaPliki();
				disc.wys();
				
				//disc.extended_Fat();
			//	disc.delete_file("TEST", "TXT");
				//disc.extended_Fat();
			/*	disc.tworzeniaPliku("TEST3", "TXT");
				disc.write_file("TEST3", "TXT", "Jarko zaaasdasdasdfasdfasfd");
				disc.tworzeniaPliku("TEST4", "TXT");
				disc.write_file("TEST4", "TXT", "Jarko zaasdfasdfsjkdhfjkashjdjkfhaslkdfhjklashjklf");
				disc.tworzeniaPliku("TEST5", "TXT");
				disc.write_file("TEST5", "TXT", "Jarsdfsdfsfsdfsghdfhhjklsgdfhjasdfhjdsahjkdfhjdkshfjjskhgjdkfhsjkdhfjshajkdfhjdkshajfhjkashjdfhjsakhfjhjsdkhfjkhasjkhdfjhasjkfhjkshdjfhjskahdfjhsajkdfhjkko zaaasdfsdfsdfasdf");
				disc.tworzeniaPliku("TEST6", "TXT");
				disc.write_file("TEST6", "TXT", "Jarkasildjfkljasklfjklsajdklfjklasjdkfljasdkljfksjaklfjklasjkfjklasjfkjaskljdfklsjaklfjdkljsakljfsdkjklso zaaasdfkujsahdfjkhasdjifhjkasdhjkfhasjkhdjkfhasjkhfdjk");
				disc.tworzeniaPliku("TEST7", "TXT");
				disc.write_file("TEST7", "TXT", "jasd");
				disc.tworzeniaPliku("TEST8", "TXT");
				disc.write_file("TEST8", "TXT", "Jarko zaaasdfasdfsdf");
				disc.tworzeniaPliku("TEST9", "TXT");
				disc.write_file("TEST9", "TXT", "Jarko zaaasdfasd");
				disc.tworzeniaPliku("TEST10", "TXT");
				disc.write_file("TEST10", "TXT", "Jarkowrt");
				disc.tworzeniaPliku("TEST11", "TXT");
				disc.write_file("TEST11", "TXT", "Jarko zaaasdoifuioasjifouasiodufjiosajiwerqwrwqrwrwqerwqrfdsafgewrhrwyryqweuhjwerjheryk");
				disc.tworzeniaPliku("TEST22", "TXT");
				disc.tworzeniaPliku("TEST922", "TXT");
				disc.tworzeniaPliku("TEST822", "TXT");
				disc.tworzeniaPliku("TEST722", "TXT");
				disc.tworzeniaPliku("TEST622", "TXT");
				disc.tworzeniaPliku("TEST422", "TXT");
				disc.tworzeniaPliku("TEST522", "TXT");
				disc.tworzeniaPliku("TEST322", "TXT");
				disc.tworzeniaPliku("TEST222", "TXT");
				disc.tworzeniaPliku("TEST122", "TXT");

				disc.write_file("TEST822", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.write_file("TEST722", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.write_file("TEST622", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.write_file("TEST422", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.write_file("TEST522", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.write_file("TEST322", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.write_file("TEST222", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.write_file("TEST122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.tworzeniaPliku("TEST1122", "TXT");

				disc.write_file("TEST1122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.tworzeniaPliku("TEST1222", "TXT");

				disc.write_file("TEST1222", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.tworzeniaPliku("TEST2122", "TXT");

				disc.write_file("TEST2122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.tworzeniaPliku("TEST3122", "TXT");

				disc.write_file("TEST3122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.tworzeniaPliku("TEST4122", "TXT");

				disc.write_file("TEST4122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.tworzeniaPliku("TEST5122", "TXT");

				disc.write_file("TEST5122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.tworzeniaPliku("TEST6122", "TXT");

				disc.write_file("TEST6122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.tworzeniaPliku("TEST7122", "TXT");

				disc.write_file("TEST7122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.tworzeniaPliku("TEST8122", "TXT");

				disc.write_file("TEST8122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				disc.tworzeniaPliku("TEST9122", "TXT");

				disc.write_file("TEST19122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				
				disc.tworzeniaPliku("TEST19122", "TXT");

				disc.write_file("TEST19122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				
				disc.tworzeniaPliku("TEST29122", "TXT");

				disc.write_file("TEST29122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				
				disc.tworzeniaPliku("TEST39122", "TXT");

				disc.write_file("TEST39122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				
				disc.tworzeniaPliku("TEST49122", "TXT");

				disc.write_file("TEST49122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				
				disc.tworzeniaPliku("TEST59122", "TXT");
				
				disc.write_file("TEST59122", "TXT", "Jarfjasyduifhasjkhfui auqiowyureuihuiwaseghyuiqwtgyurgyuqwtgyrgwyugrywgyiwsdyf kjhsidu ayghfujygas");
				
disc.tworzeniaPliku("kak", "txt");
disc.write_file("kak", "txt", "asd");
				*/
				


				


				
				
			
				//disc.wyswietl();
				disc.directory_entry();
			
				
				//disc.WyswietlaPliki();
				//disc.wyswietl();
				
			}
		

	}


