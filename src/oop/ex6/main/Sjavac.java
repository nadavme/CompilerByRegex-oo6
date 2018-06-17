package oop.ex6.main;

/**
 *
 */
public class Sjavac {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Manager manager = Manager.getInstance();
        manager.checkFile(args[0]);
    }
}




