package interpreter.bytecodes;

import interpreter.virtualmachine.RuntimeStackIllegalAccess;
import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class FalseBranchCode implements ByteCode, ByteCodeJumpers{

    private String value;  // label
    private int resolvedAddress;  // the resolved address #

    @Override
    public void init(List<String> args) {

        value = args.get(0);

    }

    @Override
    public void execute(VirtualMachine vm) {
        try {
            if(vm.pop() == 0){
                vm.setProgramCounter(resolvedAddress);
            }
        } catch (RuntimeStackIllegalAccess e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getValue() {
        return value;
    }  // used to obtain the label for the resolveAddress() function

    @Override
    public void setResolvedAddress(int resolvedAddress) {
        this.resolvedAddress = resolvedAddress;
    }


    @Override
    public String toString(){
        String base = "FALSEBRANCH  " + value;
        return base;
    }

}
