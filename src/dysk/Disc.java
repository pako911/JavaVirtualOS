package dysk;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Disc {

	

	
	public static final int FFFF = -1;
	public char[] dysk = new char [2048];			//dysk
	public int [] fat = new int [64];				//tablica fat 64 bity
	public Tab_Fat[] atrybuty = new Tab_Fat[64];	//wpis do katlogu glownego
	public int spacefree = 1984;						//wlne miejsce
	
	public Disc()
	{
		
		for(int i = 0; i < atrybuty.length; i ++) {
			atrybuty[i] = new Tab_Fat(); // magia ?? 
		}
		atrybuty[0].status = false;					
		for (int i=0;i<2048;i++)
		{
			dysk[i] = 0;							//zerwoanie dysku;
		}
		
		for (int i=0;i<64;i++)
		{
			fat[i] = 0;	
			atrybuty[i].status= false;
			atrybuty[i].zapisany = false;//zerwoanie tabliicy fat
		}
	}
	
	public void tworzeniaPliku(String nazwa, String ext)		//parametry nazwa rozszerzenie
	{
		
	
		int buffor; 		// zmienne pomocnicze
		int buffor1;
			
		if( nazwaIstnieje(nazwa , ext ) == true )
		{
			buffor = szukanieWolnegoJap();
			buffor1 = wolnyKatalog();
			
			
			Calendar cal = Calendar.getInstance();

			
			//uzupelnienie wpisow do katalogu
			atrybuty[buffor1].nazwa = nazwa;
			atrybuty[buffor1].status = true;
			atrybuty[buffor1].jap1 = buffor;
			atrybuty[buffor1].ext = ext;
			atrybuty[buffor1].rozmiar = 0;
			atrybuty[buffor1].zapisany = false;
			atrybuty[buffor1].godzina = cal.get(Calendar.HOUR_OF_DAY);
			atrybuty[buffor1].minuta = cal.get(Calendar.MINUTE);
			atrybuty[buffor1].dzien = cal.get(Calendar.DAY_OF_MONTH) ;
			atrybuty[buffor1].miesiac = cal.get(Calendar.MONTH)+1;
			atrybuty[buffor1].rok = cal.get(Calendar.YEAR);
			fat[buffor] = -1;													
			
			System.out.println("Plik zosta� pomy�lnie utworzony");
			
			iloscWolnegoMiejsca();
			
		}
		else if (wolnyKatalog() == -1)// sprawdz czy w fat
		{
			System.out.println("Katalog jest pe�ny");
		}
		else if (wolneMiejsceDysk() <64 )
		{
			System.out.println("Dysk jest pe�ny");
		}
		else 
			System.out.println("Istnieje ju� plik o takiej nazwie");
	}

	public int file_jap(String nazwa, String ext)
	{

		for (int i = 0; i < 64; i++) {
			if (atrybuty[i].nazwa == nazwa && atrybuty[i].ext == ext)
				return atrybuty[i].jap1;
		}
		return -1;
	}
	public void wys()
	{
		for (int i = 0; i<64; i++)
		{
			System.out.print("Numer tablicy: ");
			System.out.print(i);
			System.out.print(" \t");
			System.out.print("Stan: ");
			if (fat[i]!=0)
			{
				System.out.println("zaj�ty");
			}
			else
				System.out.println(fat[i]);
		}
	}
	
	public void wpisywanieDoPliku(String nazwa, String ext, String data)
	{
		/* Zadeklarowanie potrzebnych zmiennych */
		int jap_pr;
		int japnext;
		double length;
		double count_jap;
		int i;
		int k;
		int l;
		length = data.length();
		count_jap = Math.ceil((length / 64));

		/* Czy katalog wolny?  */
		int cat = what_catalog(nazwa, ext);
		if (cat != -1){
		if (atrybuty[cat].zapisany == false) 
			
				atrybuty[cat].zapisany = true;


			if (file_jap(nazwa, ext) != -1) {
				if (spacefree > length) {
					jap_pr = file_jap(nazwa, ext);
					i = jap_pr * 64;
					k = 0;
					l = i + 64;

					char datachar[] = data.toCharArray();
					//DLA PIERWSZEGO JAPA
					for (int q = 0; q < l; q++) {
						if (k <= length - 1) {
							dysk[q] = datachar[k];
							k++;
						}
					}

					char datachar1[] = data.toCharArray();
					//DLA WIECEJ JAPOW
					if (count_jap > 1) {

						for (int j = 2; j <= count_jap; j++) {
							japnext = szukanieWolnegoJap();
							fat[japnext] = -1;
							fat[jap_pr] = japnext;
							jap_pr = japnext;
							i = japnext * 64;
							l = i + 64;

							for (int i1 = 0; i1 < l; i1++) {
								if (k <= length - 1)
								{
									dysk[i1] = datachar1[k];
									k++;
								}
							}
						}
					}
					System.out.println("Wpisanie do pliku pomyslne"); 
				atrybuty[what_catalog(nazwa, ext)].rozmiar = data.length();

				}
				else System.out.println( "Za malo miejsca na dysku aby dopisac plik");
			}
			else  System.out.println( "Nie mozna nadpisac danych");
		}
		else System.out.println ("Blad dopisywania! Plik nie istnieje" );
		iloscWolnegoMiejsca();


	}

	public int wolneMiejsceDysk()
	{
		int count = 0;
		for (int i=0; i< 2048; i++)
		{
			if ((int)dysk[i]==0)
			{
				count ++;
			}
			else 
			{
				count =0;
			}
		}
		return count;
	}
	public void usuwaniePliku(String nazwa , String ext)
	{
		int jap = file_jap(nazwa,ext);
		int kolejnyJap;
		int buffor=1;
		int tab[] = new int[64];
		tab[0]=jap;
		while(jap != -1)
		{
			kolejnyJap = fat[jap];
			jap= kolejnyJap;
			tab[buffor] = kolejnyJap;
			buffor++;
		}
		buffor -= 1;
		
		if (nazwaIstnieje(nazwa ,ext) == false)
		{
			for (int i =0; i<buffor;i++)
				{
				int j=0;
				int k = j+64;
				for (j=64 * tab[i]; j < k; j++)
				{
					dysk[j] = 0;
				}
				fat[tab[i]] = 0;
		}
			int x =what_catalog(nazwa, ext);
				if(atrybuty[x].nazwa == nazwa && atrybuty[x].ext == ext)
				{
					atrybuty[x].nazwa ="";
					atrybuty[x].ext ="";
					atrybuty[x].rozmiar = 0;
					atrybuty[x].status = false;
					atrybuty[x].jap1 = 0;
					atrybuty[x].zapisany = false;
				}
				iloscWolnegoMiejsca();
				System.out.println("Usuwanie pliku przebieg�o pomy�lne");
		}
		else
			System.out.println("Plik nie istnieje");
			
		}	
			

	public void WyswietlaPliki()
	{
		int allsize = 0;
		int l = 0;
		System.out.println();
		System.out.println( "  Directory of root:");
		System.out.println();
		
		System.out.println("\t" +"Nazwa \t" +"\t" + "Rozszerzenie \t" + "Rozmiar " + "\t" + "Data \t" + "Godzina");
		for (int i = 1; i < 64; i++)
		{
			if (atrybuty[i].status == true)
			{
				System.out.println( "\t" + atrybuty[i].nazwa + " \t"+"\t"  + atrybuty[i].ext + "\t" +"\t" +"     " + atrybuty[i].rozmiar + "\t    " + atrybuty[i].rok + "-" + atrybuty[i].miesiac + "-" + atrybuty[i].dzien + "\t" + atrybuty[i].godzina +":"+ atrybuty[i].minuta);
				System.out.println();
				l++;
				allsize += atrybuty[i].rozmiar;
			}
		}
		if (dysk[3] == 'n')
		{
			System.out.println("Brak plikow w katalogu ");
		}
		System.out.println();
		int a = 1984-allsize;
		System.out.println("\t" + "\t" + l + " file(s)" + " \t" + allsize + " bytes");
		System.out.println( "\t" + "\t" + " " +"\t" +"\t"+ a + " bytes free");
		System.out.println();
	}

	public void iloscWolnegoMiejsca()
	{
		
		int free =0;
		for (int i = 0;i<64;i++)
		{
			if (fat[i] ==0)
			{
				
				free ++;
			}	
				
		}
		spacefree =  free * 64; 
	}

	public void drukujDysk(String nazwa, String ext)
{
	int jap = file_jap(nazwa,ext);
	int nextJap;
	int l=1;
	int[] tab = new int [64];
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
			int j = 64 *tab[i];
			int k =j+64;
			for(j= 64 *tab[i]; j<k;j++)
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

	public void zmianaNazwy(String nazwa, String ext, String newname, String newext)
	{
		if(nazwaIstnieje(newname, newext)==true)
		{
			if(file_jap(nazwa,ext)!=-1)
			{
				for(int i =0; i <64; i++)
				{
					if(atrybuty[i].nazwa == nazwa && atrybuty[i].ext == ext)
					{
						atrybuty[i].nazwa = newname;
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
	
	public void dopiszDoPliku(String nazwa, String ext, String data)
	{
		if(file_jap(nazwa,ext) != -1)
		{
			int jap = file_jap(nazwa,ext);
			int nextjap;
			int lastjap = 0;
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
			
			cat = what_catalog(nazwa,ext);
			how =wolnyJap(lastjap);
			
			lenght = data.length();
			count_jap = Math.ceil((lenght - how)/64);
			// wype�nianie starego japa
			
			if(atrybuty[cat].zapisany == true)
			{
				if (spacefree > (lenght - how))
				{
					atrybuty[what_catalog(nazwa, ext)].rozmiar += data.length();
					
					i = (lastjap *64) + (64 - how);
					k = i + how;
					l = 0;
					//
					//
					char datachar[] = data.toCharArray();
					for(i =(lastjap *64) + (64 - how); i<k;i++ )
					{
						if(l<lenght)
						{
							dysk[i] = datachar[l];
							l++;
						}
					}
					
					for(int j = 1; j <= count_jap; j++)
					{
						japnext = szukanieWolnegoJap();
						fat[japnext] = -1;
						fat[lastjap] = japnext;
						lastjap = japnext;
						i= japnext *64;
						k = i +64;
						//
						
						char datachar2[] = data.toCharArray();
						for(int z=japnext *64;z<k;z++)
						{
							if(l < lenght)
							{
								dysk[z] = datachar2 [l];
								l++;
							}
							
						}
					}
					System.out.println("Dopisanie pomy�lnie wykonano");
					iloscWolnegoMiejsca();
				}
				else
				{
					System.out.println("Za malo miejsca na dysku aby dopisac plik");
					iloscWolnegoMiejsca();
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

	public int wolnyJap(int nr_jap)
		{
			int i = nr_jap *64;
			int k = i +64;
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
	public int what_catalog(String nazwa , String ext)
		{
			for (int i =0; i<64; i++)
			{
				if (atrybuty[i].nazwa == nazwa && atrybuty[i].ext == ext)
				{
					return i;
				}
			}
			return -1;//-1
		}
	public int wolnyKatalog()
		{
			for (int i =0; i<64;i++)
			{
				if (atrybuty[i].status==false)
				{
					return i;
				}
				
			}
			return -1;
		}
		
	public boolean nazwaIstnieje(String nazwa, String ext)
		{
			for(int i = 0; i<64; i++)
			{
				if(atrybuty[i].nazwa == nazwa && atrybuty[i].ext == ext)
				{
					return false;
				}
			}
			return true;
		}

	public int szukanieWolnegoJap()
		{
			for (int i =0 ; i<64; i++)
			{
				if (fat[i]==0)
				{
					return i;
				}
			}
			return -1;
		}

	public void directory_entry()
		{
			System.out.println();
			System.out.println("  Directory of root:");
			System.out.println();
			
			
			System.out.println("\t" + " nazwa \t" + "Ext. \t" + "rozmiar \t" + "FJap \t" + "Write? \t" + "Numer");
			for (int i = 0; i < 64; i++)
			if (atrybuty[i].status == true)
			{
				System.out.println("\t" + atrybuty[i].nazwa + "\t" + atrybuty[i].ext + "\t" + atrybuty[i].rozmiar + "\t" +"\t" + atrybuty[i].jap1 + "\t" + atrybuty[i].zapisany + "\t" + i);
			}
			System.out.println();

		}
		
	}
	