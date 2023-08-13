package interpreter.loaders;

import interpreter.bytecodes.ByteCode;
import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.RuntimeStackIllegalAccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ByteCodeLoader {
    private String codSourceFileName;
    
    /**
     * Constructs ByteCodeLoader object given a COD source code
     * file name
     * @param fileName name of .cod File to load.
     */
    public ByteCodeLoader(String fileName){
        this.codSourceFileName = fileName;
    }
    
    /**
     * Loads a program from a .cod file.
     * @return a constructed Program Object.
     * @throws InvalidProgramException exception thrown when 
     * loadCodes fails.
     */
    public Program loadCodes() throws InvalidProgramException {

        String line;
        String[] items;
        String className;
        Class classBluePrint;
        ByteCode bc;
        Program program = new Program();
        List<String> args = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.codSourceFileName))){

            while (reader.ready()){
                line = reader.readLine();
                items = line.split("\\s+");
                className = CodeTable.getClassName(items[0]);
                classBluePrint = Class.forName("interpreter.bytecodes."+className);
                bc = (ByteCode) classBluePrint.getDeclaredConstructor().newInstance();


                for (int i = 1; i < items.length; i++){
                    args.add(items[i]);
                }

                bc.init(args);
                program.addByteCode(bc);
                args.clear();
            }

        }catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException |
                IllegalAccessException | InvocationTargetException e){
            throw new InvalidProgramException(e);

        }

        return program;
    }
}
