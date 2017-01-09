/*
 * Autor Jakub Rybakowski
 * Funkcje testujące ProcessManagera
 * Stworzony 13:30 18.10.2016
 */

package processManager;

public class Test {

	public static void main(String[] args) {
		ProcessManager processManager = new ProcessManager();
		processManager.newProcess("a1");
		processManager.newProcess("a2");
		processManager.newProcess("a3");
		processManager.newProcess("a4");
		processManager.ps();
		processManager.shutDown();
		processManager.ps();
	}
}
