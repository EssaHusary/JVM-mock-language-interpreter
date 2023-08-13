package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class GotoCode implements ByteCode, ByteCodeJumpers {

    private String value;   // label
    private int resolvedAddress;  // resolved address number

    @Override
    public void init(List<String> args) {
        this.value = args.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setProgramCounter(resolvedAddress);
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
        String base = "GOTO  " + value;
        return base;
    }


}
