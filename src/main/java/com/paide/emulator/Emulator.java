package com.paide.emulator;

import com.hermant.machine.Machine;
import com.hermant.program.Program;

public class Emulator {
    private Machine machine;

    public Emulator(){
        machine = new Machine(false, false);
    }

    public void run(Program program){
        machine.setDebug(false);
        machine.loadProgram(program);
        machine.runProgram(0);
    }

    public void debug(Program program){
        machine.setDebug(true);
        machine.loadProgram(program);
        machine.runProgram(0);
    }

    public void load(Program program){
        machine.setDebug(true);
        machine.loadProgram(program);
    }

    public void printStack(){
        System.out.println(machine.getStack());
    }

    public void printGPR(){
        System.out.println(machine.getRegister());
    }

    public void printFPR(){
        System.out.println(machine.getFPR());
    }

}
