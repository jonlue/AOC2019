package main.days;

import main.AOCRiddle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Day3 extends AOCRiddle {
    public Day3(String in, int part) {
        super(in, part);
        parse();
    }

    private List<Point> intersections;
    private Point[] wire1;
    private Point[] wire2;



    @Override
    protected String solve1() {
        return Integer.toString(getMinimum(getManhattanDistance()));
    }

    @Override
    protected String solve2() {
        return Integer.toString(getIntersectionFewestSteps());
    }

    private int[] getManhattanDistance() {
        int[] distances = new int[intersections.size()];
        for(int i = 0; i < intersections.size(); i++){
            Point p = intersections.get(i);
            distances[i] = Math.abs((int)p.getX()) + Math.abs((int)p.getY());
        }
        return distances;
    }

    private int getMinimum(int[] distances) {
        int min = Integer.MAX_VALUE;
        for (int distance : distances) {
            if (distance < min) {
                min = distance;
            }
        }
        return min;
    }

    private int getIntersectionFewestSteps(){
        int min = Integer.MAX_VALUE;
        for(Point p : intersections){
            int sum = getSteps(wire1,p) + getSteps(wire2,p);
            if(sum<min){
                min = sum;
            }
        }
        return min;
    }

    private int getSteps(Point[] wire, Point intersection) {
        int counter = 1;
        for(; counter< wire.length; counter++){
            if(wire[counter-1].equals(intersection)){
                break;
            }
        }
        return counter;
    }

    private void parse() {
        String[] wires = getInput().split("\n");
        wire1 = parseWire(wires[0].split(","));
        wire2 = parseWire(wires[1].split(","));
        getIntersections(wire1, wire2);
    }

    private Point[] parseWire(String[] wireText) {
        ArrayList<Point> wire = new ArrayList<>();
        Point last;
        for (String s : wireText) {
            if (wire.isEmpty()) {
                last = new Point(0, 0);
            } else {
                last = wire.get(wire.size() - 1);
            }
            int number = Integer.parseInt(s.substring(1));
            switch (s.charAt(0)) {
                case 'R':
                    for (int j = 1; j <= number; j++) {
                        wire.add(new Point((int)last.getX() + j, (int)last.getY()));
                    }
                    break;
                case 'U':
                    for (int j = 1; j <= number; j++) {
                        wire.add(new Point((int)last.getX(), (int)last.getY() + j));
                    }
                    break;
                case 'L':
                    for (int j = 1; j <= number; j++) {
                        wire.add(new Point((int)last.getX() - j, (int)last.getY()));
                    }
                    break;
                case 'D':
                    for (int j = 1; j <= number; j++) {
                        wire.add(new Point((int)last.getX(), (int)last.getY() - j));
                    }
                    break;
                default:
                    System.out.println(":(");
            }
        }

        Point[] wires =new Point[wire.size()];
        wires = wire.toArray(wires);
        return wires;
    }

    private void getIntersections(Point[] wire1, Point[] wire2) {
        intersections = new ArrayList<>();
        for (Point point1 : wire1) {
            for (Point point2 : wire2) {
                if (point1.equals(point2)) {
                    intersections.add(point1);
                }
            }
        }
    }

}
