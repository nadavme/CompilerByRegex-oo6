package oop.ex6.main;

import oop.ex6.blocks.GlobalBlock;
import oop.ex6.blocks.MethodBlock;
import oop.ex6.file_reader.FileReader;
import oop.ex6.file_reader.IllegalFileTypeException;
import oop.ex6.parser.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * the manager class.
 * Singleton class.
 */
public class Manager {
    private static Manager manager = new Manager();

	/**
	 * return the manager instance.
	 * @return the manager instance.
	 */
	public static Manager getInstance() {
        return manager;
    }

    /*
    * Constructor.
    */
    private Manager() {
    }

	/**
	 * check the given file and print a message.
	 * @param path the path of the file.
	 */
	public void checkFile(String path) {
        try {
            ArrayList<String> lines = FileReader.readFile(path);
            GlobalBlock globalBlock = new GlobalBlock(lines);
            Parser.parseGlobalBlock(globalBlock);
            for (MethodBlock block : globalBlock.getMethods()) {
            	globalBlock.resetScopeInit();
            	Parser.parseBlock(block, globalBlock);
            }
            System.out.println("0");
        } catch (SyntaxException e) {
            System.out.println("1");
        } catch (FileNotFoundException e) {
            System.err.println("Warning: IO Problem");
            System.out.println("2");
        } catch (IllegalFileTypeException e) {
            System.err.println(e.getMessage());
            System.out.println("2");
        }
    }
}
