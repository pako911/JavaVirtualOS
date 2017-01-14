package interpreter;

import dysk.Disc;
import memoryManagement.Memory;
import processManager.ProcessManager;
import komunikacjaMiedzyprocesowa.IPC
        
public class Interpreter {
	private int reg_A = 0, reg_B = 0, reg_C = 0, PC = 0;
	private Boolean done = false, working = false, fail = false, flag_F = false;
        private final Disc disk;
        private Memory memory;
	private ProcessManager manager;
        private IPC box;
        
	public Interpreter(int reg_A, int reg_B, int reg_C, int PC, Boolean done, Boolean working, Boolean fail, Memory memory, Disc disk, ProcessManager manager, IPC box) 
        {
		this.memory = memory;
		this.reg_A = reg_A;
		this.reg_B = reg_B;
		this.reg_C = reg_C;
		this.PC = PC;
		this.working = working;
		this.done = done;
		this.fail = fail;
                this.disk = disk;
                this.memory = memory;
                this.manager = manager;
                this.box = box;
	}

	public void set_regA(int a) {
		this.reg_A = a;
	}

	public void set_regB(int b) 
        {
		this.reg_B = b;
	}
	public void set_regC(int C) {
		this.reg_C = C;
	}

	public int get_regA() {
		return reg_A;
	}

	public int get_regB() {
		return reg_B;
	}

	public int get_regC() {
		return reg_C;
	}

	public int get_PC() {
		return PC;
	}

