package interpreter.virtualmachine;

import interpreter.bytecodes.ByteCode;

import java.util.List;
import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;

    public VirtualMachine(Program program) {
        this.program = program;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<>();
        this.programCounter = 0;
    }

    public void executeProgram() {
        isRunning = true;

        while (isRunning){
            ByteCode code = program.getCode(programCounter);
            code.execute(this);
            System.out.println(code);
            System.out.println(this.runTimeStack.dump());
            programCounter++;
        }
    }

    public void setProgramCounter(int newAddress) {
        this.programCounter = newAddress;
    }  // after address resolution

    public int pop() throws RuntimeStackIllegalAccess {
        return this.runTimeStack.pop();
    }

    public int getCurrentFrameSize() {
        return this.runTimeStack.getCurrentFrameSize();
    }

    public void pushReturnAddress() {
        this.returnAddress.push(this.programCounter);
    }

    public void push(int value) {
        this.runTimeStack.push(value);
    }

    public int store(int offset) throws RuntimeStackIllegalAccess {
        return this.runTimeStack.store(offset);
    }

    public void load(int offset) throws RuntimeStackIllegalAccess {
        this.runTimeStack.load(offset);
    }

    public void createNewFrame(int offsetFromTopOfRuntimeStack) throws RuntimeStackIllegalAccess {
        this.runTimeStack.newFrameAt(offsetFromTopOfRuntimeStack);
    }

    public void setIsRunningToBeFalse() {
        this.isRunning = false;
    }  // for 'halt' Bytecode

    public int getReturnAddress() {
        return this.returnAddress.pop();
    }

    public void popFrame() {
        this.runTimeStack.popFrame();
    }

    public int getTopOfStack() throws RuntimeStackIllegalAccess {
        return runTimeStack.peek();
    }

    public List<Integer> getArgs() {
        return this.runTimeStack.getArgs();
    }

//    public void dump() {
//        this.runTimeStack.dump();
//    }
}
