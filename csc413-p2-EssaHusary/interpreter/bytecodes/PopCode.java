package interpreter.bytecodes;

import interpreter.virtualmachine.RuntimeStackIllegalAccess;
import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class PopCode implements ByteCode {

    private int numberOfPops;
    @Override
    public void init(List<String> args) {

        numberOfPops = Integer.parseInt(args.get(0));

    }

    @Override
    public void execute(VirtualMachine vm) {

        int currentFrameSize = vm.getCurrentFrameSize();

        for (int i = 0; i < numberOfPops; i++){
            try {
                if ((i + 1) > currentFrameSize){   // don't allow number of pops to exceed frame size
                    break;
                } else {
                    vm.pop();
                }
            } catch (RuntimeStackIllegalAccess e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public String toString(){
        return "POP " + numberOfPops;
    }

}