	public Boolean exe(String rozkaz[]) {
		this.done = false;
		this.working = false;
		this.fail = false;

		// Rozkazy arytmetyczne
		if (rozkaz[0].equals("ADD")) {
			if (rozkaz[1].equals("A")) {
				if (rozkaz[2].equals("B")) {
					reg_A += get_regB();
				} else if (rozkaz[2].equals("C")) {
					reg_A += get_regC();
				}
			} else if (rozkaz[1].equals("B")) {
				if (rozkaz[2].equals("A")) {
					reg_B += get_regA();
				} else if (rozkaz[2].equals("C")) {
					reg_B += get_regC();
				}
			} else {
				if (rozkaz[2].equals("A")) {
					reg_C += get_regA();
				} else if (rozkaz[2].equals("B")) {
					reg_C += get_regB();
				}
			}

			this.working = false;
			return true;

		} else if (rozkaz[0].equals("SUB")) {
			if (rozkaz[1].equals("A")) {
				if (rozkaz[2].equals("B")) {
					reg_A -= get_regB();
				} else if (rozkaz[2].equals("C")) {
					reg_A -= get_regC();
				}
			} else if (rozkaz[1].equals("B")) {
				if (rozkaz[2].equals("A")) {
					reg_B -= get_regA();
				} else if (rozkaz[2].equals("C")) {
					reg_B -= get_regC();
				}
			} else if (rozkaz[2].equals("A")) {
					reg_C -= get_regA();
				} else if (rozkaz[2].equals("B")) {
					reg_C -= get_regB();
				}
			

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("INC")) {
			if (rozkaz[1].equals("A")) {
				reg_A += 1;
			} else if (rozkaz[1].equals("B")) {
				reg_B += 1;
			} else {
				reg_C += 1;
			}

			this.working = false;
			return true;

		} else if (rozkaz[0].equals("DEC")) {
			if (rozkaz[1].equals("A")) {
				reg_A -= 1;
			} else if (rozkaz[1].equals("B")) {
				reg_B -= 1;
			} else {
				reg_C -= 1;
			}

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("SUB")) {
			if (rozkaz[1].equals("A")) {
				reg_A -= Integer.parseInt(rozkaz[1]);
			} else if (rozkaz[1].equals("B")) {
				reg_B -= Integer.parseInt(rozkaz[1]);
			} else {
				reg_C -= Integer.parseInt(rozkaz[1]);
			}

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("MUL")) {
			if (rozkaz[1].equals("A")) {
				if (rozkaz[2].equals("B")) {
					reg_A *= get_regB();
				} else if (rozkaz[2].equals("C")) {
					reg_A *= get_regC();
				}
			} else if (rozkaz[1].equals("B")) {
				if (rozkaz[2].equals("A")) {
					reg_B *= get_regA();
				} else if (rozkaz[2].equals("C")) {
					reg_B *= get_regC();
				}
			} else {
				if (rozkaz[2].equals("A")) {
					reg_C *= get_regA();
				} else if (rozkaz[2].equals("B")) {
					reg_C *= get_regB();
				}
			}

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("LOD")) {
			if (rozkaz[1].equals("A")) {
				reg_A = Integer.parseInt(rozkaz[2]);
			} else if (rozkaz[1].equals("B")) {
				reg_B = Integer.parseInt(rozkaz[2]);
			} else {
				reg_C = Integer.parseInt(rozkaz[2]);
			}

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("DIV")) {
			if (!rozkaz[2].equals("0")) {
				if (rozkaz[1].equals("A")) {
					reg_A /= Integer.parseInt(rozkaz[2]);
				} else if (rozkaz[1].equals("B")) {
					reg_B /= Integer.parseInt(rozkaz[2]);
				} else {
					reg_C /= Integer.parseInt(rozkaz[2]);
				}

				this.working = false;
				return true;
			} else {
				System.out.print("\n\nZle argumenty funkcji.\n\n");
				this.working = false;
				return false;
			}
		} else if (rozkaz[0].equals("MOV")) {
			System.out.println("MOV"); //To jest potrzebne?
			if (rozkaz[1].equals("A")) {
				if (rozkaz[2].equals("B")) {
					reg_A = reg_B;
				} else if (rozkaz[2].equals("C")) {
					reg_A = reg_C;
				} else {
					reg_A = Integer.parseInt(rozkaz[2]);
				}

			} else if (rozkaz[1].equals("B")) {
				if (rozkaz[2].equals("A")) {
					reg_B = reg_A;
				} else if (rozkaz[2].equals("C")) {
					reg_B = reg_C;
				} else {
					reg_B = Integer.parseInt(rozkaz[2]);
				}
			} else if (rozkaz[1].equals("A")) {
				reg_C = reg_A;
			} else if (rozkaz[1].equals("B")) {
				reg_C = reg_B;
			} else {
				reg_C = Integer.parseInt(rozkaz[2]);
			}

			this.working = false;
			return true;
		} else {
			System.out.print("\n");
		}

		if (rozkaz[0].equals("PAP")) {
			// PM.ls();
		} else if (rozkaz[0].equals("KIL")) {
			manager.kill(Integer.parseInt(rozkaz[1]));
		} else if (rozkaz[0].equals("FEK")) {
			/*
			 * if (rozkaz[2] != ""){ String bufor; String data; // std::fstream
			 * plik(rozkaz[2]);
			 * 
			 * while (!plik.eof()) { getline(plik, bufor); data += bufor; } int
			 * pom = bufor.size() % ROZMIAR; for (int i = 0; i < (ROZMIAR -
			 * pom); i++){ data += char(0); } BaseM =
			 * pamiec.znajdz_miejsce(data)*ROZMIAR; Limit =
			 * pamiec.wgraj_program(rozkaz[2]); }//sciezka if (BaseM != -1){
			 * 
			 * PM.fork(); Prioritylist.addToList(PM.pointertolastprocess());
			 * PM.setBaseandLimit(BaseM, Limit); PM.exec(regA, regB, regC,
			 * flaga_F); }
			 */
		} else {
			/*
			 * if (rozkaz[1] == "WTP"){ PM.waitpid(Integer.parseInt(rozkaz[2]));
			 * } else{ if (rozkaz[1] == "PPR"){
			 * 
			 * //Prioritylist.showReady(); //Prioritylist.showPriorityList(); }
			 * else{ if (rozkaz[1] == "STN"){
			 * pri.setNiceUp(Integer.parseInt(rozkaz[2])); } else{ if (rozkaz[1]
			 * == "PTP"){ PM.lstree(); } } } }
			 */
		}

		if (rozkaz[0].equals("DVM")) {
			// pamiec.wyswietl_pamiec_wirtualna();
		} else if (rozkaz[0].equals("DRM")) {
			memory.showMemory();
		}

		// Logiczne
		if (rozkaz[0].equals("EQL")) {
			if (rozkaz[1].equals("A") && rozkaz[2].equals("B") && reg_A == reg_B || rozkaz[1].equals("A") && rozkaz[2].equals("C") && reg_A == reg_C || rozkaz[1].equals("C") && rozkaz[2].equals("B") && reg_C == reg_B) {
				flag_F = true;
			} else
				flag_F = false;

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("JMP")) {
			PC = Integer.parseInt(rozkaz[1]);

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("JPT")) {
			if (this.flag_F = true) {
				PC = Integer.parseInt(rozkaz[1]);
			} else {
				// do nothing
			}

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("JPF")) {
			if (this.flag_F = false) {
				PC = Integer.parseInt(rozkaz[1]);
			} else {
				// do nothing
			}

			this.working = false;
			return true;
		} else {
			System.out.print("\n");
		}

		return done;

	}

