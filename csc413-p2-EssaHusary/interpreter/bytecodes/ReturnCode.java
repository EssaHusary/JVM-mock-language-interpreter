package interpreter.bytecodes;

import interpreter.virtualmachine.RuntimeStackIllegalAccess;
import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class ReturnCode implements ByteCode{

    private String previousAddress;  // label that the 'call' bytecode jumped to
    private int returnedValue;  // used in toString() to allow for "EXIT base-id : value" syntax

    @Override
    public void init(List<String> args) {
        if (!args.isEmpty()){
            this.previousAddress = args.get(0);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {

        vm.popFrame();
        vm.setProgramCounter(vm.getReturnAddress());

        try {
            returnedValue = vm.getTopOfStack();
        } catch (RuntimeStackIllegalAccess e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String toString(){

        String base;

        if(previousAddress != null && previousAddress.contains("<")){   /* if 'return' has an argument and whose
                                                                           argument contains a "<" (like in
                                                                           factorial<<2>>) */
            String str = previousAddress;
            str = str.substring(0, str.indexOf("<"));
            base = "RETURN  " + previousAddress + "    " + "EXIT  " + str + "  :  " + returnedValue;
            return base;

        } else {    // if return doesn't have an argument

            base = "RETURN";
            return base;
        }


    }

}
