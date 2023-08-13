package interpreter.bytecodes;

import interpreter.virtualmachine.RuntimeStackIllegalAccess;
import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class StoreCode implements ByteCode{

    private int offset;
    private int value;  // value to allow for required syntax in toString()
    private String variable;  // id/variable name to allow for required syntax in toString()

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
            value = vm.store(offset);
        } catch (RuntimeStackIllegalAccess e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(){
        String base = "STORE  " + offset;
        if (variable != null){
            base = base + "  " + variable + "            " + variable + "  =  " + value;
        }

        return base;
    }


}