	public Boolean filesExecute(String rozkaz[]) {
		this.done = false;
		this.working = false;
		this.fail = false;

		if (rozkaz[0].equals("FCR")) {
			disk.tworzeniaPliku(rozkaz[1], rozkaz[2]);
			this.working = false;
			return true;
		} else if (rozkaz[0].equals("FWR")) {
			System.out.println(rozkaz[1]+" "+rozkaz[2]+" "+rozkaz[3]);
			disk.wpisywanieDoPliku(rozkaz[1],rozkaz[2],rozkaz[3]);
			this.working = false;
			return true;
		} else if (rozkaz[0] == "FRD") {
			disk.wyswietlDanyPlik(rozkaz[1], rozkaz[2]);
			this.working = false;
			return true;
		} else if (rozkaz[0] == "FTR") {
                        //przycinanie
			this.working = false;
			return true;
		} else if (rozkaz[0].equals("FDL")) {
			disk.usuwaniePliku(rozkaz[1], rozkaz[2]);
			this.working = false;
			return true;
		} else if (rozkaz[0] == "FOP") {
			/*
			 * rozkaz[2] -> nazwa pliku do otwarcia rozkaz[3] -> tryb otwarcia
			 */
			// fm.openFile(fm.findFile(rozkaz[2]),
			// Integer.parseInt(rozkaz[3]));
                        //otwieranie
			this.working = false;
			return true;
		} else if (rozkaz[0] == "FCL") {
			/*
			 * rozkaz[2] -> nazwa pliku do zamkniecia
			 */
			// fm.closeFile(fm.findFile(rozkaz[2]));
                        //zamykanie
			this.working = false;
			return false;
		} else if (rozkaz[0] == "FLS") {
			/*
			 * rozkaz[2] -> nazwa pliku, w ktorym przesunac offset rozkaz[3] ->
			 * przesuniecie (ujemne w lewo, dodatnie w prawo): ";
			 */

			// fm.lseekFile(fm.findFile(rozkaz[2]),
			// Integer.parseInt(rozkaz[3]),
			// Integer.parseInt(rozkaz[4]));

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("FRN")) {	
			disk.zmianaNazwy(rozkaz[1],rozkaz[2],rozkaz[3],rozkaz[4]);
			this.working = false;
			return true;
		} else if (rozkaz[0].equals("FFN")) {
			
			//szukanie

			this.working = false;
			return true;
		} else if (rozkaz[0].equals("FLT")) {
			disk.wyswietlaPliki();
			this.working = false;
			return true;
		} else if(rozkaz[0].equals("FAD")){
                        disk.dopiszDoPliku(rozkaz[1], rozkaz[2], rozkaz[3]);
                        this.working = false;
                        return true;
                } else {
			System.out.print("\n");
		}

		return done;
	}
        
//        public Boolean semaphore(String rozkaz[]) {
//		this.done = false;
//		this.working = false;
//		this.fail = false;
//                
//                if(rozkaz[0].equals("SSH")){
//                    
//                } else if(rozkaz[0].equals("")){}
//                    
//               return done;
//        }
        
        public Boolean boxes(String rozkaz[]){
            this.done = false;
            this.working = false;
            this.fail = false;
            
            switch (rozkaz[0]) {
                case "XR":
                    box.odbierz(rozkaz[1]);
                    this.working = false;
                    return true;
                case "XS":
                    box.wyslij(rozkaz[1], rozkaz[2]);
                    this.working = false;
                    return true;
                case "XD":
                    box.usunSkrzynke(rozkaz[1]);
                    this.working = false;
                    return true;
                default:
                    break;
            }
            
            return done;
        }

	public void showRegisters() {
		System.out.print("Rejestry:");
		System.out.print("\nA: " + get_regA());
		System.out.print("\nB: " + get_regB());
		System.out.print("\nC: " + get_regC());
		System.out.print("\nPC: " + get_PC());
	}

	public Boolean get_flag_F() {
		return flag_F;
	}

	public void set_flag_F(Boolean i) {
		flag_F = i;
	}
}
