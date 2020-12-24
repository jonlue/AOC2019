package main.day10;

import main.AOCRiddle;
import main.util.Asteroid;

import java.util.*;
import java.util.stream.Collectors;

public class MonitoringStation extends AOCRiddle {
    public MonitoringStation(String in, int part) {
        super(in, part);
        parse();
    }

    private List<Asteroid> asteroids;
    private Asteroid station;

    @Override
    protected String solve1() {
        return String.valueOf(countAsteroids());
    }

    @Override
    protected String solve2() {
        countAsteroids();
        return getDestroyedAsteroid(200);
    }

    private int countAsteroids() {
        int max = Integer.MIN_VALUE;
        station = null;
        for (Asteroid asteroid : asteroids) {
            List<String> set = asteroids.stream()
                    .filter((a) -> a != asteroid)
                    .map((a) -> getStep(a, asteroid))
                    .distinct()
                    .sorted()
                    .collect(Collectors.toCollection(ArrayList::new));

            if(set.size()>max){
                station = asteroid;
            }
            max = Math.max(max, set.size());

        }
        return max;
    }

    private String getDestroyedAsteroid(int numAsteroids) {
        Hashtable<Asteroid, Double> table = new Hashtable<>();
        for(Asteroid a : asteroids){
            table.put(a,station.getAngle(a));
        }

        List<Double> angles = new ArrayList<>(table.values());

        Collections.sort(angles);
        angles = angles.stream().distinct().collect(Collectors.toCollection(ArrayList::new));

        List<Asteroid> secondGoal = new ArrayList<>();
        for(Asteroid a : table.keySet()){
            double angle = angles.get(numAsteroids-1);
            if(angle == table.get(a)){
                secondGoal.add(a);
            }
        }
        return secondGoal.get(0).toString();
    }

    private String getStep(Asteroid a, Asteroid b) {
        Asteroid diff = new Asteroid(a.x - b.x,a.y - b.y);
        int ggt = ggT(diff.x,diff.y);
        return String.format("%d,%d", diff.x / ggt, diff.y /ggt);
    }

    private int ggT(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        if (a < b) {
            int t = a;
            a = b;
            b = t;
        }
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private void parse() {
        asteroids =new ArrayList<>();
        List<String> temp = Arrays.stream(getInput().split("\n")).collect(Collectors.toList());
        for(int j = 0; j < temp.size(); j++){
            String s = temp.get(j);
            for(int i = 0; i<s.length(); i++){
                if(s.charAt(i) == '#'){
                    asteroids.add(new Asteroid(i,j));
                }
            }
        }
    }
}
