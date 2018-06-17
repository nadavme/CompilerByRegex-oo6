package oop.ex6.main;

import oop.ex6.blocks.Block;
import oop.ex6.blocks.GlobalBlock;
import oop.ex6.blocks.MethodBlock;
import oop.ex6.file_reader.FileReader;
import oop.ex6.file_reader.IllegalFileTypeException;
import oop.ex6.parser.Parser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 */
public class Manager {
    private static Manager manager = new Manager();

    /**
     * @return
     */
    public static Manager getInstance() {
        return manager;
    }

    private Manager() {
    }

    /**
     *
     * @param path
     */
    public void checkFile(String path) {
        try {
            ArrayList<String> lines = FileReader.readFile(path);
            GlobalBlock globalBlock = new GlobalBlock(null, lines);
            Parser.parseGlobalBlock(globalBlock);
            for (MethodBlock block : globalBlock.getMethods()) {
                Parser.parseBlock(block, globalBlock);
            }
            System.out.println("0");
        } catch (SyntaxException e) {
            System.out.println("1");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("Warning: IO Problem");
            System.out.println("2");
        } catch (IllegalFileTypeException e) {
            System.err.println(e.getMessage());
            System.out.println("2");
        }
    }
}
