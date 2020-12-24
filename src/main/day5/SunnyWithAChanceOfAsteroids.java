package main.day5;

import main.AOCRiddle;
import main.util.IntCodeComputer;

import java.util.ArrayList;
import java.util.List;

public class SunnyWithAChanceOfAsteroids extends AOCRiddle {
    public SunnyWithAChanceOfAsteroids(String in, int part) {
        super(in, part);
        computer = new IntCodeComputer(getInput());
    }

    IntCodeComputer computer;

    @Override
    protected String solve1() {
        return String.valueOf(run(1));
    }

    @Override
    protected String solve2() {
        return String.valueOf(run(5));
    }

    private long run(long inputNumber){
        List<Long> input = new ArrayList<>();
        input.add(inputNumber);
        computer.setInput(input);

        computer.run(false);

        List<Long> temp = computer.getOutputList();

        for(int i = 0; i< temp.size(); ++i) {
            if(i == temp.size()-1) {
                return temp.get(i);
            }else if(temp.get(i) != 0) {
                return -1;
            }
        }
        return -1;
    }
}
