package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dysk.Disc;
import interpreter.Interpreter;
import komunikacjaMiedzyprocesowa.IPC;
import memory.Memory;
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
		Procesor procesor = new Procesor();
		//Interpreter interpreter = new Interpreter(reg_A, reg_B, reg_C, PC, done, working, fail);
		
		while(true) {
			try {
				String line = keyboard.readLine();	
				if(line.split(" ")[0].equals("STEP")) {
					System.out.println("STEP");
					
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
					System.out.println("PMEMORY");
					
				} else if(line.split(" ")[0].equals("PDISK")) {
					disc.directory_entry();
					
				} else if(line.split(" ")[0].equals("PSEMAPHORE")) {
					System.out.println("PSEMAPHORE");
					
				} else if(line.split(" ")[0].equals("OPEN")) { // OPEN A.TXT
					processManager.newProcess(line.split(" ")[1]);
					
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
