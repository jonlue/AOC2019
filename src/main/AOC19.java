package main;

import main.day1.MassToFuel;
import main.day10.MonitoringStation;
import main.day11.SpacePolice;
import main.day12.NBodyProblem;
import main.day13.CarePackage;
import main.day16.FlawedFrequencyTransmission;
import main.day2.GravityAssist;
import main.day3.CrossedWires;
import main.day4.SecureContainer;
import main.day5.SunnyWithAChanceOfAsteroids;
import main.day6.UniversalOrbitMap;
import main.day7.AmplificationCircuit;
import main.day8.SpaceImageFormat;
import main.day9.SensorBoost;

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
        doExecrise(exercise,part,testing);
    }

    private static void usage(){
        System.out.println("usage: exercise part testing");
        System.out.println("usage:  [0-25]  [1,2]  [1,0]?");
        System.exit(-1);
    }

    private static void doExecrise(int exercise, int part,boolean test){
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
                riddle= new MassToFuel(in,part);
                break;
            case(2):
                riddle = new GravityAssist(in,part);
                break;
            case(3):
                riddle = new CrossedWires(in,part);
                break;
            case(4):
                riddle = new SecureContainer(in,part);
                break;
            case(5):
                riddle = new SunnyWithAChanceOfAsteroids(in,part);
                break;
            case(6):
                riddle = new UniversalOrbitMap(in,part);
                break;
            case(7):
                riddle = new AmplificationCircuit(in,part);
                break;
            case(8):
                riddle = new SpaceImageFormat(in,part);
                break;
            case(9):
                riddle = new SensorBoost(in,part);
                break;
            case(10):
                riddle = new MonitoringStation(in,part);
                break;
            case(11):
                riddle = new SpacePolice(in,part);
                break;
            case(12):
                riddle = new NBodyProblem(in,part);
                break;
            case(13):
                riddle = new CarePackage(in,part);
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
                riddle = new FlawedFrequencyTransmission(in,part);
                break;
                /*
            case(17):
                //riddle = new TooMuch(in,part);
                break;
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
