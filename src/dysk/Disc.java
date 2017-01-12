package dysk;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

import dysk.Tab_Fat;

public class Disc {

	

	
	public static final int FFFF = -1;
	public char[] dysk = new char [2048];				//dysk
	public int [] fat = new int [64];					//tablica fat 64 bity
	public Tab_Fat[] atrybuty = new Tab_Fat[64];		//wpis do katlogu glownego
	public int spacefree = 2048;						//wlne miejsce
	
	public Disc()
	{
		
		for(int i = 0; i < atrybuty.length; i ++) {
			atrybuty[i] = new Tab_Fat(); 
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
			atrybuty[i].zapisany = false;
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
			
			System.out.println("Plik zostal pomyslnie utworzony");
			
			iloscWolnegoMiejsca();
			
		}
		else if (wolnyKatalog() == -1)// sprawdz czy w fat
		{
			System.out.println("Katalog jest pelny");
		}
		else if (wolneMiejsceDysk() <64 )
		{
			System.out.println("Dysk jest pelny");
		}
		else 
			System.out.println("Istnieje juz plik o takiej nazwie");
	}

	int file_jap(String nazwa, String ext)
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
			System.out.print("Numer: ");
			System.out.print(i);
			System.out.print(" \t");
			System.out.print("Fat: ");
			if (fat[i]!=0)
			{
				if (fat[i]==-1){
				System.out.println("zajety");
				}else
				System.out.println(fat[i]);
			}
			else
				System.out.println(fat[i]);
			
		}
	}
	
	 public void wpisywanieDoPliku(String nazwa, String ext, String data)
	{
		
		int jap1;
		int nastepnyJap;
		double dlugosc;
		double count_jap;
		int k;
		dlugosc = data.length();
		count_jap = Math.ceil((dlugosc / 64));

		// Czy katalog wolny?  
		if (ktory_katalog(nazwa, ext) != -1){
		if (atrybuty[ktory_katalog(nazwa, ext)].zapisany == false) 
				atrybuty[ktory_katalog(nazwa, ext)].zapisany = true;
			if (file_jap(nazwa, ext) != -1) {
				if (spacefree > dlugosc) {
					jap1 = file_jap(nazwa, ext);
					k = 0;
					//DLA PIERWSZEGO 
					char datachar[] = data.toCharArray();
					for (int q = 0; q < jap1 * 64 + 64; q++) {
						if (k <= dlugosc - 1) {
							dysk[q] = datachar[k];
							k++;
						}
					}
					//DLA WIECEJ 
					char datachar1[] = data.toCharArray();
					if (count_jap > 1) {

						for (int j = 2; j <= count_jap; j++) {
							nastepnyJap = szukanieWolnegoJap();
							fat[nastepnyJap] = -1;
							fat[jap1] = nastepnyJap;
							jap1 = nastepnyJap;
							for (int i1 = 0; i1 < nastepnyJap * 64+ 64; i1++) {
								if (k <= dlugosc - 1)
								{
									dysk[i1] = datachar1[k];
									k++;
								}
							}
						}
					}
					System.out.println("Wpisanie do pliku pomyslne"); 
				atrybuty[ktory_katalog(nazwa, ext)].rozmiar = data.length();

				}
				else System.out.println( "Za malo miejsca na dysku");
			}
			else  System.out.println( "Nie mozna nadpisac danych");
		}
		else System.out.println ("Blad! Plik nie istnieje" );
		iloscWolnegoMiejsca();


	}

	int wolneMiejsceDysk()
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
			int x =ktory_katalog(nazwa, ext);
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
			

	public void wyswietlaPliki()
	{
		int allsize = 0;
		int l = 0;
		System.out.println();
		System.out.println( "  Directory of root:");
		System.out.println();
		
		System.out.printf("%15s" ,"Nazwa "  + "Rozszerzenie " + "Rozmiar " + "Pierwszy Jap " + "  " + "Data "+" \t "   +"Godzina");
		System.out.println();
		for (int i = 0; i < 64; i++)
		{
			if (atrybuty[i].status == true)
			{
				System.out.printf("%1$13s" , atrybuty[i].nazwa +  " \t "   + atrybuty[i].ext +" \t "  +" \t "  +  + atrybuty[i].rozmiar +"\t"   + atrybuty[i].jap1 +" \t "   + atrybuty[i].rok + "-" + atrybuty[i].miesiac + "-" + atrybuty[i].dzien + " \t "   + atrybuty[i].godzina +":"+ atrybuty[i].minuta);
				System.out.println();
				l++;
				allsize += atrybuty[i].rozmiar;
			}
		}
		System.out.println();
		int a = 2048-allsize;
		System.out.printf("%1$13s" , "\t"+"\t"+"\t"+ l + " plik(i)" + " \t" + allsize + " bajty");
		System.out.println();
		System.out.printf("%1$13s" ,  "\t"+"\t"+"\t"+"\t"+ a + " wolne bajty");
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
		
		for ( int i =0 ;i<l;i++)
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
			int ostatniJap = 0;
			int l;
			int how;
			double lenght;
			int nastepnyJap;
			double count_jap;
			
			while(jap != -1)
			{
				nextjap = fat[jap];
				
				if(nextjap == -1)
				{
					ostatniJap = jap;
				}
				jap = nextjap;
			}
			
			how =wolnyJap(ostatniJap);
			
			lenght = data.length();
			count_jap = Math.ceil((lenght - how)/64);
			if(atrybuty[ktory_katalog(nazwa,ext)].zapisany == true)
			{
				if (spacefree > (lenght - how))
				{
					atrybuty[ktory_katalog(nazwa, ext)].rozmiar += data.length();
					
					l = 0;
					char datachar[] = data.toCharArray();
					for( int i =(ostatniJap *64) + (64 - how); i<(ostatniJap *64) + (64 - how) + how;i++ )
					{
						if(l<lenght)
						{
							dysk[i] = datachar[l];
							l++;
						}
					}
					for(int j = 1; j <= count_jap; j++)
					{
						nastepnyJap = szukanieWolnegoJap();
						fat[nastepnyJap] = -1;
						fat[ostatniJap] = nastepnyJap;
						ostatniJap = nastepnyJap;
						//
						
						char datachar2[] = data.toCharArray();
						for(int z=nastepnyJap *64;z<nastepnyJap *64 +64;z++)
						{
							if(l < lenght)
							{
								dysk[z] = datachar2 [l];
								l++;
							}
							
						}
					}
					System.out.println("Dopisanie pomyslnie wykonano");
					iloscWolnegoMiejsca();
				}
				else
				{
					System.out.println("Za malo miejsca na dysku");
					iloscWolnegoMiejsca();
				}
			}
			else 
			{
				System.out.println("Plik nie zostal wypelniony");
			}
		}
		else 
		{
			System.out.println("Plik o podanej nazwie nie istnieje");
		}
	
		}

		int wolnyJap(int nr_jap)
		{
			int l = 0; 
			for (int j =0; j<nr_jap *64 +64;j++)
			{
				if (dysk[j]== 0)
				{
					l++;
				}
			}
			return l;
		}
		int ktory_katalog(String nazwa , String ext)
		{
			for (int i =0; i<64; i++)
			{
				if (atrybuty[i].nazwa == nazwa && atrybuty[i].ext == ext)
				{
					return i;
				}
			}
			return -1;
		}
		int wolnyKatalog()
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
		
		Boolean nazwaIstnieje(String nazwa, String ext)
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

		int szukanieWolnegoJap()
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

	}
	