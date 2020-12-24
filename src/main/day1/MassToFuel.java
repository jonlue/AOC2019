package main.day1;

import main.AOCRiddle;

import java.util.ArrayList;
import java.util.List;

public class MassToFuel extends AOCRiddle {
    public MassToFuel(String in, int part) {
        super(in, part);
    }

    private List<Integer> modulesWeight;

    @Override
    protected String solve1() {
        parse();
        return Integer.toString(convertMassToFuel(false));
    }

    @Override
    protected String solve2() {
        parse();
        return Integer.toString(convertMassToFuel(true));
    }

    private void parse(){
        modulesWeight = new ArrayList<>();

        for (String number : getInput().split("\n")){
            modulesWeight.add(Integer.parseInt(number));
        }
    }

    private int convertMassToFuel(boolean part2){
        int sum = 0;
        for(int mass : modulesWeight){
            if(part2){
                sum += calculateFuelOfFuel(mass);
            }else {
                sum += Math.floor(mass / 3.0) - 2;
            }
        }
        return sum;
    }

    private int calculateFuelOfFuel(int mass){
        int fuel = (mass/3)-2;
        if(fuel<= 0.0){
            return 0;
        }
        return fuel + calculateFuelOfFuel(fuel);
    }
}
