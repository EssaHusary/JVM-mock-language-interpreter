package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class LitCode implements ByteCode{

    private int value;
    private String id; // id/variable name to allow for required syntax in toString()

    @Override
    public void init(List<String> args) {
        value = Integer.parseInt(args.get(0));
        if (args.size() > 1){
            id = args.get(1);
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.push(value);
    }

    @Override
    public String toString(){
        String base = "LIT  " + value;
        if (id != null){
            base = base + "       int  " + id;
        }

        return base;
    }

}
