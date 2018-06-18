package oop.ex6.main;

/**
 * the sjava class.
 */
public class Sjavac {

	/**
	 * the main method.
	 * @param args the arguments.
	 */
	public static void main(String[] args) {
        Manager manager = Manager.getInstance();
        manager.checkFile(args[0]);
    }
}




