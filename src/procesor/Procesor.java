package procesor;

import java.util.ArrayList;

import interpreter.Interpreter;
import processManager.PCB;
import processManager.PCB.Stany;
import processManager.Process;
import processManager.ProcessManager;

public class Procesor {

	private ArrayList<PCB> lista_procesow_gotowych = new ArrayList<PCB>();
	private ArrayList<PCB> lista_procesow_gotowych2 = new ArrayList<PCB>();

	public PCB Running;
	public boolean czy_wywlaszczajacy;// wybor metody przydzialu procesora
										// sjf/srt(wywlaszczająca), ustawiane po
										// dodaniu nowego procesu
	public PCB proces;
	public int temp;
	public double alpha;
	//public int theta;
	public int time;
	private ProcessManager processManager;
	private Interpreter interpreter;

	public Procesor(ProcessManager processManager, Interpreter interpreter) {
		this.interpreter = interpreter;
		this.processManager = processManager;
		alpha = 0.5;
		Running = processManager.getMain().pcb;
		//theta = 10;
		time = 0;
	}

	public void dodaj_proces(String file) {
		int PID = processManager.newProcess(file);
		proces = processManager.getProces(PID).pcb;
		//proces.state = Stany.GOTOWY;
		lista_procesow_gotowych.add(proces);
	}

	public void Scheduler() {
		if (Running != null && Running.state == Stany.ZAKONCZONY) {
			time = Running.timer;
			processManager.kill(Running.PID);
			Running = processManager.getMain().pcb;
		}

		if(Running!=null && Running.state!=Stany.AKTYWNY)
		{
		//if (true) {
			if (lista_procesow_gotowych.size() > 0)
			// if(true)
			{
				boolean bezpiecznik = false;
				for (int i = 0; i < lista_procesow_gotowych.size(); i++) {
					PCB p = lista_procesow_gotowych.get(i);
					PCB p2;
					if (time == 0) {
						p.thau = 10;
						lista_procesow_gotowych2.add(p);
					} else {
						if (bezpiecznik == false) {
							p.thau = (int) alpha * time + (1 - alpha) * Running.thau;
							bezpiecznik = true;
						} else {
							p2 = lista_procesow_gotowych2.get(lista_procesow_gotowych2.size() - 1);
							p.thau = (int) alpha * time + (1 - alpha) * p2.thau;
							lista_procesow_gotowych2.add(p);
						}

					}
				}
				bezpiecznik = false;
				for (int iterator = lista_procesow_gotowych.size() - 1; iterator >= 0; iterator--) {
					lista_procesow_gotowych.remove(iterator);
				}

			} else {
				if (!(lista_procesow_gotowych2.size() > 0)) {
					System.out.println("Nie ma zadnego procesu na liscie.");
				}
				Running = processManager.getMain().pcb;
			}

			int index_nastepnego = 0;

			if (lista_procesow_gotowych2.size() > 0) {
				PCB nastepny = lista_procesow_gotowych2.get(0);
				//System.out.println();
				for (int i = 0; i < lista_procesow_gotowych2.size(); i++) {
					PCB ptemp = lista_procesow_gotowych2.get(i);
					System.out.println(ptemp.thau);
					if (ptemp.thau < nastepny.thau) {
						nastepny = ptemp;
						index_nastepnego = i;
					}
				}
				lista_procesow_gotowych2.remove(index_nastepnego);
				Running = nastepny;
				Running.state = Stany.AKTYWNY;

			}

		}

	}

	public void wykonaj() {
		Scheduler();
		if(Running != null && Running.PID != 0) {
			Process process = processManager.getProces(Running.PID);
			String rozkaz[] = process.getNextRozkaz();
			
			interpreter.set_regA(process.pcb.A);
			interpreter.set_regB(process.pcb.B);
			interpreter.set_regC(process.pcb.C);
			interpreter.set_PC(process.pcb.counter);
			interpreter.set_flag_F(process.pcb.flag_F);
			
			interpreter.exe(rozkaz, Running.PID);
			Running.A = interpreter.get_regA();
			Running.B = interpreter.get_regB();
			Running.C = interpreter.get_regC();
			Running.counter = interpreter.get_PC();
			Running.flag_F = interpreter.get_flag_F();
			
			System.out.println("PID: " + Running.PID);
			interpreter.showRegisters();
		}

		/*
		 * if(Running!=null) { Random r=new Random(); int probability;
		 * probability=r.nextInt(100)+1;
		 * 
		 * if(probability<=15) { przelacz_proces();
		 * //System.out.println("Proces o PID: "+Running.get_PID()+
		 * " zostal wykonany, time : "+Running.time); }
		 * 
		 * while(probability>15) { probability=r.nextInt(100)+1;
		 * 
		 * if(probability<=15) { przelacz_proces();
		 * System.out.println(probability);
		 * //System.out.println("Proces o PID: "+Running.get_PID()+
		 * " zostal wykonany, time: "+Running.time); } else { theta++; } } }
		 */
	}

	public void wyswietl_liste_procesow_gotowych() {
		System.out.println("Lista procesow gotowych");
		for (PCB proces : lista_procesow_gotowych) {
			System.out.println("-------------------------");
			System.out.println("PID " + proces.PID);
			System.out.println("-------------------------");
		}
		for (PCB proces : lista_procesow_gotowych2) {
			System.out.println("------------2-------------");
			System.out.println("PID " + proces.PID);
			System.out.println("------------2-------------");
		}
	}

}
