package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import dysk.Disc;
import interpreter.Interpreter;
import komunikacjaMiedzyprocesowa.IPC;
import memoryManagement.Memory;
import procesor.Procesor;
import processManager.PCB;
import processManager.ProcessManager;

public class Main {

	private static Boolean working;
	private static Boolean fail;
	private static Boolean done;
	private static int reg_A;
	private static int reg_C;
	private static int reg_B;
	private static int PC;
	
	public static void load(String co) {
		char[] loadingc = co.toCharArray();
		for(int i = 0; i<co.length();i++) {
			System.out.print(loadingc[i]);
		}	
		System.out.println();
	}
	
	public static void main(String[] args) {
						load("	     /@@#  #@# ,@@@@@@@*                                                                            \n"
							+ "	    @    #@    @         %&                                                                         \n"
							+ "	   @ #@@ @  @#  @          *%                                                                       \n"
							+ "	   ( @@@ & @@@  &            @                                                                      \n"
							+ "	   @ @@% & @@@  @             @                                                                     \n"
							+ "	   #     @     %               @                                                                    \n"
							+ "	   (.@@@( /@#@@                .#                                                                   \n"
							+ "	  /.                            @                                                                   \n"
							+ "	  %                    @        ./                                                                  \n"
							+ "	 @                     (.&       @                                                                  \n"
							+ "	 %                      #@       @                                                                  \n"
							+ "	 ,                      @.       @                                                                  \n"
							+ "	                       .%        @                                                                  \n"
							+ "	 ,                     #,        &                                                                  \n"
							+ "	 &                     @        .,                                                                  \n"
							+ "	 *.                   &.        &                 /@@@%%%%@@                                        \n"
							+ "	  @.                 %*         %             &@.           @(                                      \n"
							+ "	   *&               @          /           .@               @ .@                                    \n"
							+ "	     %%           @(           &          @                 @   .@                                  \n"
							+ "	        %@%..,&@@             #          (                 .(     @                                 \n"
							+ "	             %                %         #                  ,/    @ &.                               \n"
							+ "	             @               @          @@(                //   %   @                               \n"
							+ "	             @.              %         @   @.              #,  @     @       ,@.    %@              \n"
							+ "	            (  /@&.    .#@  @         *,    (/             %  ,      %.     @     @.  .&            \n"
							+ "	            @               #         @      ,#            @  &       %   .(     #      @           \n"
							+ "	            @              &          @       ((          &  ,       @@   @/,%@ @&       &          \n"
							+ "	            @              @         .* ,@     &       &@&&  ,      @ @  #     @ /, ,,.  *          \n"
							+ "	            &             ..         &     @    @,#@&      %       %  @  @     ,(,(     & (         \n"
							+ "	            %             /          @       ( .*          @       #  %  %    *@*       %           \n"
							+ "	            @@@       %/  .(        &        ..@           //     @   (. .%      %        %         \n"
							+ "	            @              @       & /%        @            @     (    @  @     .@,      *          \n"
							+ "	            /,              %%   #@    /(     .(            (    (     @%  @   &/  //    @@@@@&%   @\n"
							+ "	             @              .            @    /              @         % %@#@@%      %  .%@    &   ,\n"
							+ "	             &             @              &   @              #.        .     #,         @ *    #   @\n"
							+ "	              &           @               ,  (                @       @       @        @        & @ \n"
							+ "	              /,        *&                 (,/                 @     /        ,      .&   /    .@   \n"
							+ "	               &.     #@           (       ,(                   @                   @. &@%@@@@@@@%(.\n"
							+ "	                (@@@/             ..      @,                     /@              ,@@@,     %%*      \n"
							+ "	                  @               @     #@@@&                      .@@#*.    ,&@@@@@@@              \n"
							+ "	                   /@            ./   @@&(.                               (, #(.                    \n"
							+ "	                      @@%       .@,@@@@@%                                                           \n"
							+ "	                            (*,*,,,,                                                                \n"
							+ "                                                                                                     \n"
							+ "                                     @@@@   @@   @     @   @@      @@@   @@@                         \n"
							+ "                                        @  @  @   @   @   @  @    @     @@ @@                        \n"
							+ "                                        @  @@@@    @ @    @@@@     @@   @   @                        \n"
							+ "                                      @ @  @  @    @ @    @  @       @  @@ @@                        \n"
							+ "                                      @@@  @  @     @     @  @    @@@    @@@                         \n");

		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<PCB> listaOczekujacych=new ArrayList<PCB>();
		load("ŁADOWANIE MODUŁU PAMIĘCI OPERACYJNEJ");
		Memory memory = new Memory(listaOczekujacych);		
		load("ŁADOWANIE MODUŁU DYSKU");
		Disc disc = new Disc();
		load("ŁADOWANIE MODUŁU MENADŻERA PROCESÓW");
		ProcessManager processManager = new ProcessManager(memory);
		load("ŁADOWANIE MODUŁU PROCESORA");
		Interpreter interpreter = new Interpreter(memory, disc, processManager);
		Procesor procesor = new Procesor(processManager, interpreter);

		
		while(true) {
			System.out.print("javaOS:$ ");
			try {
				String line = keyboard.readLine();	
				if(line.split(" ")[0].equals("STEP")) {
					procesor.wykonaj();
				} else if(line.split(" ")[0].equals("HELP")) {
					System.out.println("HELP - Wyświetla ten tekst");
					System.out.println("PPROCES - Wyświetla procesy w systemie");
					System.out.println("PBOXYS - Wyświetla szkrzynki w systemie");
					System.out.println("PMEMORY - Wyświetla stan pamięci");
					System.out.println("PFAT - Wyświetla stan tablicy Fat");
					System.out.println("PDISK - Wyświetla stan dysku");
					System.out.println("OPEN <PLIK> - Otwiera plik z kodem programu");
					System.out.println("EXIT - Kończy prace systemu");
					System.out.println("ABOUT - Autorzy");
					
				} else if(line.split(" ")[0].equals("PPROCES")) {
					processManager.ps();
					procesor.wyswietl_liste_procesow_gotowych();
				} else if(line.split(" ")[0].equals("PBOXYS")) {
					try {
						IPC.wszystkieSkrzynki();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				} else if(line.split(" ")[0].equals("PMEMORY")) {
					memory.showMemory();
					
				} else if(line.split(" ")[0].equals("PFAT")) {
					disc.wys();
					
				} else if(line.split(" ")[0].equals("PDISK")) {
					disc.wyswietlaPliki();
<<<<<<< HEAD
				} else if(line.split(" ")[0].equals("UDISK")) {
					disc.tworzeniaPliku( name, ext);
				} else if(line.split(" ")[0].equals("DDISK")) {
					disc.usuwaniePliku(nazwa, ext);
				} else if(line.split(" ")[0].equals("ZNDISK")) {
					disc.zmianaNazwy(nazwa, ext, newname, newext);
				} else if(line.split(" ")[0].equals("DODISC")) {
					disc.dopiszDoPliku(nazwa, ext, data);
				} else if(line.split(" ")[0].equals("PSEMAPHORE")) {
					System.out.println("PSEMAPHORE");
=======
					
>>>>>>> origin/master
				} else if(line.split(" ")[0].equals("OPEN")) { // OPEN A.TXT
					String file = line.split(" ")[1];
					procesor.dodaj_proces(file);
					
<<<<<<< HEAD
				} else if(line.split(" ")[0].equals("KILL")) {
					System.out.println("KILL");	} 
				else if(line.split(" ")[0].equals("")) {
						System.out.println("HELP");
=======
>>>>>>> origin/master
				} else if(line.split(" ")[0].equals("EXIT")) {
					System.out.println("ZAMYKAM SYSTEM :)"); 
					System.exit(0);
					
				} else if(line.split(" ")[0].equals("ABOUT")) {
					String kto = "SYSTEM TWORZYLI\n"
							   + "Marek Wojciechowski\n"
							   + "Przemysław Łapicz\n"
							   + "Marek Rybicki\n"
							   + "Paweł Korczak\n"
							   + "Mateusz Norel\n"
							   + "Lucyna Hernet\n"
							   + "Mateusz Terlecki\n"
							   + "Piotr Popiołek\n"
							   + "Kuba Rybakowski\n"
							   + "   \n"
							   + "   \n"
							   + "   \n"
							   + "Miłego dnia :)\n";
					char[] ktoc = kto.toCharArray();
					for(int i = 0; i<kto.length();i++) {
						System.out.print(ktoc[i]);
						try {
							Thread.sleep(5);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}					
				} else {
					System.out.println("NIE MA TAKIEJ KOMENDY");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		}
	}
}
