package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LabelCode implements ByteCode, ByteCodeJumpers{

    private String value;  // label

    @Override
    public void init(List<String> args) {

        this.value = args.get(0);

    }

    @Override
    public void execute(VirtualMachine vm) {

    }

    @Override
    public String getValue() {
        return value;
    }  // used to obtain the label for the resolveAddress() function

    @Override
    public void setResolvedAddress(int resolvedAddress) {

    }


    @Override
    public String toString(){
        String base = "LABEL  " + value;
        return base;
    }


}
