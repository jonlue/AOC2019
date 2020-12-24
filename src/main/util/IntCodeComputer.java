package main.util;

import java.util.*;

public class IntCodeComputer {

    private static final int MAX_NUM_PARAMETER = 3;

    private static final int ADD = 1;
    private static final int MUL = 2;
    private static final int IN = 3;
    private static final int OUT = 4;
    private static final int JIT = 5;
    private static final int JIF = 6;
    private static final int LT = 7;
    private static final int EQ = 8;
    private static final int OFS = 9;
    private static final int HALT = 99;

    private static final int POS_MODE = 0;
    private static final int IMM_MODE = 1;
    private static final int REL_MODE = 2;



    private final String originalInstructions;
    private List<Long> inputList;
    private List<Long> outputList;
    private Map<Long,Long> instructions;

    private long i;
    private int j;
    private int relativeBase;
    private boolean auto = true;
    private Scanner scanner;

    private int status;

    public IntCodeComputer(String instructionList){
        originalInstructions = instructionList;
        outputList = new ArrayList<>();
        inputList = new ArrayList<>();
        resetInstructions();
        i = 0;
        j = 0;
        relativeBase = 0;
        status = 0;
    }

    public long run(boolean returnOnOutput){
        status = 1;
        while (i < instructions.size()) {
            switch ((int) (instructions.getOrDefault(i,0L)%100)){
                case ADD:
                    add(i);
                    i += 4;
                    break;
                case MUL:
                    multiply(i);
                    i += 4;
                    break;
                case IN:
                    input(i,j);
                    ++j;
                    i += 2;
                    break;
                case OUT:
                    out(i);
                    i += 2;
                    if(returnOnOutput){
                        if(instructions.getOrDefault(i,0L) == 99){
                            status = 99;
                        }
                        return outputList.get(outputList.size()-1);
                    }
                    break;
                case JIT:
                    i = jumpIfTrue(i);
                    break;
                case JIF:
                    i = jumpIfFalse(i);
                    break;
                case LT:
                    lessThan(i);
                    i += 4;
                    break;
                case EQ:
                    equals(i);
                    i += 4;
                    break;
                case OFS:
                    offsetRelativeBase(i);
                    i += 2;
                    break;
                case HALT:
                    status = 99;
                    return -1;
                default:
                    System.out.println(":(");
                    System.out.println(instructions.getOrDefault(i,0L));
                    return -1;
            }
        }
        return instructions.getOrDefault(0L,0L);
    }


    private void add(long i) {
        int[] mode = getModes(instructions.getOrDefault(i,0L));
        long v1 = getValue(i+1, mode[0]);
        long v2 = getValue(i+2, mode[1]);

        setValue(i+3,v1+v2,mode[2]);
    }

    private void multiply(long i) {
        int[] mode = getModes(instructions.getOrDefault(i,0L));
        long v1 = getValue(i+1,mode[0]);
        long v2 = getValue(i+2,mode[1]);
        setValue(i+3,v1*v2,mode[2]);
    }

    private void input(long i, int j) {
        int[]mode = getModes(instructions.getOrDefault(i,0L));
        long inputVal;
        if(auto){
            inputVal = inputList.get(j);
        }else{
            System.out.println("  #########################################");
            System.out.println("  #  Left: -1\tNeutral: 0\tRight: 1      #");
            System.out.println("  #########################################");
            inputVal = -2;
            while(inputVal != 0 && inputVal != 1 && inputVal !=-1) {
                String temp = scanner.next();
                try{
                    inputVal = Long.parseLong(temp);
                }catch (Exception e){
                    inputVal = -2;
                }
            }
        }
        setValue(i + 1,inputVal ,mode[0]);
    }

    private void out(long i) {
        int[] mode = getModes(instructions.getOrDefault(i,0L));
        outputList.add(getValue(i+1,mode[0]));
        //System.out.println(getValue(i+1, mode[0]));
    }

    private long jumpIfTrue(long i) {
        int[] mode = getModes(instructions.getOrDefault(i,0L));
        long v1 = getValue(i+1,mode[0]);
        if(v1 != 0){
            return getValue(i+2,mode[1]);
        }
        return i+3;
    }
    private long jumpIfFalse(long i) {
        int[] mode = getModes(instructions.getOrDefault(i,0L));
        long v1 = getValue(i+1,mode[0]);
        if(v1 == 0){
            return getValue(i+2,mode[1]);
        }
        return i+3;
    }
    private void lessThan(long i) {
        int[] mode = getModes(instructions.getOrDefault(i,0L));
        long v1 = getValue(i+1,mode[0]);
        long v2 = getValue(i+2,mode[1]);
        if(v1 < v2){
            setValue(i+3,1,mode[2]);
        }else{
            setValue(i+3,0,mode[2]);
        }
    }
    private void equals(long i) {
        int[] mode = getModes(instructions.getOrDefault(i,0L));
        long v1 = getValue(i+1,mode[0]);
        long v2 = getValue(i+2,mode[1]);
        if(v1 == v2){
            setValue(i+3,1,mode[2]);
        }else{
            setValue(i+3,0,mode[2]);
        }
    }

    private void offsetRelativeBase(long i) {
        int[] mode = getModes(instructions.getOrDefault(i,0L));
        relativeBase += getValue(i+1,mode[0]);
    }


    public void setValue(long index, long value, int mode) {
        if(mode == REL_MODE){
            instructions.put(instructions.getOrDefault(index,0L)+relativeBase,value);
        }
        instructions.put(instructions.getOrDefault(index,0L),value);
    }

    private long getValue(long index, int mode) {
        switch (mode){
            case POS_MODE:
                return instructions.getOrDefault(instructions.getOrDefault(index,0L),0L);
            case IMM_MODE:
                return instructions.getOrDefault(index,0L);
            case REL_MODE:
                return instructions.getOrDefault(instructions.getOrDefault(index,0L) + relativeBase,0L);
            default:
                System.out.println(":( mode");
                System.out.println(mode);
                return Integer.MIN_VALUE;
        }
    }

    public void resetInstructions(){
        this.instructions = new HashMap<>();
        String[] ins = originalInstructions.split(",");
        for(long i = 0; i < ins.length; ++i){
            this.instructions.put(i,Long.parseLong(ins[(int)i]));
        }

        inputList = new ArrayList<>();
        outputList = new ArrayList<>();
        i = 0;
        j = 0;
        relativeBase = 0;
        status = 0;
    }
    public void setInput(List<Long> input){
        j = 0;
        this.inputList = input;
    }

    public void addInput(long in){
        this.inputList.add(in);
    }

    private int[] getModes(long mode){
        int j = 0;
        int[] modes = new int[MAX_NUM_PARAMETER];
        if(mode > 99) {
            String m = Integer.toString((int) mode);
            m = m.substring(0, m.length() - 2);


            for (int i = m.length() - 1; i > -1; --i) {
                modes[j] = Character.getNumericValue(m.charAt(i));
                j++;
            }
        }
        for(;j < MAX_NUM_PARAMETER; ++j){
            modes[j] = 0;
        }
        return modes;
    }

    public List<Long> getOutputList(){
        return outputList;
    }

    public int getStatus(){
        return status;
    }

    public void setAuto(boolean automatic){
        auto = automatic;
        if(!auto){
            scanner = new Scanner(System.in);
        }
    }

    public void setRegister(long index, long value){
        instructions.put(index,value);
    }
}
