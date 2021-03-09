package main;

import main.days.Day1;
import main.days.Day10;
import main.days.Day11;
import main.days.Day12;
import main.days.Day13;
import main.days.Day16;
import main.days.Day2;
import main.days.Day3;
import main.days.Day4;
import main.days.Day5;
import main.days.Day6;
import main.days.Day7;
import main.days.Day8;
import main.days.Day9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class AOC19 {

    public static void main(String[] args) {
        int exercise = 0;
        int part = 0;
        boolean testing = false;
        if(args.length >3 || args.length<2) {
            usage();
        }else {
            try {
                exercise = Integer.parseInt(args[0]);
                part = Integer.parseInt(args[1]);
                if(args.length==3) {
                    testing = Boolean.parseBoolean(args[2]);
                }
                if(exercise>25 || exercise<1 || part < 1 || part > 2){
                    usage();
                }
            } catch (Exception e) {
                e.printStackTrace();
                usage();
            }
        }
        doExercise(exercise,part,testing);
    }

    private static void usage(){
        System.out.println("usage: exercise part testing");
        System.out.println("usage:  [0-25]  [1,2]  [1,0]?");
        System.exit(-1);
    }

    private static void doExercise(int exercise, int part, boolean test){
        String in = "";
        try {
            in = readFile((test ? "test" : "input" + exercise) + ".txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        AOCRiddle riddle = getRiddle(exercise,part,in);
        System.out.println(riddle.solve());


    }

    private static AOCRiddle getRiddle(int exercise, int part, String in) {
        AOCRiddle riddle = null;
        switch(exercise){
            case(1):
                riddle= new Day1(in,part);
                break;
            case(2):
                riddle = new Day2(in,part);
                break;
            case(3):
                riddle = new Day3(in,part);
                break;
            case(4):
                riddle = new Day4(in,part);
                break;
            case(5):
                riddle = new Day5(in,part);
                break;
            case(6):
                riddle = new Day6(in,part);
                break;
            case(7):
                riddle = new Day7(in,part);
                break;
            case(8):
                riddle = new Day8(in,part);
                break;
            case(9):
                riddle = new Day9(in,part);
                break;
            case(10):
                riddle = new Day10(in,part);
                break;
            case(11):
                riddle = new Day11(in,part);
                break;
            case(12):
                riddle = new Day12(in,part);
                break;
            case(13):
                riddle = new Day13(in,part);
                break;
                /*
            case(14):
                riddle = new OneTimePad(in,part);
                break;
            case(15):
                riddle = new TimingIsEverything(in,part);
                break;
                */
            case(16):
                riddle = new Day16(in,part);
                break;
            case(17):
                riddle = new Day17(in,part);
                break;
            /*
            case(18):
                riddle = new LikeARouge(in,part);
                break;
            case(19):
                riddle = new AnElephantNamedJoseph(in,part);
                break;
            case(20):
                riddle = new FirewallRules(in,part);
                break;
            case(21):
                riddle = new ScrambledLettersAndHash(in,part);
                break;
            case(22):
                riddle = new GridComputing(in,part);
                break;
            case(23):
                riddle = new SafeCracking(in,part);
                break;
            case(24):
                riddle = new AirDuctSpelunking(in,part);
                break;
            case(25):
                riddle = new ClockSignal(in,part);
                break;

                 */
            default:
        }
        return riddle;
    }

    private static String readFile(String fileName) throws IOException {
        File file = new File(
                Objects.requireNonNull(AOC19.class.getClassLoader().getResource(fileName)).getFile()
        );
        return Files.readString(file.toPath());
    }
}
