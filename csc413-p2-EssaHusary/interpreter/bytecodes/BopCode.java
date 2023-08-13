package interpreter.bytecodes;

import interpreter.virtualmachine.RuntimeStackIllegalAccess;
import interpreter.virtualmachine.VirtualMachine;

import java.util.List;
import java.util.Scanner;

public class BopCode implements ByteCode{



    private String operator;

    @Override
    public void init(List<String> args) {
        operator = args.get(0);
    }

    @Override
    public void execute(VirtualMachine vm) {

        try {

            int operand2 = vm.pop();
            int operand1 = vm.pop();
            int result = 0;


            switch (operator){
                case "+":

                    result = operand1 + operand2;
                    vm.push(result);
                    break;

                case "-":

                    result = operand1 - operand2;
                    vm.push(result);
                    break;

                case "/":

                    result = operand1 / operand2;
                    vm.push(result);
                    break;

                case "*":

                    result = operand1 * operand2;
                    vm.push(result);
                    break;

                case "==":

                    if(operand1 == operand2){
                        vm.push(1);
                    } else {
                        vm.push(0);
                    }
                    break;

                case "!=":

                    if(operand1 != operand2){
                        vm.push(1);
                    } else {
                        vm.push(0);
                    }
                    break;

                case "<=":

                    if(operand1 <= operand2){
                        vm.push(1);
                    } else {
                        vm.push(0);
                    }
                    break;

                case "<":

                    if(operand1 < operand2){
                        vm.push(1);
                    } else {
                        vm.push(0);
                    }
                    break;

                case ">=":

                    if(operand1 >= operand2){
                        vm.push(1);
                    } else {
                        vm.push(0);
                    }
                    break;

                case ">":

                    if(operand1 > operand2){
                        vm.push(1);
                    } else {
                        vm.push(0);
                    }
                    break;

                case "&":

                    if(operand1 == 1 && operand2 == 1){
                        vm.push(1);
                    } else if (operand1 == 1 && operand2 == 0){
                        vm.push(0);
                    } else if (operand1 == 0 && operand2 == 1) {
                        vm.push(0);
                    } else if (operand1 == 0 && operand2 == 0) {
                        vm.push(0);
                    }
                    break;

                case "|":

                    if(operand1 == 1 || operand2 == 1){
                        vm.push(1);
                    } else if (operand1 == 1 || operand2 == 0){
                        vm.push(1);
                    } else if (operand1 == 0 || operand2 == 1) {
                        vm.push(1);
                    } else if (operand1 == 0 || operand2 == 0) {
                        vm.push(0);
                    }
                    break;


            }



        } catch (RuntimeStackIllegalAccess e) {

            throw new RuntimeException(e);

        }

    }


    @Override
    public String toString(){

        String base = "BOP  " + operator;
        return base;
    }

}
