package processManager;

import memory.Memory;
import processManager.PCB.Stany;

public class Test {

	public static void main(String[] args) {
		Memory memory = new Memory();
		ProcessManager processManager = new ProcessManager(memory);
		processManager.newProcess("A.TXT");
		processManager.newProcess("A.TXT");
		processManager.newProcess("A.TXT");
		processManager.newProcess("A.TXT");
		processManager.getListProces();
		processManager.ps();
		processManager.kill(1);
		processManager.kill(2);
		processManager.ps();
		processManager.setState(3, Stany.AKTYWNY);
		processManager.ps();
	}

}
