package main.day9;

import main.AOCRiddle;
import main.util.IntCodeComputer;

public class SensorBoost extends AOCRiddle {
    public SensorBoost(String in, int part) {
        super(in, part);
        computer = new IntCodeComputer(getInput());
    }

    private final IntCodeComputer computer;

    @Override
    protected String solve1() {
        return String.valueOf(run(1L));
    }

    @Override
    protected String solve2() {
        return String.valueOf(run(2L));
    }

    private long run(long input){
        computer.addInput(input);
        computer.run(false);
        return(computer.getOutputList().get(0));
    }
}
