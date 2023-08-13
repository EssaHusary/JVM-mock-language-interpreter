package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.List;
import java.util.Scanner;

public class ReadCode implements ByteCode{

    @Override
    public void init(List<String> args) {

    }

    @Override
    public void execute(VirtualMachine vm) {

        int userInput = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter an integer: ");

        if(sc.hasNextInt()){   // if "userInput" is an int value, save it. will be pushed when 'while' loop breaks
            userInput = sc.nextInt();

        } else {   // otherwise, request user to input a valid input (again and again until it's an int value)

            while (!sc.hasNextInt()){

                System.out.println("The input is invalid. Please enter a valid integer: ");
                sc.next();
                if (sc.hasNextInt()){
                    userInput = sc.nextInt();
                    break;
                }

            }
        }

        vm.push(userInput);
    }

    @Override
    public String toString(){
        return "READ";
    }

}
