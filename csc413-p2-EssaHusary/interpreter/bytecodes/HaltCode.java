package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class HaltCode implements ByteCode{

    @Override
    public void init(List<String> args) {

    }

    @Override
    public void execute(VirtualMachine vm) {

        vm.setIsRunningToBeFalse();

    }

}
