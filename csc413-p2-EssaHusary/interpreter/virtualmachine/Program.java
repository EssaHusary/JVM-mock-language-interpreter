package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;

import interpreter.bytecodes.ByteCode;
import interpreter.bytecodes.ByteCodeJumpers;
import interpreter.bytecodes.LabelCode;
import interpreter.loaders.CodeTable;

public class Program {

    private List<ByteCode> program;

    /**
     * Instantiates a program object using an
     * ArrayList
     */
    public Program() {
        program = new ArrayList<>();
    }

    /**
     * Gets the size of the current program.
     * @return size of program
     */
    public int getSize() {

        return program.size();

    }

    /**
     * Grabs an instance of a bytecode at an index.
     * @param programCounter index of bytecode to get.
     * @return a bytecode.
     */
    public ByteCode getCode(int programCounter) {

        return program.get(programCounter);

    }

    /**
     * Adds a bytecode instance to the Program List.
     * @param c bytecode to be added
     */
    public void addByteCode(ByteCode c) {
        program.add(c);
    }

    /**
     * Makes multiple passes through the program ArrayList resolving
     * addrss for Goto,Call and FalseBranch bytecodes. These bytecodes
     * can only jump to Label codes that have a matching label value.
     * HINT: make note what type of data-structure ByteCodes are stored in.
     * **** METHOD SIGNATURE CANNOT BE CAHNGED *****
     */

    /* The outer 'if' statement makes sure that: 1) we are only allowing ByteCodes that jump to be selected in the outer
       'for' loop, 2) we are only allowing Labels to be selected in the inner 'for' loop (to match with the jump
       ByteCodes, and 3) we ONLY care about the jump and label ByteCodes (hence the 'instanceof' of the extended
       interface called "ByteCodeJumpers"). The inner 'if' statement checks if a jump ByteCode has a symbolic address
       that matches a Label's symbolic address. If so, that jump ByteCode receives an address that has a value matching
       where that Label is in the program ArrayList */
    public void resolveAddress() {



        for (int i = 0; i < program.size() - 1; i++){

            String className1 = program.get(i).getClass().getName();

            for (int j = 0; j < program.size() - 1; j++){

                String className2 = program.get(j).getClass().getName();

                if(!className1.equalsIgnoreCase("interpreter.bytecodes.LabelCode") &&
                        className2.equalsIgnoreCase("interpreter.bytecodes.LabelCode") &&
                        program.get(i) instanceof ByteCodeJumpers && program.get(j) instanceof ByteCodeJumpers){

                    if(((ByteCodeJumpers) program.get(i)).getValue()
                            .equalsIgnoreCase(((ByteCodeJumpers) program.get(j)).getValue())){

                        ((ByteCodeJumpers) program.get(i)).setResolvedAddress(j);  /* setResolvedAddress() is from the
                                                                                      ByteCodeJumper interface, for all
                                                                                      of the Bytecodes that jump */

                    }
                }
            }
        }


    }



}
