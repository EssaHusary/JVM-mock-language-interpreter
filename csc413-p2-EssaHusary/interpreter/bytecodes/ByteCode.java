package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;
import java.util.List;

public interface ByteCode {

    public void init(List<String> args);

    public void execute(VirtualMachine vm);



}
