package interpreter.bytecodes;

import interpreter.virtualmachine.RuntimeStackIllegalAccess;
import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ArgsCode implements ByteCode{

    private int numberOfArgs;

    @Override
    public void init(List<String> args) {
        numberOfArgs = Integer.parseInt(args.get(0));
    }

    @Override
    public void execute(VirtualMachine vm) {
        try {

            vm.createNewFrame(numberOfArgs);

        } catch (RuntimeStackIllegalAccess e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(){

        String base = "ARGS  " + numberOfArgs;
        return base;
    }


}
