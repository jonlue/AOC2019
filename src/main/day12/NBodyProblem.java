package main.day12;

import main.AOCRiddle;

import java.math.BigInteger;
import java.util.*;

public class NBodyProblem extends AOCRiddle {
    public NBodyProblem(String in, int part) {
        super(in, part);
        parse();
    }

    private static final int STEPS = 1000;

    private List<Moon> moons;

    @Override
    protected String solve1() {
        return String.valueOf(calculateEnergyAfterSteps(STEPS));
    }

    @Override
    protected String solve2() {
        return String.valueOf(calculateRepeat());
    }

    private long calculateRepeat() {
        List<Moon> copy1 = new ArrayList<>();
        for (Moon moon : moons) {
            copy1.add(new Moon(moon));
        }
        MoonMoverThread xCompare = new MoonMoverThread(copy1,0);
        List<Moon> copy2 = new ArrayList<>();
        for (Moon moon : moons) {
            copy2.add(new Moon(moon));
        }
        MoonMoverThread yCompare = new MoonMoverThread(copy2,1);
        List<Moon> copy3 = new ArrayList<>();
        for (Moon moon : moons) {
            copy3.add(new Moon(moon));
        }
        MoonMoverThread zCompare = new MoonMoverThread(copy3,2);

        xCompare.start();
        yCompare.start();
        zCompare.start();

        try {
            xCompare.join();
            yCompare.join();
            zCompare.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Long> repeating = new ArrayList<>();
        repeating.add(xCompare.getResult());
        repeating.add(yCompare.getResult());
        repeating.add(zCompare.getResult());


        return kgv(repeating);
    }

    private long kgv(List<Long> values) {
        while(values.size() != 1) {
            BigInteger m1 = new BigInteger(String.valueOf(values.remove(0)));
            BigInteger m2 = new BigInteger(String.valueOf(values.remove(0)));

            values.add(0, (m1.multiply(m2)).divide(m1.gcd(m2)).longValue());
        }

        return values.get(0);
    }


    private int calculateEnergyAfterSteps(int steps) {
        doSteps(steps);
        return sumEnergy();
    }

    private void doSteps(int steps) {
        for(int i = 0; i<steps; i++) {
            compareMoons(moons);
            for(Moon m : moons){
                m.applyVelocity();
            }
        }
    }

    private int sumEnergy(){
        int totalEnergy = 0;
        for(Moon m : moons){
            totalEnergy += m.getKin()*m.getPot();
        }
        return totalEnergy;
    }

    private void compareMoons(List<Moon> moons) {
        for (int i = 0; i <moons.size(); i++) {
            for (int j = i+1; j < moons.size(); j++){
                moons.get(i).compareMoon(moons.get(j));
            }
        }
    }

    private void parse(){
        moons = new ArrayList<>();
        for(String value : getInput().split("\n")){
            value = value.replaceAll("<","").replaceAll(">","").replaceAll("=","").replaceAll("x","").replaceAll("y","").replaceAll("z","").replaceAll(" ","");
            String[] xyz = value.split(",");
            int[] cords = new int[3];
            for(int i = 0; i< xyz.length; i++){
                cords[i] = Integer.parseInt(xyz[i]);
            }
            moons.add(new Moon(cords[0],cords[1],cords[2]));
        }
    }
}
