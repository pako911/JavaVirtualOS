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

	public static void main(String[] args) {
		
		System.out.println(" @@@@   @@   @     @   @@      @@@   @@@   ");
		System.out.println("    @  @  @   @   @   @  @    @     @@ @@  ");
		System.out.println("    @  @@@@    @ @    @@@@     @@   @   @  ");
		System.out.println("  @ @  @  @    @ @    @  @       @  @@ @@  ");
		System.out.println("  @@@  @  @     @     @  @    @@@    @@@   ");
		System.out.println();
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		Disc disc = new Disc();
		Memory memory = new Memory();
		ProcessManager processManager = new ProcessManager(memory);
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
					
				} else if(line.split(" ")[0].equals("PDISK")) {
					disc.WyswietlaPliki();
					
				} else if(line.split(" ")[0].equals("PSEMAPHORE")) {
					System.out.println("PSEMAPHORE");
					
				} else if(line.split(" ")[0].equals("OPEN")) { // OPEN A.TXT
					String file = line.split(" ")[1];
					procesor.dodaj_proces(file);
					
				} else if(line.split(" ")[0].equals("KILL")) {
					System.out.println("KILL");
					
				}  else if(line.split(" ")[0].equals("EXIT")) {
					System.out.println("ZAMYKAM SYSTEM :)"); 
					System.exit(0);
					
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		
		}
	}
}
