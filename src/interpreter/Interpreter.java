package interpreter;

import java.util.Iterator;
import java.util.Vector;
import java.util.regex.Pattern;

public class Interpreter {
    private int reg_A=0, reg_B=0, reg_C=0, PC=0;
    private Boolean done = false, working = false, fail = false; 
    private Vector<String> commandList;
    
    public Interpreter(int reg_A, int reg_B, int reg_C, int PC, Boolean done, Boolean working, Boolean fail){
        this.reg_A = reg_A;
        this.reg_B = reg_B;
        this.reg_C = reg_C;
        this.PC = PC;
        this.working = working;
        this.done = done;
        this.fail = fail;
    }
   /* public void set_regA(int reg_A){
        this.reg_A = 0;
    }
    public void set_regB(int reg_B){
        this.reg_B = 0;
    }
    public void set_regC(int reg_C){
        this.reg_C = 0;
    }*/
    public int get_regA(){
        return reg_A;
    }
    public int get_regB(){
        return reg_B;
    }
    public int get_regC(){
        return reg_C;
    }
     public int get_PC(){
        return PC;
    }
      public void set_list(Vector<String> commandList){
        
        this.commandList.add("MOV");
        this.commandList.add("DEC");
    }
    Boolean exe(String command){
        this.done = false;
        this.working = false;
        this.fail = false;
        
        /*transform(rozkaz_org.begin(), rozkaz_org.end(), rozkaz_org.begin(), ::toupper);

	regex abc("([A-Z][A-Z][A-Z]) ?([A-Z0-9]*) ?([A-Z0-9]*) ?([A-Z0-9]*)");
	smatch rozkaz;
	regex_search(rozkaz_org, rozkaz, abc);

	std::cout << rozkaz[1] << " " << rozkaz[2] << " " << rozkaz[3] << " " << rozkaz[4] << endl;
        */
//	auto i = find(_lista_rozkazow.begin(), _lista_rozkazow.end(), rozkaz[1]);
       
        String pattern = "([A-Z][A-Z][A-Z]) ?([A-Z0-9]*) ?([A-Z0-9]*) ?([A-Z0-9]*)";
        Pattern r = Pattern.compile(pattern);
//        Matcher m = r.matcher(line);
        
        Iterator it = commandList.iterator();
        if(it.hasNext()){
            this.working = true;
            PC++;
            
            //Rozkazy arytmetyczne
            
            if(rozkaz[1] == "ADD"){
		if (rozkaz[2] == "A"){
                    reg_A += Integer.parseInt(rozkaz[3]);
		}
                else{
                   if (rozkaz[2] == "B"){
			reg_B += Integer.parseInt(rozkaz[3]);
                    }
                    else{
			reg_C += Integer.parseInt(rozkaz[3]);
			}

                    this.working = false;
                    return true;
		}
            }
            else{
		if(rozkaz[1] == "SUB"){
                    if (rozkaz[2] == "A"){
			reg_A -= Integer.parseInt(rozkaz[3]);
                    }
                    else{
                        if (rozkaz[2] == "B"){
                            reg_B -= Integer.parseInt(rozkaz[3]);
                        }
			else{
                            reg_C -= Integer.parseInt(rozkaz[3]);
			}
                    }

                    this.working = false;
                    return true;
		}
		else{
                    if (rozkaz[1] == "INC")
                    {
			if (rozkaz[2] == "A"){
                            reg_A += 1;
			}
			else{
                            if (rozkaz[2] == "B"){
				reg_B += 1;
                            }
                            else{
				reg_C += 1;
                            }

                            this.working = false;
                            return true;
			}
                    }
                    else{
                        if (rozkaz[1] == "DEC"){
                            if (rozkaz[2] == "A"){
				reg_A -= 1;
                            }
			else{
                            if (rozkaz[2] == "B"){
				reg_B -= 1;
                            }
                            else{
				reg_C -= 1;
                            }
			}

			this.working = false;
			return true;
                        }
                        else{
                            if(rozkaz[1] == "SUB"){
                                if (rozkaz[2] == "A"){
                                    reg_A -= Integer.parseInt(rozkaz[3]);
				}
				else{
                                    if (rozkaz[2] == "B"){
					regB -= Integer.parseInt(rozkaz[3]);
                                    }
                                    else{	
                                        reg_C -= Integer.parseInt(rozkaz[3]);
                                    }
				}

				this.working = false;
				return true;
                            }
                            else{
				if (rozkaz[1] == "MUL"){
                                    if (rozkaz[2] == "A"){
					reg_A *= Integer.parseInt(rozkaz[3]);
                                    }
				else{
                                    if (rozkaz[2] == "B"){
					regB *= Integer.parseInt(rozkaz[3]);
                                    }
                                    else{
					regC *= Integer.parseInt(rozkaz[3]);
                                    }
				}

				this.working = false;
				return true;
				}
				else{
                                    if (rozkaz[1] == "LOD"){
					if (rozkaz[2] == "A"){
                                            reg_A = Integer.parseInt(rozkaz[3]);
					}
					else{
                                            if (rozkaz[2] == "B"){
                                                reg_B = Integer.parseInt(rozkaz[3]);
                                            }
					else{
                                            regC = Integer.parseInt(rozkaz[3]);
                                            }
					}

					this.working = false;
					return true;
                                    }
                                    else{
					if (rozkaz[1] == "DIV"){
                                            if (rozkaz[3] != "0"){
						if (rozkaz[2] == "A"){
                                                    reg_A /= Integer.parseInt(rozkaz[3]);
                                                }
                                                else{
                                                    if (rozkaz[2] == "B"){
                                                        reg_B /= Integer.parseInt(rozkaz[3]);
                                                    }
                                                    else{
                                                         reg_C /= Integer.parseInt(rozkaz[3]);
                                                    }
                                                }
                                                
                                                this.working = false;
                                                return true;
                                            }
                                            else{
						System.out.print("\n\nZle argumenty funkcji.\n\n");
						this.working = false;
						return false;
                                            }
					}
					else{
                                            if (rozkaz[1] == "MOV"){
						if (rozkaz[2] == "A"){
                                                    if (rozkaz[3] == "B"){
							reg_A = reg_B;
                                                    }
                                                    else{
                                                        if (rozkaz[3] == "C"){
                                                            reg_A = reg_C;
							}
							else{
                                                            reg_A = Integer.parseInt(rozkaz[3]);
							}
                                                    }
						}
						else{
                                                    if (rozkaz[2] == "B"){
							if (rozkaz[3] == "A"){
                                                            reg_B = reg_A;
							}
							else{
                                                            if (rozkaz[3] == "C"){
                                                                reg_B = reg_C;
                                                            }
                                                            else{
								reg_B = Integer.parseInt(rozkaz[3]);
                                                            }
							}
                                                    }
                                                    else{
							if (rozkaz[3] == "A"){
                                                            reg_C = reg_A;
							}
                                                        else{
                                                            if (rozkaz[3] == "B"){
								reg_C = reg_B;
                                                            }
                                                            else{
								reg_C = Integer.parseInt(rozkaz[3]);
                                                            }
							}
                                                    }
						}

						this.working = false;
						return true;
                                            }
                                            else{
						System.out.print("\n");
                                            }
					}
                                    }
				}
                            }
                        }
                    }
                }
            }
            if (rozkaz[1] == "PAP"){
		PM.ls();
            }
            else{
		if(rozkaz[1] == "KIL"){
                    PM.kill(Integer.parseInt(rozkaz[2]));
		}
		else{
                    if (rozkaz[1] == "FEK"){
                        if (rozkaz[2] != ""){
                            String bufor;
                            String data;
                           // std::fstream plik(rozkaz[2]);
                            
                            while (!plik.eof())
                            {
                                    getline(plik, bufor);
                                    data += bufor;
                            }
                            int pom = bufor.size() % ROZMIAR;
                            for (int i = 0; i < (ROZMIAR - pom); i++){
                                    data += char(0);
                            }
                            BaseM = pamiec.znajdz_miejsce(data)*ROZMIAR;//nie przemyslalem tego xD
                            Limit = pamiec.wgraj_program(rozkaz[2]);
                        }//sciezka
                        if (BaseM != -1){

                            PM.fork();
                            Prioritylist.addToList(PM.pointertolastprocess());
                            PM.setBaseandLimit(BaseM, Limit);
                             PM.exec(regA, regB, regC, flaga_F);
                        }
                    }
                    else{
                        if (rozkaz[1] == "WTP"){
                            PM.waitpid(Integer.parseInt(rozkaz[2]));
                        }
                        else{
                            if (rozkaz[1] == "PPR"){

                                //Prioritylist.showReady();
                                //Prioritylist.showPriorityList();
                            }
                            else{
                                if (rozkaz[1] == "STN"){
                                    pri.setNiceUp(Integer.parseInt(rozkaz[2]));
                                }
                                else{
                                    if (rozkaz[1] == "PTP"){
                                        PM.lstree();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(rozkaz[1] == "DVM"){
                pamiec.wyswietl_pamiec_wirtualna();
            }
            else{
		if (rozkaz[1] == "DRM"){
                    pamiec.wyswietlPamiecRAM();
		}
            }
            //Logiczne
            if (rozkaz[1] == "EQL"){
		if (rozkaz[2] == rozkaz[3]){
                    flag_F = true;
		}
                else 
                    flag_F = false;

		this.working = false;
		return true;
		}
		else{
                    if(rozkaz[1] == "JMP"){
			PC = Integer.parseInt(rozkaz[2]);

                        this.working = false;
                        return true;
                    }
                    else{
			if (rozkaz[1] == "JPT"){
                            if (this->flag_F = true){
				PC = Integer.parseInt(rozkaz[2]);
                            }
                            else{
                                //do nothing
                            }

                            this.working = false;
                            return true;
			}
			else{
                            if (rozkaz[1] == "JPF"){
				if (this->flaga_F = false){
                                    PC = Integer.parseInt(rozkaz[2]);
				}
				else{
                                    //do nothing
				}

				this.working = false;
				return true;
                            }
                            else{
				System.out.print("\n");
                            }
			}
                    }
		}
            //Potoki
            if (rozkaz[1] == "PIP"){//tworzenie potoku
                for (auto it : PM.Processlist) {
                    if (it->state == 2){
			PIPE* nowy = new PIPE(it->deskryptor, DYSK);//tworzenie nowego potoku
			it->potok = nowy;
			//zapisanie deskryptorow do odpowiednich rejestrow:
			if (rozkaz[2] == "A")set_regA(it->deskryptor[0]);
			if (rozkaz[2] == "B")set_regB(it->deskryptor[0]);
			if (rozkaz[2] == "C")set_regC(it->deskryptor[0]);
                    }
		}
            }
            else{
		if (rozkaz[1] == "RPP"){//czytanie z potoku
                    for (auto it : PM.Processlist) {
			if (it->state == 2){
                            if (it->potok != nullptr){
				String buf;
				if (it->potok->read(it->deskryptor[0], buf, Integer.parseInt(rozkaz[3])) == -1){
                                    PM.waitpid(it->PID);
                                    break;
				}
				if (rozkaz[2] == "A")regA = Integer.parseInt(buf);
				else if (rozkaz[2] == "B")regB = Integer.parseInt(buf);
				else if (rozkaz[2] == "C")regC = Integer.parseInt(buf);
				System.out.println(buf);
                            }
			}
                    }
		}
		else{
                    if (rozkaz[1] == "WPP"){//pisanie do potoku
			for (auto it : PM.Processlist) {
                            if (it->state == 2) {
				if (it->potok != nullptr) {
                                    if (it->potok->write(it->deskryptor[1], rozkaz[2]) == -1)
					PM.waitpid(it->PID);
				}
                            }
			}
                    }
                    else{
			if (rozkaz[1] == "PCR"){//zamyka deskryptor czytania
                            for (auto it : PM.Processlist) {
				if (it->state == 2) {
                                    if (it->potok != nullptr) {
					it->potok->close(it->deskryptor[0]);
                                    }	
				}
                            }
			}
			else{
                            if (rozkaz[1] == "PCW"){//zamyka deskryptor pisania
				for (auto it : PM.Processlist) {
                                    if (it->state == 2) {
					if (it->potok != nullptr) {
										it->potok->close(it->deskryptor[1]);
					}
                                    }
				}
                            }
                            else{
                                 if (rozkaz[1] == "PDI"){//wyswietla potok
                                    for (auto it : PM.Processlist) {
                                        if (it->state == 2) {
                                            if (it->potok != nullptr) {
                                                qit->potok->display();
                                            }
                                        }
                                    }
                                }
                                else{
                                    if (rozkaz[1] == "PDU"){//wyswietla liczbe podlaczonych procesow.
                                        for (auto it : PM.Processlist) {
                                            if (it->state == 2) {
                                                if (it->potok != nullptr) {
                                                    it->potok->show_NO_users();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
		}
            }
	}
	else{
            System.out.print("Nie ma takiego rozkazu.\n\n\n");
	}
    }
    
    public void showCommandList(){
        Iterator it = commandList.iterator();
        while(it.hasNext()){
            System.out.print(it);
        }
    }
}

