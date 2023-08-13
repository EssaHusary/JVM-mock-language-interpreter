package interpreter.bytecodes;

import interpreter.virtualmachine.RuntimeStackIllegalAccess;
import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class WriteCode implements ByteCode{
    @Override
    public void init(List<String> args) {

    }

    @Override
    public void execute(VirtualMachine vm) {
        try {

            int topOfStack = vm.getTopOfStack();
            System.out.println(topOfStack);

        } catch (RuntimeStackIllegalAccess e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(){
        return "WRITE";
    }

}
