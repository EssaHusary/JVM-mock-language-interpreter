package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

class RunTimeStack {

    private List<Integer>  runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }


    public String dump(){

        String collectionOfValues = "";


//        int sizeOfFrame = this.runTimeStack.size() - this.framePointer.peek();
//
//        if (sizeOfFrame == 0){
//            collectionOfValues = collectionOfValues + "[]";
//        }

//        if (this.framePointer.peek() == 0 && this.runTimeStack.isEmpty()){
//            collectionOfValues = collectionOfValues + "[]";
//        } else if (this.framePointer.peek() == 0 && this.framePointer.size() == 1) {
//            collectionOfValues = collectionOfValues + "[" + this.runTimeStack.get(0) + "]";
//        }


        if (this.framePointer.peek() == 0 && this.runTimeStack.isEmpty()){
            collectionOfValues = collectionOfValues + "[]";
        }


        for (int i = 0; i < framePointer.size() - 1; i++){
            int begin = this.framePointer.get(i);
            int end = this.framePointer.get(i + 1);
            collectionOfValues = collectionOfValues + "[";
            if (end - begin == 0 && !this.runTimeStack.isEmpty()){
                collectionOfValues = collectionOfValues + this.runTimeStack.get(this.framePointer.peek()) + "]";
            } else if (end - begin == 0){
                collectionOfValues = collectionOfValues + "]";
            }
            for (int j = begin; j < end; j++){
                if (j == end - 1){
                    collectionOfValues = collectionOfValues + runTimeStack.get(j) + "]";
                } else {
                    collectionOfValues = collectionOfValues + runTimeStack.get(j) + ", ";
                }

            }
        }
        return collectionOfValues;


    }


    public int peek() throws  RuntimeStackIllegalAccess {
        if (this.runTimeStack.isEmpty()){
            throw new RuntimeStackIllegalAccess(new EmptyStackException());
        }
        return this.runTimeStack.get(this.runTimeStack.size() - 1);
    }

    public int push(int i){
        this.runTimeStack.add(i);
        return i;
    }

    public int pop() throws RuntimeStackIllegalAccess{

        if (this.runTimeStack.isEmpty()){
            throw new RuntimeStackIllegalAccess(new EmptyStackException());
        }
        return this.runTimeStack.remove(this.runTimeStack.size()-1);
    }


    public int store(int offsetFromFramePointer) throws RuntimeStackIllegalAccess {

        if (offsetFromFramePointer < 0){
            throw new RuntimeStackIllegalAccess("Offset must be positive!");
        }

        int storeIndex = framePointer.peek() + offsetFromFramePointer;
        int topItemOfStack = runTimeStack.get(runTimeStack.size() - 1);

        if(runTimeStack.size() - 1 < storeIndex){  // offset can't extend beyond runtime stack

            throw new RuntimeStackIllegalAccess("Invalid offset! It is out of bounds!");

        } else if (runTimeStack.isEmpty()){

            throw new RuntimeStackIllegalAccess(new EmptyStackException());

        } else if (storeIndex < (runTimeStack.size() - 1)){

            runTimeStack.remove(storeIndex);
            runTimeStack.add(storeIndex, topItemOfStack);
            runTimeStack.remove(runTimeStack.size() - 1);

        }

        return topItemOfStack;
    }

    public int load(int offsetFromFramePointer) throws RuntimeStackIllegalAccess {

        int loadIndex = framePointer.peek() + offsetFromFramePointer;
        int itemToBeLoaded = 0;

        if (offsetFromFramePointer < 0){

            throw new RuntimeStackIllegalAccess("Offset must be positive!");

        } else if (runTimeStack.isEmpty()) {

            throw new RuntimeStackIllegalAccess(new EmptyStackException());

        } else if(runTimeStack.size() - 1 < loadIndex){  // offset can't extend beyond runtime stack

            throw new RuntimeStackIllegalAccess("Invalid offset! It is out of bounds!");

        } else if (loadIndex <= (runTimeStack.size() - 1)){


            itemToBeLoaded = runTimeStack.get(loadIndex);
            runTimeStack.add(itemToBeLoaded);
            return itemToBeLoaded;

        }

        return itemToBeLoaded;

    }


    public void newFrameAt(int offsetFromTopOfRuntimeStack){

        int newFrameIndex = runTimeStack.size() - offsetFromTopOfRuntimeStack;

        if (runTimeStack.isEmpty()){  // so that we are within bounds

            framePointer.add(0);

        } else {
            framePointer.add(newFrameIndex);
        }

    }


    public void popFrame(){

        if (!runTimeStack.isEmpty()){

            int valueToBeReturned = runTimeStack.get(runTimeStack.size() - 1);

            int stopIndex = framePointer.peek();    /* stops at next frame pointer to disallow operation across frame
                                                       boundaries */

            while (runTimeStack.size() != stopIndex){
                runTimeStack.remove(runTimeStack.size() - 1);
            }
            framePointer.pop();

            runTimeStack.add(valueToBeReturned);

        }
    }


    public int getCurrentFrameSize() {
        return this.runTimeStack.size() - this.framePointer.peek();
    }



    // I added this function to extract the arguments in the current frame for the purpose of dumping the 'call'
    // ByteCode. It allows for the base-id(args) syntax as in the case of f(5, 19, 5, 3, 2), for example.
    public List<Integer> getArgs() {

        List<Integer> args = new ArrayList<>();

        if (!runTimeStack.isEmpty()){
            int currentFrameSize = this.runTimeStack.size() - this.framePointer.peek();

            for (int i = 0; i < currentFrameSize; i++){
                args.add(runTimeStack.get(runTimeStack.size() - 1 - i));
            }
        }

        return args;
    }



}
