package interpreter;


public class Interpreter {
	private int reg_A = 0, reg_B = 0, reg_C = 0, PC = 0;
	private Boolean done = false, working = false, fail = false, flag_F = false;
        private Disc disk;
        private Memory memory;

	public Interpreter(int reg_A, int reg_B, int reg_C, int PC, Boolean done, Boolean working, Boolean fail, Memory memory, Disc disk) 
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
	}

	public void set_regA(int a) {
		this.reg_A = a;
	}

	public void set_regB(int b) 
        {
		this.reg_B = b;
	}
	public void set_regC(int c) {
		this.reg_C = c;
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
		if (rozkaz[0] == "ADD") {
			if (rozkaz[1] == "A") {
				reg_A += Integer.parseInt(rozkaz[2]);
			} else if (rozkaz[1] == "B") {
				reg_B += Integer.parseInt(rozkaz[2]);
			} else {
				reg_C += Integer.parseInt(rozkaz[2]);
			}

			this.working = false;
			return true;

		} else if (rozkaz[0] == "SUB") {
			if (rozkaz[1] == "A") {
				reg_A -= Integer.parseInt(rozkaz[2]);
			} else if (rozkaz[0] == "B") {
				reg_B -= Integer.parseInt(rozkaz[2]);
			} else {
				reg_C -= Integer.parseInt(rozkaz[2]);
			}

			this.working = false;
			return true;
		} else if (rozkaz[0] == "INC") {
			if (rozkaz[1] == "A") {
				reg_A += 1;
			} else if (rozkaz[1] == "B") {
				reg_B += 1;
			} else {
				reg_C += 1;
			}

			this.working = false;
			return true;

		} else if (rozkaz[0] == "DEC") {
			if (rozkaz[1] == "A") {
				reg_A -= 1;
			} else if (rozkaz[1] == "B") {
				reg_B -= 1;
			} else {
				reg_C -= 1;
			}

			this.working = false;
			return true;
		} else if (rozkaz[0] == "SUB") {
			if (rozkaz[1] == "A") {
				reg_A -= Integer.parseInt(rozkaz[2]);
			} else if (rozkaz[0] == "B") {
				reg_B -= Integer.parseInt(rozkaz[2]);
			} else {
				reg_C -= Integer.parseInt(rozkaz[2]);
			}

			this.working = false;
			return true;
		} else if (rozkaz[0] == "MUL") {
			if (rozkaz[1] == "A") {
				reg_A *= Integer.parseInt(rozkaz[2]);
			} else if (rozkaz[1] == "B") {
				reg_B *= Integer.parseInt(rozkaz[2]);
			} else {
				reg_C *= Integer.parseInt(rozkaz[2]);
			}

			this.working = false;
			return true;
		} else if (rozkaz[0] == "LOD") {
			if (rozkaz[1] == "A") {
				reg_A = Integer.parseInt(rozkaz[2]);
			} else if (rozkaz[1] == "B") {
				reg_B = Integer.parseInt(rozkaz[2]);
			} else {
				reg_C = Integer.parseInt(rozkaz[2]);
			}

			this.working = false;
			return true;
		} else if (rozkaz[0] == "DIV") {
			if (rozkaz[2] != "0") {
				if (rozkaz[1] == "A") {
					reg_A /= Integer.parseInt(rozkaz[2]);
				} else if (rozkaz[1] == "B") {
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
			if (rozkaz[1] == "A") {
				if (rozkaz[1] == "B") {
					reg_A = reg_B;
				} else if (rozkaz[1] == "C") {
					reg_A = reg_C;
				} else {
					reg_A = Integer.parseInt(rozkaz[2]);
				}

			} else if (rozkaz[1] == "B") {
				if (rozkaz[1] == "A") {
					reg_B = reg_A;
				} else if (rozkaz[1] == "C") {
					reg_B = reg_C;
				} else {
					reg_B = Integer.parseInt(rozkaz[2]);
				}
			} else if (rozkaz[1] == "A") {
				reg_C = reg_A;
			} else if (rozkaz[1] == "B") {
				reg_C = reg_B;
			} else {
				reg_C = Integer.parseInt(rozkaz[2]);
			}

			this.working = false;
			return true;
		} else {
			System.out.print("\n");
		}

		if (rozkaz[0] == "PAP") {
			// PM.ls();
		} else if (rozkaz[0] == "KIL") {
			// PM.kill(Integer.parseInt(rozkaz[2]));
		} else if (rozkaz[0] == "FEK") {
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

		if (rozkaz[0] == "DVM") {
			// pamiec.wyswietl_pamiec_wirtualna();
		} else if (rozkaz[0] == "DRM") {
			memory.showMemory();
		}

		// Logiczne
		if (rozkaz[0] == "EQL") {
			if (rozkaz[1] == rozkaz[2]) {
				flag_F = true;
			} else
				flag_F = false;

			this.working = false;
			return true;
		} else if (rozkaz[0] == "JMP") {
			PC = Integer.parseInt(rozkaz[1]);

			this.working = false;
			return true;
		} else if (rozkaz[0] == "JPT") {
			if (this.flag_F = true) {
				PC = Integer.parseInt(rozkaz[1]);
			} else {
				// do nothing
			}

			this.working = false;
			return true;
		} else if (rozkaz[0] == "JPF") {
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

	public Boolean execute(String rozkaz[]) {
		this.done = false;
		this.working = false;
		this.fail = false;

		if (rozkaz[0] == "FCR") {
			disk.tworzeniaPliku(rozkaz[1], rozkaz[2]);

			this.working = false;
			return true;
		} else if (rozkaz[0] == "FWR") {
			/*
			 * rozkaz[2] -> nazwa pliku do ktorego chcesz zapisac rozkaz[3] ->
			 * co chcesz zapisac
			 */
			// fm.writeFile(fm.findFile(rozkaz[2]), rozkaz[3]);

			this.working = false;
			return true;
		} else if (rozkaz[0] == "FRD") {
			/*
			 * rozkaz[2] -> nazwe pliku, z ktorego mam czytac rozkaz[3] -> ilosc
			 * znakow do odczytania
			 */
			// String buff;
			// fm.readFile(fm.findFile(rozkaz[2]), buff,
			// Integer.parseInt(rozkaz[3]));
			// System.out.print(buff);
			this.working = false;
			return true;
		} else if (rozkaz[0] == "FTR") {
			/*
			 * rozkaz[2] -> nazwa pliku do przyciecia rozkaz[3] -> Ile znakow w
			 * pliku zachowac
			 */
			// fm.truncateFile(rozkaz[2], Integer.parseInt(rozkaz[3]));

			this.working = false;
			return true;
		} else if (rozkaz[0] == "FDL") {
			/*
			 * rozkaz[2] -> nazwa pliku do usuniecia
			 */
			// fm.deleteFile(rozkaz[2]);

			this.working = false;
			return true;
		} else if (rozkaz[0] == "FOP") {
			/*
			 * rozkaz[2] -> nazwa pliku do otwarcia rozkaz[3] -> tryb otwarcia
			 */
			// fm.openFile(fm.findFile(rozkaz[2]),
			// Integer.parseInt(rozkaz[3]));

			this.working = false;
			return true;
		} else if (rozkaz[0] == "FCL") {
			/*
			 * rozkaz[2] -> nazwa pliku do zamkniecia
			 */
			// fm.closeFile(fm.findFile(rozkaz[2]));

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
		} else if (rozkaz[0] == "FRN") {
			/*
			 * rozkaz[2] -> Nazwa pliku, ktory chcesz zmienic rozkaz[3] -> Na
			 * jaka
			 */

			// fm.renameFile(rozkaz[2], rozkaz[3]);

			this.working = false;
			return true;
		} else if (rozkaz[0] == "FFN") {
			/*
			 * rozkaz[2] -> nazwa szukanego pliku
			 */
			// fm.findFile(rozkaz[2]);

			this.working = false;
			return true;
		} else if (rozkaz[0] == "FLT") {
			// fm.listFiles();

			this.working = false;
			return true;
		} else {
			System.out.print("\n");
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
}
