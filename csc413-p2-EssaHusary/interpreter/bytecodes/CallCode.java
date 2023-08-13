package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;

public class CallCode implements ByteCode, ByteCodeJumpers{

    private String value;  // label
    private int resolvedAddress;
    private List<Integer> args;  // Used for dumping the arguments in toString(). Not related to init(args).

    @Override
    public void init(List<String> args) {
        this.value = args.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.pushReturnAddress();
        vm.setProgramCounter(resolvedAddress);
        args = vm.getArgs(); // fills List w/ arguments of current frame. again, for the purpose of dumping in toString
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

        String base;

        String collectionOfArgs = "";


        // The following allows for base-id(args) dumping. Ex. f(1, 6, 7, 9)
        if (!(args == null) ) {

            for (int i = 0; i < args.size(); i++){

                if (i == args.size() - 1){     // So that the final argument doesn't have a comma attached

                    collectionOfArgs = collectionOfArgs + args.get(i);

                } else {

                    collectionOfArgs = collectionOfArgs + args.get(i) + ", ";

                }

            }

        }


        String str = value;

        if(value.contains("<")){    // The case where we have a label that is, say, "function<<2>>"

            str = str.substring(0, str.indexOf("<"));
            base = "CALL  " + value + "             " + str + "(" + collectionOfArgs + ")";
            return base;

        } else {     // The case where we have a label that is, say, "Read", which does not contain a "<" character
            str = str.substring(0, str.length());
            base = "CALL  " + value + "             " + str + "(" + collectionOfArgs + ")";;
            return base;
        }

    }


}
