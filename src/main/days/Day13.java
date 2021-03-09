package main.days;

import main.AOCRiddle;
import main.util.IntCodeComputer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 extends AOCRiddle {
    public Day13(String in, int part) {
        super(in, part);
        computer = new IntCodeComputer(getInput());
        game = new String[24][45];
        score = 0;
    }

    private static final int EMPTY = 0;
    private static final int WALL = 1;
    private static final int BLOCK = 2;
    private static final int PADDLE = 3;
    private static final int BALL = 4;

    private static final int LEFT = -1;
    private static final int NEUTRAL = 0;
    private static final int RIGHT = 1;

    private IntCodeComputer computer;
    private String[][] game;

    private long score;
    private Point paddle = new Point();
    private Point ball = new Point();
    private int directionBall;

    @Override
    protected String solve1() {
        long numBlocks = 0;

        do{
            long x = computer.run(true);
            long y =computer.run(true);
            long tile = computer.run(true);
            if(tile == BLOCK){
                numBlocks++;
            }
            insertTile(x,y,tile);
        }while(computer.getStatus() != 99);
        printGame();
        return String.valueOf(numBlocks);
    }

    @Override
    protected String solve2() {
        paddle = new Point();
        ball = new Point();
        directionBall = 1;
        score = 0;
        //computer.setAuto(false);
        computer.setRegister(0,2);
        do{
            long x = computer.run(true);
            long y =computer.run(true);
            long tile = computer.run(true);
            insertTile(x,y,tile);


            List<Long> in = new ArrayList<>();
            if(ball.x == paddle.x /*|| Math.abs(ball.x - paddle.x) == 1*/){
                in.add((long) directionBall);
            }else if(ball.x > paddle.x){
                if(directionBall == 1){
                    in.add(1L);
                }else{
                    in.add(0L);
                }
            }else{
                if(directionBall == -1){
                    in.add(-1L);
                }else{
                    in.add(0L);
                }
            }
            computer.setInput(in);

            printGame();
        }while(computer.getStatus() != 99);

        return String.valueOf(score);
    }


    private void insertTile(long x, long y, long tile) {
        if(x == -1){
            score = tile;
            return;
        }
        String symbol = "";
        switch ((int) tile){
            case EMPTY:
                symbol = " ";
                break;
            case WALL:
                symbol = "#";
                break;
            case BLOCK:
                symbol = "E";
                break;
            case PADDLE:
                paddle.x = (int)x;
                paddle.y = (int)y;
                symbol = "_";
                break;
            case BALL:
                if(ball.x < x){
                    directionBall = 1;
                }else{
                    directionBall = -1;
                }
                ball.x = (int)x;
                ball.y = (int)y;
                symbol = "o";
                break;
        }
        game[(int) y][(int) x] = symbol;
    }

    private void printGame() {
        StringBuilder stringBuilder = new StringBuilder();
        for(String[] row : game){
            for (String s : row){
                stringBuilder.append(s);
            }
            stringBuilder.append("\n");
        }
        stringBuilder.append("\t\t\t\t\t\t\t");
        stringBuilder.append("Score: ");
        stringBuilder.append(score);
        stringBuilder.append("\n");
        System.out.println(stringBuilder.toString());
    }
}
