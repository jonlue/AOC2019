package main.days;

import main.AOCRiddle;
import main.util.IntCodeComputer;

import java.awt.*;
import java.util.Collections;
import java.util.HashMap;

public class Day11 extends AOCRiddle {
    public Day11(String in, int part) {
        super(in, part);
        computer = new IntCodeComputer(getInput());
    }

    private static final int SIZE = 100;

    private static HashMap<Point,Long> uniquePaintedTiles;

    private final IntCodeComputer computer;
    private String[][] registration;
    private Point position;

    private long unique;
    private int direction;

    @Override
    protected String solve1() {
        unique = 0;
        uniquePaintedTiles = new HashMap<>();
        return run(false);
    }

    @Override
    protected String solve2() {
        return run(true);
    }

    private String run(boolean part2){
        position = new Point(SIZE/2, SIZE/2);
        init(part2);
        if(part2){
            computer.addInput(1L);
        }else {
            computer.addInput(0L);
        }

        while(computer.getStatus() != 99) {

            long paint = computer.run(true);
            long rotate = computer.run(true);

            long tile = paintMoveScan(paint, rotate,part2);

            computer.setInput(Collections.singletonList(tile));
        }
        if(part2){
            return getRegistration();
        }else{
            return String.valueOf(unique);
        }
    }

    private long paintMoveScan(long paint,long rotate, boolean part2) {
        if(part2){
            registration[position.y][position.x] = paint == 1 ? "#" : " ";
        }else{
            if(!uniquePaintedTiles.containsKey(position)){
                ++unique;
            }
            uniquePaintedTiles.put(new Point(position),paint);
        }
        setDirection(rotate);
        switch (direction){
            case(0):
                position.translate(0,-1);
                break;
            case(1):
                position.translate(1,0);
                break;
            case(2):
                position.translate(0,+1);
                break;
            case(3):
                position.translate(-1,0);
                break;
        }

        if(part2) {
            return registration[position.y][position.x].equals("#") ? 1L : 0L;
        }else{
            return uniquePaintedTiles.getOrDefault(position,0L);
        }
    }

    private String getRegistration() {

        StringBuilder reg = new StringBuilder();

        int iFound = 0, jFound = 0;

        for(int i = 0; i<registration.length; ++i){
            for(int j = 0; j < registration[i].length;j++){
                if(registration[i][j].equals("#")){
                    iFound=i;
                    jFound=j;
                    break;
                }
            }
            if(iFound != 0){
                break;
            }
        }

        for(int i = iFound; i<iFound+6; ++i){
            for(int j = jFound; j < jFound+39; ++j){
                reg.append(registration[i][j]);
            }
            reg.append("\n");
        }
        return reg.toString();
    }

    private void init(boolean part2) {
        registration = new String[SIZE][SIZE];
        for(int i = 0; i< registration.length; ++i){
            for(int j = 0; j < registration[i].length; ++j){
                if(part2 && i == SIZE/2 && i ==j){
                    registration[i][j] = "#";
                }else{
                    registration[i][j] = " ";
                }
            }
        }
    }

    private void setDirection(long rotate) {
        if (rotate == 0) {
            switch (direction) {
                case (0):
                    direction = 3;
                    break;
                case (1):
                    direction = 0;
                    break;
                case (2):
                    direction = 1;
                    break;
                case (3):
                    direction = 2;
                    break;
            }
        } else {
            switch (direction) {
                case (0):
                    direction = 1;
                    break;
                case (1):
                    direction = 2;
                    break;
                case (2):
                    direction = 3;
                    break;
                case (3):
                    direction = 0;
                    break;
            }
        }
    }
}
