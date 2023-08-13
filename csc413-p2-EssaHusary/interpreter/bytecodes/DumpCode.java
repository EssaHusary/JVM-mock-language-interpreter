package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class DumpCode implements ByteCode{

    private String onOrOff;

    @Override
    public void init(List<String> args) {

        onOrOff = args.get(0);

    }

    @Override
    public void execute(VirtualMachine vm) {
//        vm.dump();
    }

}
