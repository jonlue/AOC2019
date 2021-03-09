package main.days;

import main.AOCRiddle;
import main.util.IntCodeComputer;

public class Day2 extends AOCRiddle {
    public Day2(String in, int part) {
        super(in, part);
        computer = new IntCodeComputer(getInput());
    }

    private final IntCodeComputer computer;
    private static final int EXPECTED_RESULT = 19690720;

    @Override
    protected String solve1() {
        computer.setValue(1,12,0);
        computer.setValue(2,2,0);
        return String.valueOf(computer.run(false));
    }

    @Override
    protected String solve2() {
        for(int noun = 0; noun < 100; noun++){
            for(int verb = 0; verb < 100; verb++){
                computer.resetInstructions();
                computer.setValue(1,noun,0);
                computer.setValue(2,verb,0);
                if(computer.run(false) == EXPECTED_RESULT){
                    return Integer.toString(noun*100 + verb);
                }
            }
        }
        return null;
    }
}
