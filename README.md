# JVM Mock Language Interpreter
This is an interpreter for a mock language that processes bytecodes. Let's call this mock language 'X'. A virtual machine and runtime stack are also implemented to run a program written in this fake programming language. Running the program is as simple as hitting the start button available on most IDE's.

We can run two programs for this mock language: finding the factorial of a number and calculating the nth Fibonacci number. These programs are located in the factorial.x and fib.x files. The associated "compiled" bytecode (source code) files are factorial.x.cod and fib.x.cod. Again, our mock language is called 'X', hence the extension .x for these files. I have a whole folder for bytecode classes called "bytecodes". These are all implemented to act as the instruction set of the "JVM". The "Goto" bytecode, for example, will allow us to jump to labels. I have a folder called "loaders" that stores the classes "CodeTable" and "ByteCodeLoader". The first one, "CodeTable", maps each of the bytecodes as they appear in the .cod files to their bytecode class found in the "bytecodes" folder. This is necessary as we need to load and store all of the bytecodes from the .cod file of the program that we want the virtual machine to execute. This loading is done in the "ByteCodeLoader" class. The "virtualmachine" folder has the "Program", "RunTimeStack", and "VirtualMachine" classes. The "Program" class is responsible for storing the loaded bytecodes loaded in the "ByteCodeLoader" class. "Program" also has an important function called "resolveAddress" that allows us to jump to labels when we encounter the "Goto", "Call" and "FalseBrach" bytecodes. The "RunTimeStack" class is responsible for recording and processing activation frames, which of course, are stored as a stack. The "VirtualMachine" class is simply responsible for executing the desired program. Finally, the class "Interpreter" is the driver of the project and calls the "executeProgram" function of the "VirtualMachine" class to run the program.

Running the program is as simple as hitting the start button available on most IDE's. The user is prompted to choose between one of the two programs to run. They should type "fib.x.cod" to run the fibonacci program or "factorial.x.cod" to run the factorial program. It will look like this:

![image](https://github.com/user-attachments/assets/e6c064e3-98a1-46a2-a5f4-7668ef6a1066)


Then once they specify a program, they will see a dump of what bytecodes have been processed so far. They will also be prompted for an integer argument (arg). Here is a screenshot:

![image](https://github.com/user-attachments/assets/90320e11-cb23-44b7-b1ab-96815d85f3df)


They will then see a whole dump of the execution:

![image](https://github.com/user-attachments/assets/2c9c6aaf-d387-425b-9761-9ac4886d200c)

This is the end of the execution:

![image](https://github.com/user-attachments/assets/3f07dea1-32a7-4c69-86d5-68ba30440ccb)
