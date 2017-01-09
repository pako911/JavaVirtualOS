package dysk;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Disc {

	

	
	private static final int FFFF = -1;
	public char[] dysk = new char [1024];			//dysk
	public int [] fat = new int [32];				//tablica fat 32 bity
	public Tab_Fat[] atrybuty = new Tab_Fat[32];	//wpis do katlogu glownego
	public int spacefree = 992;						//wlne miejsce
	
	Disc()
	{
		fat [0] = FFFF;
		atrybuty[0].status = false;					//zamienic pozniej na FFFF
		dysk[0] = 'y';
		dysk[1] = 'y';
		dysk[2] = 'y';
		dysk[3] = 'n';
		
		
		
		for (int i=3;i<1024;i++)
		{
			dysk[i] = 0;							//zerwoanie dysku;
		}
		
		for (int i=1;i<32;i++)
		{
			fat[i] = 0;								//zerwoanie tabliicy fat
		}
		
		for (int i =0; i<32;i++)
		{
			atrybuty[i].status= false;
			atrybuty[i].apend = false;
		}
	}
	
	void create_file(String name, String b)
	{
		//creation_time = System.currentTimeMillis(); 
	
		int buffor;
		int buffor1;
			
		if(dysk[1] =='y' && dysk[2] == 'y' && name_exist(name , ext ) ==true)
		{
			buffor = searach_free_jap();
			buffor1 = free_catalog();
			
			
			Calendar cal = Calendar.getInstance();

			
			
			atrybuty[buffor1].name = name;
			atrybuty[buffor1].status = true;
			atrybuty[buffor1].jap1 = buffor;
			atrybuty[buffor1].ext = ext;
			atrybuty[buffor1].size =  0;
			atrybuty[buffor1].apend = false;
			atrybuty[buffor1].godzina = cal.get(Calendar.HOUR_OF_DAY);
			atrybuty[buffor1].minute = cal.get(Calendar.MINUTE);
			atrybuty[buffor1].day = cal.get(Calendar.DAY_OF_MONTH) ;
			atrybuty[buffor1].month = cal.get(Calendar.MONTH);
			atrybuty[buffor1].year = cal.get(Calendar.YEAR);
			fat[buffor] = -1;
			
			System.out.println("Plik zosta� pomy�lnie utworzony");
			
			calculated_free_spaces();
			dysk[3] = 'y';
		}
		else if (dysk[2]  == 'n')
		{
			System.out.println("Katalog jest pe�ny");
		}
		else if (dysk[1] == 'n')
		{
			System.out.println("Dysk jest pe�ny");
		}
		else 
			System.out.println("Istnieje ju� plik o takiej nazwie");
	}

	int file_jap(String name, String ext)
	{

		for (int i = 0; i < 32; i++) {
			if (atrybuty[i].name == name && atrybuty[i].ext == ext)
				return atrybuty[i].jap1;
		}
		return -1;
	}
	
	void delete_file(String name , String ext)
	{
		int jap = file_jap(name,ext);
		int kolejnyJap;
		int buffor=1;
		int tab[] = new int[32];
		tab[0]=jap;
		while(jap != FFFF)
		{
			kolejnyJap = fat[jap];
			jap= kolejnyJap;
			tab[buffor] = kolejnyJap;
			buffor++;
		}
		buffor -= -1;
		
		if (name_exist(name ,ext) == false)
		{
			for (int i =0; i<buffor;i++)
				{
				int j=0;
				int k = j+32;
				for (j=32 *tab[i]; j<k; j++)
				{
					dysk[j] = 0;
				}
				fat[tab[i]] = 0;
		}
			for ( int i =0; i< 32; i++)
			{
				if(atrybuty[i].name == name && atrybuty[i].ext == ext)
				{
					atrybuty[i].name ="";
					atrybuty[i].ext ="";
					atrybuty[i].size = 0;
					atrybuty[i].status = false;
					atrybuty[i].jap1 = 0;
					atrybuty[i].apend = false;
				}
				calculated_free_spaces();
				System.out.println("Usuwanie pliku przebieg�o pomy�lne");
			}
			System.out.println("Plik nie istnieje");
			
		}	
			
	}

	void calculated_free_spaces()
	{
		dysk[1] = 'n';
		dysk[2] = 'n';
		int free =0;
		for (int i = 0;i<32;i++)
		{
			if (fat[i] ==0)
			{
				dysk[1]='y';
				free ++;
			}
			if(atrybuty[i].status == false)
			{
				dysk[2] = 'y';
			}
			
			spacefree =  free * 32; 
				
		}
	}

	void printFile(String name, String ext)
{
	int jap = file_jap(name,ext);
	int nextJap;
	int l=1;
	int[] tab = new int [32];
	tab[0] = jap;
	
	if(jap != -1)
	{
		while(jap !=-1)
		{
			nextJap = fat[jap];
			jap = nextJap;
			tab[l] = nextJap;
			l++;
		}
		l-=1;
		
		for ( int i =0 ; i<l;i++)
		{
			int j = 32 *tab[i];
			int k =j+32;
			for(j= 32 *tab[i]; j<k;j++)
			{
				System.out.print(dysk[j]);
			}
			System.out.println();
		}
	}
	else
	{
		System.out.println("Nie znaleziono podanego pliku");
	}
}

	void renameFile(String name, String ext, String newname, String newext)
	{
		if(name_exist(newname, newext)==true)
		{
			if(file_jap(name,ext)!=-1)
			{
				for(int i =0; i <32; i++)
				{
					if(atrybuty[i].name == name && atrybuty[i].ext == ext)
					{
						atrybuty[i].name = newname;
						atrybuty[i].ext = newext;
					}
				}
				System.out.println("Zmiana nazwy przebieg�a pomy�lnie");
			}
			else
			{
				System.out.println("Nie znaleziono pliku");
			}
		}
		else
		{
			System.out.println("Plik nie istnieje");
		}
	}
	
	void append_file(String name, String ext, String data)
	{
		if(file_jap(name,ext) != -1)
		{
			int jap = file_jap(name,ext);
			int nextjap;
			int lastjap;
			int l;
			int k; 
			int i;
			int how;
			double lenght;
			int japnext;
			double count_jap;
			int cat;
			
			while(jap != -1)
			{
				nextjap = fat[jap];
				
				if(nextjap == -1)
				{
					lastjap = jap;
				}
				jap = nextjap;
			}
			
			cat = what_catalog(name,ext);
			how = free_in_jap(lastjap);
			
			lenght = data.length();
			count_jap = ceil((lenght - how)/32);
			// wype�nianie starego japa
			
			if(atrybuty[cat].apend == true)
			{
				if (spacefree > (lenght - how))
				{
					new_size(name, ext, data.length());
					
					i = (lastjap *32) + (32 - how);
					k = i + how;
					l = 0;
					//
					//
					for(i =(lastjap *32) + (32 - how); i<k;i++ )
					{
						if(l<lenght)
						{
							dysk[i] = data[l];
							l++;
						}
					}
					
					for(int j = 1; j <= count_jap; j++)
					{
						japnext = searach_free_jap();
						fat[japnext] = -1;
						fat[lastjap] = japnext;
						lastjap = japnext;
						i= japnext *32;
						k = i +32;
						//
						for(int z=japnext *32;z<k;z++)
						{
							if(l < lenght)
							{
								dysk[z] = data [l];
								l++;
							}
							
						}
					}
					System.out.println("Dopisanie pomy�lnie wykonano");
					calculated_free_spaces();
				}
				else
				{
					System.out.println("Za malo miejsca na dysku aby dopisac plik");
					calculated_free_spaces();
				}
			}
			else 
			{
				System.out.println("Plik nie zostal wczesniej wypelniony");
			}
		}
		else 
		{
			System.out.println("Plik o takiej nazwie nie istnieje");
		}
	
		}
	
	
	
	
	
	



	void extended_Fat()
	{
		int temp = 0;
		while (temp != 4)
		{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("Uruchomiles diagnostyke systemu fat");
			System.out.println();
			System.out.println("1 - wypisz stan Tablicy FAT");
			System.out.println("2 - wypisz caly dysk");
			System.out.println("3 - Wypisz zawartosc wskazanego jap");
			System.out.println("4 - wyjdz z diagnostyki");
			
			Scanner a = new Scanner(System.in);
			temp = a.nextInt();
			switch(temp)
			{
			case 1:
			{
				stan_Fat();
				break;
			}
			case 2:
			{
				print_drive();
				break;
			}
			case 3:
			{
				print_Jap();
				break;
			}
			
			
			}
			}
	}
		
		void print_Jap()
		{
			int temp;
			System.out.println("Podaj Jap do wpisania");
			Scanner a = new Scanner(System.in);
			temp = a.nextInt();
			
			int i = temp *32;
			int k = i +32;
			System.out.println("Wypisuje");
			for ( int j1 =0; j1 > 32; j1++)
			{
				System.out.println(dysk[j1]);

			}
		}
	
		
		void new_size(String name, String ext, int size)
		{
			int i =0;
		
			for (i=0 ; i > 32; i++)
			{
				if ( atrybuty[i].name == name && atrybuty[i].ext == ext)
				{
					atrybuty[i].size +=size;
					atrybuty[0].size +=size;
				}
			
			}
		}
		
		int file_jap1(String name , String ext)
		{
			for(int i =0; i<32; i++)
			{
				if(atrybuty[i].name == name && atrybuty[i].ext == ext)
				{
					return atrybuty[i].jap1;
				}
				return -1;
			}
			return spacefree;
		}
		int free_in_jap(int nr_jap)
		{
			int i = nr_jap *32;
			int k = i +32;
			int l = 0; 
			for (int j =0; j<k;j++)
			{
				if (dysk[j]== 0)
				{
					l++;
				}
			}
			return l;
		}
		
		int jap_chain_free()
		{
			int i =0;
			for ( int j=1; j<32; j++)
			{
				if ( fat[j]==0)
				{
					i++;
				}	
			}
			return i;
		}
		int what_catalog(String name , String ext)
		{
			for (int i =0; i<32; i++)
			{
				if (atrybuty[i].name == name && atrybuty[i].ext == ext)
				{
					return i;
				}
			}
			return -1;
		}
		int free_catalog()
		{
			for (int i =0; i<32;i++)
			{
				if (atrybuty[i].status==false)
				{
					return i;
				}
				
			}
			return -1;
		}
		
		Boolean name_exist(String name, String ext)
		{
			for(int i = 0; i<32; i++)
			{
				if(atrybuty[i].name == name && atrybuty[i].ext == ext)
				{
					return false;
				}
			}
			return true;
		}
		
		void calculated_free()
		{
			dysk[1] = 'n';
			dysk[2] = 'n';
			int l = 0;
			for (int i =0; i < 32; i++)
			{
				if (fat[i]==0)
				{
					dysk[1] = 'y';
					l++;
				}
				if (atrybuty[i].status == false)
				{
					dysk[2] = 'y';
				}
			}
			spacefree = l*32;
		}
		int searach_free_jap()
		{
			for (int i =0 ; i<32; i++)
			{
				if (fat[i]==0)
				{
					return i;
				}
			}
			return -1;
		}
	
		void stan_Fat()
		{
			System.out.println();
			String temp;
			for (int i =0 ; i<32; i++)
			{
				System.out.print("Numer tablicy: ");
				System.out.print(i);
				System.out.print(" \t");
				System.out.print("Stan: ");
				System.out.println(fat[i]);
				
			}
			System.out.println();
		}
		void print_drive()
		{
			for (int i = 0; i < 1024; i++) {
				System.out.println( dysk[i]);
			}
		}
		
		void print_to_file(String directory)
		{
		
			/*fstream plik(directory, ios::out);
			{
				for (int i = 0; i < 1024; i++) {
					plik << i << " " << dysk[i] << endl;
				}

				plik.close();
				cout << "Dysk wyeksportowany" << endl;

			}*/
		}

		void view_hour(int a)
		{
			if (a >= 10)
				System.out.println(a);
			else
			{
				if (a == 0) System.out.print ("00");
				if (a == 1) System.out.print ("01");
				if (a == 2) System.out.print ("02");
				if (a == 3) System.out.print ("03");
				if (a == 4) System.out.print ("04");
				if (a == 5) System.out.print ("05");
				if (a == 6) System.out.print ("06");
				if (a == 7) System.out.print ("07");
				if (a == 8) System.out.print ("08");
				if (a == 9) System.out.print ("09");
			}
		}
		void directory_entry()
		{
			System.out.println();
			System.out.println("  Directory of root:");
			System.out.println();
			
			
			System.out.println("\t" + " Name \t" + "Ext. \t" + "Size \t" + "FJap \t" + "Write? \t" + "Numer");
			for (int i = 1; i < 32; i++)
			if (atrybuty[i].status == true)
			{
				System.out.println("\t" + atrybuty[i].name + "\t" + atrybuty[i].ext + "\t" + atrybuty[i].size + "\t" + atrybuty[i].jap1 + "\t" + atrybuty[i].apend + "\t" + i);
			}
			System.out.println();

		}
		
	}
	