package interpreter.bytecodes;

import interpreter.virtualmachine.RuntimeStackIllegalAccess;
import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LoadCode implements ByteCode{

    private int offset;
    private String variable;  // id/variable name for use in toString()

    @Override
    public void init(List<String> args) {
        offset = Integer.parseInt(args.get(0));
        if (args.size() > 1){
            variable = args.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        try {
            vm.load(offset);
        } catch (RuntimeStackIllegalAccess e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(){
        String base = "LOAD  " + offset;
        if (variable != null){
            base = base + "  " + variable + "            <load " + variable + ">";
        }

        return base;
    }

}
