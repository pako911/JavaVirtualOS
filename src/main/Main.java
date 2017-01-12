package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dysk.Disc;
import komunikacjaMiedzyprocesowa.IPC;
import memoryManagement.Memory;
import procesor.Procesor;
import processManager.ProcessManager;

public class Main {

	public static void load(String co) {
		System.out.print(co);
		/*String loading = " [=========================]"; 
		char[] loadingc = loading.toCharArray();
		for(int i = 0; i<loading.length();i++) {
			System.out.print(loadingc[i]);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	*/
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		System.out.println(" @@@@   @@   @     @   @@      @@@   @@@   ");
		System.out.println("    @  @  @   @   @   @  @    @     @@ @@  ");
		System.out.println("    @  @@@@    @ @    @@@@     @@   @   @  ");
		System.out.println("  @ @  @  @    @ @    @  @       @  @@ @@  ");
		System.out.println("  @@@  @  @     @     @  @    @@@    @@@   ");
		System.out.println();

		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

		load("ŁADOWANIE MODUŁU PAMIĘCI OPERACYJNEJ");
		Memory memory = new Memory();		
		load("ŁADOWANIE MODUŁU DYSKU");
		Disc disc = new Disc();
		load("ŁADOWANIE MODUŁU MENADŻERA PROCESÓW");
		ProcessManager processManager = new ProcessManager(memory);
		load("ŁADOWANIE MODUŁU PROCESORA");
		Procesor procesor = new Procesor(processManager);
		//Interpreter interpreter = new Interpreter(reg_A, reg_B, reg_C, PC, done, working, fail);
		
		while(true) {
			try {
				String line = keyboard.readLine();	
				if(line.split(" ")[0].equals("STEP")) {
					procesor.wykonaj();
				} else if(line.split(" ")[0].equals("HELP")) {
					System.out.println("HELP");
					
				} else if(line.split(" ")[0].equals("PPROCES")) {
					processManager.ps();
					
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
					disc.WyswietlaPliki();
					
				} else if(line.split(" ")[0].equals("PSEMAPHORE")) {
					System.out.println("PSEMAPHORE");
					
				} else if(line.split(" ")[0].equals("OPEN")) { // OPEN A.TXT
					String file = line.split(" ")[1];
					procesor.dodaj_proces(file);
					
				} else if(line.split(" ")[0].equals("KILL")) {
					System.out.println("KILL");
					
				} else if(line.split(" ")[0].equals("EXIT")) {
					System.out.println("ZAMYKAM SYSTEM :)"); 
					System.exit(0);
					
				} else if(line.split(" ")[0].equals("ABOUT")) {
					String kto = "SYSTEM TWORZYLI\nMarek Wojciechowski\nPrzemysław Łapicz\nMarek Rybicki\nPaweł Korczak\nMateusz Norel\nLucyna Hernet\nMateusz Terlecki\nPiotr Popiołek\nKuba Rybakowski\n"; 
					char[] ktoc = kto.toCharArray();
					for(int i = 0; i<kto.length();i++) {
						System.out.print(ktoc[i]);
						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		}
	}
}
