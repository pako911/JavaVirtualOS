package processManager;

import memoryManagement.Memory;
import processManager.PCB.Stany;

public class Test {

	public static void main(String[] args) {
		Memory memory = new Memory();
		ProcessManager processManager = new ProcessManager(memory);
		processManager.newProcess("A.TXT");
		processManager.newProcess("A.TXT");
		processManager.newProcess("A.TXT");
		processManager.newProcess("A.TXT");
		processManager.newProcess("A.TXT");
		processManager.newProcess("A.TXT");
		processManager.newProcess("A.TXT");
		processManager.ps();
		processManager.kill(3);
		processManager.ps();
		processManager.kill(4);
		processManager.ps();
		processManager.kill(5);
		processManager.ps();
		processManager.kill(6);
		processManager.ps();
		processManager.setState(7, Stany.AKTYWNY);
		processManager.ps();
		processManager.newProcess("A.TXT");
		processManager.ps();
	}
}